import org.opencv.core.*;
import org.opencv.*;
import org.opencv.imgproc.*;
import org.opencv.highgui.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.lang.*;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Range;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Point;
public class HelloCV {

    public static void main(String[] args) {
    	
    	System.loadLibrary("opencv_java249");
    Mat image0=Highgui.imread("test2.jpg");
    Mat image1=Highgui.imread("test2.jpg");

    Imgproc.cvtColor( image0,  image0, Imgproc.COLOR_BGR2GRAY );
	   Size ksize=new Size(3,3);
	   Imgproc.blur( image0, image0, ksize );
	   
	   Imgproc.adaptiveThreshold(image0, image0, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,5,10);
	   int erosion_elem = 9;
    int erosion_size = 16;
    Mat out_img=new Mat(image0,new Range(0,image0.rows()),new Range(0,image0.cols()));
    Size size1=new Size(2*erosion_size + 1, 2*erosion_size+1);
    Point p1=new Point(erosion_size, erosion_size);

    Mat element = Imgproc.getStructuringElement( Imgproc.MORPH_RECT,
                                            size1,
                                           p1);


    Imgproc.erode(out_img, out_img, element);
	   
 
// 	   Imgproc.adaptiveThreshold(out_img, out_img, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,5,10);
  Mat canny_output=out_img;
ArrayList<MatOfPoint> contours=new ArrayList<MatOfPoint>();
Mat hierarchy=new Mat();
int thresh=100;

/// Detect edges using canny
Imgproc.Canny( out_img, canny_output, thresh, thresh*2 );
/// Find contours
Imgproc.findContours( canny_output, contours, hierarchy, 3, 2);

/// Draw contours
Rect[] boundRect=new Rect[contours.size()];
ArrayList<Rect> numberrect=new ArrayList<Rect>();
Mat drawing = Mat.zeros( canny_output.size(), CvType.CV_8UC3 );
float area,area1;
area=0;
area1=0;
int ii=0;
int size0=0;
int maxsize=0;
int index=0;
int rate1=0;
int maxindex=0;
for( int i = 0; i< contours.size(); i++ )
   {
     Scalar color =new Scalar( 100, 100, 0 );
     Imgproc.drawContours( drawing, contours, i, color, 2, 8, hierarchy, 0,new Point(0,0) ); 
     boundRect[i] = Imgproc.boundingRect(contours.get(i));
     size0=boundRect[i].width*boundRect[i].height;
     if(size0>maxsize){
     	maxsize=size0;
     	index=i;
     }
   }
maxindex=index;
for( int i = 0; i< contours.size(); i++ ){
	   boundRect[i] = Imgproc.boundingRect(contours.get(i));
     size0=boundRect[i].width*boundRect[i].height;
     rate1=boundRect[i].height/boundRect[i].width;
     if(size0>0.3*maxsize&&rate1>0.3&&rate1<3){
     	//
     	numberrect.add(boundRect[i]);
     }
}
ArrayList<Mat> output=new ArrayList<Mat>();
for( index=0;index<numberrect.size();index++){
Rect rect=numberrect.get(index);
Mat miniMat=new Mat();
Mat anotherMat=new Mat();
miniMat =new Mat(image1,rect);
miniMat.copyTo(anotherMat);
Size ss=new Size(20,20);//the dst image size,e.g.100x100
Mat dst=new Mat();//dst image
//    anotherMat=255-anotherMat;
Imgproc.resize(anotherMat,dst,ss);//resize image
Mat allblac=new Mat(28, 28, CvType.CV_8UC1,new Scalar(0));

dst.copyTo(allblac,new Mat(allblac, new Rect(4,4,20,20)));

Imgproc.cvtColor(dst,dst,Imgproc.COLOR_BGR2GRAY);
Imgproc.adaptiveThreshold(dst, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,5,10);
Mat invertcolormatrix= new Mat(dst.rows(),dst.cols(), dst.type(), new Scalar(255,255,255));

Core.subtract(invertcolormatrix, dst, dst);
output.add(dst);
}
       Highgui.imwrite("dst_example.jpg",output.get(0));
       //show image
  	     Mat image_tmp = dst.get(1);
    	   
    			    MatOfByte matOfByte = new MatOfByte();

    			    Highgui.imencode(".jpg", image_tmp, matOfByte); 

    			    byte[] byteArray = matOfByte.toArray();
    			    BufferedImage bufImage = null;

    			    try {

    			        InputStream in = new ByteArrayInputStream(byteArray);
    			        bufImage = ImageIO.read(in);
    			    } catch (Exception e) {
    			        e.printStackTrace();
    			    }
    			    ImageIcon icon=new ImageIcon(bufImage);
    		        JFrame frame=new JFrame();
    		        frame.setLayout(new FlowLayout());
    		        frame.setSize(400,600);
    		        JLabel lbl=new JLabel();
    		        lbl.setIcon(icon);
    		        frame.add(lbl);
    		        frame.setVisible(true);
    		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		        
    			    
    	   
        
    }

}
