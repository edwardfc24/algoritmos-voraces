/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AlgoritmicaAvanzada
 */
public class Connection {

    private Node _start, _finish;
    private int _weight;
    private boolean _isVisited = false;

    public Connection() {
    }

    public Connection(int weight, Node start, Node finish) {

        this._weight = weight;
        this._start = start;
        this._finish = finish;
    }

    public void Paint(Graphics g) {
        if (_isVisited) {
            g.setColor(Color.RED);
            g.drawLine(_start.getX(), _start.getY(), _finish.getX(), _finish.getY());
            g.setColor(Color.BLACK);

        } else {
            g.drawLine(_start.getX(), _start.getY(), _finish.getX(), _finish.getY());
        }
        if (_start.getX() > _finish.getX() && _start.getY() > _finish.getY())// esto quiere decir que x1 esta mas a la derecha que _finish.getX() y y esta mas abajo que _finish.getY() 
        {
            g.drawString(_weight + "", _start.getX() - Math.abs((_start.getX() - _finish.getX()) / 2), _start.getY() - Math.abs((_start.getY() - _finish.getY()) / 2));
        }
        if (_start.getX() < _finish.getX() && _start.getY() < _finish.getY()) {
            g.drawString(_weight + "", _finish.getX() - Math.abs((_start.getX() - _finish.getX()) / 2), _finish.getY() - Math.abs((_start.getY() - _finish.getY()) / 2));

        }
        if (_start.getX() > _finish.getX() && _start.getY() < _finish.getY()) {
            g.drawString(_weight + "", _start.getX() - Math.abs((_start.getX() - _finish.getX()) / 2), _finish.getY() - Math.abs((_start.getY() - _finish.getY()) / 2));
        }
        if (_start.getX() < _finish.getX() && _start.getY() > _finish.getY()) {
            g.drawString(_weight + "", _finish.getX() - Math.abs((_start.getX() - _finish.getX()) / 2), _start.getY() - Math.abs((_start.getY() - _finish.getY()) / 2));
        }

    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        this._weight = weight;
    }

    public Node getStart() {
        return _start;
    }

    public void setStart(Node _start) {
        this._start = _start;
    }

    public Node getFinish() {
        return _finish;
    }

    public void setFinish(Node _finish) {
        this._finish = _finish;
    }

    public boolean isIsVisited() {
        return _isVisited;
    }

    public void setIsVisited(boolean _isVisited) {
        this._isVisited = _isVisited;
    }

}
