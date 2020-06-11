# Graphs-with-Correlation-filter

# Abstract
The project is an implementation of a Java graph, followed by removing the weakly correlated edges and writing them to .xml files.
The input data is in the form of two matrices: a matrix of correlations of the logarithmic return and a matrix of correlations of volatility, which are in .csv format with a separator ",".
# Implementation
The implemented classes are:
## 1. Node.java
The class represents the Vertex object with variables id and name, as id is influenced by the variable counter, which is static, i.e the id is formed automatically. The variable name is the name of the vertex and is used in the constructor. The class consists of setters and getters for the variables and two overriden methods: equals () - to be able to compare vertices, toString () - to print vertices in user-friendly format
## 2. Edge.java
The class represents the object Edge with variables 
target: start vertex of the edge
source: finalvertex of the edge
weight: edge weight. 
There are also the variables id, which is on the same principle as at the vertex, and target_label and source_label which are just auxiliary for creating .xml files. Again we have the same methods except for one new one: 
compareTo () used when sorting the edges as returning:
- -1: if the second object is larger than the first
- 0: if the two objects are equal
- 1: if the first object is larger than the second
## 3. Graph
Represents the implementation of a graph with variable nodes and edges, which are respectively a list containing all vertices in the graph and a list containing all edges. It contains methods:
- Getters for variables
- add_edge (): the method accepts three string variables: name of the first vertex, name of the second vertex and weight. Adds the new edge to the edges list.
- add_node (): the method takes a vertex name in the form of a String and adds the new edge to the nodes list.
- delete_node (): the method takes a vertex name in the form of a String and removes it from both edges and nodes.
- fill_graph (): the method implements reading a csv file line by line and using the add_edge () and add_node () methods fills the graph.
- correlation_filter (): the method sorts the edges for each vertex in descending order by weight and leaves only the first three(those with the strongest correlation)
- create_xml (): when a file name is specified, the method creates an .xml file of an object of type Graph and saves it in the project directory.
