/**
 * Created by amuhamma on 7/8/2017.
 */

public class Nodal_Links {


    Node Src;
    Node Dst;
    double bandwidth;
    String serviceChain;
    boolean primaryPathFound;
    boolean secondaryPathFound;




    public Node dData;
    public Nodal_Links next;                  // next link in list
    public Nodal_Links previous;              // previous link in list
    // -------------------------------------------------------------
    public Nodal_Links(Node d)                // constructor
    { dData = d; }
    // -------------------------------------------------------------

    public void displayLink()          // display this link
    { System.out.print(dData.getNodeName() + " "); }



    public void setPrimaryPathFound(){
        primaryPathFound = true;
    }

    public void setSecondaryPathFound(){
        secondaryPathFound = true;
    }

    public boolean getPrimaryPathFound(){
        return primaryPathFound;
    }

    public boolean getSecondayPathFound(){
        return secondaryPathFound;
    }


    public Nodal_Links (Node Src, Node Dst, double bandwidth, String serviceChain, boolean primaryPathFound, boolean secondaryPathFound ){
        this.Src = Src;
        this.Dst = Dst;
        this.bandwidth = bandwidth;
        this.serviceChain = serviceChain;
        this.primaryPathFound = primaryPathFound;
        this.secondaryPathFound = secondaryPathFound;
    }

    public Nodal_Links (Node Src, Node Dst){
        this(Src,Dst,0.0,null,false,false);

    }

    public Nodal_Links (Node Src, Node Dst, double bandwidth){
        this(Src,Dst,0.0,null,false,false);
    }

    public Nodal_Links (Node Src, Node Dst, double bandwidth, String serviceChain){
        this(Src,Dst,bandwidth,serviceChain,false,false);
    }

}

