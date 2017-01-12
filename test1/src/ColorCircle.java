import java.awt.*;
import javax.swing.*;
import java.awt.color.*;
import java.awt.event.*;
public class ColorCircle extends JFrame{
	Color color=Color.blue;
	JPanel jp1;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		repaint();
	}
	public ColorCircle(){
		panel pl=new panel();
		jp1=new JPanel(new FlowLayout());
		final JButton jbt=new JButton("click");
		jbt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getSource()==jbt)
					setColor(Color.YELLOW);
				System.out.println("jbt is clicked");
			}
			
		});
		jp1.add(jbt);
		add(pl,BorderLayout.CENTER);
		add(jp1,BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorCircle frame=new ColorCircle();
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

class panel extends JPanel{
	
	public void paint(Graphics g){
		super.paint(g);
		//g.setColor(Color.red);
		g.fillOval(10, 10, 30, 30);
		g.setColor(color);
		g.fillOval(40, 40, 30, 30);
		
	}
	
}
}