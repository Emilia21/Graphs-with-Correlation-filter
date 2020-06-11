/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

/**
 *
 * @author epetkova2
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Edge implements Comparable<Edge> {

    private static int counter = 0;

    @XmlAttribute
    private int id;

    @XmlIDREF
    @XmlAttribute
    private Node source;
    @XmlIDREF
    @XmlAttribute
    private Node target;

    @XmlAttribute
    private Double weight;


    public Edge(Node source, Node target, String weight) {
        this.setSource(source);
        this.setTarget(target);
        this.setWeight(weight);
        this.id = counter;
        counter++;
    }

    public Edge() {
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        try {
            this.weight = Double.parseDouble(weight);
        } catch (Exception e) {
            System.out.println("Data error.");
            System.exit(0);
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        Edge c = (Edge) o;
        return (source.equals(c.source) == true
                && target.equals(c.target) == true)
                || (source.equals(c.target) == true
                && target.equals(c.source) == true);
    }

    @Override
    public String toString() {
        return String.format("Edge betwen node %s and node %s with weight %f",
                this.getSource().getLabel(), this.getTarget().getLabel(), this.getWeight());
    }

    @Override
    public int compareTo(Edge e) {
        return Math.abs(weight) < Math.abs(e.weight) ? 1
                : Math.abs(weight) > Math.abs(e.weight) ? -1
                : 0;
    }

}
