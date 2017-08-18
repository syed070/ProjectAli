import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by amuhamma on 7/8/2017.
 */
public class Input_Reader {





    public ArrayList<Nodal_Links> readDemandFile(String filename){
        ArrayList<Nodal_Links> demandList = new ArrayList<>();
        HashMap<String,Integer> linksMap = new HashMap<>();
        Nodal_Links nl;
        // String filename = "NodeList.txt";
        try(Scanner scanner = new Scanner(new File(filename))){

            double bandwidth=0.0;
            String serviceChain;

            String line;
            String[] lineSplit;
            String nodeName;
            while (scanner.hasNext()){
                Node Src = new Node();
                Node Dst = new Node();
                line = scanner.nextLine();
                lineSplit = line.split("\t");
                String tempSrcName = lineSplit[0];
                Src.nodeName = tempSrcName;
                String tempDstName = lineSplit[1];
                Dst.nodeName = tempDstName;
                bandwidth = Double.parseDouble(lineSplit[2]);
                serviceChain = lineSplit[3];
                nl = new Nodal_Links(Src,Dst,bandwidth,serviceChain);
                demandList.add(nl);
            }

        } catch (IOException e){
            System.out.println("Node List not found");
        }

        return  demandList;
    }


    public ArrayList<Node> readNodeFile(String filename){
        ArrayList<Node> nodeList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filename))){
            Node node = new Node();
            double betweennessCentrality = 0.0;
            String line;
            String[] lineSplit;
            String nodeName;
            while (scanner.hasNext()){
                line = scanner.nextLine();
                lineSplit = line.split("\t");
                nodeName = lineSplit[0];
                nodeList.add(new Node(nodeName,betweennessCentrality));
            }

        } catch (IOException e){
            System.out.println("Node List not found");
        }

        return  nodeList;
    }

    public ArrayList<Nodal_Links> aggregateRequests(ArrayList<Nodal_Links> nl){

//        for(Nodal_Links n:nl){
//            for (Nodal_Links m:nl){
//                if(n==m)
//                    continue;
//                if(n.Src.getNodeName().equals(m.Src.getNodeName()) &&
//                        n.Dst.getNodeName().equals(m.Dst.getNodeName())){
//                    n.bandwidth+= m.bandwidth;
//                    m.bandwidth = n.bandwidth;
//                }
//
//
//            }
//        }
        return nl;
    }


}
