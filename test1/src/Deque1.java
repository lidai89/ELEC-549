
import java.util.Iterator;
import java.util.Scanner;


public class Deque1<Item> implements Iterable<Item> {
        
        private class Node {
                Item item;
                Node next = null;
                Node prev = null;
        }
        
        private class DequeueIterator implements Iterator<Item> {
                private Node current = listFront;
                
                public boolean hasNext() {
                        return current != null;
                }
                
                public Item next() {
                        if (current == null)
                                throw new java.util.NoSuchElementException();
                        
                        Item item = current.item;
                        current = current.next;
                        return item;
                }
                
                public void remove() {
                        throw new java.lang.UnsupportedOperationException();
                }
        }
        
        private Node listFront;
        private Node listEnd;
        private int size;
        
        public Deque1() {                    // construct an empty deque
                listFront = null;
                listEnd = null;
                size = 0;
        }
        
        public boolean isEmpty() {          // is the deque empty?
                return listFront == null;
        }
        
        public int size() {                 // return the number of items on the deque
                return size;
        }
        
        public void addFirst(Item item) {    // insert the item at the front
                if (item == null)
                        throw new java.lang.NullPointerException();

                if (isEmpty()) {
                        listFront = new Node();
                        listEnd = listFront;
                        listFront.item = item;
                } else {
                        Node newFront = new Node();
                        newFront.item = item;
                        newFront.next = listFront;
                        listFront.prev = newFront;
                        listFront = listFront.prev;
                }
                
                size++;
        }
        
        public void addLast(Item item) {     // insert the item at the end
                if (item == null)
                        throw new java.lang.NullPointerException();
                
                if (isEmpty()) {
                        listFront = new Node();
                        listEnd = listFront;
                        listFront.item = item;
                } else {
                        Node newEnd = new Node();
                        newEnd.item = item;
                        listEnd.next = newEnd;
                        newEnd.prev = listEnd;
                        listEnd = newEnd;
                }
                
                size++;
        }
        
        public Item removeFirst() {         // delete and return the item at the front
                if (isEmpty())
                        throw new java.util.NoSuchElementException();
                
                Item returnItem = listFront.item;
                listFront = listFront.next;
                if (listFront != null) {
                        listFront.prev.next = null;
                        listFront.prev = null;
                } else
                        listEnd = null;
                
                size--;
                
                return returnItem;
        }
        
        public Item removeLast() {           // delete and return the item at the end
                if (isEmpty())
                        throw new java.util.NoSuchElementException();
                
                Item returnItem = listEnd.item;
                
                if (size == 1) {
                        listFront = null;
                        listEnd = null;
                }
                else {
                        listEnd = listEnd.prev;
                        listEnd.next.prev = null;
                        listEnd.next = null;
                }
                
                size--;
                
                return returnItem;
        }
        
        public Iterator<Item> iterator() {   // return an iterator over items in order from front to end
                return new DequeueIterator();
        }
        public static void main(String[] args) {
        	   
        	
        }
}