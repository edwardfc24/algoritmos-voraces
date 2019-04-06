/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class Prim<E, T> {

    private List<Connection> _connections = new LinkedList<>();
    private HashMap<E, Node<T>> _nodes = new HashMap<>();
    private List<Connection> _listPrim = new LinkedList<>();
    private List<Connection> _Pendingconnections = new LinkedList<>();

    public Prim(List<Connection> connections, HashMap<E, Node<T>> nodes) {
        this._connections = connections;
        this._nodes = nodes;
    }

    public List<Connection> getPrim(int idStartNode) {

        Node<T> startNode = _nodes.get(idStartNode);
        if (startNode == null) {
            System.out.println("The node with id " + idStartNode + " doesn't exist.");
            return null;
        }
        if (allVisited()) {
            return _listPrim;
        }
        Connection min = minConnection(startNode, _Pendingconnections);

        boolean isValid = verifyLoop(min);
        if (!isValid) {
            _listPrim.add(min);
            _nodes.get(min.getStart().getId()).setIsVisited(true);
            _nodes.get((int) min.getFinish().getId()).setIsVisited(true);

        }

        if (startNode.equals(min.getStart())) {
            _Pendingconnections.addAll(verifyConnections(min.getFinish()));
            getPrim((int) min.getFinish().getId());
        } else {
            _Pendingconnections.addAll(verifyConnections(min.getStart()));
            getPrim((int) min.getStart().getId());
        }

        return _listPrim;

    }
    
    public void showInConsole() {
        for (Connection primActual : _listPrim) {
            System.out.println("Peso " + primActual.getWeight() + " Inicio " + primActual.getStart().getContent() + " Fin " + primActual.getFinish().getContent());
        }

    }

    public boolean allVisited() {
        int contador = 0;
        for (Node<T> actual : _nodes.values()) {
            if (actual.isVisited) {
                contador++;
            }
        }
        if (contador == _nodes.size()) {
            return true;
        }
        return false;
    }

    public boolean verifyLoop(Connection min) {
        //Devuelve true si existe ciclo
        boolean flag1 = false;
        boolean flag2 = false;
        for (Connection actual : _listPrim) {
            if (actual.getStart().equals(min.getStart()) || actual.getStart().equals(min.getFinish())) {
                flag1 = true;
            }
            if (actual.getFinish().equals(min.getStart()) || actual.getFinish().equals(min.getFinish())) {
                flag2 = true;
            }
        }
        if (flag1 && flag2) {
            return true;
        } else {
            return false;
        }
    }

    public List<Connection> verifyConnections(Node<T> node) {
        List<Connection> auxList = new LinkedList<>();
        for (Connection actual : _connections) {

            if (actual.getStart().equals(node) && !actual.getFinish().isVisited
                    || actual.getFinish().equals(node) && !actual.getStart().isVisited) {
                auxList.add(actual);
            }
        }
        return auxList;
    }

    public Connection minConnection(Node starNode, List<Connection> list) {

        List<Connection> aux = verifyConnections(starNode);
        int min = Integer.MAX_VALUE;
        Connection minConnection = new Connection();
        if (list.size() > 0) {
            for (Connection actual : list) {
                boolean flag = false;
                for (Connection prim : _listPrim) {
                    if (prim.equals(actual)) {
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }
                if (actual.getWeight() < min) {
                    min = actual.getWeight();
                    minConnection = actual;
                }
            }
        } else {
            for (Connection actual : aux) {
                if (actual.getWeight() < min) {
                    min = actual.getWeight();
                    minConnection = actual;
                }
            }
            for (Connection actual : aux) {
                if (!actual.equals(minConnection)) {
                    _Pendingconnections.add(actual);
                }

            }
        }

        return minConnection;
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

}
