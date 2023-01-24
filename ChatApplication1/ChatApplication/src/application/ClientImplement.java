package application;

import java.io.Serializable;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;


@SuppressWarnings("serial")
public class ClientImplement extends UnicastRemoteObject implements ClientInterface, Serializable{

	public static  List<String> users = new ArrayList<>();
	private static ServerInterface server;
    private static String name = null;
     //List<Message> messages = new ArrayList<>();
	
    
	protected ClientImplement() throws RemoteException {
		super();
		this.name = name;
        this.server = server;
        
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

	
	
/*
	@Override
	public void logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		 users.remove(username);
	        Message message = new Message();
	        message.setUsername(username);
	        System.out.println(" left the conversation ");
	        
		
	}*/
/*
	@Override
	public void sendMessage(Message message) throws RemoteException {
		 messages.add(message);
		
	}

	@Override
	public List<Message> getAllMessages() throws RemoteException {
		// TODO Auto-generated method stub
		for(Message m : messages){
            System.out.println(m.getUsername() + " " + m.getMsg());
        }
        return messages;
	}

	@Override
	public List<String> getAllUsers() throws RemoteException {
		// TODO Auto-generated method stub
		for(String u : users){
            System.out.println(u);
        }
        return users;
	}
*/

	@Override
	public void sendMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
   
		 System.out.println(message);
		
	}

	@Override
	public String getMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
	 return message;
		
	}
	
	public String getName() {
        return name;
    }

	
	
}
