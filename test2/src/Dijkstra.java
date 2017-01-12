/*
 * CopyRight@ Dai Li
 * use a priority queue to process the dijkstra algorithm process
 * adj list to store weighted edges
 */
import java.io.*;
import java.util.*;
import java.text.*;
public class Dijkstra {

PriorityQueue<Node> pq;
vertex[] v;
ArrayList<ArrayList<Node>> adj;
ArrayList<ArrayList<Integer>> adjnull;

double[] pathlength;
int in;
int out;

public class Node{
	int number;
	double distance;
public	Node(int number,double distance){
		this.number=number;
		this.distance=distance;
	}
Comparator<Node> order=new Comparator<Node>(){
	public int compare(Node n1, Node n2){
		double d1=n1.distance;
		double d2=n2.distance;
		if(d2>d1){
			return -1;
		}
		else if(d1>d2){
			return 1;
		}
		else{
			return 0;
		}
	}
		
	};
}
Comparator<Node> order=new Comparator<Node>(){
	public int compare(Node n1, Node n2){
		double d1=n1.distance;
		double d2=n2.distance;
		if(d2>d1){
			return -1;
		}
		else if(d1>d2){
			return 1;
		}
		else{
			return 0;
		}
	}
		
	};
public class vertex{
	int index;
	int x;
	int y;
}
public Dijkstra(String filename){
	//read in data line by line
	FileInputStream fis = null;
	  InputStreamReader isr = null;
	  BufferedReader br = null; 
	  String str="";
	  String[] strtemp;
	  try{
	  fis = new FileInputStream(filename);
	    isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	    str=br.readLine();
	    strtemp=str.split(" ");
	    int vn=Integer.parseInt(strtemp[0]);
	    int e=Integer.parseInt(strtemp[1]);

	
    v=new vertex[vn];
   
    pathlength=new double[vn];

	int k;
	int tempx;
	int tempy;
	double dis;
	int v1,v2;
    adj=new ArrayList<ArrayList<Node>>();
    adjnull=new ArrayList<ArrayList<Integer>>();
//read vertex
	for(k=0;k<vn;k++){
		str=br.readLine();
		strtemp=str.split(" ");
		if(strtemp[0]!=(k+"")){
			//System.out.print("Error");
			}
			//System.out.print(strtemp[0]+" ");
		ArrayList<Integer> pi=new ArrayList<Integer>();
		for(int k1=0;k1<strtemp.length;k1++){
			if(strtemp[k1].length()>=1){
			int tttt=Integer.parseInt(strtemp[k1].charAt(0)+"");
			if(tttt<=9&&tttt>=0)
			{
				//System.out.print("yes");
				pi.add(Integer.parseInt(strtemp[k1]));
				//System.out.print(Integer.parseInt(strtemp[k1]));
			}
		}
		}
		tempx=pi.get(1);
		
		tempy=pi.get(2);
	//	System.out.print(tempy);
		vertex vnew=new vertex();
		vnew.index=k;
		vnew.x=tempx;
		vnew.y=tempy;
		v[k]=vnew;
		Node n0=new Node(k,0);
		n0.number=k;
		n0.distance=0;
		ArrayList<Node> a1=new ArrayList<Node>();
		a1.add(n0);
		adj.add(a1);
		ArrayList<Integer> a2=new ArrayList<Integer>();
		a2.add(k);
		adjnull.add(a2);
		pathlength[k]=655353.53535;
		
	
	
		}
        pathlength[in]=0;
        //read edges
		for(k=0;k<e;k++){
			str=br.readLine();
		    strtemp=str.split(" ");
		    ArrayList<Integer> pi=new ArrayList<Integer>();
			for(int k1=0;k1<strtemp.length;k1++){
				if(strtemp[k1].length()>=1){
				int tttt=Integer.parseInt(strtemp[k1].charAt(0)+"");
				if(tttt<=9&&tttt>=0)
				{
					//System.out.print("yes");
					pi.add(Integer.parseInt(strtemp[k1]));
					//System.out.print(Integer.parseInt(strtemp[k1]));
				}
			}
			}
		    v1=pi.get(0);
		    v2=pi.get(1);
		    dis=Math.pow(Math.pow((v[v1].x-v[v2].x),2)+Math.pow((v[v1].y-v[v2].y),2),0.5);
		   
			Node nn1;
			Node nn2;
			nn1=new Node(v2,dis);
			nn2=new Node(v1,dis);
            nn1.number=v1;
            nn2.number=v2;
            nn1.distance=dis;
            nn2.distance=dis;
            adj.get(v1).add(nn2);
            adj.get(v2).add(nn1);
            adjnull.get(v1).add(v2);
            adjnull.get(v2).add(v1);
		    
		}
	//	System.out.print("number of edges: "+e);
	//	System.out.println();
		str=br.readLine();
		
		
		//read multiple sink and inflow
		while(str.length()!=0){
		//dijkstra algorithm	
			int[] visit=new int[vn];
		    ArrayList<ArrayList<Integer>> path;
		    path=new ArrayList<ArrayList<Integer>>();
			for(k=0;k<vn;k++){
				pathlength[k]=65535;
				ArrayList<Integer> at=new ArrayList<Integer>();
				path.add(at);
			}
	    strtemp=str.split(" ");
	    ArrayList<Integer> pi=new ArrayList<Integer>();
		for(int k1=0;k1<strtemp.length;k1++){
			if(strtemp[k1].length()>=1){
			int tttt=Integer.parseInt(strtemp[k1].charAt(0)+"");
			if(tttt<=9&&tttt>=0)
			{
				//System.out.print("yes");
				pi.add(Integer.parseInt(strtemp[k1]));
				//System.out.print(Integer.parseInt(strtemp[k1]));
			}
		}
		}
	    in=pi.get(0);
	    out=pi.get(1);
	//    pq=new PriorityQueue<Node>(11,order);
	    for(k=0;k<vn;k++){
	    	Node node=new Node(k,655456.54);
	    //	node.number=k;
	    //	node.distance=-1;
	    	if(adjnull.get(in).contains(k)){
	    		node.distance=Math.pow(Math.pow((v[k].x-v[in].x),2)+Math.pow((v[k].y-v[in].y),2),0.5);
	    		pathlength[k]=Math.pow(Math.pow((v[k].x-v[in].x),2)+Math.pow((v[k].y-v[in].y),2),0.5);
	    	    
	    	    path.get(k).add(in);
	    	    
	    	}
	    	//pq.add(node);
	    }
	   ArrayList<Integer> list=new ArrayList<Integer>();
		int flag=-1;
		//while(!pq.isEmpty()){
		while(flag!=out){
			//Node ntest=pq.poll();
			int smallest=-1;
			double shortest=65535;
	        for(int i=0;i<vn;i++){
	        	if(visit[i]==0&&pathlength[i]<shortest){
	        		smallest=i;
	        		shortest=pathlength[i];
	        	}
	        }
			visit[smallest]=1;
			flag=smallest;
			//flag=ntest.number;
			list.add(flag);
		//	int size=pq.size();
			//PriorityQueue<Node> q1=new PriorityQueue<Node>(pq);
			//pq.clear();
			for(k=0;k<vn;k++){
				//int a=q1.poll().number;
				double l=Math.pow(Math.pow((v[flag].x-v[k].x),2)+Math.pow((v[flag].y-v[k].y),2),0.5);
				if(((pathlength[flag]+l)<pathlength[k])&&adjnull.get(flag).contains(k)){
					pathlength[k]=pathlength[flag]+l;
					ArrayList<Integer> np=new ArrayList<Integer>(path.get(flag));
					path.set(k,np);
					path.get(k).add(flag);
				}
				//Node nnode=new Node(k,pathlength[a]);
				//nnode.number=a;
				//nnode.distance=pathlength[a];
				//pq.add(nnode);
			}
		}
		int s=path.get(out).size();
		
	
	DecimalFormat df = new DecimalFormat(".00");
	System.out.print(df.format(pathlength[out]));
	System.out.println();
	System.out.print("["+list.get(0));
	for(k=1;k<s;k++){
		System.out.print(","+path.get(out).get(k));
	}
	System.out.print(","+out+"]");
	
	
str=br.readLine();
if(str==null||str.length()==0)break;
System.out.println();
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
		
	
}
	  
public static void main(String[] args){
	Dijkstra dk=new Dijkstra("usa.txt");
	
}
}
