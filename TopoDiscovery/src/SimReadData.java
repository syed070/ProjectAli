import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ali Muhammad on 11/4/2016.
 */
public class SimReadData {

    public ArrayList<Nodal_Links> readDemandMatrix(String filename){
        ArrayList<Nodal_Links> demandList = new ArrayList<>();
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

        return  demandList;    }


//    public ArrayList<Nodal_Links> readDemandMatrix(String filename){
//        ArrayList<Nodal_Links> demandList = new ArrayList<>();
//        try(Scanner scanner = new Scanner(new File(filename))){
//            String src;
//            String dst;
//            Double cost;
//            String line;
//            String[] lineSplit;
//            while (scanner.hasNext()) {
//                line = scanner.nextLine();
//                lineSplit = line.split("\t");
//                src = lineSplit[0];
//                dst = lineSplit[1];
//                cost = Double.parseDouble(lineSplit[2]);
//                demandList.add(new NodalLink(src, dst, cost.doubleValue()));
//            }
//
//        } catch (IOException e){
//            System.out.println("Node List not found");
//        }
//
//        return  demandList;
//    }

//    public ArrayList<Nodal_Links> readDemandFile(String filename){
//        ArrayList<Nodal_Links> demandList = new ArrayList<>();
//        Nodal_Links nl;
//        // String filename = "NodeList.txt";
//        try(Scanner scanner = new Scanner(new File(filename))){
//
//            double bandwidth=0.0;
//            String serviceChain;
//
//            String line;
//            String[] lineSplit;
//            String nodeName;
//            while (scanner.hasNext()){
//                Node Src = new Node();
//                Node Dst = new Node();
//                line = scanner.nextLine();
//                lineSplit = line.split("\t");
//                String tempSrcName = lineSplit[0];
//                Src.nodeName = tempSrcName;
//                String tempDstName = lineSplit[1];
//                Dst.nodeName = tempDstName;
//                bandwidth = Double.parseDouble(lineSplit[2]);
//                serviceChain = lineSplit[3];
//                nl = new Nodal_Links(Src,Dst,bandwidth,serviceChain);
//                demandList.add(nl);
//            }
//
//        } catch (IOException e){
//            System.out.println("Node List not found");
//        }
//
//        return  demandList;
//    }


    public ArrayList<String> readNodeFile(String filename){
        ArrayList<String> nodeList = new ArrayList<>();
        // String filename = "TDOutput_0.60.txt";
        try(Scanner scanner = new Scanner(new File(filename))){
            String line;
            String[] lineSplit;
            String nodeName;
            while (scanner.hasNext()){
                line = scanner.nextLine();
                lineSplit = line.split("\t");
                nodeName = lineSplit[0];
                nodeList.add(nodeName);
            }

        } catch (IOException e){
            System.out.println("Node List not found");
        }

        return  nodeList;
    }


    public ArrayList<String> readADFile(String filename){
        ArrayList<String> ADList = new ArrayList<>();
//        try(Scanner scanner = new Scanner(new File(filename))){
            try(Scanner scanner = new Scanner(new File(filename))){
            String line;
            String[] lineSplit;
            String nodeName;
            while (scanner.hasNext()){
                line = scanner.nextLine();
                lineSplit = line.split("\t");
                nodeName = lineSplit[1];
//                if(lineSplit[0].equals("834")){
//                    System.out.println("Arret");
//                }
                ADList.add(nodeName);
            }

        } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return  ADList;
    }


}
