package graph;

import java.util.*;

public class Graph<T> {

    private final Map<Node, List<Node>> adjacencyList;
    private final Map<T, Node> nodes;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.nodes = new HashMap<>();
    }

    public void addNode(T label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new LinkedList<>());
    }

    public void addEdge(T from, T to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();


        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(T label) {
        Node node = nodes.remove(label);
        if (node == null) return;

        adjacencyList.remove(node);
        for (var n : adjacencyList.values())
            n.remove(node);
    }

    public void removeEdge(T from, T to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public List<T> depthFirstTraversal(T root) {
        Node node = nodes.get(root);
        if (node == null)
            throw new IllegalArgumentException();

        List<T> list = new LinkedList<>();
        depthFirstTraversal(node, list, new HashSet<>());

        return list;
    }

    private void depthFirstTraversal(Node node, List<T> list, Set<Node> visited) {
        if (visited.contains(node))
            return;

        list.add(node.label);
        visited.add(node);

        for (Node neighbour : adjacencyList.get(node))
            depthFirstTraversal(neighbour, list, visited);
    }

    public List<T> traverseDepthFirstIteratively(T root) {
        var node = nodes.get(root);
        if (node == null)
            throw new IllegalArgumentException();

        List<T> list = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            var current = stack.pop();

            if (visited.contains(current))
                continue;

            list.add(current.label);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    stack.push(neighbour);
        }

        return list;
    }

    public List<T> traverseBreadthFirst(T root) {
        var node = nodes.get(root);
        if (node == null)
            throw new IllegalArgumentException();

        List<T> list = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited.contains(current))
                continue;

            list.add(current.label);
            visited.add(current);

            queue.addAll(adjacencyList.get(current));
        }

        return list;
    }

    public List<T> topologicalSort() {
       Stack<T> stack = new Stack<>();
       Set<Node> visited = new HashSet<>();
       for (Node node : nodes.values()) {
           topologicalSort(node, stack, visited);
       }

       var list = new LinkedList<T>();
       while (!stack.isEmpty())
           list.add(stack.pop());
       return list;
    }

    private void topologicalSort(Node root, Stack<T> stack, Set<Node> visited) {
        if (visited.contains(root))
            return;

        visited.add(root);

        for (var neighbour : adjacencyList.get(root))
            topologicalSort(neighbour, stack, visited);

        stack.push(root.label);
    }

    public boolean hasCycle() {
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        for (Node node : nodes.values()) {
            if (hasCycle(node, visiting, visited))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> visiting, Set<Node> visited) {
        if (visited.contains(node))
            return false;

        visiting.add(node);
        for (Node neighbour : adjacencyList.get(node)) {
            if (visiting.contains(neighbour))
                return true;
            if (hasCycle(neighbour, visiting, visited))
                return true;
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : this.nodes.values()) {
            List<Node> neighbours = this.adjacencyList.get(node);
            if (neighbours == null) continue;
            sb.append(node.label + "'s neighbours: " + Arrays.toString(neighbours.toArray()))
                    .append('\n');
        }
        return sb.toString();
    }

    private class Node {
        T label;

        Node(T label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(label, node.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }
}
