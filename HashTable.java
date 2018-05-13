package hw7;

import java.util.Arrays;
import java.util.Comparator;

public class HashTable {

	private class HashNode implements Comparable<HashNode> {

		Entry entry;
		HashNode next;
		
		public HashNode(Entry entry) {
			this.entry = entry;
		}

		@Override
		public int compareTo(HashNode o) {
			return this.entry.getWord().toLowerCase().compareTo(o.entry.getWord().toLowerCase());
		}
		
	}
	
	private int size, capacity;
	private HashNode[] hashTable;
	private double maxLoadFactor;
	
	public HashTable() {
		this.hashTable = new HashNode[16];
		this.size = 0;
		this.capacity = 16;
		this.maxLoadFactor = 0.75;
	}
	
	public void add(Entry e) {
		
		if (loadFactor() > maxLoadFactor)
				resize(capacity*2);
		
		HashNode newNode = new HashNode(e);
		int index = e.hashCode();
		index = index & 0xFFFFFFF;
		index = index & (capacity-1);
		
		if (!contain(newNode, index)) {
			newNode.next = hashTable[index];
			hashTable[index] = newNode;
			size++;
		}
		
		
	}

	private void resize(int i) {
		
		this.capacity = i;
		
		HashNode[] oldHashTable = hashTable;
		
		hashTable = new HashNode[i];
		
		this.size = 0; 
		
		for (int a = 0; a < oldHashTable.length; a++) {
			
			if (oldHashTable[a] == null) {
				continue;
			} else {
				
				HashNode curr  = oldHashTable[a];
				
				while (curr!=null) {
					
					if (curr.entry.getCount() > 1) {
						size += curr.entry.getCount() - 1;
					}
					
					this.add(curr.entry);
					curr = curr.next;
				}
				
				continue;
				
			}
			
		}

	}

	private double loadFactor() {
		return size*1.0/capacity;
	}
	
	public boolean contain(HashNode hashNode, int index) {
		
		if (hashTable[index] != null) {
			
			HashNode curr = hashTable[index];
			
			while (curr!=null) {
				if (curr.compareTo(hashNode) == 0) {
					curr.entry.incrementCount();
					size++;
					return true;
				}
				curr = curr.next;
			}
		}
		
		return false;
		
	}
	
	
	public int size() {
		return size;
	}	
	
	public String toString() {
		
		this.sort();
		
		int count = 0;
		
		StringBuilder sb = new StringBuilder("HashTable: \n");
		
		for (int a = 0; a < capacity; a++) {
			if (hashTable[a]!=null) {
				
				HashNode curr = hashTable[a];
				
				while (curr != null) {
					sb.append(curr.entry.getWord()+":"+curr.entry.getCount()+"\n");
					count += curr.entry.getCount();
					curr = curr.next;
				}
			}
		}
		
		sb.append("count: "+count);
		return sb.toString();
	}
	
	private void sort() {
		
		  Arrays.sort(hashTable, new Comparator<HashNode>() {

			  @Override
		        public int compare(HashNode o1, HashNode o2) {
		            if (o1 == null && o2 == null) {
		                return 0;
		            }
		            if (o1 == null) {
		                return 1;
		            }
		            if (o2 == null) {
		                return -1;
		            }
		            return o1.compareTo(o2);
		        }
			  
		  });
	}

}