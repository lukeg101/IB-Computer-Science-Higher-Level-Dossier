import java.util.*;
public class LinkedListTest
{
    public LinkedListTest()
    {}
    public static void testADDRECORD ()
    {
        boolean done = false;
        SinglyLinkedList a = new SinglyLinkedList();
        while (done != true)
        {
            Scanner kbReader = new Scanner (System.in);
            System.out.println("Input Forename");
            String fName = kbReader.nextLine();
            System.out.println("Input Surname");
            String sName = kbReader.nextLine();
            System.out.println("Input Phone Number");
            String pNum = kbReader.nextLine();
            System.out.println("Input Email Address");
            String eAdd = kbReader.nextLine();
            System.out.println("Input the first line of the Home Address");
            String hAdd = kbReader.nextLine();
            System.out.println("Input Post Code");
            String pCode = kbReader.nextLine();
            CustomerFile custDat = new CustomerFile(fName,sName,pNum,eAdd,hAdd,pCode);
            Node custNode = new Node(custDat, null);
            a.addRecord(custNode,a.getHead());
            System.out.println("are you done?");
            String resp = kbReader.nextLine();
            if (resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y"))
            {
                done = true;
            }
            else if (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"))
            {
                continue;
            }
        }
        a.printList(a.getHead());
        System.out.println("The size of the list is " + a.length(a.getHead()));
    }
    public static void testort()
    {
        boolean done = false;
        SinglyLinkedList a = new SinglyLinkedList();
        while (done != true)
        {
            Scanner kbReader = new Scanner (System.in);
            System.out.println("Input Forename");
            String fName = kbReader.nextLine();
            System.out.println("Input Surname");
            String sName = kbReader.nextLine();
            System.out.println("Input Phone Number");
            String pNum = kbReader.nextLine();
            System.out.println("Input Email Address");
            String eAdd = kbReader.nextLine();
            System.out.println("Input the first line of the Home Address");
            String hAdd = kbReader.nextLine();
            System.out.println("Input Post Code");
            String pCode = kbReader.nextLine();
            CustomerFile custDat = new CustomerFile(fName,sName,pNum,eAdd,hAdd,pCode);
            Node custNode = new Node(custDat, null);
            a.appendLast(a.getHead(),custNode);
            System.out.println("are you done?");
            String resp = kbReader.nextLine();
            if (resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y"))
            {
                done = true;
            }
            else if (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"))
            {
                continue;
            }
        }
        a.printList(a.getHead());
        System.out.println("The size of the list is " + a.length(a.getHead()));
        a.printList(a.getHead());
    }
    public static void testSearch()
    {
        boolean done = false;
        SinglyLinkedList a = new SinglyLinkedList() ; 
        while (done != true)
        {
            Scanner kbReader = new Scanner (System.in);
            System.out.println("Input Forename");
            String fName = kbReader.nextLine();
            System.out.println("Input Surname");
            String sName = kbReader.nextLine();
            System.out.println("Input Phone Number");
            String pNum = kbReader.nextLine();
            System.out.println("Input Email Address");
            String eAdd = kbReader.nextLine();
            System.out.println("Input the first line of the Home Address");
            String hAdd = kbReader.nextLine();
            System.out.println("Input Post Code");
            String pCode = kbReader.nextLine();
            CustomerFile custDat = new CustomerFile(fName,sName,pNum,eAdd,hAdd,pCode);
            Node custNode = new Node(custDat, null);
            a.addRecord(custNode,a.getHead());
            System.out.println("are you done?");
            String resp = kbReader.nextLine();
            if (resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y"))
            {
                done = true;
            }
            else if (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"))
            {
                continue;
            }
        }
        a.printList(a.getHead());
        System.out.println("The size of the list is " + a.length(a.getHead()));
        System.out.println("please search for the customer record you require");
        Scanner kbReader = new Scanner (System.in);
        String searchLst = kbReader.nextLine();
        Node searchAttempt = a.searchList(a.getHead(), searchLst);
        System.out.println("Is this the record you require?");
        if (a.isEmpty() == true)
        {
            System.out.println("Sorry the list is empty!");
        }
        else
        {
            System.out.println("the list has Items! There are " + a.length(a.getHead()) + " items");
        }
        if (searchAttempt == null)
        {
            System.out.println("File not found");
        }
        else 
            searchAttempt.getData().displayAllDetails();
        String confirm = kbReader.nextLine();
        if (confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("YES"))
        {
            System.out.println("proceeding to remove this node");
            a.removeNode(searchAttempt, a.getHead());
        }
        else
            System.out.println("OK");
        a.printList(a.getHead());
    }
}