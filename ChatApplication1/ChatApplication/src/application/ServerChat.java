package application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.scene.control.TextArea;

public class ServerChat {

	public static void main(String[] args) throws RemoteException {
		
	            System.out.println("Starting Server");
	            ServerImplement objet=new ServerImplement();
	            ClientImplement client=new ClientImplement();
	            System.out.println("Starting Server1");
	            Registry registry1 = LocateRegistry.createRegistry(1100);
	            Registry registry2 = LocateRegistry.createRegistry(1101);
	            System.out.println("Starting Server2");
	            registry1.rebind("chatServer",objet ); //creates the remote object and registers it
	            registry2.rebind("chatClient",client);
	            System.out.println("Server Started");
	            
	            
	      
	    
}
	
}