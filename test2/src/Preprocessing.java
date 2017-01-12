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
import org.opencv.core.Size;
import org.opencv.core.Point;
public class Preprocessing {
	ArrayList<Mat> output;
	public ArrayList<Mat> outputmat(){
		return output;
	}
	public Preprocessing(Mat img){
		
		Mat image0=new Mat(img,new Range(0,img.rows()),new Range(0,img.cols()));
	    //Mat image0=Highgui.imread("test3.jpg");
	    //Mat image1=Highgui.imread("test3.jpg");
	    Mat image1=new Mat(img,new Range(0,image0.rows()),new Range(0,image0.cols()));
	    //when it is a stock
	    
	    Mat image2=new Mat();
	    image0.copyTo(image2);
	    	   Imgproc.cvtColor( image0,  image0, Imgproc.COLOR_BGR2GRAY );
	    	   
	    	   
	    	   
	    	   Size ksize=new Size(10,10);
	    	   Imgproc.blur( image0, image0, ksize );
	    	   
	    	   Imgproc.adaptiveThreshold(image0, image0, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,5,5);
	    	   
	    	   int erosion_elem = 9;
	  	     int erosion_size = 30;
	  	     Mat out_img=new Mat(image0,new Range(0,image0.rows()),new Range(0,image0.cols()));
	  	     Size size1=new Size(2*erosion_size + 1, 2*erosion_size+1);
	  	     Point p1=new Point(erosion_size, erosion_size);

	  	     Mat element = Imgproc.getStructuringElement( Imgproc.MORPH_RECT,
	  	                                             size1,
	  	                                            p1);


	  	     Imgproc.erode(out_img, out_img, element);
	  	   Highgui.imwrite("gray.jpg",image0);
	  	    Mat image3=out_img.clone();
	  	  
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
	       double size0=0;
	       double maxsize=0;
	       int index=0;
	       double rate1=0;
	       
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
	      
	       
	       for( int i = 0; i< contours.size(); i++ ){
	    	   Scalar color =new Scalar( 100, 100, 0 );
	            size0=boundRect[i].width*boundRect[i].height;
	            rate1=(double)(boundRect[i].height)/(double)boundRect[i].width;
	            Core.rectangle(drawing,boundRect[i].tl(),boundRect[i].br(),color,1,8,0);
	            if(size0>0.2*maxsize&&rate1>0.3){
	            	//
	            	
	            	
	            	numberrect.add(boundRect[i]);
	            }
	       }
	       ArrayList<Integer> xlabel=new ArrayList();
	       for(int i=0;i<numberrect.size();i++){
	    	   xlabel.add(numberrect.get(i).x);
	       }
	       Collections.sort(xlabel);
	       ArrayList<Rect> numberrect2=new ArrayList<Rect>();
	       for(int i=0;i<numberrect.size();i++){
	    	   int thisx=xlabel.get(i);
	    	   for(int j=0;j<numberrect.size();j++){
	    		   if(numberrect.get(j).x==thisx){
	    			   numberrect2.add(numberrect.get(j));
	    		   }
	    	   }
	       }
	       ArrayList<Rect> numberrect1=new ArrayList<Rect>();
	       for(int i=0;i<numberrect2.size();i++){
	    	  if(i==0) {numberrect1.add(numberrect2.get(i));}
	       else if(numberrect2.get(i).x!=numberrect1.get(numberrect1.size()-1).x){
	    		   numberrect1.add(numberrect2.get(i));
	    	   }
	       }
	       Highgui.imwrite("drawing.jpg",drawing);
	       Mat drawing1 = Mat.zeros( canny_output.size(), CvType.CV_8UC3 );
	       for(int i=0;i<numberrect.size();i++){
	    	   Scalar color =new Scalar( 100, 100, 0 );
	    	   Core.rectangle(drawing1,numberrect.get(i).tl(),numberrect.get(i).br(),color,1,8,0);
	       }
	       Highgui.imwrite("drawingnumber.jpg",drawing1);
	       output=new ArrayList<Mat>();
	       for( index=0;index<numberrect1.size();index++){
	       Rect rect=numberrect1.get(index);
	       Mat miniMat=new Mat();
	       Mat anotherMat=new Mat();
	       Mat image4=new Mat();
	       Imgproc.cvtColor( image2, image4, Imgproc.COLOR_BGR2GRAY );
	       Size ksize11=new Size(5,5);
    	   Imgproc.blur( image4, image4, ksize11 );
    	   
    	  
    	   Imgproc.adaptiveThreshold(image4, image4, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,5,5);
	       
    	   int erosion_elem1 = 3;
	  	     int erosion_size1 = 5;
	  	    
	  	     Size size11=new Size(2*erosion_size1 + 1, 2*erosion_size1+1);
	  	     Point p11=new Point(erosion_size1, erosion_size1);

	  	     Mat element1 = Imgproc.getStructuringElement( Imgproc.MORPH_RECT,
	  	                                             size11,
	  	                                            p11);


	  	     Imgproc.erode(image4, image4, element1);
	       miniMat =new Mat(image4,rect);
	       miniMat.copyTo(anotherMat);
	       Size ss=new Size(20,20);//the dst image size,e.g.100x100
	       Mat dst=new Mat();//dst image
	       
	       Imgproc.resize(anotherMat,dst,ss);//resize image
	       Size ss1=new Size(28,28);//the dst image size,e.g.100x100
	       Mat allblack=new Mat();//dst image
	       
	       Imgproc.resize(anotherMat,allblack,ss1);//resize image
	      // Mat allblack=new Mat(28, 28, CvType.CV_8UC1,new Scalar(255));
	      // Mat mask=allblack.submat(new Rect(4,4,20,20));
	       for(int i=0;i<28;i++){
	    	   for(int j=0;j<28;j++){
	    		   if(i<24&&i>3&&j<26&&j>5){
	    		   allblack.put(j,i,dst.get(j-6,i-4));
	    		   }
	    		   else allblack.put(j,i,255);
	    	   }
	       }
	       
	     //  Imgproc.cvtColor(dst,dst,Imgproc.COLOR_BGR2GRAY);
	      // Imgproc.adaptiveThreshold(dst, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,Imgproc.THRESH_BINARY,5,10);
	     //  Mat invertcolormatrix= new Mat(dst.rows(),dst.cols(), dst.type(), new Scalar(255,255,255));

	      // Core.subtract(invertcolormatrix, dst, dst);
	       output.add(allblack);
	       }
	}

    public static void main(String[] args) {
    	System.loadLibrary("opencv_java249");
    	Mat img=Highgui.imread("test.jpg");
    	Mat img2=img.clone();
    	
    	Mat invertcolormatrix= new Mat(img.rows(),img.cols(), img.type(), new Scalar(255,255,255));

    	Core.subtract(invertcolormatrix, img, img);
    	Preprocessing prc=new Preprocessing(img);
    	
    	ArrayList<Mat> dst=prc.outputmat();
       Highgui.imwrite("dst_example.jpg",dst.get(0));
       //show image
  	     Mat image_tmp = img2;
    	   
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


