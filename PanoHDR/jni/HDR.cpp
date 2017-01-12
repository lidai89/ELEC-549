#include <string>
#include <opencv2/opencv.hpp>
#include "NativeLogging.h"
#include <vector>
#include <math.h>
//#include"G:\Users\lidai\workspace\lab01\android ocr\jni\preprocessing.h"
//#include <stdafx.h>

using namespace std;
using namespace cv;

static const char* TAG = "CreateHDR";

void read_input_images(vector<string>& inputImagePaths, vector<Mat>& images)
{
    /* Read the images at the locations in inputImagePaths
     * and store them in the images vector.
     * Helpful OpenCV functions: imread.
     */
}

void align_images(vector<Mat>& images)
{
    /* Update the images vector with aligned images.
     * Helpful OpenCV classes and functions:
     * ORB, BFMatcher, findHomography, warpPerspective
     * The above classes and functions are just recommendations.
     * You are free to experiment with other methods as well.
     */
	char f1[100],f2[100];

	Mat image2,image1;




	image2= images[0];//"1.jpg"); // template

	image1= images[1];//"2.jpg&"); // input
	cv::ORB orb;
		cv::Mat grey1, desc1;
		cv::Mat grey2, desc2;
		vector <cv::KeyPoint> kp1, kp2;



		cvtColor( image1, grey1, CV_RGB2GRAY );

		cvtColor( image2, grey2, CV_RGB2GRAY );

		orb(grey1, cv::Mat(), kp1, desc1);
		orb(grey2, cv::Mat(), kp2, desc2);
		 cv::BFMatcher matcher( cv::NORM_L2, false );
		     std::vector< cv::DMatch > matches;
		     matcher.match( desc1, desc2, matches );
		 	std::vector<DMatch>  good_matches;
	//below is for test
		 	int min_dist=100;
		 	for( int i = 0; i <= desc1.rows; i++ )

		 	{

		 	if( matches[i].distance <= 3*min_dist )

		 	{ good_matches.push_back( matches[i]); }

		 	}

		 	std::vector< Point2f > obj;

		 	std::vector< Point2f > scene;

		 	for( int i = 0; i < good_matches.size(); i++ )

		 	{

		 	obj.push_back( kp1[ good_matches[i].queryIdx ].pt );

		 	scene.push_back( kp2[ good_matches[i].trainIdx ].pt );

		 	}

		 	Mat H = findHomography( obj, scene, CV_RANSAC );

		 	cv::Mat result;

		 	warpPerspective(image1,result,H,cv::Size(image1.cols+image2.cols,image1.rows));

		 	cv::Mat half(result,cv::Rect(0,0,image2.cols,image2.rows));

		 	image2.copyTo(half);
	    images[0]=image2;
	    images[1]=image1;

	/*Mat gray_image1;

	Mat gray_image2;

	cvtColor( image1, gray_image1, CV_RGB2GRAY );

	cvtColor( image2, gray_image2, CV_RGB2GRAY );

	if( !gray_image1.data || !gray_image2.data )

	//{ std::cout<< " --(!) Error reading images " << std::endl; return -1; }

	int minHessian = 400;

	SurfFeatureDetector detector(400);

	std::vector<KeyPoint>  keypoints_object, keypoints_scene;

	detector.detect( gray_image1, keypoints_object );

	detector.detect( gray_image2, keypoints_scene );

	SurfDescriptorExtractor extractor;

	Mat descriptors_object, descriptors_scene;

	extractor.compute( gray_image1, keypoints_object, descriptors_object );

	extractor.compute( gray_image2, keypoints_scene, descriptors_scene );

	FlannBasedMatcher matcher;

	std::vector< DMatch > matches;

	matcher.match( descriptors_object, descriptors_scene, matches );


	double max_dist = 0; double min_dist = 100;

	for( int i = 0; i < descriptors_object.rows; i++ )

	{

	double dist = matches[i].distance;

	if( dist <= min_dist ) min_dist = dist;

	if( dist > max_dist ) max_dist = dist;

	}

	std::vector<DMatch>  good_matches;

	for( int i = 0; i <= descriptors_object.rows; i++ )

	{

	if( matches[i].distance <= 3*min_dist )

	{ good_matches.push_back( matches[i]); }

	}

	std::vector< Point2f > obj;

	std::vector< Point2f > scene;

	for( int i = 0; i < good_matches.size(); i++ )

	{

	obj.push_back( keypoints_object[ good_matches[i].queryIdx ].pt );

	scene.push_back( keypoints_scene[ good_matches[i].trainIdx ].pt );

	}

	Mat H = findHomography( obj, scene, CV_RANSAC );

	cv::Mat result;

	warpPerspective(image1,result,H,cv::Size(image1.cols+image2.cols,image1.rows));

	cv::Mat half(result,cv::Rect(0,0,image2.cols,image2.rows));

	image2.copyTo(half);*/

}


