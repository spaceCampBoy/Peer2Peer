package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import shared.IRegisterServer;
import shared.model.Peer;
import shared.model.PeerList;

public class RegisterServer implements IRegisterServer{
	private PeerList peerList;
	
	public RegisterServer() throws RemoteException
	{
		peerList = new PeerList();
		UnicastRemoteObject.exportObject(this, 0);
	}
	public static void main(String[] args)
	{
		try
		{
			LocateRegistry.createRegistry(1099);
			IRegisterServer server = new RegisterServer();
			Naming.rebind("registerServer", server);
			System.out.println("Starting server....");

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	@Override
	public synchronized Peer searchPeer(String alias) throws RemoteException, MalformedURLException, NotBoundException
	{
		return peerList.getPeer(alias);
	}

	@Override
	public synchronized void registerPeer(Peer peer) throws RemoteException, MalformedURLException, NotBoundException
	{
		peerList.addPeer( peer);
	}

}
