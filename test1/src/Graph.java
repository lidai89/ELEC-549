import static org.junit.Assert.assertEquals;

import java.util.*;
import java.io.*;
public class Graph {// use 2-d vectors to store adjacency list
	//use a map to store actor names
	ArrayList<Integer[]> adj;
	Map<String, Integer> map;
	
	//read from file and set up the graph
	public Graph(String fileName){
		adj=new ArrayList<Integer[]>();
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
		    int k=0;
		 // read each line and get the name strings
		   while ((str = br.readLine()) != null) {
			   str1=str.split("/");
			   Integer[] v1=new Integer[1];
			   
			   for(i=1;i<str1.length;i++)
			   {//put names into the map if not already in
		    if(!map.containsKey(str1[i])){
		    	map.put(str1[i], count);
		    	//System.out.print(str1[i]+crlf);
		    	v1[0]=count;
		    	adj.add(v1);
		    	count++;
		    }
			  
			   }
			   
			   
			   for(i=1;i<str1.length;i++)
			   for(j=1;j<str1.length;j++){
				   Integer[] vec=adj.get(map.get(str1[i]));
				   Vector<Integer> vec1=new Vector<Integer>();
				   for(k=0;k<vec.length;k++)
					   vec1.add(vec[k]);
				   if(!vec1.contains(map.get(str1[j]))){
					   Integer[] newvec=new Integer[vec.length+1];
					   for(k=0;k<vec.length;k++)
						   newvec[k]=vec[k];
					   newvec[vec.length]=map.get(str1[j]);
					   adj.set(map.get(str1[i]),newvec);
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
			
			Integer[] vn=adj.get(k1);
			
			        for(int j=0;j<vn.length;j++){
			        	if(visit[vn[j]]==0){
					Node node3=new Node();
					node3.value=vn[j];
					node3.step=stop+1;
					queue.add(node3);
			        	}
			        
			
			}
		}
		return stop;
	}
	public static void main(String[] args){
		
		Graph g= new Graph(args[0]);
		int size=g.adj.size();
		int i=0;
	
		System.out.print("The degree is ");
		System.out.print(g.pathLength("Langevin, Sophie", "Trani, Jessica"));
		//assertEquals(1,g.pathLength("Bacon, Kevin", "Bennett, Gregory"));
	}

}
