import java.awt.*;
import java.lang.*;
public class paintball{
private java.awt.Color color = java.awt.Color.BLUE;   // The color of the ball

private java.awt.Point location = new java.awt.Point(23, 84);  // the location of the center of the ball

private int radius = 10;   // the radius of the ball

   

public void paintComponent(Graphics g) {

    super.paintComponent(g);   // paint whatever normally gets painted.

    g.setColor (color);  // set the color to paint with

    g.fillOval (location.x-radius, location.y-radius, 2*radius, 2*radius); // paint a circle onto the graphics object centered on location and with defined radius.

}
public static void main(){
	paintball a=new paintball();
	Graphics g=new Graphics();
	a.paintComponent(g);
}

}
