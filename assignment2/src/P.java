import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Test extends JPanel implements MouseListener{
private static final long serialVersionUID=8409711115431875102L;
private int yes=0,no=0;
private Font
font_number=new Font(Font.MONOSPACED, Font.PLAIN, 20)
;
public static void main(String[] args) {
JFrame f=new JFrame();
f.setTitle("ÎÞÁÄµã»÷Æ÷");
Test panel=new Test();
panel.addMouseListener(panel);
f.setContentPane(panel);
f.setSize(500, 400);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setVisible(true);
}
@Override
public void paint(Graphics g)
{
g.setColor(Color.WHITE);
g.fillRect(0, 0, getWidth(), getHeight());
g.setColor(Color.GREEN);
int mid_x=getWidth()/2,mid_y=getHeight()/2;
g.fillRect(10, 10, mid_x-20, yes);
g.setColor(Color.RED);
g.fillRect(mid_x+10, 10, mid_x-20, no);
g.setFont(font_number);
g.drawString("Yes: "+Integer.toString(yes), 20, 40);
g.setColor(Color.GREEN);
g.drawString("No: "+Integer.toString(no), mid_x+20, 40);
}
@Override
public void mouseClicked(MouseEvent e) {
switch(e.getButton()){
case MouseEvent.BUTTON1:
yes++;
repaint();
break;
case MouseEvent.BUTTON3:
no++;
repaint();
break;
}
}
@Override
public void mouseEntered(MouseEvent arg0){}
@Override
public void mouseExited(MouseEvent arg0) {}
@Override
public void mousePressed(MouseEvent arg0) {}
@Override
public void mouseReleased(MouseEvent arg0) {}
}