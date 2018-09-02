package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PeerView extends Application {
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PeerView.fxml"));
		Parent root = loader.load();
		ViewController vc = (ViewController)loader.getController();
		
		PeerController pc = new PeerController();
		vc.setPrimaryStage(primaryStage);
		
		pc.setViewController(vc);
		vc.setPeerController(pc);
		pc.begin();
		
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("PeerView.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	

}

