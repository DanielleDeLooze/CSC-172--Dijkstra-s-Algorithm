
public class HashTable {
	protected int capacity;
	protected int items;
	protected Vertex[] table;
	protected double loadfactor;
	protected Vertex[] temp;
	
	
	HashTable(int capacity){
		this.capacity = capacity;
		items = 0;
		table = new Vertex[capacity];
		loadfactor = items / capacity;
	}
	
	public static int hash(String key, int tableSize){ //method to generate a hashvalue for each String. Takes the sum of all char in the string and returns the sum mod tableSize
		int hashVal = 0;
		for(int i = 0; i < key.length(); i++){
			hashVal += key.charAt(i);
		}
		
		return hashVal % tableSize;
	}//end method
	
	public void insert(Vertex vertex){
		loadfactor = items / capacity;
		
		if(loadfactor > .75){ 
			temp = table;
			capacity = capacity * 2;
			table = new Vertex[capacity];
			items = 0;
			rehash(temp);
			
		}
		if(!check(vertex)){
			/*
			System.out.println("The Name "+word+" is already in table"); commented out because it was printing too much while testing Lorem
			*/
		}
		else{
			int hashVal = hash(vertex.name, capacity); //obtain initial hashvalue for word
			if(table[hashVal] == null){ //if there is nothing in its spot, insert word at hashvalue
				table[hashVal] = vertex;
				items++;
			}
			else{
			
				while(table[hashVal] != null){ //if something is in its spot, add one to hashvalue until empty spot is found
					if(hashVal != capacity -1){
						hashVal++;
					}
					else{
						hashVal = 0;
					}
				}
				table[hashVal] = vertex;
				items++;
			}
		}
	}//end insert method
	

	
	public void print(){
		for(Vertex element: table){
			if(element != null){
				System.out.println(element.name+ " ");
			}
		}
	}//end print method
	
	public boolean check(Vertex vertex){
		for(int i = 0; i < table.length ; i++){
			if(table[i] == null){
			
			}
			else if (table[i].equals(vertex.name)){
				return false;
			}
		}
		
		return true;
	}//end check method
	
	public void rehash(Vertex[] temp){
		for(int i = 0; i < temp.length; i++){
			if(temp[i] != null){
				this.insert(temp[i]);
			}
		}
	}
	
	public int getCapacity(){
		return capacity;
	}//end get capacity method
	
	public int getItemNum(){
		return items;
	}// end get items
	
	public double getLoad(){
		return loadfactor;
	}//end get loadfactor
	
}
