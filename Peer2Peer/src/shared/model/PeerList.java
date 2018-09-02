package shared.model;

import java.util.HashMap;
import java.util.Map;

public class PeerList {
	private Map<String,Peer> peers;
	public PeerList()
	{
		peers = new HashMap<String,Peer>();
	}
	
	public void addPeer(Peer peer)
	{
		peers.put(peer.getAlias(), peer);
	}
	
	public Peer getPeer(String alias)
	{
		return peers.get(alias);
	}
	
	public String[] getPeers()
	{
		String[] peersalias = new String[peers.size()];
		int count = 0;
		for (String key : peers.keySet()) {
			peersalias[count] = key;
			count++;
		}
		return peersalias;
	}
	

}	
