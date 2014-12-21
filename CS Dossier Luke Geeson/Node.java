import java.io.Serializable; //needed for the Serializable interface
/**
 * this class creates the nodes used in the singly linked list of customer records. This method contains
 * the "get" and "set" methods for the node data and nextNode variables 
 * 
 * @author Luke Geeson
 * @version 1.00
 * @date 25/11/12
 * @school Norton Knatchbull School
 */
public class Node implements Serializable //used so it can be serialized
{
    /**
     * this is the constructor, it will initialise the variables of the Node object
     * 
     * @param data      this is the CustomerFile object that will make up the data of the node
     * @param next      this is the next Object in the list
     */
    public Node(CustomerFile data, Node next)
    {
        nextNode = next;
        nodeData = data;
    }
    public Node(){}
    //--------------------------------------------------------------------------------------------------
    /**
     * this method initialises the variable "nodeData" with a CustomerFile object as the data - method is void
     * 
     * @param input     this is the Object object which will be assigned to the Object object "nodeData"
     */
    public void setData(CustomerFile input)
    {
        this.nodeData = input;
    }
    //--------------------------------------------------------------------------------------------------
    /**
     * this method returns the CustomerFile object data assigned to "nodeData"
     * 
     * @return nodeData this is the CustomerFile object which contains the data for a customer file
     */
    public CustomerFile getData()
    {
        return this.nodeData;
    }
    //--------------------------------------------------------------------------------------------------
    /**
     * this method initialises the Node object "nextNode" with the Node input - method is void
     * 
     * @param inputNext the Node which will initialise the variable "nextNode"
     */
    public void setNext(Node inputNext)
    {
        this.nextNode = inputNext;
    }
    //--------------------------------------------------------------------------------------------------
    /**
     * this method returns the Node stored in the variable "nextNode" which is the next node in the linked list
     * 
     * @return nextNode the Node which contains the next item in the linked list
     */
    public Node getNext()
    {
        return this.nextNode;
    }
    //--------------------------------------------------------------------------------------------------
    //state variables used in this class - private for data protection
    private CustomerFile nodeData;          //contains the customer record of this node
    private Node nextNode;                  //contains a reference to the next node
}