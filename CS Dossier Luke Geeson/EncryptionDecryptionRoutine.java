public class EncryptionDecryptionRoutine
{
    public static String decryptor(String toDecrypt)
    {
        String source,cipher;
        char oldChar, newChar;
        source = toDecrypt;
        cipher = "";
        for (int c=0; c<source.length(); c=c++ )
        {
            oldChar = source.charAt(c);
            if ( (oldChar >= 'a') && (oldChar <= 'z') ) //if the char ranges from 'a' to 'z'
            {
                int ascii = (int)oldChar;               //takes ASCII code from character at position c
                int pos = ascii - (int)'a';             //ASCII position c minus ASCII of 'a' = 97
                int code = ((int)'z') - pos;            //takes int value of char 'z' = 122 and takes away int pos
                newChar = (char)code;                   //casts the int back to a char
            }
            else //if the character does not range from a to z on the ASCII code list
            {
                newChar = oldChar;
            }
            cipher = newChar + cipher;                  //adds the new character AT THE START OF THE STRING
        }
        return cipher;
    }
    public static void main (String args[])
    {
        String original = "password";
        System.out.println("New password " + original);
        original = decryptor(original);
        System.out.println("new password is " + original);
    }
}