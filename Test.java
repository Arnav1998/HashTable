package hw7;

import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Test extends Application {

//	public static void main(String[] args) {
//		
//		
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Open Resource File");
//		fileChooser.showOpenDialog(stage);
//		
//		HashTable hashtable = new HashTable();
//		
//		hashtable.add("Arnav");
//		hashtable.add("arnav");
//		hashtable.add("Ashwyn");
//		hashtable.add("Ashwyn");
//
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Input File");

		File inputFile = fileChooser.showOpenDialog(null);
		
		HashTable ht = new HashTable();
		

		if (inputFile != null) {
			
			File file = inputFile;
			
			Scanner sc = new Scanner(file);
			
			while (sc.hasNext()) {
				
				//System.out.println("hey");
				ht.add(sc.next());
				sc.next();
				//System.out.println(sc.next());
//				System.out.println("size: "+ht.size);
//				System.out.println("LF: "+ ht.LF);
//				System.out.println("capacity: "+ht.capacity);
			}
			
			new Alert(AlertType.INFORMATION, "Solution Generated").showAndWait();

		} else {
			
			new Alert(AlertType.INFORMATION, "File not found").showAndWait();
			
		}
		
	}
}
