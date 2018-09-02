package shared.model;

import java.io.Serializable;

import shared.IPeer;

@SuppressWarnings("serial")
public class Peer implements Serializable{
	private String alias;
	private IPeer peerInterface;
	public Peer(String alias, IPeer peerInterface)
	{
		this.peerInterface = peerInterface;
		this.alias = alias;
	}
	
	public String getAlias()
	{
		return alias;
	}
	
	public IPeer getPeerInterface()
	{
		return peerInterface;
	}
	
}
