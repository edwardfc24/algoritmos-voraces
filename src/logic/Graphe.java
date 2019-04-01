/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

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
    private List<Connection> _Dijkstra = new LinkedList<>();
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

        for (Connection actual : _MinRouteTree) {
            actual.setIsKruskal(true);
        }
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

    public void DijkstraVerification(int start, int finish) {

        if (!this._nodes.containsKey(start) && !this._nodes.containsKey(finish)) {
            JOptionPane.showMessageDialog(null, "The node doesn't exist");
        } else {
            verifyConnections(start, finish);
        }
    }

    private void verifyConnections(int start, int finish) {
        Node<E> _Start = (Node<E>) _nodes.get(start);
        int max_weight = 0;
        _Dijkstra.clear();
        for (Connection actualConnection : _connections) {
            if (_Start.equals(actualConnection.getStart()) || _Start.equals(actualConnection.getFinish())) {
                if (actualConnection.getWeight() > max_weight) {
                    _Dijkstra.add(actualConnection);
                    max_weight = actualConnection.getWeight();
                } else {
                    int position = 0;
                    List<Connection> auxList = new LinkedList<>();
                    auxList.addAll(_Dijkstra);
                    _Dijkstra.clear();
                    for (Connection item : auxList) {
                        if (actualConnection.getWeight() > item.getWeight()) {
                            _Dijkstra.add(item);
                        } else {
                            _Dijkstra.add(actualConnection);
                            break;
                        }
                        position++;
                    }
                    _Dijkstra.addAll(auxList.subList(position, auxList.size()));
                }
            }
        }
    }

    public List<Connection> getDijkstra() {
        return _Dijkstra;
    }

    public void setDijkstra(List<Connection> _Dijkstra) {
        this._Dijkstra = _Dijkstra;
    }

    private Node nextNode(Connection connection) {
        return connection.getFinish();
    }

}
