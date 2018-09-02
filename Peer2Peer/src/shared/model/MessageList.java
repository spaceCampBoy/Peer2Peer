package shared.model;

import java.util.ArrayList;

public class MessageList {
	ArrayList<Message> messages;
	public MessageList()
	{
		messages = new ArrayList<Message>();
	}
	
	public void addMessage(Peer sender, Peer receiver, String text)
	{
		messages.add(new Message(sender,receiver,text));
	}
	
	
}
