/*
 * Copyright@Dai Li
 * use a ford-fulkerson max flow algorithm to implement the escape plan
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class EscapePlan {
	private boolean[] marked;     // mark the residual path
    private FlowEdge[] edgeTo;    // edgeTo[v] = last edge on shortest residual s->v path
    private double value;         // max flow value
  
    
    public EscapePlan() {
    	int v;
		int e;
		int source;
		int sink;
		FileInputStream fis = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null; 
		  String str="";
		  String[] strtemp;
		 try{
			  fis = new FileInputStream("input.txt");
			    isr = new InputStreamReader(fis);
			    br = new BufferedReader(isr);
			    str=br.readLine();
			    strtemp=str.split(" ");
			    v=Integer.parseInt(strtemp[0])+2;
			    e=Integer.parseInt(strtemp[1]);
			    source=Integer.parseInt(strtemp[2]);
			    sink=Integer.parseInt(strtemp[3]);
			    e=e+source+sink;
			    
			    FlowNetwork G=new FlowNetwork(v);
			    
			    //read sources
			    str=br.readLine();
			    strtemp=str.split(" ");
			    if(source>0){
			    for(int i=0;i<source;i++){
			    	G.addEdge(new FlowEdge(v-2,Integer.parseInt(strtemp[i]),1));
			    }
			    }
			    //read sinks
			    str=br.readLine();
			    strtemp=str.split(" ");
			    if(sink>0){
			    for(int i=0;i<sink;i++){
			    	G.addEdge(new FlowEdge(Integer.parseInt(strtemp[i]),v-1,source));
			    }
			    }
			    for(int i=0;i<e-sink-source;i++){
			    	str=br.readLine();
				    strtemp=str.split(" ");
				    G.addEdge(new FlowEdge(Integer.parseInt(strtemp[0]),Integer.parseInt(strtemp[1]),1));
			    }
			    
			    int s=v-2;
			    int t=v-1;
			    validate(s, G.V());
		        validate(t, G.V());
		        if (s == t)               throw new IllegalArgumentException("Source equals sink");
		        

		        
		        value = excess(G, t);
		        while (AugmentingPath(G, s, t)) {

		            // compute bottleneck capacity
		            double bottle = 2;
		            for (int vv = t; vv != s; vv = edgeTo[vv].other(vv)) {
		                bottle = Math.min(bottle, edgeTo[vv].residualCapacityTo(vv));
		            }

		            // augment flow
		            for (int vv = t; vv != s; vv = edgeTo[vv].other(vv)) {
		                edgeTo[vv].addResidualFlowTo(vv, bottle); 
		            }

		            value += bottle;
		        }
		        
		        // print the output
                if(Math.abs(value-source)<0.1&&source>0&&sink>0){
                	System.out.println("YES");
                	
                }
                else System.out.println("NO");
			 
			    }
		 catch (FileNotFoundException k) {
			   System.out.println("No such file");
			  } catch (IOException k) {
			   System.out.println("fail to read the file");
			  }
		 finally {
			   try {
			     br.close();
			     isr.close();
			     fis.close();
			    
			   } catch (IOException k) {
			    k.printStackTrace();
			   }
		 
	}
       
        
    }

  
    public double value()  {
        return value;
    }

    // is v in the s side of the min s-t cut?
  

    // throw an exception if v is outside prescibed range
    private void validate(int v, int V)  {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }


    private boolean AugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        // breadth-first search
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty() && !marked[t]) {
            int v = queue.dequeue();

            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);

                // if residual capacity from v to w
                if (e.residualCapacityTo(w) > 0) {
                    if (!marked[w]) {
                        edgeTo[w] = e;
                        marked[w] = true;
                        queue.enqueue(w);
                    }
                }
            }
        }

        // is there an augmenting path?
        return marked[t];
    }
    // return excess flow at vertex v
    private double excess(FlowNetwork G, int v) {
        double excess = 0.0;
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from()) excess -= e.flow();
            else               excess += e.flow();
        }
        return excess;
    }

 
    

    


	public static void main(String[] args){
		EscapePlan ep=new EscapePlan();
}
}