package parallelalgorithms.group9.homework3.llp.boruvka;

import com.parallelalgorithms.group9.homework3.util.GraphInterface;

import java.util.ArrayList;
import java.util.List;

public class Boruvka implements GraphInterface {
    private int[] parent;
    private int[] cheapest;

    public List<Edge> findMST(List<Edge> edges, int numNodes) {
        parent = new int[numNodes];
        cheapest = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            parent[i] = -1;
            cheapest[i] = -1;
        }

        List<Edge> mstEdges = new ArrayList<>();
        boolean changed = true;

        while (changed) {
            changed = false;

            edges.parallelStream().forEach(edge -> {
                int u = find(edge.source);
                int v = find(edge.destination);

                if (u != v) {
                    if (cheapest[u] == -1 || edge.weight < edges.get(cheapest[u]).weight) {
                        cheapest[u] = edges.indexOf(edge);
                    }
                    if (cheapest[v] == -1 || edge.weight < edges.get(cheapest[v]).weight) {
                        cheapest[v] = edges.indexOf(edge);
                    }
                }
            });

            for (int i = 0; i < numNodes; i++) {
                if (cheapest[i] != -1) {
                    int u = find(edges.get(cheapest[i]).source);
                    int v = find(edges.get(cheapest[i]).destination);

                    if (u != v) {
                        mstEdges.add(edges.get(cheapest[i]));
                        union(u, v);
                        changed = true;
                    }

                    cheapest[i] = -1;
                }
            }
        }

        return mstEdges;
    }

    private int find(int node) {
        if (parent[node] == -1) {
            return node;
        }
        return find(parent[node]);
    }

    private void union(int u, int v) {
        parent[u] = v;
    }

    public static void main(String[] args) {
        int numNodes = 6;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 3, 1));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 4, 3));
        edges.add(new Edge(3, 5, 2));
        edges.add(new Edge(4, 5, 1));

        Boruvka boruvka = new Boruvka();
        List<Edge> mst = boruvka.findMST(edges, numNodes);

        for (Edge edge : mst) {
            System.out.println("Edge: " + edge.source + " - " + edge.destination + ", Weight: " + edge.weight);
        }
    }
}

