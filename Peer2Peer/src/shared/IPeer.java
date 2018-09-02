package shared;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.model.Message;

public interface IPeer extends Remote {
	void receiveMessage(Message message) throws RemoteException, MalformedURLException, NotBoundException;
	
}
