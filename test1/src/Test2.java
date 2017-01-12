import java.lang.Math;
import java.util.*;
public class Test2 {
	public static void main(String[] args){
		int n=50;
	    Random ran = new Random();
	    Set <Integer> set = new HashSet<Integer>();
	    while(set.size()==n?false:true){
	    	int num = ran.nextInt(100)+1;
	    	set.add(num);
	    }
	    Iterator<Integer> it = set.iterator();
	    int count = 0;
	    int temp=0;
	    while(it.hasNext()){
	    	temp=it.next();
	    	System.out.println("第"+ ++count +"个随机数 =="+temp);
	    }
	}
}
