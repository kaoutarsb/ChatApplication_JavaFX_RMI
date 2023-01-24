package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javafx.scene.control.TextArea;



public interface ClientInterface extends Remote{
	
	public void sendMessage(String message) throws RemoteException;
	public String getMessage(String message) throws RemoteException;
	public boolean login(String username) throws RemoteException;
	// declaring methods to call remotely
   
    //public void logout(String username) throws RemoteException;
    /*public void sendMessage(Message message) throws RemoteException;
    public List<Message> getAllMessages() throws RemoteException;
    public List<String> getAllUsers() throws RemoteException;*/
	
}
