/*
 * CopyRight@ Dai Li Class Subset
 * Use a RandomizedQueue to store and return k random strings from N inputs
 */
import java.util.*;
public class Subset {
	  public static void main(String[] args){
		  
		  //k from commandline
		  int k=Integer.parseInt(args[0]);
		  String crlf=System.getProperty("line.separator");
		  RandomizedQueue<String> queue=new RandomizedQueue<String>();
		  System.out.print("Please enter the string sequence");
		  System.out.println();
		  
		  //Enque the Randomized Queue
		  String a=StdIn.readString();
		  queue.enqueue(a);
		  while(StdIn.readChar()!=crlf.charAt(0))
		  { a=StdIn.readString();

		  queue.enqueue(a);
	  }
		  
		  //Deque the randomizedqueue
		 int i=0;
		 for(i=0;i<k;i++)
		 {
			 System.out.print(queue.dequeue());
			 System.out.println();
		 }
		 /*Another way is to make the randomizedqueue have a limited size of k, deque
		 then enqueue each time there is a new input*/
/*		 
		 for(i=0;i<k;i++)
		 {
			 String a=StdIn.readString();
			  queue.enqueue(a); 
		 }
		 while(StdIn.readChar()!=crlf.charAt(0))
		  { a=StdIn.readString();

		  queue.enqueue(a);
	  }
		  
		  //Deque the randomizedqueue
		  int i=0;
		 for(i=0;i<k;i++)
		 {
			 System.out.print(queue.dequeue());
			 System.out.println();
		 }
		 */
}
}