void compute_weights(vector<Mat>& images, vector<Mat>& weights)
{
    /*
     *  Compute the weights for each image and place them in the given weights vector.
     *  Helpful OpenCV functions: Laplacian, split, merge.
     */
}

void blend_pyramids(int numLevels, vector<Mat>& images, vector<Mat>& weights, Mat& output)
{
    /*
     *  Blend the images using the calculated weights.
     *  Store the result of exposure fusion in the given output image.
     *  Helpful OpenCV functions: buildPyramid, pyrUp.
     */
}
void findX(IplImage* imgSrc,int* min, int* max){
	int i;
	int minFound=0;
	CvMat data;
	CvScalar maxVal=cvRealScalar(imgSrc->width * 255);
	CvScalar val=cvRealScalar(0);
	//For each col sum, if sum < width*255 then we find the min
	//then continue to end to search the max, if sum< width*255 then is new max
	for (i=0; i< imgSrc->width; i++){
		cvGetCol(imgSrc, &data, i);
		val= cvSum(&data);
		if(val.val[0] < maxVal.val[0]){
			*max= i;
			if(!minFound){
				*min= i;
				minFound= 1;
			}
		}
	}
}

void findY(IplImage* imgSrc,int* min, int* max){
	int i;
	int minFound=0;
	CvMat data;
	CvScalar maxVal=cvRealScalar(imgSrc->width * 255);
	CvScalar val=cvRealScalar(0);
	//For each col sum, if sum < width*255 then we find the min
	//then continue to end to search the max, if sum< width*255 then is new max
	for (i=0; i< imgSrc->height; i++){
		cvGetRow(imgSrc, &data, i);
		val= cvSum(&data);
		if(val.val[0] < maxVal.val[0]){
			*max=i;
			if(!minFound){
				*min= i;
				minFound= 1;
			}
		}
	}
}
CvRect findBB(IplImage* imgSrc){
	CvRect aux;
	int xmin, xmax, ymin, ymax;
	xmin=xmax=ymin=ymax=0;

	findX(imgSrc, &xmin, &xmax);
	findY(imgSrc, &ymin, &ymax);

	aux=cvRect(xmin, ymin, xmax-xmin, ymax-ymin);

	return aux;

}

