package shared;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.model.Peer;

public interface IRegisterServer extends Remote {
	public Peer searchPeer(String alias) throws RemoteException, MalformedURLException, NotBoundException;
	public void registerPeer(Peer peer) throws RemoteException, MalformedURLException, NotBoundException;
}
