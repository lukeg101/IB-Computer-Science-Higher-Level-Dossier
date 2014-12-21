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
        boolean correctPas = false;                 //boolean for the password loop
        int attemptNo = 0;                          //used to allow 3 attempts
        String passwordAttempt = "";                //string for password attempts
        Scanner kbReader = new Scanner (System.in); //scanner used throughout method
        while (correctPas != true)                  //password input loop
        {
            if ( attemptNo == 3)                    //if the user has attempted to login 3 times
            {
                passwordAttempt = null;             //same varible is used to input system code
                while (passwordAttempt == null)     //will loop until system code is input
                {
                    System.out.println("Login failed too many times, please input the SYSTEM PASSCODE");
                    String systemCode = kbReader.nextLine();//system code input
                    passwordAttempt = getPassword(systemCode);//using the system code to get the password
                }
            }
            else
            {
                System.out.println("Input login password - it is case sensitive ");
                passwordAttempt = kbReader.nextLine();
            }
            if (getPasswordWithoutCode().equals("") || getPasswordWithoutCode().equals(" ") || getPasswordWithoutCode() == null)
            {                                       //if the default password is nothing or null
                setPassword("password");
            }
            if (passwordAttempt.equals(getPasswordWithoutCode()))
            {                                       //if the password is correct
                correctPas = true;                  //used to leave the loop
                Date date = new Date();             //get the date of login
                System.out.println("Login successful - Welcome, the date is " + date.toString() + "\n");
            }
            else 
            {                                       //if the password is incorrect
                System.out.println("Incorrect password - please try again " + (2-attemptNo) + " attempts remaining\n");
                if (attemptNo < 3)                  //increments for login attempts
                {
                    attemptNo++;                    //increment the attempts by 1
                }
                correctPas = false;                 //continue the loop
            }
        }
        SinglyLinkedList a = loadListFromFile();    //load the list from the file
        boolean done = false;                       //boolean for the main loop
        while (done != true)                        //loop until the user is finished
        {
            saveListToFile(a); //saves the list after each successive - keeps everything up to date
            System.out.println("What do you want to do? add/search/Print all/get size/sort/clear list/change password or EXIT to quit");
            String choice = kbReader.nextLine();    //takes user request
            choice.trim();                          //trims request for useless space
            if(choice.equalsIgnoreCase("ADD") || choice.equalsIgnoreCase("A"))
            {                                       //interprets user 'add' request
                System.out.println("Input Forename"); //add forename
                String fName = new String (kbReader.nextLine());
                boolean hasSurname = false;         //loop for surname 
                String sName = "";
                while (hasSurname != true)          //while an empty surname is NOT input
                {
                    System.out.println("Input Surname");//acquire user input
                    sName = new String (kbReader.nextLine());
                    if (sName.equalsIgnoreCase("") || sName == null )
                    {                               //if the surname input is empty
                        System.out.println("Invalid input - please insert a surname");
                    }
                    else
                    {                               //if the surname input is not empty
                        hasSurname = true;
                    }
                }
                System.out.println("Input Phone Number");//add phone number
                String pNum = new String (kbReader.nextLine());
                System.out.println("Input Email Address");//add email address
                String eAdd = new String (kbReader.nextLine());
                System.out.println("Input the first line of the Home Address"); //add home address
                String hAdd = new String (kbReader.nextLine());
                System.out.println("Input Post Code");//add post code
                String pCode = new String (kbReader.nextLine());
                CustomerFile custDat = new CustomerFile(fName,sName,pNum,eAdd,hAdd,pCode); //create customer file for data
                Node custNode = new Node(custDat, null);//create node to store data
                if (a == null || a.getHead() == null)//if the list is empty 
                {
                    a = new SinglyLinkedList();     //create new list
                }
                a.addRecord(custNode, a.getHead()); //adds the record to the list
                System.out.println("File added successfully\n");
            }
            else if(choice.equalsIgnoreCase("SEARCH") || choice.equalsIgnoreCase("S"))
            {                                       //interprets the users 'search' request
                Node result = null, current = null; //nodes used for comparison and search results
                boolean foundFile = false;          //used for while loop
                String search = "";                 //used for surname input for searcg
                while (foundFile != true)           //while the record is not found
                {
                    if (result == null)//if this is the first search
                    {
                        System.out.println("Input Surname of customer required");
                        search = kbReader.nextLine();
                        search = search.trim();
                    }
                    if (a == null) //if the list is emty
                    {
                        System.out.println("Search failed - the list is empty\n");
                        foundFile = true;
                        continue;
                    }
                    else if (current != null)//is this is a second search
                    {
                        if (current.getNext() == null)
                        { //if the previous search yielded a wrong record and it is the last item (or only item) in the list
                            System.out.println("search failed");
                            foundFile = true;
                            continue;
                        }
                        else
                        { //will continue search from the previous point in the list
                            result = a.searchList(current.getNext(), search);
                        }
                    }
                    else //search from the beginning
                    {
                        result = a.searchList(a.getHead(), search);
                    }
                    if (result == null || search == "") //if the search fails OR the search input is empty
                    {
                        System.out.println("Search failed, please try again");
                        foundFile = true;
                        break;
                    }
                    else //if a record is found
                    {
                        System.out.println("search successful - printing details of file\n");
                        result.getData().displayAllDetails();
                    }
                    System.out.println("Is this the record you need? yes/no");
                    String validation = kbReader.nextLine();//request confirmation for found record
                    if (validation.equalsIgnoreCase("YES") || validation.equalsIgnoreCase("Y"))
                    {  //interpret user confirmation for the correct record
                        current = result;
                        foundFile = true;
                        boolean doneEdittingFile = false;//used for modification or remove loop
                        while (doneEdittingFile != true)
                        {
                            System.out.println("What would you like to do with this record? remove/modify or EXIT");
                            String remOrMod = kbReader.nextLine(); //requests action to be performed on record
                            if (remOrMod.equalsIgnoreCase("REMOVE") || remOrMod.equalsIgnoreCase("R"))
                            {
                                boolean finRemove = false; //used for remove loop
                                while (finRemove != true)
                                {
                                    System.out.println("Are you sure that you want to remove this file? yes/no");
                                    String confirm = kbReader.nextLine();//confirm that the user wants to remove the file
                                    if (confirm.equalsIgnoreCase("YES") || confirm.equalsIgnoreCase("Y"))
                                    {   //if they do
                                        a.removeNode(current, a.getHead());
                                        System.out.println("Record successfully removed\n");
                                        finRemove = true;
                                    }
                                    else if(confirm.equalsIgnoreCase("NO") || confirm.equalsIgnoreCase("N"))
                                    {   //if they do not
                                        System.out.println("File not removed\n");
                                        result = null;
                                        current = null;
                                        finRemove = true;
                                    }
                                    else
                                    {   //if the user inputs invalid data
                                        System.out.println("Invalid input - please try again");
                                        finRemove = false;
                                    }
                                }
                                doneEdittingFile = true;//used to escape remove or modify loop
                            }
                            else if (remOrMod.equalsIgnoreCase("MODIFY") || remOrMod.equalsIgnoreCase("M") || remOrMod.equalsIgnoreCase("CHANGE") || remOrMod.equalsIgnoreCase("C"))
                            {   //interprets the modify action for the record
                                boolean finChange = false;//used for modification loop
                                while (finChange != true)
                                {
                                    System.out.println("What data within this record would you like to change? \nforename/surname/email address/home address/post code/phone number or EXIT to leave");
                                    String changeDecision = kbReader.nextLine();//to specify what is to be changed
                                    if (changeDecision.equalsIgnoreCase("FORENAME") || changeDecision.equalsIgnoreCase("FIRSTNAME") || changeDecision.equalsIgnoreCase("FIRST NAME")|| changeDecision.equalsIgnoreCase("FIRST") || changeDecision.equalsIgnoreCase("F"))
                                    {   //to change the forename
                                        System.out.println("Input new forename");
                                        String newForename = kbReader.nextLine();
                                        current = a.changeNode(current, changeDecision, newForename);
                                        System.out.println("Forename changed\n");
                                        finChange = true;//used to escape modification loop
                                    }
                                    else if (changeDecision.equalsIgnoreCase("SURNAME") || changeDecision.equalsIgnoreCase("SECOND NAME") || changeDecision.equalsIgnoreCase("SECONDNAME") || changeDecision.equalsIgnoreCase("S"))
                                    {   //to change the surname
                                        boolean hasSurname = false; //used for surname loop
                                        String newSurname = "";
                                        while (hasSurname != true) //test to insure surname input is not empty
                                        {
                                            System.out.println("Input New Surname");
                                            newSurname = new String (kbReader.nextLine());
                                            if (newSurname.equalsIgnoreCase("") || newSurname == null )
                                            {   //if the surname input is empty
                                                System.out.println("Invalid input - please insert a surname");
                                            }
                                            else
                                            {   //else escape the surname loop
                                                hasSurname = true;
                                            }
                                        }
                                        current = a.changeNode(current, changeDecision, newSurname);
                                        System.out.println("Surname changed");
                                        finChange = true;
                                        a.sortList(); //sorts the list as the surname is the key value
                                    }
                                    else if ((changeDecision.equalsIgnoreCase("PHONENUMBER") || changeDecision.equalsIgnoreCase("PHONE NUMBER") || changeDecision.equalsIgnoreCase("PHONE") || changeDecision.equalsIgnoreCase("P")))
                                    {   //to change the phone number of the record
                                        System.out.println("Input new phone number");
                                        String newNumber = kbReader.nextLine();
                                        current = a.changeNode(current, changeDecision, newNumber);
                                        System.out.println("Phone number changed\n");
                                        finChange = true;
                                    }
                                    else if ((changeDecision.equalsIgnoreCase("EMAILADDRESS") || changeDecision.equalsIgnoreCase("EMAIL ADDRESS") || changeDecision.equalsIgnoreCase("EMAIL") || changeDecision.equalsIgnoreCase("E")))
                                    {   //to change the email address of the record
                                        System.out.println("Input new Email Address");
                                        String newEmail = kbReader.nextLine();
                                        current = a.changeNode(current, changeDecision, newEmail);
                                        System.out.println("Email address changed\n");
                                        finChange = true;
                                    }
                                    else if ((changeDecision.equalsIgnoreCase("HOMEADDRESS") || changeDecision.equalsIgnoreCase("HOME ADDRESS") || changeDecision.equalsIgnoreCase("HOME") || changeDecision.equalsIgnoreCase("H")))
                                    {   //to change the home address of the record
                                        System.out.println("Input the new first line of house address");
                                        String newHAddress = kbReader.nextLine();
                                        current = a.changeNode(current, changeDecision, newHAddress);
                                        System.out.println("Home address changed\n");
                                        finChange = true;
                                    }
                                    else if (changeDecision.equalsIgnoreCase("POSTCODE") || changeDecision.equalsIgnoreCase ("POST CODE"))
                                    {   //change the post code of the record
                                        System.out.println("Input new post code");
                                        String newPCode = kbReader.nextLine();
                                        current = a.changeNode(current, changeDecision, newPCode);
                                        System.out.println("Post code changed\n");
                                        finChange = true;
                                    }
                                    else if (changeDecision.equalsIgnoreCase("EXIT"))
                                    {   //if the user does not want to edit the record 
                                        finChange = true; //exits the loop
                                        continue;         //returns to the main menu
                                    }
                                    else
                                    {   //else if the user inputs invalid data at this stage
                                        finChange = false;//will continue in the modify loop until correct input is seen
                                        System.out.println("Invalid input - please try again");
                                    }
                                }
                                doneEdittingFile = true;//used to escape the loop
                            }
                            else if(remOrMod.equalsIgnoreCase("EXIT") || remOrMod.equalsIgnoreCase("E"))
                            {   //if the user decides to quit instead of removing or moedifying the record
                                doneEdittingFile = true; //exit the loop - return to main menu
                                continue;
                            }
                            else
                            {   //if invalid commands are input at the remove or modify stage
                                doneEdittingFile = false;//continue the loop
                                System.out.println("Invalid input - please try again\n");
                                remOrMod = null;         //resets input for repeat of request when loop continues
                            }
                        }
                    }
                    else if (validation.equalsIgnoreCase("NO") || validation.equalsIgnoreCase("N"))
                    {   //if the record found is not the one required
                        current = result; //set it as the new current item so that the search continues from this point
                    }
                    else
                    {   //if an invalid command is detected
                        System.out.println("Invalid request, please try again"); //invalid input and continue loop
                    }
                }
            }
            else if(choice.equalsIgnoreCase("PRINT ALL") || choice.equalsIgnoreCase("PRINT") || choice.equalsIgnoreCase("P"))
            {   //if the user wants to print the list
                if (a == null || a.isEmpty() == true)
                {   //if the list is empty
                    System.out.println("The list is empty - cannot print list\n");
                }
                else
                {   //else print the list
                    a.printList(a.getHead());//calls print list method of the class
                }
            }
            else if(choice.equalsIgnoreCase("GET SIZE") || choice.equalsIgnoreCase("SIZE") || choice.equalsIgnoreCase("GS"))
            {   //if the user wants the size of the list
                if (a == null || a.isEmpty() == true)
                {   //if the list is empty
                    System.out.println("The list is empty, the size is 0\n");
                    continue;
                }
                else
                {   //else return and print the size of the list 
                    System.out.println("The size of the list is " + a.length(a.getHead()) + " items\n");
                }
            }
            else if(choice.equalsIgnoreCase("SORT"))
            {   //if the user wants to sort the list
                a.sortList();//calls the sort method
            }
            else if(choice.equalsIgnoreCase("CLEAR LIST") || choice.equalsIgnoreCase("CLEAR") || choice.equalsIgnoreCase("C"))
            {   //if the user wants to clear the list
                boolean doClear = false;//used for clear list confirmation loop
                while (doClear != true)
                {
                    System.out.println("Are you sure you want to clear the whole list? yes/no");
                    String toClear = kbReader.nextLine();//confirmation
                    if (toClear.equalsIgnoreCase("YES") || toClear.equalsIgnoreCase("Y"))
                    {   //the user wants to clear the list
                        a = null;
                        System.out.println("List cleared\n");
                        doClear = true; //exit clear list loop
                        toClear = null; //clears input 
                    }
                    else if(toClear.equalsIgnoreCase("NO") || toClear.equalsIgnoreCase("N"))
                    {   //the user does not want to clear the list
                        System.out.println("List not cleared");
                        doClear = true; //exit clear list loop
                        toClear = null; //clears input
                    }
                    else
                    {   //else the user inputs invalid command
                        System.out.println("Invalid request - please try again");
                        doClear = false; //exit the clear list loop
                    }
                }
            }
            else if(choice.equalsIgnoreCase("EXIT") || choice.equalsIgnoreCase("E"))
            {   //if the user chooses to exit the program 
                System.out.println("System closing");
                done = true; //exits the whole loop
            }
            else if (choice.equalsIgnoreCase("CHANGEPASSWORD") || choice.equalsIgnoreCase("CHANGE PASSWORD") || choice.equalsIgnoreCase("PASSWORD"))
            {
                boolean isPassword = false; //used for change password loop
                String oldPassword = "";    //used for password input
                while (isPassword != true)
                {
                    System.out.println("Input old password, it is case sensitive");
                    oldPassword = kbReader.nextLine(); //get old password
                    if(oldPassword.equals(getPasswordWithoutCode()))
                    {   //if the old password input is correct
                        System.out.println("Input new password"); 
                        String newPass = kbReader.nextLine();
                        setPassword(newPass);//change password
                        System.out.println("Password Changed\n");
                        isPassword = true;
                    }
                    else
                    {   //if the passowrd input is incorrect
                        boolean confirm = false;
                        while (confirm != true)
                        {   //ask user whether they want to retry input or return to main menu and not change password
                            System.out.println("Incorrect password, do you want to try again? yes/no");
                            String retry = kbReader.nextLine();
                            if (retry.equalsIgnoreCase("YES") || retry.equalsIgnoreCase("Y"))
                            {   //if they do want to try again
                                isPassword = false; //continue loop
                                confirm = true;     //exit this inner loop
                            }
                            else if(retry.equalsIgnoreCase("NO") || retry.equalsIgnoreCase("N"))
                            {   //if they do not want to try again
                                isPassword = true;  //exits loop
                                confirm = true;     //exits this inner loop
                                System.out.println("Password not changed\n");
                            }   
                            else    
                            {   //if the user inputs invalid command
                                System.out.println("Invalid input, please try again");
                                confirm = false;    //continues this loop until valid input is found
                            }
                        }
                    }
                }
            }
            else
            {   //if the user inputs wrong information at this stage
                System.out.println("Invalid request, please try again\n");
                done = false; //continue loop
                continue;
            }
        }
        saveListToFile(a); //saves the list with any final changes
        kbReader.close();  //closes the reader
    }
    //-----------------------------------------------------------------------------------------------
    /**
     * this method will serialise and save the Singly linked list object to the ".ser" file which is stored
     * in the 'doc' folder of this project - void, no returns
     * 
     * @param lst           the Singly linked list which is to be saved
     */
    private static void saveListToFile(SinglyLinkedList lst)
    {
        try
        {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(lst);//writes the list to the file
            os.flush();//flush the stream
            os.close();//closes outpit stream to finalise changes to the list
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
            lst = (SinglyLinkedList) is.readObject();//loads the list from the file
            is.close();//closes reader 
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
            osPassword.writeObject(newPassword);//writes the new password to file
            osPassword.flush();//flush the stream
            osPassword.close();//close the stream
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
        {   //if the passcode input is equals the system code
            String userPassword = "";
            try
            {
                ObjectInputStream isPassword = new ObjectInputStream (new FileInputStream (passwordFileName));
                userPassword = (String) isPassword.readObject();//retrieve the password from the file
                isPassword.close();//close the stream
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
            return userPassword;//return the retrieved password
        }
        else 
        {   //else if the password input is not equal to the passcode 
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
            userPassword = (String) isPassword.readObject();//retrieve the password from the file
            isPassword.close();//close the stream
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
        fileName = newFileName + ".ser";//saves the file path of the customer database fil
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
        return fileName;//returns the file name of the database location
    }
    //-----------------------------------------------------------------------------------------------
    //state variables - private for data protection
    private static String fileName = "ListData.ser";         //the name of the file being accessed
    private static final String PASSCODE = "14GF5602C2";     //a set passcode used to retreive auser password if lost
    private static String passwordFileName = "passDat.ser";  //the string in which the user password is stored
}