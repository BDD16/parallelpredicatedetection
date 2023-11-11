package com.parallelalgorithms.group9.homework3.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public interface GraphInterface{
    static int numNodes = 0; // number of nodes in the graph@

    boolean[][] edges = null;

    public default int getNumNodes(){
        return this.numNodes;
    }


    public default void createAdjacencyList() {
    }

    // Add an edge to the adjacency list

    public default void addEdgeToAdjacency(int source, int destination) {
    }

    public default void initAdjacencyMatrix(){
    }


    public default void addEdgesToAdjacencyFromGraph(){
    }

    @Override
    public String toString();

    @Override
    public boolean equals(Object o);

    public default void addEdge(int from, int to) throws IllegalArgumentException {

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

    public default boolean reachable(Set<Integer> sources, Set<Integer> targets) {
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
    public default List<Integer> shortestPath(int source, int target) {
        return null;
    }

    // Construct the path from the parent array
    private List<Integer> constructPath(int[] parent, int source, int target) {
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        return path;
    }

    public default List<Integer> setToArray(Set<Integer> set){
        List<Integer> setArray = set.stream().toList();
        return setArray;
    }

    public default int maxValue(Set<Integer> set){
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

    public default boolean allValidNodes(Set<Integer> set){
        List<Integer> findInHere = setToArray(set);

        for(int i = 0; i < findInHere.size(); i += 1){
            if(findInHere.get(i) < 0 && !findInHere.isEmpty() || findInHere.get(i) >= this.numNodes){
                return false;
            }
        }
        return true;
    }
}