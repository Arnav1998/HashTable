package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HashTableTest extends Application {

	private static File inputFile;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Input File");

		inputFile = fileChooser.showOpenDialog(null);
		
		
		//-----------------------------
		
		Instant start = Instant.now();

		HashTable hashTable = HashTableTest.generateHashTable();
		
		Instant end = Instant.now();

		System.out.println("HashTable: "+Duration.between(start, end));
		
		System.out.println("HashTable size: "+hashTable.size());
		
		File outputFile = new File("output1.txt");
		
		PrintWriter pw = new PrintWriter(outputFile);
		
		pw.print(hashTable.toString());
		
		pw.close();
		
		//----------------------------
		
		Instant start2 = Instant.now();

		SimpleList simpleList = HashTableTest.generateSimpleList();
		
		Instant end2 = Instant.now();

		System.out.println("SimpleList: "+Duration.between(start2, end2));
		
		System.out.println("SimpleList size: "+simpleList.size());
		
		File outputFile2 = new File("output2.txt");
		
		PrintWriter pw2 = new PrintWriter(outputFile2);
		
		pw2.print(simpleList.toString());
		
		pw2.close();
		
		System.exit(0);
		
	}
	
	public static SimpleList generateSimpleList() {		
		
		SimpleList simpleList = new SimpleList();
	
		if (inputFile != null) {
			
			   Scanner sc = null;
			   
			    try {
			        sc = new Scanner(inputFile);
			        
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();  
			    }

			    while (sc.hasNext()) {
			        String s = sc.next();
			        
			        Entry entry = new Entry(s.toLowerCase());
			        
			        if (simpleList.find(entry.getWord())==-1) {
			        	simpleList.add(entry);
			        } else {
			        	simpleList.getEntry(simpleList.find(entry.getWord())).incrementCount();
			        }

			    }
			
			return simpleList;
			
		} else {
			
			new Alert(AlertType.INFORMATION, "File not found").showAndWait();
			return null;
			
		}
	
	}
			
	public static HashTable generateHashTable()	{
		
		HashTable hashTable = new HashTable();
		
		if (inputFile != null) {
			
			   Scanner sc = null;
			   
			    try {
			        sc = new Scanner(inputFile);
			        
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();  
			    }

			    while (sc.hasNext()) {
			        String s = sc.next();
			        hashTable.add(new Entry(s));   
			    }
			
			return hashTable;
			
		} else {
			
			new Alert(AlertType.INFORMATION, "File not found").showAndWait();
			return null;
			
		}
	}
	
}