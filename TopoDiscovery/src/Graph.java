
import java.io.*;
import java.util.*;

/**
 * Created by Ali Muhammad on 12/5/2016.
 */
class Graph {



    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges


    /** One edge of the graph (only used by Graph constructor) */
    public static class Edge {
        public final Node v1, v2;
        public final Double bandwidth;

        public Edge(Node v1, Node v2, Double bandwidth) {
            this.v1 = v1;
            this.v2 = v2;
            this.bandwidth = bandwidth;
        }
    }

    /** One vertex of the graph, complete with mappings to neighbouring vertices */
    public static class Vertex implements Comparable<Vertex>{
        Node n= new Node();
        public String name = n.nodeName;
        public Double dist = Double.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;

        public final Map<Vertex, Double> neighbours = new HashMap<>();

        public Vertex(Node n)
        {
            this.name=n.nodeName;

        }

//        public Vertex(String name)
//        {
//            this.name = name;
//        }

        private void printPath()
        {

            if (this == this.previous)
            {
               System.out.printf("%s", this.name);

            }
            else if (this.previous == null)
            {
                System.out.printf("%s(unreached)", this.name);
            }
            else
            {
                this.previous.printPath();
                System.out.print("\t"+this.name+"->"+this.dist+"\t");
             //   System.out.printf(" -> %s  %d \t", this.name, this.dist);
//                temp2 = this.name;
//                System.out.println("\nTemp2:\t"+temp2);
            }
        }

        public int compareTo(Vertex other)
        {
            if (dist == other.dist)
                return name.compareTo(other.name);

            return Double.compare(dist, other.dist);
        }

        @Override public String toString()
        {
            return "(" + name + ", " + dist + ")";
        }
    }

    /** Builds a graph from a set of edges */
    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        //one pass to find all vertices
        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1.nodeName, new Vertex(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2.nodeName, new Vertex(e.v2));
        }

        //another pass to set neighbouring vertices
        for (Edge e : edges) {
            graph.get(e.v1.nodeName).neighbours.put(graph.get(e.v2.nodeName), e.bandwidth);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        // set-up vertices
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Double.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour
            for (Map.Entry<Vertex, Double> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final Double alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
//        System.out.println();
    }
    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
         //   System.out.println();
        }
    }




    public ArrayList<Nodal_Links> costAggregator(ArrayList<Nodal_Links> ad,String tempDesti,Double tempoCosti) {
        String src, dst ="";

        for (Vertex v : graph.values()) {


            if (v.name.equals(tempDesti)) {

                boolean test = false;
                Double tempCost = tempoCosti;

                if (v.previous == null)
                    continue;

                while (!v.name.equals(v.previous.name)) {
                    src = v.name;
                    dst = v.previous.name;
                    for (Nodal_Links nl : ad) {


                        if (nl.Src.nodeName.equals(src) && nl.Dst.nodeName.equals(dst) ||
                                nl.Dst.nodeName.equals(src) && nl.Src.nodeName.equals(dst)) {
                            if (test == false) {
                                nl.bandwidth = nl.bandwidth + tempCost;
                                test = true;
                                break;
                            } else if (test == true) {
                                nl.bandwidth = nl.bandwidth + tempCost;
                                //tempCost = nl.cost;
                                break;
                            }

                        }
                    }
                    v = v.previous;
                }
                //  v.printPath();
              //  System.out.println();
            }
        }
        return  ad;
    }

}



