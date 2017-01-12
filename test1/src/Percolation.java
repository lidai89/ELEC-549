//Copyright@DaiLi NetId:dl37
//This is the basic class for percolation
import java.util.*;
//the class percolation contains a 2-dimension array and a weightedquickunion tree to describe its connectivity
//the open status is represented by '0' and closed status is represented by '1'
public class Percolation {
	public int[][] grid;
	public WeightedQuickUnionUF uf;
	public Percolation(int N){
		grid=new int[N][N];
		for(int i=1;i<=N;i++)
			for(int j=1;j<=N;j++)
				grid[i-1][j-1]=1;
		uf=new WeightedQuickUnionUF(N*N);
	}
	//open site (i,j) and connect it to nearby open sites if any
public void open(int i, int j){
	int N=grid.length;
	grid[i][j]=0;
	if(i>0)
	{
		if(grid[i-1][j]==0)
		{int p=i*N+j;
		int q=(i-1)*N+j;
			uf.union(p, q);
		}
	}
	if(i<N-1)
	{
		if(grid[i+1][j]==0)
		{int p=i*N+j;
		int q=(i+1)*N+j;
			uf.union(p, q);
		}
	}
	if(j>0)
	{
		if(grid[i][j-1]==0)
		{int p=i*N+j;
		int q=i*N+j-1;
			uf.union(p, q);
		}
	}
	if(j<N-1)
	{
		if(grid[i][j+1]==0)
		{int p=i*N+j;
		int q=i*N+j+1;
			uf.union(p, q);
		}
	}
}
public boolean isopen(int i, int j){
	if (grid[i][j]==0)
		return true;
	else return false;
	
}
//If a site is connected to a 1st-row site, then it is full.
public boolean isfull(int i,int j){
	int N=grid.length;
	int num=i*N+j;
	int p=uf.find(num);
	int flag=0;
	for (int c=0;c<N;c++){
	if (uf.connected(p,c))
		flag=1;
	
	}
	if(flag==1)
		return true;
	else return false;
}
//A graph percolates if a bottom-row site is full	
public boolean Percolates()
{
	int N=grid.length;
	int flag=0;
	for(int i=0;i<N;i++)
	{
		if(isfull(N-1,i))
			flag=1;
	}
	if(flag==1)
		return true;
	else return false;
}


	public static void main(String args[])
	{  int num=20;
		Percolation p=new Percolation(num);


//	p.open(0,1);
//	p.open(1,1);
	//open n sites, generate n random numbers
	int site[];
	int n=200;
	site=new int[n];
    Random ran = new Random();
    Set <Integer> set = new HashSet<Integer>();
    while(set.size()==n?false:true){
    	int nm = ran.nextInt(400);
    	set.add(nm);
    }
    Iterator<Integer> it = set.iterator();
    int count = 0;
    int temp=0;
    while(it.hasNext()){
    	temp=it.next(); site[count]=temp;
    	System.out.println("the"+ ++count +"site =="+temp);
    }

	for(int a=0;a<n;a++)
	{int n1=(int)(site[a]/num);
	int n2=site[a]-num*n1;
	p.open(n1,n2);}
//	System.out.print(p.isfull(1,4));
	for(int i=1;i<=num;i++)
	{
		for(int j=1;j<=num;j++)
		{System.out.print(p.grid[i-1][j-1]);
		System.out.print(" ");
		}
		System.out.println("");
	}
	System.out.print("The percolation status is "+p.Percolates());
}
}
