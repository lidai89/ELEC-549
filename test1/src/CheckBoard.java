
//Chehkerboard.java
import java.awt.*;
import javax.swing.*;
public class CheckBoard extends JFrame{
 //�����������̸����ƴ�С
 public CheckBoard(){
  //�������̸�����
  super("�������̸�");
  //�������̸��С
  setSize(640,640);setVisible(true);
 }
public void paint(Graphics g)
{//���ó���Ļ��Ʒ���
 super.paint(g);
 //����һ����ɫ����
 g.setColor(new Color(255,255,255));
 g.fillRect(0, 0, 640, 640);
 //�������̸�
 g.setColor(new Color(0,0,0));
 for(int i=0;i<8;i++){//y����ѭ��,ѭ��8��,ʹ�����б�����ʽ
  //������һ�У��������У�
   for (int j=0;j<8;j++){//x����ѭ�������ƻ����кڿ�
    if(j%2!=0){
     g.fillRect(j*80, i*80, 40, 40);
    }
   }
   //�����ڶ��У���ż���У�
   i++;
   for(int j=0;j<8;j++){
    if(j%2==0){//x����ѭ��������ż���кڿ�
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
