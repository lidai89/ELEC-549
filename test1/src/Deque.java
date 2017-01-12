/*
 * Copyright @ Dai Li
 * Use a two-direction linked list to implement the Deque
 * 
 */
import java.util.*;

public class Deque<Item> implements Iterable<Item> {
	
	//Node class as the node for the linked list, stores the item and both previous and next node.
	public class Node
	{
		Item word;
		Node previous=this;
		Node next=this;

	}
	private Node head;
	private Node tail;
	private int size;
	
   public Deque()// construct an empty deque// function number 1
   {
	   head=null;
	   tail=null;
	   size=0;
	   
	   
   }
   public boolean isEmpty()         
   {
	   if(size==0)
		   return true;
	   else
		   return false;
   }// is the deque empty?// function number 2
   public int size()                        // return the number of items on the deque// function number 3
   {
	   return size;
   }
   public void addFirst(Item item)  // insert the item at the front// function number 4
   {
   if(item==null)
	   throw new java.lang.NullPointerException();
   else if(isEmpty())
   {
	   head=new Node();
	   
	   head.word=item;
      head.previous=null;
      head.next=null;
      tail=head;
size++;
   }
   else{
	   Node i=new Node();
	   i.word=item;
	   head.previous=i;
	   i.next=head;
	   i.previous=null;
	   head=i;
	   size++;
   }
   }
   public void addLast(Item item)           // insert the item at the end// function number 5
   {
	   if(item==null)
		   throw new java.lang.NullPointerException();
	   else if(isEmpty())
	   {
		   head=new Node();
		   
		   head.word=item;
          head.previous=null;
          head.next=null;
          tail=head;
		   size++;
	   }
	   else{
		   Node i=new Node();;
		   i.word=item;
		   tail.next=i;
		   i.previous=tail;
		   i.next=null;
		   tail=i;
		   size++;
	   }
   }
   public Item removeFirst()                // delete and return the item at the front// function number 6
   {
	   Node i;
	   if(size==0)
		   throw new java.util.NoSuchElementException();
	   else if(size==1)
	   {
		   i=head;
		   size--;
		   head=null;
		   tail=null;
		   return i.word;
	   }
	   
	   else
	   {
		   i=head;
		   head=head.next;
		   head.previous=null;
		   size--;
		   return i.word;
	   }
	   
   }
   public Item removeLast()                 // delete and return the item at the end// function number 7
   {
	   Node i;
   
   if(size==0)
	   throw new java.util.NoSuchElementException();
   else if(size==1)
   {
	   i=head;
	   size--;
	   head=null;
	   tail=null;
	   return i.word;
   }
   
   else
   {
	   i=tail;
	   tail=tail.previous;
	   tail.next=null;
	   size--;
	   return i.word;
   }
   }
   public void printAll()  // print all the Item objects in the Deque using the iterator// function number 8
   {
	   Iterator<Item> it=iterator();
	   System.out.print(head.word);
	   while(it.hasNext())
	   {   
		   Item i=it.next();
		   System.out.print(" "+i);
	
	   }
	   
   }
   public class DequeIterator implements Iterator<Item>{
	   private Node it=head;
		  private int ind=0;
		   public boolean hasNext(){
			   if(it.next==null)return false;
			   else return true;
			   
		   }
		   
		   public Item next(){
			   if(it==null) throw new java.util.NoSuchElementException();
			   else
			   {
				   Node i=it;
				   it=it.next;
				   ind++;
				   return it.word;
				   
			   }
		   }
		   public void remove() {
	           throw new java.lang.UnsupportedOperationException();
	   }
   }
   
   public Iterator<Item> iterator() {   // return an iterator over items in order from front to end
           return new DequeIterator();
   }
   
   public static void main(String[] args)   // unit testing
   {
	
	 

	   int order=0;

       int i=0;
	   String str=new String();
	   String str1=new String();
	   Scanner std=new Scanner(System.in);
        Deque<String> queue=new Deque<String>();
	   while(true)
	   {
		 //  Scanner sc0=new Scanner(System.in);
		 //  order=sc0.nextInt();
		 //  sc0.close();

   
 //     String a=sc.nextLine();
  //    System.out.print(a);
		   str=std.nextLine();
		   str1=""+str.charAt(0);
          order=Integer.parseInt(str1);
          if(order==4||order==5){
		   
           str1=str.substring(2);
           i=0;
           while(i<str1.length())
           {
        	   if((str1.charAt(i))==' ')
        		   break;
        	   else i++;
           }
        	   str1=str1.substring(0,i);
          }



          
           switch(order)
           {
         
           case 2: System.out.print("Queue is Empty?"+queue.isEmpty());break;
           case 3: System.out.print("size is "+queue.size);break;
           case 4: queue.addFirst(str1);break;
           case 5: queue.addLast(str1);break;
           case 6: queue.removeFirst();break;
           case 7: queue.removeLast();break;
           case 8: queue.printAll();break;
           case 1: queue=new Deque<String>();break;
           }
          }
       //   sc.close();
   }
}
