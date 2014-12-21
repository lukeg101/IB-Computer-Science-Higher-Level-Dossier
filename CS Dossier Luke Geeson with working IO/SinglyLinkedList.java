import java.io.Serializable;
/**
 * this is the class that is used to make a singly linked list with Node objects as the elements. There
 * are methods to add, remove, modify, search, find the size, determine if it is empty, sort, merge sort
 * set/get the head node (add first), set/get the last node (add last), print items in ascending/descending 
 * order and a method to convert the linked list into a node array
 * 
 * @author Luke Geeson
 * @version 1.00
 * @date 29/11/12
 * @school Norton Knatchbull School
 */
public class SinglyLinkedList implements Serializable
{
    /**
     * this constructor is used when a new linked list is created and you want to pass the first node 
     * of the list to it. If there is only one Node passed then pass null as the nextNode parameter
     * 
     * @param firstNode         the first node in the linked list, set as the head of the list
     * @param nextNode          the next node after the first, pass as null if there are no other nodes
     */
    public SinglyLinkedList (Node firstNode, Node nextNode)
    {
        setHead(firstNode);
        firstNode.setNext(nextNode);
    }
    /**
     * this is the default constructor
     */
    public SinglyLinkedList()
    {
        this.head = null;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * returns a boolean value dependent on whether the Linked List is empty - no parameters
     * 
     * @return empty            will determine whether it is empty - 'true' = the list is empty
     */
    public boolean isEmpty()
    { 
        boolean empty;
        if (getHead() == null)
        {
            empty = true;                                   //if there is not a head node ie the list is empty
        }
        else
        {
            empty = false;                                  //if there is a head node ie the list is full
        }
        return empty;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * returns the size of the list
     * 
     * @param input             calculates the size of the list with this variable - pass head node
     * 
     * @return int              the size of the list (null if empty)
     */
    public static int length(Node input)
    { 
        if (input == null)
        {
            return 0;                                       //if the node is empty, if the head node is empty then the list is empty
        }
        else
        {
            return 1 + length(input.getNext());             //recursive length check
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * inserts a Node in the list, in order according to the Surname is void as it does not return anything
     * 
     * @param newData           the new Node which contains the new data to be inserted 
     * @param trailNode         the node which the new data will compare with
     */
    public void addRecord(Node newData, Node trailNode)
    {
        if(trailNode == null)                               //if the trailNode is null
        {           
            if (this.head == null)                          //if the linked list has no head ie it is empty
            {
                this.setHead(newData);                      //sets the new data as the head of the list
            }
            else                                            //if the list is at it's end
            {
                trailNode = newData;                        //sets the newData as the last item    
            }
            newData.setNext(null);                          //sets the next item as null if the node is the first item (the head) or the last item                               
        }
        else if (trailNode == getHead() && newData.getData().getSurname().compareTo(trailNode.getData().getSurname())<0)
        {                                                   //if the trail node = head node and the new data comes before it
            newData.setNext(getHead());                     //sets the newdata.next to the current head
            setHead(newData);                               //sets the newdata as the head of the list
        }
        else if (trailNode.getNext() == null)                //if the trail.next is empty
        {
            trailNode.setNext(newData);                     //set the next item in the list 
            newData.setNext(null);                          //set the next item in the list as null
        }
        else if(newData.getData().getSurname().compareTo(trailNode.getNext().getData().getSurname())<0)
        {                                                   //if the new item comes before the trail.next
            if (newData.getData().getSurname().compareTo(trailNode.getData().getSurname())>0)
            {                                               //if the new item comes after the trail node
                newData.setNext(trailNode.getNext());       //sets the new data as the node before the item after the trail node
                trailNode.setNext(newData);                 //sets the new data as the item after the trail node
            }
        }
        else
        {                                                   //if the new data is not within the confines of the 2 nodes (trail node and the item after it)
            addRecord(newData,trailNode.getNext());         //recursive traversal through the list to the correct point
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method searches for the first occurance of the Node with an identical key String to "search"
     * 
     * @param compNode          the node which will be compared with the search string
     * @param search            the string which that is sought after
     * 
     * @return compNode or null this will return the first occurance of the Node containing the search String null if no such node is found
     */
    public Node searchList(Node compNode, String search)
    {
        if (compNode == null)
        {
            return null;                                    //if the list is empty
        }
        else if (compNode.getData().getSurname().equalsIgnoreCase(search))
        {
            return compNode;                                //if the node has the search string
        }
        else
        {
            return searchList(compNode.getNext(),search);   //recursive traversal through the list
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method will take a Node from the parameter and remove the first occurance of it in the singly
     * linked list. it is void  and takes 2 parameters:
     * 
     * @param toRemove          the node which needs to be removed
     * @param compNode          the node with which the 'toRemove' node will be compared - allows recursive traversal
     */
    public void removeNode(Node toRemove, Node compNode)
    {
        if (isEmpty() == true)
        {                                                   //if the list is empty
            System.out.println("The list is empty - cannot remove this item as it does not exist");
        }
        else if (toRemove == getHead())
        {                                                   //if the head of the list is the node to remove
            if (getHead() != null && getHead().getNext() == null)
            {                                               //if the head is not null but the next node is
                this.head = null;                           //removes the node from the list
            }
            else if (getHead().getNext() != null)
            {                                               //if the next item is not null and the head is to be removed
                toRemove = getHead();                       //marks the head to be removed
                this.head = getHead().getNext();            //the head is set as the next node
                toRemove = null;                            //the old head is deleted 
            }
        }
        else if (compNode.getNext() == toRemove && compNode.getNext() == getLast(getHead()))
        {                                                   //if the node to remove is at the end of the list
            compNode.setNext(null);                         //removes the last item in the list
        }
        else if (toRemove == compNode.getNext() )
        {                                                   //if the next item in the list is the item to remove
            toRemove = compNode.getNext();                  //marks the next item to be removed
            Node remPrev = compNode;                        //gets the previous node for reallocation
            Node remAfter = toRemove.getNext();             //gets the next node for reallocation 
            remPrev.setNext(remAfter);                      //reallocates other nodes
            toRemove = null;                                //removes old node
        }
        else 
        {                                                   //recursive traversal through the list until another statement is satisfied
            removeNode(toRemove, compNode.getNext());
        }         
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method will take a node and change the specific details of it  based on the data supplied
     * NOTE: this method alters DATA of the node - use get/set data methods to alter the node itself
     * 
     * @param nodeToChange      the node which will be altered
     * @param changeDecision    the decision of which variable is to be altered
     * @param changeInput       the new information that will replace the old
     * 
     * @return nodeToChange     the old node is return with the changes
     */
    public static Node changeNode(Node nodeToChange, String changeDecision, String changeInput)
    {
        if (changeDecision.equalsIgnoreCase("FORENAME") || changeDecision.equalsIgnoreCase("FIRSTNAME") || changeDecision.equalsIgnoreCase("FIRST NAME")|| changeDecision.equalsIgnoreCase("FIRST") || changeDecision.equalsIgnoreCase("F"))
        {                                                   //if the forename is to be changed
            nodeToChange.getData().setForename(changeInput);//change the forename of the node
        }
        else if (changeDecision.equalsIgnoreCase("SURNAME") || changeDecision.equalsIgnoreCase("SECOND NAME") || changeDecision.equalsIgnoreCase("SECOND NAME") || changeDecision.equalsIgnoreCase("S"))
        {                                                   //if the surname is to be changed
            nodeToChange.getData().setSurname(changeInput); //change the surname
        }   
        else if (changeDecision.equalsIgnoreCase("PHONENUMBER") || changeDecision.equalsIgnoreCase("PHONE NUMBER") || changeDecision.equalsIgnoreCase("PHONE"))
        {                                                   //if the phone number is to be changed
            nodeToChange.getData().setPhoneNumber(changeInput); //change it
        }
        else if(changeDecision.equalsIgnoreCase("EMAILADDRESS") || changeDecision.equalsIgnoreCase("EMAIL ADDRESS") || changeDecision.equalsIgnoreCase("EMAIL") || changeDecision.equalsIgnoreCase("E"))
        {                                                   //if the email address is to be changed
            nodeToChange.getData().setEmailAddress(changeInput);//change it
        }
        else if (changeDecision.equalsIgnoreCase("HOMEADDESS") || changeDecision.equalsIgnoreCase("HOME ADDRESS") || changeDecision.equalsIgnoreCase("HOME") || changeDecision.equalsIgnoreCase("H"))
        {                                                   //if the home address is to be changed
            nodeToChange.getData().setHomeAddress(changeInput);//change it
        }
        else if (changeDecision.equalsIgnoreCase("POSTCODE") || changeDecision.equalsIgnoreCase ("POST CODE"))
        {                                                   //if the postcode is to be changed
            nodeToChange.getData().setPostCode(changeInput);//change it
        }
        return nodeToChange;                                //return the modified node
    }
    //------------------------------------------------------------------------------------------------
    public static void sortList(SinglyLinkedList lst,Node headNode, Node lastNode) //need to do
    {
        
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method will take two singly linked list objects as parameters and merge them - it has 4 
     * possible ways of working 1.if both are empty 2.one is full and the other is not, 3. vice versa
     * or 4.both are full.
     * 
     * @param lst1              the first list
     * @param lst2              the second list
     * 
     * @return newList          returns a new list with the 2 lists merged
     */
    public static SinglyLinkedList mergeLists(SinglyLinkedList lst1, SinglyLinkedList lst2)
    {
        SinglyLinkedList newList = new SinglyLinkedList();  //new list for merging
        if (lst1 == null && lst2 == null)                   //if both lists are empty
        {
            newList = null;                                 //cannot merge what is not there
            System.out.println("Both lists are empty - cannot merge");
        }
        else if (lst1 == null && lst2 != null)              //if list 1 is empty but the other is not
        {
            newList = lst2;                                 //set the new list as list 2
        }                                                   
        else if (lst1 != null && lst2 == null)              //if list 2 is empty but the other is not
        {
            newList = lst1;                                 //set the new list as list 1
        }
        else                                                //if both lists have data
        {
            newList = lst1;                                 //set the new list as list 1 and add all records from the second
            Node current = lst2.getHead();                  //start point for adding to the new list from list 2
            for (int j = 0; j < lst2.length(lst2.getHead()); j++)
            {
                newList.addRecord(current,newList.getHead()); //add record method inserts in order
                current = current.getNext();                //iterative approach to merging the lists - allows traversal
            }
        }
        return newList;                                     //returns the new list
    }
    //------------------------------------------------------------------------------------------------
    /**
     * prints the linked list in descending order  to the node which is input
     * 
     * @param input             prints all items from this point onward in descending order
     */
    public void printReverseList(Node input)
    {
        if (input != null)
        {
            printReverseList(input.getNext());              //recursive progression to the end of the list
            input.getData().displayAllDetails();            //prints the information of the node
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * prints the entire linked list in ascending order from the node input - will print from any node
     * in the list to the end of the list but to print the whole list - pass the head node
     * 
     * @param input             the node from which you print
     */
    public void printList(Node input)
    {
        if(input != null)
        {
            input.getData().displayAllDetails();            //prints all data from the node passed
            printList(input.getNext());                     //recursive print
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * assignes the value input to the "head" variable and sets as the head of the list
     * 
     * @param input             a Node which will be set as the head
     */
    public void setHead(Node input)
    {
        input.setNext(this.head);                           //sets the head as the node after the input node
        this.head = input;                                  //sets the node input as the head node
    }
    //------------------------------------------------------------------------------------------------
    /**
     * returns the value assigned to variable "head" effectively setting the head
     * and adding a node to the list
     * 
     * @return head             the head of the list
     */
    public Node getHead()
    {
        return this.head;   
    }
    //------------------------------------------------------------------------------------------------
    /**
     * adds a node to the end of the list
     * 
     * @param input             a node which will be set as the last item in the list
     * @param elementOfList     a node which will be used to cycle through the list and append the input node to the end
     */
    public void appendLast(Node elementOfList,Node input)
    {
        if (elementOfList == null)                              //if the node is empty
        {
            if (isEmpty() == true)                              //if the list is empty
            {
                setHead(input);                                 //sets the item as the new head
                input.setNext(null);                            //sets the next as null - it is the only item in the list
            }
            else                                                //if the list is not empty
            {
                elementOfList = input;                          //sets the last item in the list as the input node
                elementOfList.setNext(null);                    //creates an end point to the list
            }
        }
        else if (elementOfList.getNext() == null)               //if the next item in the list is null
        {
            elementOfList.setNext(input);                       //set the last item as the new node
            input.setNext(null);                                //the new node is now the last item
        }
        else                                                    //recursive traversal through the list if these are not satisfied
        {
            appendLast(elementOfList.getNext(), input);
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * returns the value assigned to the last item in the list
     * 
     * @param elementOfList     used to cycle through the list and reach the end of the list
     * @return elementOfList    returned as the last item in the list
     */
    public Node getLast(Node elementOfList)
    {
        if (elementOfList == null)
        {
            return elementOfList;                               //if the linked list is empty - returns null
        }
        else if (elementOfList.getNext() == null)
        {
            return elementOfList;                               //returns the last item in thr list
        }
        else
        {
            return getLast(elementOfList.getNext());            //recursive traversal thorugh the list
        }
    }
    //------------------------------------------------------------------------------------------------
    /**
     * converts the Linked List to a data array so that the data contained in an array will itself be an array
     * - no paramters 
     * 
     * @return dataArray        an object array that will contain the data within each node of the linked list
     */
    public Node[] toArray()
    {
        int k = SinglyLinkedList.length(this.head);             //gets the size of the list
        Node dataArray[] = new Node[k];                         //initialises a Node array of the size of the list
        Node listNode = getHead();                              //gets the head of the  list
        for (int j = 0; j<k-1;j++)
        {
            dataArray[j] = listNode;                            //initialises each item of the array to a respective item in the linked list
            listNode = listNode.getNext();                      //gets the next item in the list
        }
        return dataArray;                                       //returns the array once complete
    }
    //------------------------------------------------------------------------------------------------
    //state  variables - private for data protection
    private Node head;                                          //the head of the list
}