/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author AlgoritmicaAvanzada
 * @param <E>
 * @param <T>
 */
public class Graphe<E, T> {

    private HashMap<E, Node<T>> _nodes = new HashMap<>();
    private List<Connection> _connections = new LinkedList<>();
    private List<Connection> _MinRouteTree = new LinkedList<>();
    private List<Connection> _RoutePrim = new LinkedList<>();
    private List<Connection> _verifyConnections = new LinkedList<>();
    private List<Connection> _verifyConnectionsAux = new LinkedList<>();
    public int actual_max = 0;

    public Graphe() {
    }

    public void insertNode(E id, T content, int x, int y) {
        Node<T> node = new Node(content, id);
        if (_nodes.containsKey(id)) {
            System.out.println("Node already exits");
        } else {
            node.setX(x);
            node.setY(y);
            _nodes.put(id, node);
        }
    }

    public void insertNode(Node node) {
        if (_nodes.containsKey((E) node.getId())) {
            System.out.println("Node id: " + node.getId() + " already exits");
        } else {
            _nodes.put((E) node.getId(), node);
        }
    }

    public String insertConnection(int fromId, int toId, int weight) {
        if (fromId == toId) {
            System.out.println("Should be differents nodes");
            return "Should be differents nodes";
        }
        if (!this._nodes.containsKey(fromId) && !this._nodes.containsKey(toId)) {
            System.out.println("One of the entered nodes does not exist.");
            return "One of the entered nodes does not exist.";
        }

        Node<T> to = this._nodes.get(toId);
        Node<T> from = this._nodes.get(fromId);
        Connection connection = new Connection(weight, to, from);
        for (Connection actual : this._connections) {
            if ((actual.getStart().equals(to) && actual.getFinish().equals(from))
                    || (actual.getFinish().equals(to) && actual.getStart().equals(to))) {
                System.out.println("The connection already exists");
                return "The connection " + fromId + "-" + toId + " already exists.";
            }
        }
        insert_sortConnections(connection);
        return "ok";
    }

    public void insert_sortConnections(Connection connection) {
        if (connection.getWeight() > actual_max) {
            _connections.add(connection);
            actual_max = connection.getWeight();
        } else {
            List<Connection> auxList = new LinkedList<>();
            auxList.addAll(_connections);
            _connections.clear();
            int contador = 0;
            for (Connection item : auxList) {
                if (connection.getWeight() > item.getWeight()) {
                    _connections.add(item);
                } else {
                    _connections.add(connection);
                    break;
                }
                contador++;
            }
            _connections.addAll(auxList.subList(contador, auxList.size()));
            contador = 0;
        }

    }

    public void insertMinRouteTree() {
        for (Connection connection : _connections) {
            boolean flag1 = false;
            boolean flag2 = false;
            for (Connection MinRouteTree : _MinRouteTree) {
                if (connection.getStart().equals(MinRouteTree.getStart()) || connection.getStart().equals(MinRouteTree.getFinish())) {
                    flag1 = true;

                }
                if (connection.getFinish().equals(MinRouteTree.getStart()) || connection.getFinish().equals(MinRouteTree.getFinish())) {
                    flag2 = true;

                }
            }
            if (!(flag1 && flag2)) {
                _MinRouteTree.add(connection);
            }
        }

        _MinRouteTree.forEach((actual) -> {
            actual.setIsKruskal(true);
        });
    }

    public void insertPrim3(int nodo) {
        Node actualNodo = _nodes.get(nodo);
        actualNodo.setIsVisited(true);
        Connection newConnection = null;
        for (Connection connections : _connections) {
            if (!connections.isIsVisited()) {
                if (actualNodo.equals(connections.getStart()) && connections.getFinish().isIsVisited() == false
                        || actualNodo.equals(connections.getFinish()) && connections.getStart().isIsVisited() == false) {
                    newConnection = connections;
                    break;
                }
            }
        }
        if (newConnection == null) {
            for (Connection connections : _connections) {
                if (!connections.isIsVisited()) {
                    if (connections.getFinish().isIsVisited() == true && connections.getStart().isIsVisited() == false
                            || connections.getStart().isIsVisited() == true && connections.getFinish().isIsVisited() == false) {
                        if (connections.getFinish().isIsVisited() == false) {
                            actualNodo = connections.getFinish();
                        } else {
                            actualNodo = connections.getStart();
                        }
                        newConnection = connections;
                        break;
                    }
                }
            }
        }
        if (newConnection != null) {
            newConnection.setIsVisited(true);
            if (newConnection.getStart().equals(actualNodo) && newConnection.getFinish().isIsVisited() == false
                    || newConnection.getFinish().equals(actualNodo) && newConnection.getStart().isIsVisited() == false) {
                if (newConnection.getStart().equals(actualNodo)) {
                    insertPrim3((int) newConnection.getFinish().getId());
                } else {
                    insertPrim3((int) newConnection.getStart().getId());
                }
            } else {
                for (Connection connections : _connections) {
                    if (!connections.isIsVisited()) {
                        if (connections.getFinish().isIsVisited() == true && connections.getStart().isIsVisited() == false
                                || connections.getStart().isIsVisited() == true && connections.getFinish().isIsVisited() == false) {

                            newConnection = connections;
                            break;
                        }
                    }
                }
                if (newConnection != null) {
                    newConnection.setIsVisited(true);
                    if (newConnection.getStart().equals(actualNodo) && newConnection.getFinish().isIsVisited() == false
                            || newConnection.getFinish().equals(actualNodo) && newConnection.getStart().isIsVisited() == false) {
                        if (newConnection.getStart().equals(actualNodo)) {
                            insertPrim3((int) newConnection.getFinish().getId());
                        } else {
                            insertPrim3((int) newConnection.getStart().getId());
                        }
                    }
                }
            }
        }
    }

