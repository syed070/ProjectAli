import java.util.*;

/**
 * Created by amuhamma on 7/8/2017.
 */
public class Node{
    String nodeName;
    double betweennessCentrality;

    public String getNodeName(){
        return nodeName;
    }

    public double getBetweennessCentrality(){
        return betweennessCentrality;
    }

    public void setBetweennessCentrality(double bc){
        betweennessCentrality = bc;
    }

    public void setNodeName(String name){
        nodeName = name;
    }

    public Node(String nodeName, double betweennessCentrality){
        this.nodeName = nodeName;
        this.betweennessCentrality = betweennessCentrality;

    }

    public  Node(String nodeName){
        this(nodeName,0);
    }

    public Node(){

    }

    public ArrayList<Node> updateNodeList(ArrayList<Node> oldNodeList, Map<String, Double> hm){

        ArrayList<Node> newNodeList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : hm.entrySet()) {
                Node n = new Node();
                n.setBetweennessCentrality(entry.getValue());
                n.setNodeName(entry.getKey());
                newNodeList.add(n);
            }
        return newNodeList;
    }

    public void printNodeList(ArrayList<Node> nl){

        for(Node n:nl){
            System.out.println("\n Node:"+n.getNodeName()+"\t Betweenness Centrality\t "+n.getBetweennessCentrality());
        }
    }


    public static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }




}
