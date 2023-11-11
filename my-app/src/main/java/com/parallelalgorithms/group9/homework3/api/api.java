package parallelalgorithms.group9.homework3.api;

import parallelalgorithms.group9.homework3.llp.boruvka.Boruvka;
import parallelalgorithms.group9.homework3.llp.boruvka.Edge;
import parallelalgorithms.group9.homework3.llp.toposort.TopoSortGraph;
import com.parallelalgorithms.group9.homework3.util.GraphInterface;

import java.util.ArrayList;
import java.util.List;

public class api {
    private TopoSortGraph topologicalProblem;
    private Boruvka boruvkaProblem;

    public static List<Edge> edges;

    public int numberOfVertices = 11;

    public api(){
        this.topologicalProblem = new TopoSortGraph(this.numberOfVertices);
        this.boruvkaProblem = new Boruvka();
        this.setupEdges();
    }

    public void setupEdges(){
        int numNodes = 6;
        edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 4, 3));
        edges.add(new Edge(3, 5, 2));
        edges.add(new Edge(4, 5, 1));

    }
    
    public void initEdges(List<Edge> initializer){
        edges = initializer;
    }
    public static GraphInterface graphSetup(){
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

        return g;
    }

    // api problem to run
    public List<Edge> runBoruvkaMST(){

        List<Edge> mst = this.boruvkaProblem.findMST(edges, this.numberOfVertices);

        return mst;
    }

    // api problem to run
    public List<Integer> runTopologicalSort(){
        TopoSortGraph graph = (TopoSortGraph) graphSetup();

        List<Integer> topologicalOrder = graph.topologicalSort();
        this.topologicalProblem = graph;
        this.topologicalProblem.addEdgesToAdjacencyFromGraph();
        List<Integer> result = this.topologicalProblem.topologicalSort();
        return result;

    }

    public void numberOfVertices(int i) {
        this.numberOfVertices = i;
    }
}
