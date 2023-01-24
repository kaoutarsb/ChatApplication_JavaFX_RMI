package application;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImplement extends UnicastRemoteObject implements ServerInterface{

	public static  List<String> users = new ArrayList<>();
    
	protected ServerImplement() throws RemoteException {
		super();
	
	}
	@Override
	public boolean login(String username) throws RemoteException {
		// TODO Auto-generated method stub
		
		boolean flag = false;
        for (String user : users) {
            if (username.equals(user)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag == false) {
            users.add(username);
            System.out.println(" joined the conversation ");
            
            
            
            return true;
        }else{
            return false;
        } 
        //can also use .contains method in a List without using a flag
	}

	@Override
	public void sendMessage2(String message) throws RemoteException {
		// TODO Auto-generated method stub
   
		 System.out.println(message);
		
	}
	@Override
	public String getMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
	 return message;
		
	}
}
