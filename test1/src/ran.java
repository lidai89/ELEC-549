import java.util.*;
public class ran {
	public static void main(String[] args){
	int original[];
	int n=100;
	original=new int[n];
	int site[];
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
	for(int i=0;i<n;i++)
	{
		System.out.print(site[i]+" ");
		
	}
}
	
}
