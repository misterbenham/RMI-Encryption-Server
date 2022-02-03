package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    String encryptMessage(String inputText, String inputPassword) throws RemoteException;
    // remote method for encrypting the string. Requires input and password.
    String decryptMessage(String encryptedText, String inputPassword) throws RemoteException;
    // remote method for decrypting the string. Requires input and original password.
}
