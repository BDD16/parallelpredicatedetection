package parallelalgorithms.group9.homework3.util;

import java.util.*;


/**
 * Homework3 Blake De Garza and Dustin Kattner
 *
 * Graph.java
 */
public class Graph implements com.parallelalgorithms.group9.homework3.util.GraphInterface {
    private int numNodes; // number of nodes in the graph
    public List<Integer>[] adjacencyList; //helper list

    private boolean[][] edges;

    public int getNumNodes(){
        return this.numNodes;
    }

    public void createAdjacencyList() {
        int V = this.numNodes;
        adjacencyList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    // Add an edge to the adjacency list
    public void addEdgeToAdjacency(int source, int destination) {
        adjacencyList[source].add(destination);
    }


    public void initAdjacencyMatrix(){
        adjacencyList = new ArrayList[this.numNodes];
        for (int i = 0; i < this.numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }
    public void addEdgesToAdjacencyFromGraph(){
        for(int i = 0; i < edges.length; i += 1){ //rows
            for( int j = 0; j < edges[0].length; j += 1){ //columns
                if(edges[i][j]){
                    // for readability’s sake
                    addEdgeToAdjacency(i, j);// adjacencyList[i].add(j);//keeps track of source to the neighbor
                }
            }
        }
    }
    public Graph(int size) throws IllegalArgumentException {

        if(size <= 0){
            //check for class argument is sane value
            throw new IllegalArgumentException();
        }

        this.numNodes = size;
        edges = new boolean[this.numNodes][this.numNodes];

    }

    @Override
    public String toString() {
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to) throws IllegalArgumentException {

        if(from < 0){
            // check for sane values
            throw new IllegalArgumentException();
        }
        if(to < 0 ){
            // check for sane values
            throw new IllegalArgumentException();
        }
        if(from >= numNodes){
            //ensure it's not an out-of-bounds read
            throw new IllegalArgumentException();
        }
        if (to >= numNodes) {
            //ensure it's not an out-of-bounds read
            throw new IllegalArgumentException();
        }
        if(edges == null){
            throw new IllegalArgumentException();
        }
        edges[from][to] = true;

    }

    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources == null || targets == null) throw new IllegalArgumentException();

        if (targets.size() > sources.size()){
            return false;
        }

        else if ( !allValidNodes(sources)){
            throw new IllegalArgumentException();
        }

        else if (! allValidNodes(targets) ){
            throw new IllegalArgumentException();
        }
        else{
            // find if it's actually reachable
            initAdjacencyMatrix();
            addEdgesToAdjacencyFromGraph();
            int foundList = 0;
            for(int source : sources){
                for(int target: targets){
                    List<Integer> path = shortestPath(source, target);
                    if(!path.isEmpty()){
                        System.out.println("found path from " + source + " to " + target + " : " + path.toString());
                        foundList += 1;
                        break;
                    }
                }
                if(foundList == targets.size()){
                    break;
                }
            }
            if( foundList == targets.size()){
                System.out.println("foundList is: " + foundList);
                return true;
            }
            else{
                return false;
            }
        }
    }

    //Done by Breadth First Search (BFS) in a Sequential fashion
    public List<Integer> shortestPath(int source, int target) {
        int V = this.numNodes;
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        //---ensure we update the adjacency list in case an edge or node was added ---
        initAdjacencyMatrix();
        addEdgesToAdjacencyFromGraph();
        System.out.println("Adjacency: " + Arrays.toString(adjacencyList));
        // base case of adjaency list has no edges
        if(adjacencyList[source].isEmpty()){
            return new ArrayList<>(); // No path found due to no edges from source therefore it cannot have a path to target
        }
        //--- done with adjacencyList init ---
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == target) {
                return constructPath(parent, source, target);
            }

            for (int neighbor : adjacencyList[current]) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

    // Construct the path from the parent array
    public List<Integer> constructPath(int[] parent, int source, int target) {
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        return path;
    }

    public List<Integer> setToArray(Set<Integer> set){
        List<Integer> setArray = set.stream().toList();
        return setArray;
    }

    public int maxValue(Set<Integer> set){
        List<Integer> findInHere = setToArray(set);

        int max = Integer.MIN_VALUE;
        int compareThis = 0;

        for(int i = 0; i < findInHere.size(); i +=1){
            compareThis = findInHere.get(i);
            if( compareThis > max) {
                max = compareThis;
            }

        }
        return max;
    }

    public boolean allValidNodes(Set<Integer> set){
        List<Integer> findInHere = setToArray(set);

        for(int i = 0; i < findInHere.size(); i += 1){
            if(findInHere.get(i) < 0 && !findInHere.isEmpty() || findInHere.get(i) >= this.numNodes){
                return false;
            }
        }
        return true;
    }
}