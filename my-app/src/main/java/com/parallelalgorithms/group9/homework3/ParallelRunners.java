package parallelalgorithms.group9.homework3;

import parallelalgorithms.group9.homework3.api.groupNine;
import parallelalgorithms.group9.homework3.llp.boruvka.Edge;
import parallelalgorithms.group9.homework3.llp.toposort.TopoSortGraph;

import java.util.List;

/**
 * Homework3 Blake De Garza and Dustin Kattner
 * Main entry point of package
 * ParallelRunners.java
 */
public class ParallelRunners
{

    public static List<Edge> result;

    public static <GraphInterface> GraphInterface graphSetup(){
        /*
         *
         * 0
            1:0
            2:0
            3:1,2
            4:3
            5:3
            9:8
            10:9
            6:4,5
            7:6
            8:7

         */

        TopoSortGraph g = new TopoSortGraph(11);
        g.addEdge(1, 0);
        g.addEdge(2, 0);
        g.addEdge(3, 1);
        g.addEdge(3, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 3);
        g.addEdge(9, 8);
        g.addEdge(10, 9);
        g.addEdge(6, 4);
        g.addEdge(6, 5);
        g.addEdge(7,6);
        g.addEdge(8, 7);

        g.initAdjacencyMatrix();
        g.addEdgesToAdjacencyFromGraph();

        return (GraphInterface) g;
    }

    public static void run(String[] args){
        main(args);
}
    public static void main( String[] args )
    {
        groupNine OG9 = new groupNine();
        OG9.api_obj.numberOfVertices(6);
        OG9.api_obj.setupEdges();
        result = OG9.api_obj.runBoruvkaMST(); // I need to fix this, but it's mostly there.
        for (Edge edge : result) {
            System.out.println("Edge: " + edge.source + " - " + edge.destination + ", Weight: " + edge.weight);
        }

        OG9.api_obj.numberOfVertices(11);
        List<Integer> topoResult = OG9.api_obj.runTopologicalSort();
        System.out.println(topoResult);


    }
}
