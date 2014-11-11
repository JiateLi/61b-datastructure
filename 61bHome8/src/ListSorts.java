/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
	int num = q.size();
	LinkedQueue queofques = new LinkedQueue();
	for(int i = 1; i < num; i++){
		Object item = q.nth(i);
		LinkedQueue curque = new LinkedQueue();
		curque.enqueue(item);
		queofques.enqueue(curque);
	}
	while (!q.isEmpty()){
		try{
			q.dequeue();
		}catch (QueueEmptyException e1){
			System.out.println("QueueEmptyException");
		}
	}
    return queofques;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
	LinkedQueue mergeque = new LinkedQueue();
	if (q1.isEmpty() && q2.isEmpty()){
		return mergeque;
	}
	else if (q1.isEmpty()){
		mergeque = q2;
		while (!q2.isEmpty()){
			try{
				q2.dequeue();
			}catch (QueueEmptyException e1){
				System.out.println("QueueEmptyException");
			}
		}
		return mergeque;
	}
	else if (q2.isEmpty()){
		mergeque = q1;
		while (!q1.isEmpty()){
			try{
				q1.dequeue();
			}catch (QueueEmptyException e2){
				System.out.println("QueueEmptyException");
			}
		}
		return mergeque;
	}
	else{
		int j = 1;
		for (int i = 1; i <= q1.size(); i++){
			while(((Comparable) q2.nth(j)).compareTo((Comparable) q1.nth(i)) < 0 && j <= q2.size()){
				mergeque.enqueue(q2.nth(j));
				j++;
			}
			mergeque.enqueue(q1.nth(i));
		}
		while (!q1.isEmpty()){
			try{
				q1.dequeue();
			}catch (QueueEmptyException e3){
				System.out.println("QueueEmptyException");
			}
		}
		while (!q2.isEmpty()){
			try{
				q2.dequeue();
			}catch (QueueEmptyException e1){
				System.out.println("QueueEmptyException");
			}
		}
		return mergeque;
	}
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
	  while (!qIn.isEmpty()){
		  try{
			  Comparable curitem =  (Comparable) qIn.dequeue();
			  if (curitem.compareTo(pivot) < 0){
				  qSmall.enqueue(curitem);
			  }
			  else if(curitem.compareTo(pivot) > 0){
				  qLarge.enqueue(curitem);
			  }
			  else{
				  qEquals.enqueue(curitem);
			  }
		  }
		  catch(QueueEmptyException e1){
				System.out.println("QueueEmptyException");
		  }
	  }  
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
	LinkedQueue curq = makeQueueOfQueues(q);
	while(curq.size() > 1)
	try{
		Object cur_a = curq.dequeue();
		Object cur_b = curq.dequeue();
		LinkedQueue mque = mergeSortedQueues((LinkedQueue)cur_a, (LinkedQueue)cur_b);
		curq.enqueue(mque);
	}catch (QueueEmptyException e1){
		System.out.println("QueueEmptyException");
	}
	q.append(curq);
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
	 int ram = new Integer((int) (q.size() * Math.random()));
	 Comparable pivot = (Comparable)q.nth(ram); 
     LinkedQueue qSmall = new LinkedQueue();
     LinkedQueue qEquals = new LinkedQueue();
     LinkedQueue qLarge = new LinkedQueue();
     partition(q, pivot, qSmall, qEquals, qLarge);
     quickSort(qSmall);
     quickSort(qLarge);
     q.append(qSmall);
     q.append(qEquals);
     q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    */
  }

}