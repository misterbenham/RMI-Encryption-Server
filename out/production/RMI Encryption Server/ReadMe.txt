*** RMI Encryption Server ***

This application uses the Java Remove Method Invocation (RMI) and Jasypt (see description) to encrypt a string and
decrypt the encrypted string. The user has the option to encrypt or decrypt an input, however, they must use a password
to do so. The password will be save alongside the string and can therefore only be decrypted with the input of that
same password. Using Jasypts StandardPBEStringEncryptor, the registry encrypts the string with a hash algorithm.
Oracles documentation for Java RMI can be found using the link below:

(https://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html)


ServerInterface Class:

As explained in Oracles documentation, we must firstly define the remote interface, in this case the server that extends
remote. We will later be using two methods, one for string encryption and the other for string decryption. The
'encryptMessage' method will take a string for input (inputText) and another string for the password (inputPassword).
The 'decryptMessage' method will take the encrypted hash string for input (encryptedText) and the same password
(inputPassword) in order to allow the program to decrypt the string. Both methods use RemoteException.


Server Class:

The next stage is to implement the server by implementing the 'ServerInterface' previously made. The server will require
importing the Java RMI Registry and Jasypts StandardPBEStringEncryptor. This server class has a main method that creates
an instance of the remote object implementation, exports the remote object, and then binds that instance to a name in
the Java RMI Registry. We use a try catch for the registry.

Firstly, we must create a registry and choose a port number where the ServerInterface exports to (port:1099). We must
declare a new server (named obj), and then export the server and port number 1099 to the ServerInterface, using a
'stub'. Next, bind the remote object's stub in the registry by firstly locating the registry and then use 'registry.bind'.
Name this registry 'EncryptionServer'. The implementation class Server implements the remote interface ServerInterface,
providing an implementation for the remote methods that will be made for encrypting and decrypting inputs. The catch
statement throws an error.

Now we need to create the methods for encrypting and decrypting user inputs, which are both similar. As explained in
the 'ServerInterface class' the method for encrypting strings (encryptMessage) needs to take a string for input
(inputText) and another string for the password (inputPassword). We then declare new jasypt StandardPBEStringEncryptor
(named encryptor) and can set the users password using the 'inputPassword' variable. We can then encrypt the users input
'inputText' using 'encryptor.encrypt' and store in a variable named 'output'. The variable output is the encrypted hash
string which can be displayed on console and must be returned for later use.

The 'decryptMessage' method will take the encrypted hash string for input (encryptedText) and the same password
(inputPassword). Similarly to the 'encryptMessage' method, we must declare a new StandardPBEStringEncryptor and then use
Jasypts 'decryptor.setPassword' method, passing the users password (inputPassword) to allow the program to decrypt the
string. Then, using Jasypts 'decryptor.decrypt' method, passing the 'encryptedText' variable and storing this into a
another variable, also named 'output'. We must return the output variable.


Client Class:

As explained in Oracles documentation, the client class obtains a stub for the registry on the server's host, looks up
the remote object's stub by name in the registry, and then invokes the encrypt/decrypt methods on the remote object
using the stub.

Firstly, we use a scanner to receive the users choice to either encrypt (option 1), decrypt (option 2) or quit the
program (option 3) and store this choice into a variable named 'option'. We will use an 'if statement' to separate
code for each option. We must create another scanner for the string input, called 'myText'. Option 1 (encryption)
and option 2 (decryption) both use the same try catch statement with jasypt encrypt and decrypt methods; The client
first obtains the stub for the registry by invoking the static LocateRegistry.getRegistry method with the hostname
specified (host:1099). Next, the client invokes the remote method lookup on the registry stub to obtain the stub for
the remote object from the server's registry. Finally, the client invokes the encryptMessage/decryptMessage methods
on the remote object's stub, which causes the following actions to happen:

     - The client-side runtime opens a connection to the server using the host and port information in the remote
        object's stub and then serializes the call data.
     - The server-side runtime accepts the incoming call, dispatches the call to the remote object, and serializes
        the result (output) to the client.
     - The client-side runtime receives, deserializes, and returns the result to the caller.
     - The response message returned from the remote invocation on the remote object is then printed to System.out.

Option 3 simply quits the application and any other user input that is not numbers 1, 2, 3 or print a message to the
console telling the user to enter an available option only.


TO USE:

- Run the server by selecting Server and clicking the 'run' button.
- The console will display 'Server Ready'.
- Run an instance of client.
- Choose option 1 to encrypt by inputting number 1.
- Enter test to encrypt (user choice of string) e.g. 'multitasking'.
- Enter a password to set the users choice e.g. '123456'.
- Copy or note down the encrypted string hash algorithm (GqDR8i/sundki4F13/z7P9on+zvQ4hEL)

- Restart the client instance.
- Choose option 2 to decrypt.
- Enter the encrypted string hash algorithm (GqDR8i/sundki4F13/z7P9on+zvQ4hEL).
- Enter the input password (123456).
- The console will display the original string input (multitasking).

- Choose option 3 to quit the program.