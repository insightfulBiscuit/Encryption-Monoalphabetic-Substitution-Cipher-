/*
EncryptionAssignment class
Desc:
Daniel Kovalevskiy
Dec. 12, 2021
*/

//import Scanner
import java.util.Scanner;
//import 
import java.io.*;

//create EncriptionAssignment class
public class EncryptionAssignment{
    //create main method
    public static void main(String[] args) throws IOException{
        //Variable declaration
        final String ORIGINAL_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int OPTION_GENERATE_CIPHER = 1;
        final int OPTION_READ_CIPHER = 2;
        String cipherAlphabet = "";
        String originalMessage;
        String encryptedMessage;
        String decryptedMessage;
        int menuOption;
        boolean invalidInput;
        String fileName;

        //creating Scanner object
        Scanner input = new Scanner(System.in);

        //do only once
        do{
            //do until the input is valid
            do {
                //prints out the menu options
                System.out.print("1 - Generate new cipher" + "\n" +
                                 "2 - Retrieve cipher from file" + "\n" +
                                 "Please select a valid option: ");
                //user inputs the menu option
                menuOption = input.nextInt();
                input.nextLine();

                //if input is either 1 or 2
                if (menuOption == 1 || menuOption == 2){
                    //the input is valid
                    invalidInput = false;
                }
                //else (any number other than 1 or 2)
                else {
                    //the input is invalid
                    System.out.println("\n\nThe input is invalid. Try again.\n");
                    invalidInput = true;
                }
            } while(invalidInput);
            
            //if menu option is to generate the cipher
            if (menuOption == OPTION_GENERATE_CIPHER){
                //promts user to input the file name in which it will be stored
                System.out.print("Please enter the file name in which the cipher will be stored: ");
                fileName = input.nextLine();

                //generates and writes the cipher to the file
                cipherAlphabet = generateCipher(ORIGINAL_ALPHABET);
                writeCipher(fileName, cipherAlphabet);
            }

            //else if the menu option is to read the existing file
            else if (menuOption == OPTION_READ_CIPHER){
                //promts user to input the file name in which it is stored
                System.out.print("Please enter the file name in which the cipher is stored: ");
                fileName = input.nextLine();

                //reads the cipher from the file
                cipherAlphabet = readCipher(fileName);
            }

            System.out.println();

            //reads and outputs the original message
            originalMessage = readCipher("message.txt");
            System.out.println("Original Message = " + originalMessage);

            //reads and outputs the encrypted message
            encryptedMessage = encrypt(originalMessage, ORIGINAL_ALPHABET, cipherAlphabet);
            System.out.println("Encrypted Message = " + encryptedMessage);

            //writes and reads the encrypted message to the file
            writeCipher("encryptedMessage.txt", encryptedMessage);
            encryptedMessage = readCipher("encryptedMessage.txt");

            //decrypts and outputs the decrypted message
            decryptedMessage = decrypt(encryptedMessage, ORIGINAL_ALPHABET, cipherAlphabet);
            System.out.println("Decrypted Message = " + decryptedMessage);

        } while(false);

    }

    //generate cipher method
    public static String generateCipher(String originalAlphabet){
        String cipherAlphabet = "";
        int index;
        char newChar;
        //while the cipher alphabet is less than 26 letters
        while (cipherAlphabet.length() < 26){
            //generates the random index
            index = (int) (((originalAlphabet.length() - 1) - 0 + 1) * Math.random() + 0);
            //will fetch the letter from the original alphabet at the generated index
            newChar = originalAlphabet.charAt(index);

            //if the letter does not exist in the cipher alphabet 
            if (cipherAlphabet.contains(String.valueOf(newChar)) == false){
                //the letter will be added to the cipher alphabet
                cipherAlphabet += newChar;
            }
        }

        //return the cipher alphabet
        return cipherAlphabet;
    }

    //decrypt method
    public static String decrypt(String encryptedMessage, String originalAlphabet, String cipherAlphabet){
        String decryptedMessage = "";
        char character;
        int charIndex;

        //for every letter within the message
        for (int letter = 0; letter < encryptedMessage.length(); letter++){
            //stores value of character
            character = encryptedMessage.charAt(letter);
            //if the character is within the original alphabet
            if (originalAlphabet.contains(String.valueOf(Character.toUpperCase(character)))){
                //Stores the index of the letter
                charIndex = cipherAlphabet.indexOf(Character.toUpperCase(character));
                //if the character is lower case
                if (Character.isLowerCase(character)){
                    //will add the lower case character to the decrypted message string
                    decryptedMessage += Character.toLowerCase((originalAlphabet.charAt(charIndex)));
                }

                //else (the character is upper case)
                else {
                    //will add the upper case character to the decrypted message string
                    decryptedMessage += originalAlphabet.charAt(charIndex);
                }
            }

            //else (the character is non-alphabetic)
            else {
                //add that character to the decrypted message string
                decryptedMessage += character;
            }
        }

        //returns the decrypted message
        return decryptedMessage;
    }

    //encrypt method
    public static String encrypt(String originalMessage, String originalAlphabet, String cipherAlphabet){
        String messageEncrypt = "";
        char character;
        int charIndex;

        //for every letter within the message
        for (int letter = 0; letter < originalMessage.length(); letter++){
            //stores value of character
            character = originalMessage.charAt(letter);
            //if the character is within the cipher alphabet
            if (cipherAlphabet.contains(String.valueOf(Character.toUpperCase(character)))){
                //Stores the index of the letter
                charIndex = originalAlphabet.indexOf(Character.toUpperCase(character));
                //if the character is lower case
                if (Character.isLowerCase(character)){
                    //will add the lower case character to the message encrypt string
                    messageEncrypt += Character.toLowerCase((cipherAlphabet.charAt(charIndex)));
                }
                //else (the character is upper case)
                else {
                    //will add the upper case character to the message encrypt string
                    messageEncrypt += cipherAlphabet.charAt(charIndex);
                }
            }

            //else (the character is non-alphabetic)
            else {
                //add that character to the message encrypt string
                messageEncrypt += character;
            }
        }

        //returns the encrypted message
        return messageEncrypt;
    }

    //read cipher method
    public static String readCipher(String fileName) throws IOException{
        //cipher.txt
        String cipherAlphabet;

        //create File and Scannner objects
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        //reads the cipher from the file
        cipherAlphabet = scan.nextLine();

        //closing scan
        scan.close();

        //returns cipher
        return cipherAlphabet;
    }

    //write cipher method
    public static void writeCipher(String fileName, String cipherAlphabet) throws IOException{
        //create FileWriter and PrintWriter objects
        FileWriter store = new FileWriter(fileName);
        PrintWriter write = new PrintWriter(store);

        //writes the cipher alphabet to the file
        write.println(cipherAlphabet);

        //closing write
        write.close();
    }
}        