/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Graphics;

/**
 *
 * @author Orlando
 */
public class Node<E> {

    private E _id;
    private E _content;
    public static final int d = 60; //circulo de 60 pts

    private int x, y;

    public Node() {
    }

    public Node(E id, E content) {
        this._id = id;
        this._content = content;
    }

    public Node(E _id, E _content, int x, int y) {
        this._id = _id;
        this._content = _content;
        this.x = x;
        this.y = y;
    }
    

    public void Paint(Graphics g) {
        g.drawOval(this.x - d / 2, this.y - d / 2, d, d);
        String sentence = _id.toString() + ", " + _content.toString();
        g.drawString(sentence, x - 30, y);
    }

    public E getContent() {
        return _content;
    }

    public void setContent(E content) {
        this._content = content;
    }

    public E getId() {
        return _id;
    }

    public void setId(E id) {
        this._id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Id: " + _id + "\nContent: " + _content;
    }

}
