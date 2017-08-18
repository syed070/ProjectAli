import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by amuhamma on 7/8/2017.
 */
public class Driver {


    public static void main(String[] args) throws FileNotFoundException {

        /*
        Algorithm

        1. Aggregate Requests, Dsd = BW s-> d
        2. Find all shortest paths between each sd pair (use DFS to find it)
        3. Find Suurballe paths and union all paths (Paths in Dll list)
        4. Calculate CB for each node
        5. Cluster of node-pairs / groups
        6. For left over node-pairs, find the distance between the potential groups from the src dst
         */


        // 1.
        Input_Reader ir = new Input_Reader();
        ArrayList<Nodal_Links> dList = ir.aggregateRequests(ir.readDemandFile("DemandMatrixGermanyTesting.txt"));

        //To change it with BFS algo that gives all shortest paths for each node pairs
        FloydWarshall fw = new FloydWarshall(); //To get all the shortest paths for each SD pair

        Node nn = new Node();
        DoubleLinkedList dll = new DoubleLinkedList(); //To store paths
        ArrayList<DoubleLinkedList> allPaths = new ArrayList<>();

        // *******************
        HashMap<String, Double> temp;
        HashMap<String, Integer> map;


        ArrayList<Node> nList = ir.readNodeFile("NodeListTesting.txt");

        map = fw.mappingNodesIndex(nList);
        Graph_Algos ga = new Graph_Algos();

        // *******************
        // Find all Shortest paths
        allPaths = fw.runFloydWarshall(nList.size(),fw.generateWeightedMatrix(map, dList),map); // Reads from demand matrix and prints all the shortest paths

        dll.storePaths();
        // Find CB
        temp = ga.graphInput(nList, dList);
        Map<String,Double> sortedMap = nn.sortByComparator(temp,false);
        nList = nn.updateNodeList(nList, sortedMap);
        nn.printNodeList(nList);
        Node cb = nList.get(0);


        System.out.println();
    }
}