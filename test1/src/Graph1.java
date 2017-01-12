import java.util.*;
import java.io.*;
public class Graph1 {// use 2-d vectors to store adjacency list
	//use a map to store actor names
	Vector<Vector<Integer>> adj;
	Map<String, Integer> map;
	
	//read from file and set up the graph
	public Graph1(String fileName){
		adj=new Vector<Vector<Integer>>();
		map=new HashMap<String, Integer>();
		FileInputStream fis = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null; 
		  int count=0;
		  try {
		   String str = "";
		   String[] str1;
		   String crlf=System.getProperty("line.separator");
		   fis = new FileInputStream(fileName);
		    isr = new InputStreamReader(fis);
		    br = new BufferedReader(isr);
		    int i=0;
		    int j=0;
		 // read each line and get the name strings
		   while ((str = br.readLine()) != null) {
			   str1=str.split("/");
			   Vector<Integer> v1=new Vector<Integer>();
			   for(i=1;i<str1.length;i++)
			   {//put names into the map if not already in
		    if(!map.containsKey(str1[i])){
		    	map.put(str1[i], count);
		    	//System.out.print(str1[i]+crlf);
		    	v1.add(count);
		    	adj.add(v1);
		    	count++;
		    }
			  
			   }
			   
			   
			   for(i=1;i<str1.length;i++)
			   for(j=1;j<str1.length;j++){
				   Vector<Integer> vec=adj.elementAt(map.get(str1[i]));
				   if(!vec.contains(map.get(str1[j]))){
					   adj.elementAt(map.get(str1[i])).add(map.get(str1[j]));
					   }
			   }
				 
			 
		   }
		   
		   
		  } catch (FileNotFoundException e) {
		   System.out.println("No such file");
		  } catch (IOException e) {
		   System.out.println("fail to read the file");
		  } finally {
		   try {
		     br.close();
		     isr.close();
		     fis.close();
		    
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
		  
		  
		  //once more initialize the adjacent matrix
		
	}
	public class Node{
		int value;
		int step;
		
	}
	public int pathLength(String name1, String name2){
		
		int k1=map.get(name1);
		int k2=map.get(name2);
		int stop=0;
		int[] visit=new int[map.size()];
		Queue<Node> queue=new LinkedList<Node>();
	    Node node1=new Node();
	    node1.value=k1;
	    node1.step=0;
	    visit[k1]=0;
		queue.add(node1);
		if(name1==name2){
			queue.remove();
		}
	
		while(!queue.isEmpty()){
			Node node2=queue.poll();
			k1=node2.value;
			stop=node2.step;
			visit[k1]=1;
			if(k1==k2)break;
			
			  Vector<Integer> vn=adj.elementAt(k1);
			
			        for(int j=0;j<vn.size();j++){
			        	if(visit[vn.elementAt(j)]==0){
					Node node3=new Node();
					node3.value=vn.elementAt(j);
					node3.step=stop+1;
					queue.add(node3);
			        	}
			        
			
			}
		}
		return stop;
	}
	public static void main(String[] args){
		
		Graph1 g= new Graph1(args[0]);
		int size=g.adj.size();
		int i=0;
	
		System.out.print("The degree is ");
		System.out.print(g.pathLength("Barker, Geoff","Allen, Karen"));
	}

}
