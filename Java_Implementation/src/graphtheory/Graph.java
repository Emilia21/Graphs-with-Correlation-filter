/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author epetkova2
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Graph {

    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    private List<Node> nodes;
    @XmlElementWrapper(name = "edges")
    @XmlElement(name = "edge")
    private List<Edge> edges;

    public Graph(String csvFile) {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        this.fill_graph(csvFile);
    }

    public Graph() {
    }

    private void add_edge(String source, String target, String weight) {
        Node sourse_node = nodes.stream()
                .filter(node -> source.equals(node.getLabel()))
                .findAny()
                .orElse(null);
        Node target_node = nodes.stream()
                .filter(node -> target.equals(node.getLabel()))
                .findAny()
                .orElse(null);
        if (!source.equals(target)) {
            Edge e = new Edge(sourse_node, target_node, weight);
            if (!edges.contains(e)) {
                edges.add(e);
            }
        }

    }

    private void add_node(String label) {
        Node n = new Node(label);
        if (!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public void delete_node(String label) {
        nodes.removeIf(node -> node.getLabel().equals(label));
        edges.removeIf(edge -> edge.getTarget().getLabel().equals(label)
                || edge.getSource().getLabel().equals(label));
    }

    //read the csv file; get and fill all vertices; get and fill the edges
    private void fill_graph(String csvFile) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            String[] nodes = line.split(csvSplitBy);
            nodes = Arrays.copyOfRange(nodes, 1, nodes.length);
            for (String node : nodes) {
                if (node.length() > 0) {
                    this.add_node(node.trim());
                }
            }

            while ((line = br.readLine()) != null) {
                String[] weights = line.split(csvSplitBy);
                String source = weights[0];
                for (int i = 1; i < weights.length; i++) {
                    String target = nodes[i - 1];
                    String weight = weights[i];
                    this.add_edge(source, target, weight);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void print_nodes() {
        nodes.forEach(node -> System.out.println(node));
    }

    public void print_edges() {
        edges.forEach(edge -> System.out.println(edge));
    }

    public void correlation_filter() {
        List<Edge> filtered = new ArrayList<>();
        nodes.forEach(node -> {
            Stream<Edge> currStream = edges.stream().filter(e
                    -> e.getSource().equals(node)
            ).sorted().limit(3);
            currStream.forEach(x -> filtered.add(x));
        });

        edges = edges.stream()
                .filter(edge -> filtered.contains(edge)).collect(Collectors.toList());
    }
    
    public void create_xml(String file_name) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Graph.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(this, new FileOutputStream("./" + file_name));
            System.out.println("XML created successfully");
        } catch (Exception ex) {
            System.out.println("Error. XML hadn't been created.");
            ex.printStackTrace();
        }
    }
}
