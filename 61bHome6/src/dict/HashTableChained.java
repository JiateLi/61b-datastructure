/* HashTableChained.java */

package dict;


/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	list.DList[] bucket;
	int sizeBucket;//number of entry
	int numBucket;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	   sizeBucket = 0;
	   boolean flag = true;
	   for(int i = sizeEstimate; i < 2*sizeEstimate; i++){
		   for(int j = 2; j < sizeEstimate; j++){
			   if(i % j == 0){
				   flag = false;
			   }
		   }
		   if (flag == true){
			   numBucket = i;
			   break;
		   }
	   }
	   bucket = new list.DList[numBucket];
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  sizeBucket = 0;
	  numBucket = 97;
	  bucket = new list.DList[numBucket];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	  
    return ((code % 16908799) % numBucket);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return sizeBucket;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return (sizeBucket == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  Entry ent = new Entry();
	  ent.key = key;
	  ent.value = value;
	  int bucketnum = this.compFunction(key.hashCode());
	  bucket[bucketnum].insertFront(ent);
	  sizeBucket = sizeBucket + 1;
    return ent;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
	  int bucketnum = this.compFunction(key.hashCode());
	  list.DListNode findnode = bucket[bucketnum].front();
	  Entry ent = new Entry();
	  while(findnode.item != null){
		  ent = (Entry)findnode.item;
		  if (ent.key() == key){
			  return ent;
		}
		  findnode = bucket[bucketnum].next(findnode);
	  }
    // Replace the following line with your solution.
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
	  int bucketnum = this.compFunction(key.hashCode());
	  list.DListNode findnode = bucket[bucketnum].front();
	  Entry ent = new Entry();
	  while(findnode.item != null){
		  ent = (Entry)findnode.item;
		  if (ent.key() == key){
			  bucket[bucketnum].remove(findnode);
			  sizeBucket = sizeBucket -1;
			  return ent;
		}
		  findnode = bucket[bucketnum].next(findnode);
	  }
    // Replace the following line with your solution.
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	  for(int i =0; i < numBucket; i++){
		  list.DListNode findnode = bucket[i].front();
		  while(findnode.item != null){
			  bucket[i].remove(findnode);
			  findnode = bucket[i].front();
		  }
	  }
  }

}