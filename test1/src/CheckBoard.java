
//Chehkerboard.java
import java.awt.*;
import javax.swing.*;
public class CheckBoard extends JFrame{
 //定西西洋棋盘格名称大小
 public CheckBoard(){
  //西洋棋盘格名称
  super("西洋棋盘格");
  //西洋棋盘格大小
  setSize(640,640);setVisible(true);
 }
public void paint(Graphics g)
{//调用超类的绘制方法
 super.paint(g);
 //绘制一个白色背景
 g.setColor(new Color(255,255,255));
 g.fillRect(0, 0, 640, 640);
 //绘制棋盘格
 g.setColor(new Color(0,0,0));
 for(int i=0;i<8;i++){//y方向循环,循环8行,使用两行遍历方式
  //遍历第一行，（基数行）
   for (int j=0;j<8;j++){//x方向循环，绘制基数行黑框
    if(j%2!=0){
     g.fillRect(j*80, i*80, 40, 40);
    }
   }
   //遍历第二行，（偶数行）
   i++;
   for(int j=0;j<8;j++){
    if(j%2==0){//x方向循环，绘制偶数行黑框
     g.fillRect(j*80, i*80, 40, 40);
    }
   }
  }
 }
public static void main(String args[])
{
 CheckBoard application=new CheckBoard();
 application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}
