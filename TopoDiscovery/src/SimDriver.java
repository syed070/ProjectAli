

import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.commons.lang.SerializationUtils;

import java.io.*;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by amuhamma on 11/3/2016.
 */
public class SimDriver {


    public static void main(String[] args){


        ArrayList<Nodal_Links> nodelinks = new ArrayList<>();
            SimReadData r = new SimReadData();
            Dijkstra di = new Dijkstra();
            nodelinks = di.shortestPathRun(); // cost Aggregation
            costAggregatorTwoWayLinks(nodelinks);
           System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n ");

    }


        public static void costAggregatorTwoWayLinks(ArrayList<Nodal_Links> ndl){

            boolean[] tempArray = new boolean[ndl.size()];
            for(int x=0;x<ndl.size();x++){
                tempArray[x]=true;
                    for(int y=0;y<ndl.size();y++) {
                     if (ndl.get(y).Src.nodeName.equals(ndl.get(x).Dst.nodeName) &&
                             ndl.get(y).Dst.nodeName.equals(ndl.get(x).Src.nodeName)) {
                         if(tempArray[y]!=true){
                             ndl.get(x).bandwidth = ndl.get(x).bandwidth + ndl.get(y).bandwidth;
                             ndl.get(y).bandwidth = ndl.get(x).bandwidth;
                             tempArray[y]=true;
                         }
                     }

            }

            }
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



}