    public void resetVisited() {
        for (Connection _connection : _connections) {
            _connection.setIsVisited(false);
        }

        for (int i = 0; i < _nodes.size(); i++) {
            _nodes.get(i).setIsVisited(false);
        }
    }

    public void startPrim(int nodeId) {
        Node startNode = _nodes.get(nodeId);
        startNode.setIsVisited(true);
        Connection minConnection = getMinConnection(startNode);
        if (minConnection != null) {
            minConnection.setIsVisited(true);

            if (startNode.getId() == minConnection.getStart().getId() && minConnection.getFinish().isIsVisited() == false) {
                startPrim((int) minConnection.getFinish().getId());
            } else if (startNode.getId() == minConnection.getFinish().getId() && minConnection.getStart().isIsVisited() == false) {
                startPrim((int) minConnection.getStart().getId());
            }
        }
    }

    public Connection getMinConnection(Node node) {
        Connection minConnection = null;
        for (Connection connection : _connections) {
            if (!connection.isIsVisited()) {
                if (connection.getStart().equals(node) && connection.getFinish().isIsVisited() == false
                        || connection.getFinish().equals(node) && connection.getStart().isIsVisited() == false) {
                    if (minConnection == null || connection.getWeight() < minConnection.getWeight()) {
                        minConnection = connection;
                    }
                }
            }
        }
        if (minConnection == null) {
            minConnection = getMinConnection();
        }
        return minConnection;
    }

    public Connection getMinConnection() {
        Connection minConnection = null;
        for (Connection connection : _connections) {
            if ((connection.getStart().isIsVisited() == true && connection.getFinish().isIsVisited() == false)
                    || (connection.getStart().isIsVisited() == false && connection.getFinish().isIsVisited() == true)
                    && (minConnection == null || connection.getWeight() < minConnection.getWeight())) {
                minConnection = connection;
            }
        }
        return minConnection;
    }

//    private Connection verifyConnections(int start) {
//        Node<E> _Start = (Node<E>) _nodes.get(start);
//        int max_weight = 0;
//        _verifyConnections.clear();
//        for (Connection actualConnection : _connections) {
//            if (_Start.equals(actualConnection.getStart()) || _Start.equals(actualConnection.getFinish())) {
//                if (actualConnection.getWeight() > max_weight) {
//                    _verifyConnections.add(actualConnection);
//                    max_weight = actualConnection.getWeight();
//                } else {
//                    int position = 0;
//                    List<Connection> auxList = new LinkedList<>();
//                    auxList.addAll(_verifyConnections);
//                    _verifyConnections.clear();
//                    for (Connection item : auxList) {
//                        if (actualConnection.getWeight() > item.getWeight()) {
//                            _verifyConnections.add(item);
//                        } else {
//                            _verifyConnections.add(actualConnection);
//                            break;
//                        }
//                        position++;
//                    }
//                    _verifyConnections.addAll(auxList.subList(position, auxList.size()));
//                }
//            }
//        }
//        return _verifyConnections.get(0); //Retorna la conexion mas chica
//    }
    public List<Connection> getRoutePrim() {
        return _RoutePrim;
    }

    public void setRoutePrim(List<Connection> _RoutePrim) {
        this._RoutePrim = _RoutePrim;
    }

    public List<Connection> getConnections() {
        return _connections;
    }

    public void setConnections(List<Connection> conections) {
        this._connections = conections;
    }

    public HashMap<E, Node<T>> getNodes() {
        return _nodes;
    }

    public void setNodes(HashMap<E, Node<T>> nodes) {
        this._nodes = nodes;
    }

    @Override
    public String toString() {
        return _nodes.toString();
    }

}
