mport java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class P {
 public static void main(String[] args) {
  new FrameX().setVisible(true);
 }
}
class FrameX extends JFrame implements ActionListener{
 private JComboBox mode,color,radius;
 private JPanel menu;
 private JButton clear,reset;
 private CanvasX canvas;
 private int x,y;
 private static Color[] cs = {Color.yellow,Color.red,Color.blue,Color.green,};
 
 public FrameX(){
  super("»­Í¼Å¶¡«¡«ºÇºÇ");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(600,400);
  this.setLocationRelativeTo(null);
  this.mode=new JComboBox(new Object[]{"Ìî³ä","Ãè±ß",});
  this.color = new JComboBox(new Object[]{"»Æ","ºì","À¶","ÂÌ",});
  this.radius=new JComboBox(new Object[]{"20","35","50","70","100","150",});
  this.menu=new JPanel(null);
  this.menu.setPreferredSize(new Dimension(1,30));
  this.getContentPane().add(menu,"South");
  
  JLabel l = new JLabel("Ä£Ê½£º");
  l.setSize(40,23);
  l.setLocation(10,0);
  this.menu.add(l);
  
  this.mode.setBounds(l.getWidth(),0,60,23);
  this.menu.add(mode);
  this.mode.addActionListener(this);
  
  l = new JLabel("ÑÕÉ«£º");
  l.setBounds(mode.getX()+mode.getWidth()+10,0,40,23);
  this.menu.add(l);
  this.color.setBounds(l.getX()+l.getWidth(),0,60,23);
  this.color.addActionListener(this);
  this.menu.add(color);
  
  l = new JLabel("°ë¾¶£º");
  l.setBounds(color.getX()+color.getWidth()+10,0,40,23);
  this.menu.add(l);
  this.radius.setBounds(l.getX()+l.getWidth(),0,60,23);
  this.radius.addActionListener(this);
  this.menu.add(radius);
  
  this.reset=new JButton("Çå³ý");
  this.reset.setBounds(radius.getX()+radius.getWidth()+20,0,78,23);
  this.reset.addActionListener(this);
  this.menu.add(reset);
  
  this.canvas=new CanvasX();
  this.getContentPane().add(canvas,"Center");
  this.reset();
 }
 
 public void actionPerformed(ActionEvent e) {
  Object o = e.getSource();
  if(o.equals(reset))reset();
  else this.canvas.setShapeX(makeShapeX());
 }

 private ShapeX makeShapeX() {
  int wh = Integer.parseInt(radius.getSelectedItem().toString());
  Rectangle r = new Rectangle(0,0,wh,wh);
  int mod = this.mode.getSelectedIndex()+1;
  Color c = cs[this.color.getSelectedIndex()];
  ShapeX s = new ShapeX();
  s.bc=c;
  s.fc=c;
  s.mod=mod;
  s.s=r;
  return s;
 }

 private void reset() {
  this.canvas.clear();
  this.mode.setSelectedIndex(0);
  this.color.setSelectedIndex(0);
  this.radius.setSelectedIndex(0);
  this.canvas.setShapeX(makeShapeX());
 }
}

class ShapeX{
 Shape s;
 int mod;
 Color fc,bc;
}

class CanvasX extends Canvas implements MouseListener{
 public static final int MODE_FILL=1;
 public static final int MODE_DRAW=2;
 private ShapeX sx;
 
 public CanvasX(){
  this.addMouseListener(this);
 }
 
 public void setShapeX(ShapeX x){
  this.sx=x;
 }

 public void clear(){
  Graphics g = this.getGraphics();
  if(g==null)return;
  g.setColor(Color.white);
  g.fillRect(0,0,this.getWidth(),this.getHeight());
  g.dispose();
  sx=null;
 }
 
 public void process(int x,int y){
  if(sx==null)return;
  Rectangle r = (Rectangle)sx.s;
  r.setBounds(x-r.width/2,y-r.height/2,r.width,r.height);
  Graphics2D g = (Graphics2D)this.getGraphics();
  if(sx.mod==MODE_FILL){
   g.setColor(sx.fc);
   g.fill(sx.s);
  }
  else if(sx.mod==MODE_DRAW){
   g.setColor(sx.bc);
   g.draw(sx.s);
  }
  else if(sx.mod==(MODE_FILL|MODE_DRAW)){
   g.setColor(sx.fc);
   g.fill(sx.s);
   g.setColor(sx.bc);
   g.draw(sx.s);
  }
  g.dispose();
 }

 public void mouseClicked(MouseEvent e) {}
 public void mouseEntered(MouseEvent e) {}
 public void mouseExited(MouseEvent e) {}
 public void mousePressed(MouseEvent e) {process(e.getX(),e.getY());}
 public void mouseReleased(MouseEvent e) {}
}