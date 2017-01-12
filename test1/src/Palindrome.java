/*
 * Copyright@Dai Li
 * use a compare function to compare if A-T C-G pairs match
 * use a Deque to store the input, compare the head and tail characters using removeFirst
 * and removeLast
 */
import java.util.*;
public class Palindrome {
	public static boolean compare(char a, char b){
		boolean c=false;
		switch(a){
		case 'A':if(b=='T') c=true;else c=false;break;
		case 'C':if(b=='G') c=true;else c=false;break;
		case 'T':if(b=='A') c=true;else c=false;break;
		case 'G':if(b=='C') c=true;else c=false;break;
		}
		return c;
	}
	public static void main(String[] args){
		Deque<Character> queue=new Deque<Character>();
	
		 char r='a';
		 String crlf=System.getProperty("line.separator");
		 boolean flag=true;
		 while(r!=crlf.charAt(0)){
			 r=StdIn.readChar();
			 queue.addLast(r);
		 }
		 queue.removeLast();
		 int n=queue.size();
		 int k=1;
		 char head;
		 char tail;
		 if(n==0)System.out.print("True");
		 else{
		while(!queue.isEmpty()){
			if(queue.size()==1){
				flag=false;
				break;
			}
			head=queue.removeFirst();
			tail=queue.removeLast();
			if(!compare(head,tail)){flag=false;}
		}
		if(flag==true){
			System.out.print("True");
		
		System.out.println();
		}
		else{
			System.out.print("False");
		
		System.out.println();
		}
		 }
		 
	}
}
