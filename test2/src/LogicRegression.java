import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LogicRegression {
	int dim;
	int inputnumber;
	int resultdim;
	double[][] coeff;
	int[][] input;
	int[] output;
	int result=2;
	double rate=0.001;// study rate
	double epsilon=0.0001;//maximum error 
	double error;
	public LogicRegression(){
		error=100;
		FileInputStream fis = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null; 
		  String str="";
		  String[] strtemp;
		  //output input
			FileInputStream fis1 = null;
			  InputStreamReader isr1 = null;
			  BufferedReader br1 = null; 
			  String str1="";
			  String[] strtemp1;
		try{
			
			//read input from file and decide the dimension of input
			  fis = new FileInputStream("input.txt");
			  fis1 = new FileInputStream("output.txt");
			    isr = new InputStreamReader(fis);
			    isr1= new InputStreamReader(fis1);
			    br = new BufferedReader(isr);
			    br1= new BufferedReader(isr1);
			    str=br.readLine();
			    str1=br1.readLine();
			    strtemp=str.split(" ");
			    strtemp1=str1.split(" ");
			    int temp=Integer.parseInt(strtemp[0]);
			    dim=strtemp.length-1;
			    inputnumber=temp/dim;
			    resultdim=Integer.parseInt(strtemp1[0]);
			    input=new int[inputnumber][dim+1];
			    for(int i=0;i<dim;i++){
			    	input[0][i]=Integer.parseInt(strtemp[i+1]);
			    }
			    input[0][dim]=1;
			    output=new int[resultdim];
			    coeff=new double[result][dim+1];
			    output[0]=Integer.parseInt(strtemp1[1]);
			    str1=br1.readLine();
			    int m=0;
			    while(str1!=null){
			    	m++;
			    	strtemp1=str1.split(" ");
			    	output[m]=Integer.parseInt(strtemp1[0]);
			    	str1=br1.readLine();
			    }
			    str=br.readLine();
			    int j=0;
			    while(str!=null){
			    	j=j+1;
			    	strtemp=str.split(" ");
			    	 for(int i=0;i<dim;i++){
					    	input[j][i]=Integer.parseInt(strtemp[i]);
					    }
			    	 input[j][dim]=1;
			    	str=br.readLine();
			    	
			    }
			    }
		catch (FileNotFoundException e) {
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
	
public void iterateonce(){
		double[][] tao=new double[result][inputnumber];
		double[][] dif=new double[result][dim+1];
		double[][] orincoeff=new double[result][dim+1];
		for(int s=0;s<result;s++){
		orincoeff[s]=coeff[s].clone();
		}
		for(int i=0;i<result;i++){
			for(int j=0;j<inputnumber;j++){
			
				for(int l=0;l<dim+1;l++){
					tao[i][j]=tao[i][j]+coeff[i][l]*input[j][l];
				}
				tao[i][j]=1/(1+Math.exp(-tao[i][j]));
				
				
				
			}
			}
		
		double temp;
		for(int i=0;i<result;i++)
		{
			for(int j=0;j<dim+1;j++){
				
				for(int l=0;l<inputnumber;l++){
					temp=(double)(output[l])-tao[i][l];
					temp=temp*(double)(input[l][j]);
					dif[i][j]=dif[i][j]+temp;
				}
				
				coeff[i][j]=coeff[i][j]+rate*dif[i][j];
			}
		}
		error=0;
		for(int i=0;i<result;i++)
		{
			for(int j=0;j<result;j++){
				error=error+(orincoeff[i][j]-coeff[i][j])*(orincoeff[i][j]-coeff[i][j]);
			}
		}
		System.out.print("error is "+error);
		System.out.println();
		System.out.print(Arrays.deepToString(tao));
		System.out.println();
	}
    public double returnerror()
    {   
    	return error;
    
  
    }
	public static void main(String[] args){
		
		LogicRegression lr=new LogicRegression();
		while(lr.returnerror()>1e-8){
		//for(int i=0;i<20;i++){
		lr.iterateonce();
		
		}
	}
	

}
