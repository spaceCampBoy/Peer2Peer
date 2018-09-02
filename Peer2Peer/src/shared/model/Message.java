package shared.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
	private String text;
	private Peer sender;
	private Peer receiver;
	
	public Message(Peer sender, Peer receiver, String text)
	{
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String getText()
	{
		return text;
	}
	
	public Peer getSender() 
	{
		return sender;
	}
	
	public Peer getReceiver()
	{
		return receiver;
	}
	
	public String toString()
	{
		return sender.getAlias() + " --- " + receiver.getAlias() + " : " + text;
	}
	
}
