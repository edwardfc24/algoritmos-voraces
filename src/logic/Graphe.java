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
    public int actual_max = 0;
    private Dijkstra dijkstra = new Dijkstra(_nodes, _connections);
    private Prim prim = new Prim(_connections, _nodes);

    public Graphe() {
    }

    public String insertNode(E id, T content, int x, int y) {
        Node<T> node = new Node(id, content);
        if (_nodes.containsKey(id)) {
            System.out.println("Node already exits");
            return "The node with id: con el identificador " + id + " already exist.";
        }
        node.setX(x);
        node.setY(y);
        _nodes.put(id, node);
        return "ok";
    }

    public String insertNode(Node node) {
        if (_nodes.containsKey((E) node.getId())) {
            System.out.println("Node id: " + node.getId() + " already exits");
            return "The node with id: con el identificador " + node.getId() + " already exist.";
        }
        _nodes.put((E) node.getId(), node);
        return "ok";
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
                connection.setIsKruskal(true);
            }
        }

    }

    public void runPrim(int starNode) {
    
        List<Connection> primList = prim.getPrim(starNode);
        for (int i = 0; i < _connections.size(); i++) {
            Connection actual = _connections.get(i);
            for (Connection primActual : primList) {
                if (actual.equals(primActual)) {
                    _connections.get(i).setIsKruskal(true);
                }
            }
        }
        
        prim.showInConsole();
    }

    public String runDijkstra(int toId, int fromId) {

        String insertResult = dijkstra.insertStartAndFinishNode(toId, fromId);
        if (!insertResult.equals("ok")) {
            return insertResult;
        }

        List<Connection> listAux = dijkstra.verifyConnections(dijkstra.getStart());
        if (listAux == null) {
            return "Start and end nodes are not defined";
        }
        for (Connection actual : listAux) {
            System.out.println(actual.getStart().getId() + " " + actual.getFinish().getId() + " " + actual.getWeight());
        }
        return "ok";
    }

    public void insertNodesList(List<Node<T>> list) {
        for (Node node : list) {
            insertNode((E) node.getId(), (T) node.getContent(), node.getX(), node.getY());
        }
        /*
        insertNodes.add(new Node(0, "nodeA", 100, 150));
        insertNodes.add(new Node(1, "nodeB", 250, 150));
        insertNodes.add(new Node(2, "nodeF", 350, 150));
        insertNodes.add(new Node(3, "nodeC", 100, 300));
        insertNodes.add(new Node(4, "nodeD", 250, 300));
        insertNodes.add(new Node(5, "nodeE", 350, 300));
        insertNodes.add(new Node(6, "nodeF", 250, 450));
         */
        _connections.add(new Connection(5, _nodes.get(0), _nodes.get(1)));
        _connections.add(new Connection(8, _nodes.get(0), _nodes.get(3)));
        _connections.add(new Connection(7, _nodes.get(0), _nodes.get(4)));
        _connections.add(new Connection(3, _nodes.get(1), _nodes.get(2)));
        _connections.add(new Connection(9, _nodes.get(1), _nodes.get(4)));
        _connections.add(new Connection(6, _nodes.get(2), _nodes.get(4)));
        _connections.add(new Connection(5, _nodes.get(2), _nodes.get(5)));
        _connections.add(new Connection(6, _nodes.get(3), _nodes.get(4)));
        _connections.add(new Connection(3, _nodes.get(4), _nodes.get(5)));
        _connections.add(new Connection(4, _nodes.get(3), _nodes.get(6)));
        _connections.add(new Connection(5, _nodes.get(4), _nodes.get(6)));
        _connections.add(new Connection(9, _nodes.get(5), _nodes.get(6)));
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

    public Dijkstra getDijkstra() {
        return dijkstra;
    }

    public void setDijkstra(Dijkstra dijkstra) {
        this.dijkstra = dijkstra;
    }

    public Prim getPrim() {
        return prim;
    }

    public void setPrim(Prim prim) {
        this.prim = prim;
    }

    @Override
    public String toString() {
        return _nodes.toString();
    }

}
