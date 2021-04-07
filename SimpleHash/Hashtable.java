import java.util.HashMap;

/*  Linear Probing Hash Table */ 
public class Hashtable {
	private int currentSize, maxSize;       
  
   public Pair[] map = null;	// stores hash table elements
 
    // Constructor 
    public Hashtable(int capacity) {
        currentSize = 0;
        maxSize = capacity;
    	map = new Pair[maxSize];
		for(int i = 0; i < maxSize ; i++)
			map[i]=null;
    }  
 
 
    // Function to check if hash table is empty 
    public boolean isEmpty() 
    {
        return getSize() == 0;
    }
    
    // Function to get size of hash table 
    public int getSize() 
    {
        return currentSize;
    }
 

    // Function to check if hash table is full 
    public boolean isFull() 
    {
        return currentSize == maxSize;
    }
 

 
    // Function to check if hash table contains a key
    public boolean contains(String key) 
    {
        return get(key) !=  null;
    }
 
    // Function to get hash-code/hash-value for a given key 
    public int hash(String key) 
    {
        return Math.abs(key.hashCode()) % maxSize;
    }    
 
    // Function to get value for a given key 
    public String get(String key) 
    {
      int index = hash(key);
      
      if (index <= maxSize && map[index] != null) {
        if(map[index].getKey().equals(key)) {
          return map[index].getValue();
          
        }
        
      }
      
      
      int maxIndex = maxSize - 1;
      int i = index + 1;
      
      while (true) {
        if (i >= maxIndex) {
          i = 0;
          continue;
          
        }
        
        if (i == index) {
          return null;
          
        }
        
        if (map[i] != null && map[i].getKey().equals(key)) {
          return map[i].getValue();
          
        }
        
        i++;
        
      }
      
    }

    
    // Function to insert key-value pair 
    public void put(String key, String val)  
    {                
      if (isFull()) {
        rehash();
        
      }
      
      Pair insert = new Pair(key,val);
      
      int index = hash(key);
      
      if (map[index] == null) {
        map[index] = insert;
        currentSize++;
        
      } else {
        int maxIndex = maxSize - 1;
        int i = index + 1;
        
        while (true) {
          if (i >= maxIndex) {
            i = 0;
            continue;
            
          }
          
          if (map[i] == null) {
            map[i] = insert;
            currentSize++;
            break;
            
          }
          
          i++;
          
        }
        
      }

    }
    
    
    /// Function to rehash when the table is full
    public void rehash()  
    {   
    	// Hint: 1-backup the reference to the old hash map 
    	//      2-create a new map twice the old size
    	//      3-hash all elements from the old hash map to new hash map
      Hashtable old = this;
      this.map = new Pair[maxSize*2];
      this.maxSize = maxSize * 2;
      this.currentSize = 0;
      
      for (Pair p : old.map) {
        if (p != null) {
          this.put(p.getKey(), p.getValue());
          
        }
        
      }
      
    }
     
 
    // Function to print HashTable 
    public void printHashTable()
    {
        System.out.println("\nHash Table: Key, Value ");
        for (int i = 0; i < maxSize; i++)
            if (map[i] != null)
            	System.out.println(map[i].getKey()+", "+map[i].getValue());
        System.out.println();
    }   
}

class Pair{

	private String key;
	private String value;

	public Pair(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

}


