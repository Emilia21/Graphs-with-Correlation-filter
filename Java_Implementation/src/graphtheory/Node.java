/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

/**
 *
 * @author epetkova2
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Node {

    static int counter = 0;

    @XmlID
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "label")
    private String label;

    public Node(String label) {
        this.setLabel(label);
        this.id = String.valueOf(counter);
        counter++;
    }

    public Node() {
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        Node c = (Node) o;
        return label.equals(c.label) == true;
    }

    @Override
    public String toString() {
        return "Node Label: " + this.getLabel();
    }

}
