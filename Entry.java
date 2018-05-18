package hw7;

/**
* Created by Keenan.
*
* YOU ARE NOT ALLOWED TO ALTER THIS CLASS other than adding implementation for the hashCode() method.
* If you wish to change something that you feel would increase performance, you must ask me first.
*/
public class Entry {

   //The word to store in the Entry, this will be a key value for your hash table.
   private String word;

   //The count of how many times the word appears.
   private int count;

   /**
    * Constructor that creates an {@code Entry} object given a word.
    *
    * @param word The word to set in the {@code Entry}.
    */
   public Entry(String word) {
       this.word = word;
       this.count = 1;
   }

   /**
    * Returns the word of this {@code Entry}.
    *
    * @return The word of this {@code Entry}.
    */
   public String getWord() {
       return this.word;
   }

   /**
    * Returns the count of how many times this word appears in the document.
    *
    * @return the word count.
    */
   public int getCount() {
       return this.count;
   }

   /**
    * Increases the count of the word in this {@code Entry} by one.
    *
    */
   public void incrementCount() {
       this.count++;
   }

   @Override
   public String toString() {
       String result = "";

       result += "Word:\t" + this.word + "\n" +
               "Count:\t" + this.count;

       return result;
   }

   @Override
   public int hashCode() {
       
	   word = word.toLowerCase();
	   
	   int hash = 0;
	   
	   for (int a = 0; a < word.length(); a++) {
		   hash = hash * 31 + word.charAt(a);
	   }
	   
	   return hash;
	   
   }
}