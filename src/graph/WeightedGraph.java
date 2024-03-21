package graph;

import java.util.*;

public class WeightedGraph<T> {


    private class Edge {
        Node from;
        Node to;
        int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private class Node {
        T value;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Objects.toString(value);
        }
    }

    private final Map<T, Node> nodes;
    private final Map<Node, List<Edge>> adjacencyList;

    public WeightedGraph() {
        this.nodes = new HashMap<>();
        this.adjacencyList = new HashMap<>();
    }

    public void addNode(T item) {
        var node = new Node(item);
        nodes.put(item, node);

        adjacencyList.putIfAbsent(node, new LinkedList<>());
    }

    public void addEdge(T from, T to, int weight) {
        Node fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalArgumentException();

        Node toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(new Edge(fromNode, toNode, weight));
        adjacencyList.get(toNode).add(new Edge(toNode, fromNode, weight));
    }
}
