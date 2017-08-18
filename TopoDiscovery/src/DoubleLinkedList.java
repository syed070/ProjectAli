
public class DoubleLinkedList {

    private Nodal_Links first;               // ref to first item
    private Nodal_Links last;                // ref to last item

    // -------------------------------------------------------------
    public DoubleLinkedList()         // constructor
    {
        first = null;                  // no items on list yet
        last = null;
    }
    // -------------------------------------------------------------
    public boolean isEmpty()          // true if no links
    { return first==null; }
    // -------------------------------------------------------------
    public void insertFirst(Node dd)  // insert at front of list
    {
        Nodal_Links newLink = new Nodal_Links(dd);   // make new link

        if( isEmpty() )                // if empty list,
            last = newLink;             // newLink <-- last
        else
            first.previous = newLink;   // newLink <-- old first
        newLink.next = first;          // newLink --> old first
        first = newLink;               // first --> newLink
    }
    // -------------------------------------------------------------
    public void insertLast(Node dd)   // insert at end of list
    {
        Nodal_Links newLink = new Nodal_Links(dd);   // make new link
        if (isEmpty())                // if empty list,
            first = newLink;            // first --> newLink
        else {
            last.next = newLink;        // old last --> newLink
            newLink.previous = last;    // old last <-- newLink
        }
        last = newLink;                // newLink <-- last
    }

    public void displayForward()
    {
        System.out.print("List (first-->last): ");
        Nodal_Links current = first;          // start at beginning
        while(current != null)         // until end of list,
        {
            current.displayLink();      // display data
            current = current.next;     // move to next link
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
    public void displayBackward()
    {
        System.out.print("List (last-->first): ");
        Nodal_Links current = last;           // start at end
        while(current != null)         // until start of list,
        {
            current.displayLink();      // display data
            current = current.previous; // move to previous link
        }
        System.out.println("");
    }
    // -------------------------------------------------------------
    public static void storePaths()
    {                             // make a new list
        DoubleLinkedList theList = new DoubleLinkedList();

        Node n = new Node();
        n.setNodeName("Montreal");

        Node m = new Node();
        m.setNodeName("laval");

        Node l = new Node();
        l.setNodeName("lachine");

        theList.insertFirst(n);      // insert at front
        theList.insertFirst(m);
        theList.insertFirst(l);
        theList.insertLast(n);      // insert at front
        theList.insertLast(m);
        theList.insertLast(l);
        theList.displayForward();     // display list forward
        theList.displayBackward();    // display list backward


    }
}