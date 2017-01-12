/*
 * Copyright@ Dai Li
 * Use a array to implement RandomizedQueue
 */


import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] element;
	private int size;

   public RandomizedQueue()                 // construct an empty randomized queue// function number 1
   {
	   size=0;
	   element =  (Item[]) new Object[10];
   }
   public boolean isEmpty() // is the queue empty?// function number 2
   {
	   if (size==0)
		   return true;
	   else return false;
   }
   public int size()                        // return the number of items on the queue// function number 3
   {
	   return size;
   }
   public void enqueue(Item item)           // add the item// function number 4
   {
       if (item == null)
           throw new java.lang.NullPointerException();
       else
    	  if(size==element.length)
    	  {
    		  resize(2*size);
    		  
    	  }
    	   element[size]=item;
       size++;
   }
   public void resize(int resize)
   {  int i=0;
	   Item[] newelement=(Item[]) new Object[resize];
	   for(i=0;i<size;i++)
		   newelement[i]=element[i];
	   element=newelement;
	   
   }
   //Select a random item from array and fill the position with last element
   public Item dequeue()                    // delete and return a random item// function number 5
   {
	   if (size == 0)
           throw new java.util.NoSuchElementException();
	   
	   else{
		   if(size<=element.length/4&&element.length>10)
			   resize(element.length/2);
    	   int index=(int)(size*Math.random());
    	   Item it=element[index];
    	   element[index]=element[size-1];
    	   element[size-1]=null;
    	   size=size-1;
    	   return it;
       }
   }
   public Item sample()           // return (but do not delete) a random item// function number 6
   {
	   if (size == 0)
           throw new java.util.NoSuchElementException();
	   else{
		   int index=(int)(size*Math.random());
    	   Item it=element[index];
    	   return it;
	   }
		   
   }
	   
   public void printAll()  // print all the Item objects in the Deque using the iterator// function number 7
   {
	   Iterator<Item> itr=iterator();
	   System.out.print(itr.next());
	   while(itr.hasNext())
			   {
		   System.out.print(" "+itr.next());
			   }
   }
	public class RandomizedQueueIterator implements Iterator<Item> {
		private Item it;
		private Item[] ele=element;
		private int num=size;
		public boolean hasNext(){
			if (num>0)
				return true;
			else return false;
		}
		   public Item next(){
			   int ind=(int)(num*Math.random());
			   it=ele[ind];
			   ele[ind]=ele[num-1];
	
			   num=num-1;
			   return it;
		   }
		   public void remove() {
	           throw new java.lang.UnsupportedOperationException();
	   }
	   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
	   return new RandomizedQueueIterator();
   }
   
   public static void main(String[] args) {
   RandomizedQueue<String> queue=new RandomizedQueue<String>();

   int order=0;
   int k=0;
   int i=0;
   String str=new String();
   String str1=new String();
   Scanner std=new Scanner(System.in);

   while(true)
   {
	 //  Scanner sc0=new Scanner(System.in);
	 //  order=sc0.nextInt();
	 //  sc0.close();


//     String a=sc.nextLine();
//    System.out.print(a);
	   str=std.nextLine();
	   str1=""+str.charAt(0);
	   if(str1.charAt(0)>='0'&&str1.charAt(0)<='9')
	   { order=Integer.parseInt(str1);}
      if(order==4){
	   
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
       case 4: queue.enqueue(str1);break;
       case 5: queue.dequeue();break;
       case 6: System.out.print(queue.sample());break;

       case 7: queue.printAll();break;
       case 1: queue=new RandomizedQueue<String>();break;
       }
   //   sc.close();
}
}
}