import java.util.*;
public class test
{
    public static void main (String [] args)
    {
        boolean done = false;
        while (done==false)
        {
            System.out.println("input forename");
            Scanner kBReader = new Scanner (System.in);
            String forename = kBReader.nextLine();
            System.out.println("input surname");
            String surname = kBReader.nextLine();
            System.out.println("input phone number");
            String phoneNumber = kBReader.nextLine();
            System.out.println("input email address");
            String emailAddress = kBReader.nextLine();
            System.out.println("input home address");
            String homeAddress = kBReader.nextLine();
            System.out.println("input post code");
            String postcode = kBReader.nextLine();
            CustomerFile a = new CustomerFile(forename, surname, phoneNumber, emailAddress, homeAddress, postcode);
            System.out.println("done?");
            if (kBReader.nextLine().equalsIgnoreCase("YES"))
            {
                done = true;
            }
            else
            {
                continue;
            }
            a.displayAllDetails();
        }
    }
}