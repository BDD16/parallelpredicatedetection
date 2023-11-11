package parallelalgorithms.group9.homework3.llp.toposort;

import parallelalgorithms.group9.homework3.util.Graph;
import com.parallelalgorithms.group9.homework3.util.GraphInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Homework3 Blake De Garza and Dustin Kattner
 *
 * topologicalsort.java
 */
public class TopoSortGraph implements GraphInterface {
    private int V; // Number of vertices
    private List<Integer>[] adjacencyList;

    public TopoSortGraph(int V) {
        this.V = V;
        adjacencyList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }

    /*
     * Topological sort as implemented as a cascade parallel algorithm
     * Cascade technique outlines that there is a sequential algorithm
     * then you split up the problem and divide and conquer in a parallel
     * fashion with a shared RAM object (results).
     *
     */

    public ArrayList<List<Integer>> topologicalDFSSort(Graph g, Integer V){
        List<Integer> result = new ArrayList<Integer>();
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
        result.add(V);
        result.stream().parallel().forEach(number -> results.add(this.topologicalSort()));
        return results;
    }

    public List<Integer> hashmapToList(HashMap<Integer, Boolean> hashStruct){
        List<Integer> result = new ArrayList<>();
        for(Integer key: hashStruct.keySet()){
            if(hashStruct.get(key)){
                result.add(key);
            }
        }
        return result;
    }

    HashMap<Integer, Boolean> depthFirstSearchIterative(Graph g, Integer v){
        Stack<Integer> S = new Stack<Integer>();
        HashMap<Integer, Boolean> discovery = new HashMap<Integer, Boolean>(); // True if discovered, false if not

        S.push(v);
        while(S.isEmpty()){
            v = S.pop();
            if( !discovery.get(v)){
                discovery.put(v, true);
                for( Integer edges: g.adjacencyList[v]){
                    S.push(edges);
                }
            }
        }

        return discovery;
    }


    public List<Integer> topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (int neighbor : adjacencyList[v]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        stack.push(v);
    }


}

