/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Javier
 */
public class Dijkstra<E, T> {

    private Node<T> _Start, _Finish;
    private List<Connection> _StartConnection = new LinkedList<>();
    private List<Connection> _connections = new LinkedList<>();
    private HashMap<E, Node<T>> _nodes = new HashMap<>();
    public PriorityQueue<AdjacentList> forVisit;

    public Dijkstra() {
    }

    public Dijkstra(HashMap<E, Node<T>> nodes, List<Connection> connections) {
        this._nodes = nodes;
        this._connections = connections;
        this.forVisit = new PriorityQueue<AdjacentList>();
    }

    public String insertStartAndFinishNode(int toId, int fromId) {

        if (!this._nodes.containsKey(fromId) || !this._nodes.containsKey(toId)) {
            System.out.println("One of the entered nodes does not exist.");
            return "One of the entered nodes does not exist.";
        }

        Node<T> nodeStart = _nodes.get(toId);
        Node<T> nodeFinish = _nodes.get(fromId);

        _Start = nodeStart;
        _Finish = nodeFinish;
        System.out.println("Node start " + _Start.getId() + " Node Finish " + _Finish.getId());
        return "ok";
    }

    public List<Connection> verifyConnections(Node index) {
        if (index == null) {
            return null;
        }
        int max_weight = 0;
        _StartConnection.clear();
        for (Connection actualConnection : _connections) {
            if (index.equals(actualConnection.getStart())) {
                if (actualConnection.getWeight() > max_weight) {
                    _StartConnection.add(actualConnection);
                    max_weight = actualConnection.getWeight();
                } else {
                    List<Connection> auxList = new LinkedList<>();
                    auxList.addAll(_StartConnection);
                    _StartConnection.clear();
                    int contador = 0;
                    for (Connection item : auxList) {
                        if (actualConnection.getWeight() > item.getWeight()) {
                            _StartConnection.add(item);
                        } else {
                            _StartConnection.add(actualConnection);
                            break;
                        }
                        contador++;
                    }
                    _StartConnection.addAll(auxList.subList(contador, auxList.size()));
                }

            }
        }

        return _StartConnection;
    }

    public Node<T> getStart() {
        return _Start;
    }

    public void setStart(Node<T> _Start) {
        this._Start = _Start;
    }

    public Node<T> getFinish() {
        return _Finish;
    }

    public void setFinish(Node<T> _Finish) {
        this._Finish = _Finish;
    }

    public List<Connection> getStartConnection() {
        return _StartConnection;
    }

    public void setStartConnection(List<Connection> _StartConnection) {
        this._StartConnection = _StartConnection;
    }

    public List<Connection> getConnections() {
        return _connections;
    }

    public void setConnections(List<Connection> _connections) {
        this._connections = _connections;
    }

    public HashMap<E, Node<T>> getNodes() {
        return _nodes;
    }

    public void setNodes(HashMap<E, Node<T>> _nodes) {
        this._nodes = _nodes;
    }

    public String getDijsktra() {
        AdjacentList first = new AdjacentList(0, null, _Start);
        forVisit.add(first);
        first.actualNode.isVisited = true;
        while (!forVisit.isEmpty()) {
            // Obtengo el adyacente actual
            AdjacentList previous_adjacent = forVisit.poll();
            // Obtengo el nodo predecesor
            Node previous = previous_adjacent.getActualNode();
            // Obtengo las conexiones previas y escojo la mas cercana
            List<Connection> connections = verifyConnections(previous);
            ///**** Debe repetirse
            Connection conn = connections.get(0); // getAdjacentes->PriorityQueue
            Node actual = _nodes.get(conn.getFinish().getId());
            // Almacenar la relacion con el predecesor
            AdjacentList adjacent = new AdjacentList(previous_adjacent.getWeight() + conn.getWeight(), previous, actual);
            forVisit.add(adjacent);
            ////*****
            actual.isVisited = true;
        }
        return "";
    }

}
