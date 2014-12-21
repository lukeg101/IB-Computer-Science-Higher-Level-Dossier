import java.io.*;                   //used for file i/o and Scanner
import java.util.*;                 //used for the Scanner object
import java.io.IOException.*;       //used to catch exceptions
import java.io.ObjectOutputStream;  //used to write data to secondary memory using a data stream
import java.io.ObjectInputStream;   //used to read data from secondary memory using a data stream
/**
 * This is the main class that manages with file i/o and getting commands and information from the user
 * that can be set into fields using the get/set methods. There is a main method that will be used to
 * acquire this. 
 * 
 * @author Luke Geeson
 * @version 1.20
 * @date 12/12/2012
 * @school Norton Knatchbull School
 */
public class mainClass
{
    /**
     * this is the default constructor
     */
    public mainClass(){}
    //-----------------------------------------------------------------------------------------------
    /**
     * this method acts allows the user to input commands and information to the various methods. this
     * method will interpret the commands and pass control to the other methods of this class which need it
     * void - does not return anything - does not return anything
     */
    public static void main ()
    {
        boolean correctPas = false;
        int attemptNo = 0;
        String passwordAttempt = "";
        Scanner kbReader = new Scanner (System.in);
        while (correctPas != true)
        {
            if ( attemptNo == 3)
            {
                passwordAttempt = null;
                while (passwordAttempt == null)
                {
                    System.out.println("Login failed too many times, please input the SYSTEM PASSCODE");
                    String systemCode = kbReader.nextLine();
                    passwordAttempt = getPassword(systemCode);
                }
            }
            else
            {
                System.out.println("Input login password - it is case sensative ");
                passwordAttempt = kbReader.nextLine();
            }
            if (getPasswordWithoutCode().equals("") || getPasswordWithoutCode().equals(" ") || getPasswordWithoutCode() == null)
            {
                setPassword("password");
            }
            if (passwordAttempt.equals(getPasswordWithoutCode()))
            {
                correctPas = true;
                Date date = new Date();
                System.out.println("Login successful - Welcome, the date is " + date.toString() + "\n");
            }
            else
            {
                System.out.println("Incorrect password - please try again " + (2-attemptNo) + " attempts remaining\n");
                if (attemptNo < 3)
                {
                    attemptNo++;
                }
                correctPas = false;
            }
        }
        SinglyLinkedList a = loadListFromFile();
        boolean done = false;
        while (done != true)
        {
            saveListToFile(a);
            System.out.println("What do you want to do? add/search/Print all/get size/sort/clear list/change password or EXIT to quit");
            String choice = kbReader.nextLine();
            choice.trim();
            if(choice.equalsIgnoreCase("ADD") || choice.equalsIgnoreCase("A"))
            {
                System.out.println("Input Forename");
                String fName = new String (kbReader.nextLine());
                boolean hasSurname = false;
                String sName = "";
                while (hasSurname != true)
                {
                    System.out.println("Input Surname");
                    sName = new String (kbReader.nextLine());
                    if (sName.equalsIgnoreCase("") || sName == null )
                    {
                        System.out.println("Invalid input - please insert a surname");
                    }
                    else
                    {
                        hasSurname = true;
                    }
                }
                System.out.println("Input Phone Number");
                String pNum = new String (kbReader.nextLine());
                System.out.println("Input Email Address");
                String eAdd = new String (kbReader.nextLine());
                System.out.println("Input the first line of the Home Address");
                String hAdd = new String (kbReader.nextLine());
                System.out.println("Input Post Code");
                String pCode = new String (kbReader.nextLine());
                CustomerFile custDat = new CustomerFile(fName,sName,pNum,eAdd,hAdd,pCode);
                Node custNode = new Node(custDat, null);
                if (a == null)
                {
                    a = new SinglyLinkedList();
                }
                a.addRecord(custNode, a.getHead());   
                System.out.println("File added successfully\n");
            }
            else if(choice.equalsIgnoreCase("SEARCH") || choice.equalsIgnoreCase("S"))
            {
                System.out.println("Input Surname of customer required");
                String search = kbReader.nextLine();
                search.trim();
                
                Node result = a.searchList(a.getHead(), search);
                if (result == null || search == "")
                {
                    System.out.println("Search failed, please try again");
                    continue;
                }
                else
                {
                    System.out.println("search successful - printing details of file\n");
                    result.getData().displayAllDetails();
                }
                System.out.println("Is this the record you need? yes/no");
                String validation = kbReader.nextLine();
                if (validation.equalsIgnoreCase("YES") || validation.equalsIgnoreCase("Y"))
                {
                    System.out.println("What would you like to do with this record?: remove/modify or EXIT to stop");
                    String remOrMod = kbReader.nextLine();
                    boolean doneEdittingFile = false;
                    while (doneEdittingFile != true)
                    {
                        if (remOrMod.equalsIgnoreCase("REMOVE") || remOrMod.equalsIgnoreCase("R"))
                        {
                            boolean finRemove = false;
                            while (finRemove != true)
                            {
                                System.out.println("Are you sure that you want to remove this file? yes/no");
                                String confirm = kbReader.nextLine();
                                if (confirm.equalsIgnoreCase("YES") || confirm.equalsIgnoreCase("Y"))
                                {
                                    a.removeNode(result, a.getHead());
                                    System.out.println("Record successfully removed\n");
                                    finRemove = true;
                                }
                                else if(confirm.equalsIgnoreCase("NO") || confirm.equalsIgnoreCase("N"))
                                {
                                    System.out.println("File not removed\n");
                                    result = null;
                                    finRemove = true;
                                }
                                else
                                {
                                    System.out.println("Invalid input - please try again");
                                    finRemove = false;
                                }
                            }
                            doneEdittingFile = true;
                        }
                        else if (remOrMod.equalsIgnoreCase("MODIFY") || remOrMod.equalsIgnoreCase("M") || remOrMod.equalsIgnoreCase("CHANGE") || remOrMod.equalsIgnoreCase("C"))
                        {
                            boolean finChange = false;
                            while (finChange != true)
                            {
                                System.out.println("What data within this record would you like to change? forename/surname/email address/home address/post code/phone number or EXIT to leave");
                                String changeDecision = kbReader.nextLine();
                                if (changeDecision.equalsIgnoreCase("FORENAME") || changeDecision.equalsIgnoreCase("FIRSTNAME") || changeDecision.equalsIgnoreCase("FIRST NAME")|| changeDecision.equalsIgnoreCase("FIRST") || changeDecision.equalsIgnoreCase("F"))
                                {
                                    System.out.println("Input new forename");
                                    String newForename = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newForename);
                                    finChange = true;
                                }
                                else if (changeDecision.equalsIgnoreCase("SURNAME") || changeDecision.equalsIgnoreCase("SECOND NAME") || changeDecision.equalsIgnoreCase("SECOND NAME") || changeDecision.equalsIgnoreCase("S"))
                                {
                                    System.out.println("Input new surname");
                                    String newSurname = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newSurname);
                                    finChange = true;
                                }
                                else if ((changeDecision.equalsIgnoreCase("PHONENUMBER") || changeDecision.equalsIgnoreCase("PHONE NUMBER") || changeDecision.equalsIgnoreCase("PHONE")))
                                {
                                    System.out.println("Input new phone number");
                                    String newNumber = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newNumber);
                                    finChange = true;
                                }
                                else if ((changeDecision.equalsIgnoreCase("EMAILADDRESS") || changeDecision.equalsIgnoreCase("EMAIL ADDRESS") || changeDecision.equalsIgnoreCase("EMAIL") || changeDecision.equalsIgnoreCase("E")))
                                {
                                    System.out.println("Input new Email Address");
                                    String newEmail = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newEmail);
                                    finChange = true;
                                }
                                else if ((changeDecision.equalsIgnoreCase("HOMEADDESS") || changeDecision.equalsIgnoreCase("HOME ADDRESS") || changeDecision.equalsIgnoreCase("HOME") || changeDecision.equalsIgnoreCase("H")))
                                {
                                    System.out.println("Input the new first line of house address");
                                    String newHAddress = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newHAddress);
                                    finChange = true;
                                }
                                else if (changeDecision.equalsIgnoreCase("POSTCODE") || changeDecision.equalsIgnoreCase ("POST CODE"))
                                {
                                    System.out.println("Input new post code");
                                    String newPCode = kbReader.nextLine();
                                    result = a.changeNode(result, changeDecision, newPCode);
                                    finChange = true;
                                }
                                else if (changeDecision.equalsIgnoreCase("EXIT"))
                                {
                                    finChange = true;
                                    continue;
                                }
                                else
                                {
                                    finChange = false;
                                    System.out.println("Invalid input - please try again");
                                }
                            }
                            doneEdittingFile = true;
                        }
                        else if(remOrMod.equalsIgnoreCase("EXIT") || remOrMod.equalsIgnoreCase("E"))
                        {
                            doneEdittingFile = true;
                            continue;
                        }
                        else
                        {
                            doneEdittingFile = false;
                            System.out.println("Invalid input - please try again\n");
                        }
                    }
                }
                else if (validation.equalsIgnoreCase("NO") || validation.equalsIgnoreCase("N"))
                {
                    
                }
                else
                {
                    System.out.println("Invalid request, please try again");
                }
            }
            else if(choice.equalsIgnoreCase("PRINT ALL") || choice.equalsIgnoreCase("PRINT") || choice.equalsIgnoreCase("P"))
            {
                if (a == null)
                {
                    System.out.println("The list is empty - cannot print list\n");
                    done = false;
                }
                else if(a.isEmpty() == true)
                {
                    System.out.println("The list is empty - cannot print list\n");
                }
                else
                {
                    a.printList(a.getHead());
                }
            }
            else if(choice.equalsIgnoreCase("GET SIZE") || choice.equalsIgnoreCase("SIZE") || choice.equalsIgnoreCase("GS"))
            {
                if (a == null)
                {
                    System.out.println("The list is empty, the size is 0\n");
                    continue;
                }
                else
                {
                    System.out.println("The size of the list is " + a.length(a.getHead()) + "\n");
                }
            }
            else if(choice.equalsIgnoreCase("SORT"))
            {
               
            }
            else if(choice.equalsIgnoreCase("CLEAR LIST") || choice.equalsIgnoreCase("CLEAR") || choice.equalsIgnoreCase("C"))
            {
                boolean doClear = false;
                while (doClear != true)
                {
                    System.out.println("Are you sure you want to clear the whole list? yes/no");
                    String toClear = kbReader.nextLine();
                    if (toClear.equalsIgnoreCase("YES") || toClear.equalsIgnoreCase("Y"))
                    {
                        a = null;
                        System.out.println("List cleared\n");
                        doClear = true;
                    }
                    else if(toClear.equalsIgnoreCase("NO") || toClear.equalsIgnoreCase("N"))
                    {
                        System.out.println("List not cleared");
                        doClear = true;
                    }
                    else
                    {
                        System.out.println("Invalid request - please try again");
                        doClear = false;
                    }
                }
            }
            else if(choice.equalsIgnoreCase("EXIT") || choice.equalsIgnoreCase("E"))
            {
                System.out.println("System closing");
                done = true;
            }
            else if (choice.equalsIgnoreCase("CHANGEPASSWORD") || choice.equalsIgnoreCase("CHANGE PASSWORD") || choice.equalsIgnoreCase("PASSWORD"))
            {
                System.out.println("Input new password");
                String newPass = kbReader.nextLine();
                setPassword(newPass);
                System.out.println("Password set");
            }
            else
            {
                System.out.println("Invalid request, please try again\n");
                done = false;
                continue;
            }
        }
        saveListToFile(a);
        kbReader.close();
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method will serialise and save the Singly linked list object to the ".ser" file which is stored
     * in the 'doc' folder of this project - void, no returns
     * 
     * @param lst           the Singly linked list which is to be saved
     */
    public static void saveListToFile(SinglyLinkedList lst)
    {
        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(lst);
            os.flush();
            os.close();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method will 'deserialize' and  load the SinglyLinkedList object from the '.ser' file, which
     * is saved in the "doc" folder of this project
     * 
     * @return SinglyLinkedList the list which is loaded from the secondary storage
     */
    private static SinglyLinkedList loadListFromFile()
    {
        SinglyLinkedList lst = null;
        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            lst = (SinglyLinkedList) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return lst;
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method is used to set the password that the system uses as a level  of security - private so
     * it cannot be changed from outside the class. It is simply a set method and it saves the password data to a new file
     * 
     * @param newPassword   the new password that the user wants to change to 
     */
    private static void setPassword(String newPassword)
    {
        try
        {
            ObjectOutputStream osPassword = new ObjectOutputStream (new FileOutputStream (passwordFileName));
            osPassword.writeObject(newPassword);
            osPassword.flush();
            osPassword.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method is used to return the user password if they forget it, it requires them to pass
     * a integer which will be compared with a unique and unchanging system code. private so it is not
     * accessible by malicious users
     * 
     * @param systCode      to be compared with the system code
     * @return userPassword the user password returned
     */
    private static String getPassword(String systCode)
    {
        if (systCode.equals(PASSCODE))
        {
            String userPassword = "";
            try
            {
                ObjectInputStream isPassword = new ObjectInputStream (new FileInputStream (passwordFileName));
                userPassword = (String) isPassword.readObject();
                isPassword.close();
                System.out.println("Correct system code, your password is: " + userPassword);
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            return userPassword;
        }
        else 
        {
            System.out.println("That is the incorrect system code, please try again");
            return null;
        }
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method also returns the user password however, this method is only used to compare the
     * passwords input with those on file - it is not accessible by the user - takes no parameters
     * 
     * @return userPassword the password stored on file    
     */
    private static String getPasswordWithoutCode()
    {
        String userPassword = "";
        try
        {
            ObjectInputStream isPassword = new ObjectInputStream (new FileInputStream (passwordFileName));
            userPassword = (String) isPassword.readObject();
            isPassword.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return userPassword;
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this is a "set" method which sets the file name of the ".ser" file which stores the data in
     * serialized form - void as it simply sets data and private because it should not be accessible 
     * from outside this class
     * 
     * @param newFileName       sets the variable fileName as this value
     */
    private static void setFileName(String newFileName)
    {
        fileName = newFileName + ".ser";
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method simply returns the value assigned to fileName, returns the name of the file - simply
     * a get method so there are no parameters and private so outside users cannot see the name of the file
     * 
     * @return String           the file name
     */
    private static String getFileName()
    {
        return fileName;
    }
    //-----------------------------------------------------------------------------------------------
    //state variables - private for data protection
    private static String fileName = "ListData.ser";         //the name of the file being accessed
    private static final String PASSCODE = "14GF5602C2";     //a set passcode used to retreive auser password if lost
    private static String passwordFileName = "passDat.ser";  //the string in which the user password is stored
}