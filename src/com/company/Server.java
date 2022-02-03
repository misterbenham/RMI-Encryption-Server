package com.company;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.jasypt.digest.*;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Server implements ServerInterface {

    public Server() {}

    public String encryptMessage(String inputText, String inputPassword) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(inputPassword);
        String output = encryptor.encrypt(inputText);
        System.out.println(output);

        return output;

        // method for encrypting strings using Jasypt StandardPBEStringEncryptor.
    }

    public String decryptMessage(String encryptedText, String inputPassword){

        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(inputPassword);
        String output = decryptor.decrypt(encryptedText);
        return output;

        // method for decrypting strings using Jasypt StandardPBEStringEncryptor.
    }

    public static void main(String args[]) {

        try {
            LocateRegistry.createRegistry(1099);
            Server obj = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(obj, 1099);
            // Create registry on port number 1099.
            // Use stub to export registry to ServerInterface class.

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("EncryptionServer", stub);
            // Binds the remote object's stub in the registry

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
