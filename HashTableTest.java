package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
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

		System.out.println("HashTable: "+Duration.between(start, end).toMillis()/1000+" Seconds");
		
		System.out.println("HashTable size: "+hashTable.size());
		
		File outputFile = new File("HashTable.txt");
		
		PrintWriter pw = new PrintWriter(outputFile);
		
		pw.print(hashTable.toString());
		
		pw.close();
		
		//----------------------------
		
		Instant start2 = Instant.now();

		SimpleList simpleList = HashTableTest.generateSimpleList();
		
		Instant end2 = Instant.now();

		System.out.println("SimpleList: "+Duration.between(start2, end2).toMillis()/1000+" Seconds");
		
		System.out.println("SimpleList size: "+ getSimpleListWordCount(simpleList));
		
		File outputFile2 = new File("SimpleList.txt");
		
		PrintWriter pw2 = new PrintWriter(outputFile2);
		
		Entry[] entries = this.sortSimpleList(simpleList);
		
        String formatter = "%-20s%-1d";
		
		for (int a = 0; a < entries.length; a++ ) {
			pw2.println(String.format(formatter, entries[a].getWord(), entries[a].getCount()));
		}
		
		pw2.print(String.format(formatter, "Word Count:", getSimpleListWordCount(simpleList)));
		
		pw2.close();
		
		System.exit(0);
		
	}
	
	private int getSimpleListWordCount(SimpleList simpleList) {
	
		int count = 0;
		
		for (int a = 0; a < simpleList.size(); a++) {
			count += simpleList.getEntry(a).getCount();
		}
		return count;
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
	
	private Entry[] sortSimpleList(SimpleList simpleList) {
		
		Entry[] entries = new Entry[simpleList.size()];
		
		for (int a = 0; a < simpleList.size(); a++) {
			entries[a] = simpleList.getEntry(a);
		}
		
		Arrays.sort(entries, new Comparator<Entry>() {

			  @Override
		        public int compare(Entry o1, Entry o2) {
		            if (o1 == null && o2 == null) {
		                return 0;
		            }
		            if (o1 == null) {
		                return 1;
		            }
		            if (o2 == null) {
		                return -1;
		            }
		            return o1.getWord().compareTo(o2.getWord());
		        }
			  
		});
		
		return entries;
		
		
	}
	
}