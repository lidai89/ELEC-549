public class KNN {
 
public static void main(String[] args) {
// TODO Auto-generated method stub
Compute com = new Compute() ;
float[][] train = {{60,18.4f},
{85.5f,16.8f},
{64.5f,21.6f},
{61.5f,20.8f},
{87,23.6f},
{82.8f,22.4f},
{69,20},
{93,20.8f},
{51,22},
{75,19.6f},
{64.8f,17.2f},
{43.2f,20.4f},
{84,17.6f},
{49.2f,20.4f},
{47.4f,16.4f},
{43,18.8f},
{51,14},
{63,14.8f},
} ;
String[] flag={"1","1","1","1","1","1","1","1","1",
"2","2","2","2","2","2","2","2","2"} ;
float[][] test ={{32,19.2f},
{108,17.6f},
{81,20},
{52.8f,20.8f},
{59.4f,16},
{66,18.4f}
} ;
String[] flagOrigin = {"1","1","1","2","2","2"} ;
String[] tag={"1","2"} ;
int k = 13 ;
float errorRate = com.integrate(train, flag, test, flagOrigin, k, tag) ;
System.out.println("correct rate is " + (1-errorRate)) ;
}
}
class Compute{
 
public float integrate(float[][] train,String[] flag,float[][] test,String[]flagOrigin,int k,String[] tag){
 
float[] distance ;
int[] index ;
String ans=null ;
int count = 0 ;
System.out.println("Testing...") ;
for(int i=0;i<test.length;i++){
distance = getDistance(train,test) ;
index = sort(distance) ;
ans = getAnswer(index, k, flag, tag) ;
System.out.println("The class predicted is " + ans
+ "\t" + "and the class original is " + flagOrigin) ;
if (!ans.equals(flagOrigin)){
count++ ;
}
}
return (float)count/test.length ;
}
public float[] getDistance(float[][] train,float[][] test){
 
int row = train.length ; //行数，即训练集样本数
int col = train[0].length ; //列数，即指标数目或维数
float[] distance = new float[row] ;
for(int i=0;i<row;i++){
double temp =0;
for(int j=0;j<col;j++){
temp+=Math.pow((test[j]-train[j]),2) ;
}
distance = (float)Math.sqrt(temp) ;
}
return distance;
}
public int[] sort(float[] dis){
 
int length = dis.length ;
int[] index = new int[length] ; //记录排序后对应的下标
for(int i=0;i<length;i++){
index = i ;
}
for(int i=0;i<length;i++)
for(int j=i;j<length;j++){
if (dis>dis[j]){
float temp ;
temp = dis ;
dis = dis[j] ;
dis[j] = temp ;
int t ;
t = index ;
index = index[j] ;
index[j] = t ;
}
}
return index ;
}
public String getAnswer(int[] index,int k,String[] flag,String[] tag){
 
int[] count = new int[tag.length]; //对样本输入每个类进行技术
for(int i=0;i<k;i++){
for(int j=0;j<tag.length;j++){
if (flag[index].equals(tag[j])){
count[j]++ ;
}
}
}
int ind = max(count) ;
return tag[ind] ; //返回所属类的标签
}
public int max(int[] count){
int max = count[0] ;
int index = 0 ;
for(int i=0;i<count.length;i++){
if (max<count){
max = count ;
index = i ;
}
}
return index ;
}
}