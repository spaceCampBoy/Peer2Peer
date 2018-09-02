package client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import shared.model.Message;

public class ViewController implements Initializable {
	@FXML
	private ListView<String> listView;
	@FXML
	private ListView<Message> messageList;
	@FXML
	private TextField searchTextField;
	@FXML
	private TextField messageTextField;
	
	private String me;
	
	
	private ObservableList<Message> messages = FXCollections.observableArrayList();
	ObservableList<String> peersList = FXCollections.observableArrayList();
	
	private PeerController pc;
	private Alert alert;
	private Stage primaryStage;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		listView.setItems(peersList);
		messageList.setItems(messages);
		messageList.setCellFactory(messageListView -> new MessageListViewCell());
	}
	
	@FXML
	public void search(MouseEvent e)
	{
		String aliasToSearch = searchTextField.getText();
		System.out.println("Searching for " + aliasToSearch);
		if(aliasToSearch == null || aliasToSearch.equals(""))
		{
			error("Operation failed!"
					+ "/nTo search for a peer please: "
					+ "/n1- Write peer's alias in the field on the top. "
					+ "/n2- Click Search button. ");
			return;
		}
		pc.searchPeer(aliasToSearch);
	}
	
	@FXML
	public void sendMessage(MouseEvent e)
	{
		String receiverAlias = listView.getSelectionModel().getSelectedItem();
		String messageText = messageTextField.getText();
		if(receiverAlias == null || messageText == null || messageText.equals(""))
		{
			error("Operation failed!"
					+ "/nTo send a message please: "
					+ "/n1- Select a Peer from left panel. "
					+ "/n2- Write desired message in the field on the bottom. "
					+ "/n3- Click Send Message button.");
			return;
		}
		pc.send(receiverAlias, messageText);
		
	}
	
	public String inputAlias()
	{
		TextInputDialog inputD = new TextInputDialog();
		inputD.setTitle("Alias");
		inputD.setHeaderText("Please write your alias and click ok");
		
		
		return inputD.showAndWait().get();
	}
	
	public void setPrimaryStage(Stage ps)
	{
		primaryStage = ps;
	}
	
	public void displayAlias(String alias)
	{
		me = alias; 
		if(primaryStage != null)
		{
			primaryStage.setTitle("Peer --> "+alias);			
		}
	}

	public void error(String string)
	{
		if(alert == null)
		{
			alert = new Alert(Alert.AlertType.ERROR);
		}
		alert.setContentText(string);
		alert.showAndWait();
		
	}

	public void addMessage(Message message)
	{
		messages.add(message);
		updateMessageList();
		
	}
	
	private void updateMessageList()
	{
		
	}
	
	public void updatePeersList(String[] list)
	{
		for (int i = 0; i< list.length; i++) {
			peersList.add(i, list[i]);
		}
		System.out.println("updating peers list: "+peersList.toString());
		listView.setItems(peersList);
	}
	
	public void setPeerController(PeerController pc)
	{
		this.pc = pc;
	}
	
	
	public class MessageListViewCell extends ListCell<Message> {

		@FXML
		private TextArea senderName;
		@FXML
		private TextArea senderText;
		@FXML
		private TextArea receiverText;
		@FXML
		private TextArea receiverName;
		@FXML
		private GridPane gridPane;

		private FXMLLoader mLLoader;
		
		@Override
		protected void updateItem(Message message, boolean empty)
		{
			super.updateItem(message, empty);

			if (empty || message == null) {

				setText(null);
				setGraphic(null);

			} else {
				if (mLLoader == null) {
					mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
					mLLoader.setController(this);

					try {
						mLLoader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				System.out.println("Sender: "+message.getSender().getAlias());
				System.out.println("Receiver: "+message.getReceiver().getAlias());
				System.out.println("Message: " + message.getText());
				
				if(message.getSender().getAlias().equals(me))
				{
					senderText.setVisible(false);
					senderName.setVisible(false);
					receiverName.setText(me);
					receiverText.setText(message.getText());
				}
				else
				{
					senderName.setText(message.getSender().getAlias());
					senderText.setText(message.getText());
					receiverText.setVisible(false);
					receiverName.setVisible(false);
				}
				

				setText(null);
				setGraphic(gridPane);
			}
		}
	}
	
}