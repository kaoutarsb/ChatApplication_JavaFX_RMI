package application;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote{
	
	//void broadcastMessage(String message,List<String> list) throws RemoteException;
	 public boolean login(String username) throws RemoteException;
	 public String getMessage(String message) throws RemoteException;
	 public void sendMessage2(String message) throws RemoteException;
			
}
