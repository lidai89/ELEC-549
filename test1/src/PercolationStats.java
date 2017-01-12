import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class PercolationStats {
	private int N;
	private int T;
	private int[] stopnumber;
	public PercolationStats(int N1, int T1)
	{ stopnumber=new int[N1];
	N=N1;
	T=T1;
		for(int time=1;time<=T;time++)
	{
		Percolation p=new Percolation(N);
	//	int num=20;
	//		Percolation p=new Percolation(num);


//		p.open(0,1);
//		p.open(1,1);
		//open n sites, generate n random numbers
		int site[];
		int n=N*N;
		site=new int[n];
/*	    Random ran = new Random();
	    Set <Integer> set = new HashSet<Integer>();
	    while(set.size()==n?false:true){
	    	int nm = ran.nextInt(n);
	    	set.add(nm);
	    }
	    Iterator<Integer> it = set.iterator();
	    int count = 0;
	    int temp=0;
	    while(it.hasNext()){
	    	temp=it.next(); site[count]=temp;
	    	System.out.println("the"+ ++count +"site =="+temp);
	    }
	    */
		int original[];

		original=new int[n];

		site=new int[n];
		for(int i=0;i<n;i++)
		{original[i]=i;
			
		}
		for(int i=0;i<n;i++)
		{
			int base=(int)((n-i-1)*Math.random());
			site[i]=original[base];
			for(int ii=base;ii<n-i-1;ii++){
				original[ii]=original[ii+1];
			}original[n-1-i]=0;
		}
		
        int a=0;
		while(!p.Percolates())
		{int n1=(int)(site[a]/N);
		int n2=site[a]-N*n1;
		p.open(n1,n2);
		a=a+1;}
		stopnumber[time-1]=a;
		System.out.print("Percolation happens at opensites number exceeds "+a);
		System.out.println("");
//		System.out.print(p.isfull(1,4));
/*		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=N;j++)
			{System.out.print(p.grid[i-1][j-1]);
			System.out.print(" ");
			}
			System.out.println("");
		}
		*/
	//	System.out.print("The percolation status is "+p.Percolates());
	
	}
	}
	public double mean()
	{
		double sum=0;
		for(int i=0;i<T;i++)
		{sum=sum+stopnumber[i];}
		sum=sum/T;
		return sum;
	}
	public double stddev()
	{
		double sum=0;
		for(int i=0;i<T;i++)
		{sum=sum+(stopnumber[i]-mean())*(stopnumber[i]-mean());}
		sum=sum/(T-1);
		sum=Math.sqrt(sum);
		return sum;
	}
	public double confidenceLow()
	{
		double low=0;
		low=mean()-1.96*stddev()/Math.sqrt(T);
		return low;
	}
	public double confidenceHigh()
	{
		double high=0;
		high=mean()+1.96*stddev()/Math.sqrt(T);
		return high;
	}
	public static void main(String args[])
	{   Stopwatch a=new Stopwatch();
		PercolationStats s=new PercolationStats(100,10);
        System.out.print("standard deviation is "+s.stddev()+" ");
        System.out.println("");
        double t=a.elapsedTime();
        System.out.print("running time is "+t);

	}
}
