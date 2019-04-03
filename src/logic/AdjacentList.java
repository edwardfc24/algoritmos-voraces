/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Javier
 */
public class AdjacentList {
    public int weight;
    public Node ancester;
    public Node actualNode;
    public boolean isLowest;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getAncester() {
        return ancester;
    }

    public void setAncester(Node ancester) {
        this.ancester = ancester;
    }

    public Node getActualNode() {
        return actualNode;
    }

    public boolean isIsLowest() {
        return isLowest;
    }

    public void setIsLowest(boolean isLowest) {
        this.isLowest = isLowest;
    }

    public void setActualNode(Node actualNode) {
        this.actualNode = actualNode;
    }

    public AdjacentList(int weight, Node ancester, Node actualNode) {
        this.weight = weight;
        this.ancester = ancester;
        this.actualNode = actualNode;
    }
    
    
}
