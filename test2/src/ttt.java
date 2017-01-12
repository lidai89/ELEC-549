
import java.util.Comparator;  
import java.util.PriorityQueue;  
import java.util.Queue;  
  
public class ttt {  
    private String name;  
    private int population;  
    public ttt(String name, int population)  
    {  
        this.name = name;  
        this.population = population;  
    }  
    public String getName()  
    {  
         return this.name;  
    }  
  
    public int getPopulation()  
    {  
         return this.population;  
    }  
    public String toString()  
    {  
         return getName() + " - " + getPopulation();  
    }  
    public static void main(String args[])  
    {  
        Comparator<ttt> OrderIsdn =  new Comparator<ttt>(){  
            public int compare(ttt o1, ttt o2) {  
                // TODO Auto-generated method stub  
                int numbera = o1.getPopulation();  
                int numberb = o2.getPopulation();  
                if(numberb > numbera)  
                {  
                    return 1;  
                }  
                else if(numberb<numbera)  
                {  
                    return -1;  
                }  
                else  
                {  
                    return 0;  
                }  
              
            }  
  
              
              
        };  
        Queue<ttt> priorityQueue =  new PriorityQueue<ttt>(11,OrderIsdn);  
          
                  
              
        ttt t1 = new ttt("t1",1);  
        ttt t3 = new ttt("t3",3);  
        ttt t2 = new ttt("t2",2);  
        ttt t4 = new ttt("t4",0);  
        priorityQueue.add(t1);  
        priorityQueue.add(t3);  
        priorityQueue.add(t2);  
        priorityQueue.add(t4);  
        System.out.println(priorityQueue.poll().toString());  
    }  
}  