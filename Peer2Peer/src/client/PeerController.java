package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import shared.IPeer;
import shared.IRegisterServer;
import shared.model.Message;
import shared.model.Peer;
import shared.model.PeerList;

@SuppressWarnings("serial")
public class PeerController extends UnicastRemoteObject implements IPeer {
	private ViewController vc;

	private IRegisterServer adr;

	private Peer me;
	private PeerList cache = new PeerList();

	public PeerController() throws RemoteException
	{
	}

	public void setViewController(ViewController vc)
	{
		this.vc = vc;
	}

	public void begin()
	{
		try {
			String alias = vc.inputAlias();
			while (alias == null || "".equals(alias))
				alias = vc.inputAlias();

			vc.displayAlias(alias);

			me = new Peer(alias, this);

			adr = (IRegisterServer) Naming.lookup("rmi://192.168.56.1/registerServer");
			adr.registerPeer(me);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void send(String receiverAlias, String text)
	{
		try {
			Peer receiver = cache.getPeer(receiverAlias);
			Message msg = new Message(me, receiver, text);
			receiver.getPeerInterface().receiveMessage(msg);
			vc.addMessage(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
			vc.error("Trouble sending to " + receiverAlias);
		}
	}


	@Override
	public void receiveMessage(Message message) throws RemoteException, MalformedURLException, NotBoundException
	{
		vc.addMessage(message);

		if (cache.getPeer(message.getSender().getAlias()) == null)
		{
			cache.addPeer(message.getSender());
			vc.updatePeersList(cache.getPeers());
			
		}

	}

	public void searchPeer(String aliasToSearch)
	{
		try {
			Peer peer = adr.searchPeer(aliasToSearch);
			if (peer != null) {
				System.out.println("Found : " + peer.getAlias());
				cache.addPeer(peer);
				vc.updatePeersList(cache.getPeers());
			} else {
				System.out.println("Showing Error");
				vc.error("Could not find " + aliasToSearch);
			}

		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
