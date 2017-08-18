/**
 * Created by Ali Muhammad on 12/5/2016.
 */


import java.io.*;
import java.util.*;

public class Dijkstra {



    public static void main(String[] args) {

//        ArrayList<NodalLink> tempNodal;
//        Map<String, Double> costMatrix = new HashMap<>() ;
//        ArrayList<String> nodeList;
//        SimReadData r = new SimReadData();
//        tempNodal = r.readDemandMatrix("demandMatrixTesting.txt");
//        costMatrix = costVectorReplicator(tempNodal);
//        nodeList = r.readNodeFile("nodeListTesting.txt");
//
//          Graph h = new Graph(graphCreator(tempNodal));
//          h.resetCostOfLinks(tempNodal);
//     for(int x=0;x<nodeList.size();x++){
//        String START = nodeList.get(x);
//        h.dijkstra(START);
//        h.printAllPaths();
//        tempNodal = h.costAggregator(tempNodal);
//        System.out.println("++++++++++++++++++++++++++++++++");
//    }

//        finalCostAggregation(tempNodal,costMatrix);
//        System.out.println("++++++++++++++++++++++++++++++++");
    }


    public ArrayList<Nodal_Links>  shortestPathRun(){

        ArrayList<Nodal_Links> tempNodal;
        ArrayList<Nodal_Links> tempNodalWithDemands;
        Map<String, Double> costMatrix = new HashMap<>() ;
        ArrayList<String> nodeList;
        SimReadData r = new SimReadData();
        tempNodal = r.readDemandMatrix("networkGraphCopyTesting.txt");
        tempNodalWithDemands = r.readDemandMatrix("demandMatrixGermanyTesting.txt");
        costMatrix = costVectorReplicator(tempNodal);
        nodeList = r.readNodeFile("nodeListTesting.txt");

        Graph h = new Graph(graphCreator(tempNodal));
      //  h.resetCostOfLinks(tempNodal);
        for(int x=0;x<nodeList.size();x++){
            ArrayList<Nodal_Links> tempNodalLinkstemp = new ArrayList<>();
            String START = nodeList.get(x);
            h.dijkstra(START);
     //       h.printAllPaths();
            tempNodalLinkstemp = findNodeInDemandMatrix(START,tempNodalWithDemands);
           // tempNodal = h.costAggregator(tempNodal);
           for(int y=0;y<tempNodalLinkstemp.size();y++){
               String tempoDesti = tempNodalLinkstemp.get(y).Dst.getNodeName();
               Double tempoCosti = tempNodalLinkstemp.get(y).bandwidth;
               tempNodal = h.costAggregator(tempNodal,tempoDesti,tempoCosti);
           }


        }

      //  finalCostAggregation(tempNodal,costMatrix);
        return  tempNodal;
    }


    public static ArrayList<Nodal_Links> findNodeInDemandMatrix(String nodeName, ArrayList<Nodal_Links> demandMatrix){

        ArrayList<Nodal_Links> tempNodalList = new ArrayList<>();
        for(Nodal_Links t:demandMatrix){
            if(t.Src.nodeName.equals(nodeName)){
                tempNodalList.add(t);
            }
        }
        return  tempNodalList;

    }


    public static Graph.Edge[] graphCreator(ArrayList<Nodal_Links> anl){

        Graph.Edge[] tempGraph = new Graph.Edge[anl.size()];
        for(int x=0;x<anl.size();x++){
             tempGraph[x] = new Graph.Edge(anl.get(x).Src, anl.get(x).Dst,anl.get(x).bandwidth);
        }
    return  tempGraph;
    }


    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static  Map<String,Double> costVectorReplicator(ArrayList<Nodal_Links> al){

        Map<String, Double> costVectorReplica = new HashMap<String, Double>();
        for(Nodal_Links n:al){
             costVectorReplica.put(n.Src.nodeName+"-"+n.Dst.nodeName,n.bandwidth);
          }
        return  costVectorReplica;
    }







}