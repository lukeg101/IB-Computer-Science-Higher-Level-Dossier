import java.io.Serializable; //needed for Seriazable interface
/**This class creates the CustomerFile objects which will store the customer information for each
 * respective customer. There are "get" and "set" methods to initialise values to each respective
 * instance variable and the constructor will use the methods to initialise the variables
 * 
 * @author Luke Geeson
 * @version 1.00
 * @date 20/11/12
 * @school Norton Knatchbull School
 */
public class CustomerFile implements Serializable //can be serialized and stored in a seriazable file
{
    /**
     * this is the constructor, it will initialise values to the instance variables that create the
     * customer record using the get and set methods of this class. It does not return anything
     * so it is void but it will output a String ensuring the user that a record has been created 
     * 
     * @param fName         this is the forename string which will be assigned to forename
     * @param sName         this is the surname string which will be assigned to surname
     * @param pNum          this is the phone number which will be assigned to phoneNumber
     * @param eAddress      this is the email address which will be assigned to eAddress
     * @param hAddress      this is the home address which will be assigned to homeAddress
     * @param pCode         this is the post code which will be assigned to postCode
     */
    public CustomerFile(String fName, String sName, String pNum, String eAddress, String hAddress, String pCode)
    {
        setForename(fName);
        setSurname(sName);
        setPhoneNumber(pNum);
        setEmailAddress(eAddress);
        setHomeAddress(hAddress);
        setPostCode(pCode);
        System.out.println("Details successfully input");
    }
    /**
     * this is the default constructor
     */
    public CustomerFile(){}
    //------------------------------------------------------------------------------------------------
    /**
     * This is the method that will initialise the variable "forename" - method is void, it doesn't return
     * anything
     * 
     * @param a             this is the string that will be assigned to String forename
     */
    public void setForename(String inputForename)
    {
        this.forename = convertAndPresent(inputForename);
    }
    //------------------------------------------------------------------------------------------------
    /**
     * This is the method that will return the variable "forename"
     * 
     * @return forename     this is the variable that stores the forename
     */
    public String getForename()
    {
        return this.forename;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * This method initialises the variable "surname" with the input string - method is void, it doesn't
     * return anything
     * 
     * @param a             this String will be used to initialise the "surname" variable
     */
    public void setSurname(String inputSurname)
    {
        this.surname = convertAndPresent(inputSurname);
    }
    //------------------------------------------------------------------------------------------------
    /**
     * This method will return the String variable "surname"
     * 
     * @return surname      this is the variable that stores the surname
     */
    public String getSurname()
    {
        return this.surname;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method initialises the String variable "phoneNumber" - method is void
     * 
     * @param a             this string will be used to initialise the "phoneNumber" variable
     */
    public void setPhoneNumber(String inputPNum)
    {
        this.phoneNumber = inputPNum.trim();
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method returns the String variable "phoneNumber"
     * 
     * @return phoneNumber  the variable that stores the phone number
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method initialises the variable "emailAddress" - method is void
     * 
     * @param a             the String used to initialise the variable "emailAddress"
     */
    public void setEmailAddress(String inputEAddress)
    {
        inputEAddress = inputEAddress.trim();
        this.emailAddress = inputEAddress;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method returns the variable "emailAddress"
     * 
     * @return emailAddress the variable that stores the email address
     */
    public String getEmailAddress()
    {
        return this.emailAddress;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method initialises the variable "homeAddress" - method is void
     * 
     * @param a             the string used to initialise the variable "homeAddress"
     */
    public void setHomeAddress(String inputHAddress)
    {
        inputHAddress = inputHAddress.trim();
        this.homeAddress = inputHAddress;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method returns the variable "homeAddress"
     * 
     * @return homeAddress  the String used to store the home address
     */
    public String getHomeAddress()
    {
        return this.homeAddress;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method initialises the variable "postCode" - method is void
     * 
     * @param a             the String used to initialise the variable "postCode"
     */
    public void setPostCode(String inputPCode)
    {
        inputPCode = inputPCode.trim();
        inputPCode = inputPCode.toUpperCase();
        this.postCode = inputPCode;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method returns the variable "postCode"
     * 
     * @return postCode     the string used to store the post code
     */
    public String getPostCode()
    {
        return this.postCode;
    }
    //------------------------------------------------------------------------------------------------
    /**
     * this method converts Strings input so that the first letter is changed to uppercase to be used with proper nouns
     * such as names and days of the year - in this method it changes the String input (the forename and Surname for each file)
     * 
     * @param toConvert     the string which will be converted to a proper noun e.g input: "luke" output: "Luke"
     * 
     * @return converted    returns the converted String with an appropriate capital letter
     */
    public static String convertAndPresent(String toConvert)
    {
        toConvert = toConvert.trim();
        if (toConvert.equals(""))
        {
            toConvert = "empty field";
        }
        char firstLetter = toConvert.charAt(0);                                                                   //takes the first letter
        String converted = String.valueOf(firstLetter).toUpperCase() + toConvert.substring(1,toConvert.length()); //returns the letter (in upper case)
        return converted;                                                                                         //returns this string
    }
    //------------------------------------------------------------------------------------------------
    /**
     * method used to display all the details of a CustomerFile - method is void as it is printing and
     * takes no parameters
     */
    public void displayAllDetails()
    {
        System.out.println("Customer name:\t " + getForename() + " " + getSurname());               //prints the name of the customer
        System.out.println("Phone number:\t " + getPhoneNumber());                                  //prints the phone number
        System.out.println("Email address\t " + getEmailAddress());                                 //prints the email address
        System.out.println("Home address:\t " + getHomeAddress());                                  //prints the home address
        System.out.println("Post code:\t " + getPostCode());                                        //prints the post code
        System.out.print("\n");                                                                     //prints an eye pleasing gap between text on screen
    }
    //------------------------------------------------------------------------------------------------
    //state variables used in this class - private for data protection
    private String forename;                //declares the forename of this customer as a string
    private String surname;                 //declares the surname of the customer as a string
    private String phoneNumber;             //declares the phone number of the customer as a string
    private String emailAddress;            //declares the email address of the customer as a string
    private String homeAddress;             //declares the home address of the customer as a string
    private String postCode;                //declares the post code of the customer as a string
}