IplImage preprocessing(IplImage* imgSrc,int new_width, int new_height){
	IplImage* result;
	IplImage* scaledResult;

	CvMat data;
	CvMat dataA;
	CvRect bb;//bounding box
	CvRect bba;//boundinb box maintain aspect ratio

	//Find bounding box
	bb=findBB(imgSrc);

	//Get bounding box data and no with aspect ratio, the x and y can be corrupted
	cvGetSubRect(imgSrc, &data, cvRect(bb.x, bb.y, bb.width, bb.height));
	//Create image with this data with width and height with aspect ratio 1
	//then we get highest size betwen width and height of our bounding box
	int size=(bb.width>bb.height)?bb.width:bb.height;
	result=cvCreateImage( cvSize( size, size ), 8, 1 );
	cvSet(result,CV_RGB(255,255,255),NULL);
	//Copy de data in center of image
	int x=(int)floor((float)(size-bb.width)/2.0f);
	int y=(int)floor((float)(size-bb.height)/2.0f);
	cvGetSubRect(result, &dataA, cvRect(x,y,bb.width, bb.height));
	cvCopy(&data, &dataA, NULL);
	//Scale result
	scaledResult=cvCreateImage( cvSize( new_width, new_height ), 8, 1 );
	cvResize(result, scaledResult, CV_INTER_NN);

	//Return processed data
	return *scaledResult;

}
void erzhihua(Mat input){
	int x=input.rows;
	int y=input.cols;


}
bool CreateHDR(vector<string> inputImagePaths, string outputImagePath)
{
    //Read in the given images.
	//test, delete later
	Mat image0=imread(inputImagePaths[0]);
	Mat image1=imread(inputImagePaths[1]);
	vector<Mat> images;

/*

cvtColor(image0, out_img, CV_BGR2GRAY);
threshold(out_img, out_img, 0, 255, CV_THRESH_OTSU + CV_THRESH_BINARY);

erode(out_img, out_img, elem);

vector< vector< Point> > contours;
findContours(out_img, contours, CV_RETR_EXTERNAL, CV_CHAIN_APPROX_NONE);

vector<vector<Point> >::iterator it = contours.begin();
while (it!=contours.end()) {
    RotatedRect rect = minAreaRect(Mat(*it));
    if(verifyRect(rect)){
        ++it; // A valid rectangle found
    } else {
        it= contours.erase(it);
    }
}


vector<Rect> boundRect(contours.size());
for (int i = 0; i < contours.size(); ++i) {
    Scalar color = Scalar(200, 200, 200);
    boundRect[i] = boundingRect(Mat(contours[i]));
    rectangle(out_img, boundRect[i].tl(), boundRect[i].br(), color, 0.2, 8, 0);

    CvRect roi = CvRect(boundRect[i]);
    IplImage orig = out_img;
    IplImage *res = cvCreateImage(cvSize(roi.width, roi.height), orig.depth, orig.nChannels);
    cvSetImageROI(&orig, roi);
    cvCopy(&orig, res);
    cvResetImageROI(&orig);

    IplImage *bininv_img;
    bininv_img = cvCreateImage(cvSize(128, 128), IPL_DEPTH_8U, 1);
    cvResize(res, bininv_img);
    cvThreshold(bininv_img, bininv_img, 100, 255, CV_THRESH_BINARY_INV);

    int ret = do_ocr(bininv_img);
    res_elem elem;
    elem.num = ret;
    elem.xpos = boundRect[i].tl().x;
    res_vec.push_back(elem);

}
*/
 /*   vector<Mat> images;

    read_input_images(inputImagePaths, images);
    //Verify that the images were correctly read.
    if(images.empty() || images.size()!=inputImagePaths.size())
    {
        LOG_ERROR(TAG, "Images were not read in correctly!");
        return false;
    }

    //Verify that the images are RGB images of the same size.
    const int numChannels = 3;
    Size imgSize = images[0].size();
    for(const Mat& img: images)
    {
        if(img.channels()!=numChannels)
        {
            LOG_ERROR(TAG, "CreateHDR expects 3 channel RGB images!");
            return false;
        }
        if(img.size()!=imgSize)
        {
            LOG_ERROR(TAG, "HDR images must be of equal sizes!");
            return false;
        }
    }
    LOG_INFO(TAG, "%d images successfully read.", images.size());

    //Now we'll make sure that the images line up correctly.
    align_images(images);
    LOG_INFO(TAG, "Image alignment complete.");

    //Compute the weights for each image.
    vector<Mat> weights;
    compute_weights(images, weights);
    if(weights.size()!=images.size())
    {
        LOG_ERROR(TAG, "Image weights were not generated!");
        return false;
    }
    LOG_INFO(TAG, "Weight computation complete.");

    //Fusion using pyramid blending.
    int numLevels = 9;
    Mat outputImage;
    blend_pyramids(numLevels, images, weights, outputImage);
    if(outputImage.empty())
    {
        LOG_ERROR(TAG, "Blending did not produce an output!");
        return false;
    }
    LOG_INFO(TAG, "Blending complete!");*/

    //Save output.
    Mat outputImage;
 //   outputImage=images;
 //   for ( int i = 1; i < 31; i = i + 2 )
//    int i=31;
   //      bilateralFilter ( image1, outputImage, i, i*2, i/2 );
  //         }
  //       int m,n;
  //       m=image1.rows;
 //        n=image1.cols;
   //      m=sizeof(image1[1][1])/sizeof(image1[0][0][0]);
    //     int[m,n] rem;
     //    rem=image1[1];

  //  Mat mat1;
    IplImage temp=image0;

   Mat out_img;
   Mat imgcopy=image0;
   cvtColor( image0, out_img, CV_BGR2GRAY );
   blur( out_img, out_img, Size(3,3) );
     adaptiveThreshold(out_img, out_img, 255, ADAPTIVE_THRESH_GAUSSIAN_C,THRESH_BINARY,5,10);

     int erosion_elem = 3;
     int erosion_size = 9;
     Mat element = getStructuringElement( MORPH_RECT,
                                            Size( 2*erosion_size + 1, 2*erosion_size+1 ),
                                            Point( erosion_size, erosion_size ) );


     erode(out_img, out_img, element);

 //  IplImage temp1=preprocessing(img,img->width,img->height);
  //  img=&temp1;
  //
     Mat canny_output;
       vector<vector<Point> > contours;
       vector<Vec4i> hierarchy;
int thresh=100;
RNG rng(12345);
       /// Detect edges using canny
       Canny( out_img, canny_output, thresh, thresh*2, 3 );
       /// Find contours
       findContours( canny_output, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0) );

       /// Draw contours
       vector<Rect> boundRect(contours.size());
       Mat drawing = Mat::zeros( canny_output.size(), CV_8UC3 );
       float area,area1;
       area=0;
       area1=0;
       int ii=0;
       int size0=0;
       int maxsize=0;
       int index=0;
       for( int i = 0; i< contours.size(); i++ )
          {
            Scalar color = Scalar( rng.uniform(0, 255), rng.uniform(0,255), rng.uniform(0,255) );
            drawContours( drawing, contours, i, color, 2, 8, hierarchy, 0, Point() );
            boundRect[i] = boundingRect(Mat(contours[i]));
            size0=boundRect[i].width*boundRect[i].height;
            if(size0>maxsize){
            	maxsize=size0;
            	index=i;
            }
                rectangle(out_img, boundRect[i].tl(), boundRect[i].br(), color, 1, 8, 0);
                /*
                area1=abs((boundRect[i].br().x-boundRect[i].tl().x)*(boundRect[i].br().y-boundRect[i].tl().y));
                if(area1>area){
                	area=area1;
                	ii=i;
                }
                */
          }
       Rect rect=boundingRect(Mat(contours[index]));
       cv::Mat miniMat,anotherMat;
       miniMat = out_img(rect);
       miniMat.copyTo(anotherMat);
       Size size(20,20);//the dst image size,e.g.100x100
       Mat dst;//dst image
       anotherMat=255-anotherMat;
       resize(anotherMat,dst,size);//resize image
       Mat allblac(28, 28, CV_8UC1, Scalar(0));
       //cvtColor( allblac, allblac, CV_BGR2GRAY );
       dst.copyTo(allblac(Rect(4,4,20,20)));
    bool result1 = imwrite(outputImagePath,allblac);
    if(!result1)
    {
        LOG_ERROR(TAG, "Failed to save output image to file!");
    }
    return result1;
}
