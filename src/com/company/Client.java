package com.company;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        Scanner userChoice = new Scanner(System.in);
        System.out.println("Press 1 for Encryption. 2 for Decryption. 3 to quit the program: ");
        int option = userChoice.nextInt();  // Scanner used for user input to choose encrypt, decrypt or quit.


        Scanner myText = new Scanner(System.in);
        // if statement separates code for different user options.
        if (option == 1) {
            System.out.println("Enter text to encrypt: ");
            String inputText = myText.nextLine();
            // Users string input is stored in inputText variable.

            Scanner myPassword = new Scanner(System.in);
            System.out.println("Enter password: ");
            String inputPassword = myPassword.nextLine();
            // Users password is stored in inputPassword variable.

            String host = (args.length < 1) ? null : args[0];
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                // obtain stub for registry
                ServerInterface stub = (ServerInterface) registry.lookup("EncryptionServer");
                // invoke the remote method lookup on the registry stub to obtain the stub for
                // the remote object from the server's registry.
                String response = stub.encryptMessage(inputText, inputPassword);
                // Jasypt encrypt method.
                System.out.println("Encrypted String: " + response);
                //invoke the encryptMessage/decryptMessage methods
                //on the remote object's stub and store/ print as 'response'.

            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
                // catch prints error exception to console.
            }
        }

        else if (option == 2) {
            System.out.println("Enter encrypted text: ");
            String inputText = myText.nextLine();

            Scanner myPassword = new Scanner(System.in);
            System.out.println("Enter password: ");
            String inputPassword = myPassword.nextLine();

            String host = (args.length < 1) ? null : args[0];
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                // obtain stub for registry
                ServerInterface stub = (ServerInterface) registry.lookup("EncryptionServer");
                // invoke the remote method lookup on the registry stub to obtain the stub for
                // the remote object from the server's registry.
                String response = stub.decryptMessage(inputText, inputPassword);
                // Jasypt decrypt method
                System.out.println("Decrypted String: " + response);
                //invoke the encryptMessage/decryptMessage methods
                //on the remote object's stub and store/ print as 'response'.

            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
                // catch prints error exception to console.
            }
            return;
        }

        else if (option == 3){
            System.exit(0);
            // Quits the program.
        }

        else{
            System.out.println("Please choose an available option:");
            // Exception handling- prints out message to console.
        }
    }
}
