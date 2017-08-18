/**
 * Created by Ali Muhammad on 8/2/2017.
 */
import static java.lang.String.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FloydWarshall {

      public static ArrayList<DoubleLinkedList> runFloydWarshall(int numVertices,int[][]weights,HashMap<String, Integer> tempMap) {

          return floydWarshall(weights, numVertices,tempMap);
    }

    static ArrayList<DoubleLinkedList> floydWarshall(int[][] weights, int numVertices,HashMap<String, Integer> tempMap) {

        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];

        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }

        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

     return printResult(dist, next, tempMap);

    }

    static ArrayList<DoubleLinkedList> printResult(double[][] dist, int[][] next,HashMap<String, Integer> tempMap) {
        ArrayList<DoubleLinkedList> allPaths = new ArrayList<>();

        System.out.println("pair     dist    path");
        for (int i = 0; i < next.length; i++) {
            DoubleLinkedList singlePath = new DoubleLinkedList();
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    System.out.println();
                        String path = format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
                    //
                   System.out.print(getKeyFromValue(tempMap, u) + " -> ");
                //    singlePath.insertFirst(createNode((String)getKeyFromValue(tempMap,u)));
                   System.out.print(getKeyFromValue(tempMap, v));
            //        singlePath.insertFirst(createNode((String)getKeyFromValue(tempMap,v)));
                    System.out.print("\t"+(int) dist[i][j]);

                    do {
                       // System.out.print("\t"+getKeyFromValue(tempMap,u)+" -> ");
                        singlePath.insertFirst(createNode((String) getKeyFromValue(tempMap, u)));
                        u = next[u - 1][v - 1];
                        path += " -> " + u;

                    } while (u != v);
                    System.out.print(getKeyFromValue(tempMap,u));
                    singlePath.insertFirst(createNode((String) getKeyFromValue(tempMap, u)));
                 //   System.out.println(path);
                }
                allPaths.add(singlePath);
            }
        }
        return allPaths;
    }

    public static Node createNode(String tempNodeName){
        Node n = new Node();
        n.setNodeName(tempNodeName);

        return n;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }



    public int[][] generateWeightedMatrix(HashMap<String, Integer> mapper, ArrayList<Nodal_Links> nl) {

        int[][] weights = new int[nl.size()][3];
        for (int x = 0; x < nl.size(); x++) {
            weights[x][0] = mapper.get(nl.get(x).Src.getNodeName());
            weights[x][1] = mapper.get(nl.get(x).Dst.getNodeName());
            weights[x][2] = (int) nl.get(x).bandwidth;
        }

        return weights;
    }

    public HashMap<String, Integer> mappingNodesIndex(ArrayList<Node> n) {
        HashMap<String, Integer> mapper = new HashMap<>();

        for (Node nn : n) {
            mapper.put(nn.getNodeName(), (n.indexOf(nn) + 1));
        }
        return mapper;
    }

}