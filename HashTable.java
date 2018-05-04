package hw7;
public class HashTable {
	
	private HashNode[] hashtable;
	public int size;
	public int capacity;
	public double LF;
	
	public HashTable() {
		
		this.hashtable = new HashNode[19];
		this.size = 0;
		this.capacity = 19;
		this.LF = 0;
	}
	
	public void add(String word) {
		
		if (this.LF >= 0.9) {
			createNewTable();
		}
		
		Entry entry = new Entry(word);
		
		int index = Math.abs(entry.hashCode())%capacity;
			
		//System.out.println(index+":"+word);
		
		if (this.hashtable[index] == null) {
			
			//System.out.println("hey");
			this.hashtable[index] = new HashNode(entry);
			this.size++;
			this.LF = size*1.0/capacity;
			
		} else {
			
			//if doesnt contain that word
			if (!this.contains(entry)) {
				
				//System.out.println("hey");
				HashNode newNode = new HashNode(entry);
				newNode.next = this.hashtable[index];
				this.hashtable[index] = newNode;
				this.size++;
				this.LF = size*1.0/capacity;
				
			} else {
				//contains. If contains then dont add and just increment the count.
				
				HashNode curr = this.hashtable[index];
				
				while (curr != null) {
					if (curr.data == entry) {
						curr.data.incrementCount();
						this.size++;
						this.LF = size*1.0/capacity;
						break;
					}
					
					curr = curr.next;
				}
				
			}
			
		}
		
	}
	
	private void createNewTable() {

		//double capacity and then set it to the nearest prime to that num
		this.capacity = capacity*3;
		
		HashNode[] oldHashTable = this.hashtable;
		this.hashtable = new HashNode[capacity];
		
		this.LF = size*1.0/capacity;
		//rehash into new table
		for (int a = 0; a < oldHashTable.length; a++) {
			if (oldHashTable[a]!=null) {
				
				HashNode curr = oldHashTable[a];
				
				while (curr != null) {
					
					this.add(curr.data.getWord());
					curr = curr.next;
					
				}
				//this.add(oldHashTable[a].data.getWord());
			}
		}
		
	}

	public boolean contains(Entry entry) {
		
		//System.out.println("inside contain");
		
		int index = Math.abs(entry.hashCode())%capacity;
		
		///System.out.println(index);
		
		if (this.hashtable[index] == null) {
			return false;
		} else {
			
			HashNode curr = this.hashtable[index];
			
			while (curr != null) {
				
				if (curr.data.getWord().equals(entry.getWord())) {
					return true;
				} 
				
				curr = curr.next;
			}
		}
		
		return false;
		
	}
	
	private class HashNode {
		
		Entry data;
		HashNode next;
		
		public HashNode(Entry data) {
			this.data = data;
			this.next = null;
		}
		
	}

}
