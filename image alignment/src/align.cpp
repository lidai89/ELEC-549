#include <stdafx.h>

#include <stdio.h>

#include <iostream>

#include "opencv2/core/core.hpp"

#include "opencv2/features2d/features2d.hpp"

#include "opencv2/highgui/highgui.hpp"

#include "opencv2/nonfree/nonfree.hpp"

#include "opencv2/calib3d/calib3d.hpp"

#include "opencv2/imgproc/imgproc.hpp"

using namespace cv;

&amp;nbsp;

int main()

{


char f1[100],f2[100];

Mat image2,image1;


printf("\n enter the name of the image1");

scanf("%s",&f1);

printf("\n enter the name of the image2\n");

scanf("%s",&f2);

image2= imread(f1);//"1.jpg"); // template

image1= imread(f2);//"2.jpg&"); // input

Mat gray_image1;

Mat gray_image2;

cvtColor( image1, gray_image1, CV_RGB2GRAY );

cvtColor( image2, gray_image2, CV_RGB2GRAY );

if( !gray_image1.data || !gray_image2.data )

{ std::cout<< " --(!) Error reading images " << std::endl; return -1; }

int minHessian = 400;

SurfFeatureDetector detector( minHessian );

std::vector&lt; KeyPoint &gt; keypoints_object, keypoints_scene;

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

if( dist &lt; min_dist ) min_dist = dist;

if( dist &gt; max_dist ) max_dist = dist;

}

std::vector&lt; DMatch &gt; good_matches;

for( int i = 0; i &lt; descriptors_object.rows; i++ )

{

if( matches[i].distance &lt; 3*min_dist )

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

image2.copyTo(half);

imshow("input1",image1);

imshow("input2",image2);

imshow( "Result", result );

cvWaitKey(0);

return 0;

}
