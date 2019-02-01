/*
 * Class: CSCI 1110
 * Author: Ross J. Robertson
 * Date Last Modified: June 18th 2018
 * Purpose:To take an image converted to gray-scale and perform edge detection on
 * said image, turning it into a black and white image, where the white pixels
 * show the edges.
 */

import java.util.*;
import java.lang.Math;

public class Problem2
{

    public static void main( String [] args )
    {

        Scanner in = new Scanner(System.in);

        //Taking in the input and output names of the image files we'll be processing,
        //and the value we'll use for the edge-detection threshold.
        String inName = in.next();
        String outName = in.next();
        int threshold = in.nextInt();

        //Here we're loading the image that we'll be processing using the given name.
        ImageRW img = new ImageRW();
        img.load(inName);

        //here we're using ImageRW's built-in functions to obtain the dimensions of the image.
        int width = img.getWidth();
        int height = img.getHeight();

        //Something to clarify which could remove a lot of confusion as to how this
        //program works, is that for these for loops that move across the image the
        //starting point, (width == 0, height == 0), always begins in the top left
        //corner of the image and ends in the bottom-right corner.

        //Edge detector begins.

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){

                //This case is for examining the bulk of this image. It will examine every
                //pixel except for those that are the furthest to the right, and the furthest
                //to the bottom.
                if((i < width - 1)&&(j < height - 1)){
                    int[] rgb = new int[3];
                    int[] rgbX1 = new int[3];
                    int[] rgbY1 = new int[3];
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);

                    //Here we're calculating the relationship of the pixel we're examining to
                    //the pixel below and beside it.
                    double a = rgb[0] - rgbX1[0];
                    double b = rgb[0] - rgbY1[0];
                    a = a * a;
                    b = b * b;
                    double c = a + b;
                    double difference = (Math.sqrt(c));

                    //If the threshold is less than d(x, y) then we'll set the pixel to
                    //white. Else, we'll set this pixel to black.
                    if(difference > threshold){
                        rgb[0] = 255;
                        rgb[1] = 255;
                        rgb[2] = 255;
                        img.setPixel(i, j, rgb);
                    }
                    else if(difference <= threshold){
                        rgb[0] = 0;
                        rgb[1] = 0;
                        rgb[2] = 0;
                        img.setPixel(i, j, rgb);
                    }
                }

                //This case is when we're examining the rightmost side of the picture, barring
                //the bottom-rightmost pixel. Since there's no actual pixel to the right each
                //time we're examining the relationship of the three pixels to produce d(x, y)
                //we must modify the formula given to us to acquire d(x, y) accurately. Since
                //the relationship is analogous to vectors we must remove all of (p(x, y) - p(x+1, y))^2
                //instead of just p(x+1, y) if we want to obtain the correct answer.
                if((i == width - 1)&&(j != height - 1)){
                    int[] rgb = new int[3];
                    int[] rgbY1 = new int[3];
                    img.getPixel(i, j, rgb);
                    img.getPixel(i, j + 1, rgbY1);

                    //Here we calculate the relationship of d(x, y).
                    double a = rgb[0] - rgbY1[0];
                    a = a * a;
                    double difference = (Math.sqrt(a));

                    //If the threshold is less than d(x, y) then we'll set the pixel to
                    //white. Else, we'll set this pixel to black.
                    if(difference > threshold){
                        rgb[0] = 255;
                        rgb[1] = 255;
                        rgb[2] = 255;
                        img.setPixel(i, j, rgb);
                    }
                    else if(difference <= threshold){
                        rgb[0] = 0;
                        rgb[1] = 0;
                        rgb[2] = 0;
                        img.setPixel(i, j, rgb);
                    }
                }

                //This case is when we're examining the bottom line of pixels of
                //the image, except for the bottom-rightmost pixel. Since we have
                //no pixel below to use as a comparison for the relationship to the
                //pixel we're interested in for edge detection we must restructure the
                //formula for d(x, y) given to us. Since the section (p(x, y) - p(x, (y+1)))
                //determines this relationship to d(x, y) and we don't have anything to
                //examine below, said section must mathematically count as nothing, or more clearly,
                //zero. Once we've made this modification, the edge detection works as desired.
                if((i != width - 1)&&(j == height - 1)){
                    int[] rgb = new int[3];
                    int[] rgbX1 = new int[3];
                    img.getPixel(i, j, rgb);
                    img.getPixel(i +1, j, rgbX1);

                    //Here we calculate the relationship of d(x, y).
                    double a = rgb[0] - rgbX1[0];
                    a = a * a;
                    double difference = (Math.sqrt(a));

                    //If the threshold is less than d(x, y) then we'll set the pixel to
                    //white. Else, we'll set this pixel to black.
                    if(difference > threshold){
                        rgb[0] = 255;
                        rgb[1] = 255;
                        rgb[2] = 255;
                        img.setPixel(i, j, rgb);
                    }
                    else if(difference <= threshold){
                        rgb[0] = 0;
                        rgb[1] = 0;
                        rgb[2] = 0;
                        img.setPixel(i, j, rgb);
                    }
                }

                //This case is when we are examining the bottom-rightmost pixel.
                //Since we have no pixels to examine below or to the right of this
                //pixel to determine its status as an edge, we can think of the
                //resulting d(x, y) as 0. And since all absolute values are either
                //greater than or equal to 0, (and the square root of the sum of
                //squares will be absolute in value), we can conclude that d(x, y)
                //will always be either less than or equal to the threshold. Therefore,
                //this pixel must be black.
                if((i == width - 1)&&(j == height - 1)){
                    int[] rgb = new int[3];
                    rgb[0] = 0;
                    rgb[1] = 0;
                    rgb[2] = 0;
                    img.setPixel(i, j, rgb);
                }

            }
        }
        //Edge detector ends.


        img.save( outName );





    }
}