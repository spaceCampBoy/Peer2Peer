//package shared.model;
//
//import java.io.Serializable;
//import java.net.MalformedURLException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
//
//import shared.*;
//
//public class Peer2 implements IPeer, Serializable{
//	private String alias;
//	private IRegisterServer registerServer;
//	public Peer2(String alias)  throws MalformedURLException, RemoteException, NotBoundException
//	{
//		this.alias = alias;
//		registerServer = (IRegisterServer) Naming.lookup("rmi://localhost:1099/registerServer");
//		UnicastRemoteObject.exportObject(this,0);
//	}
//	public static void main(String[] args)
//	{
//		try {
//			Peer2 peer = new Peer2("First Peer");
//			peer.registerPeer(peer);
//			System.out.println("Client is running...");
//		} catch (MalformedURLException | RemoteException | NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//	
//	public void registerPeer(Peer peer) throws RemoteException, MalformedURLException, NotBoundException
//	{
//		registerServer.registerPeer(peer);
//	}
//	
//	public String getAlias()
//	{
//		return alias;
//	}
//	
//	
//	public Peer getPeerFromRegisterServer(String alias) throws RemoteException, MalformedURLException, NotBoundException
//	{
//		return registerServer.searchPeer(alias);
//	}
//	
//	public void sendMessageToPeer(Peer receiver, String text) throws RemoteException, MalformedURLException, NotBoundException
//	{
//		IPeer iReceiver = receiver;
//		iReceiver.receiveMessage(new Message(this, receiver, text));
//	}
//	
//	@Override
//	public void receiveMessage(Message message) throws RemoteException, MalformedURLException, NotBoundException
//	{
//		System.out.println(message.toString());
//	}
//
//}
