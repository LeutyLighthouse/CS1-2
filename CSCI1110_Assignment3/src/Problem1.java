/*
 * Class: CSCI 1110
 * Author: Ross J. Robertson
 * Date Last Modified: June 18th, 2018
 * Purpose: To read in an image and convert it from colour
 * to gray-scale.
 */


import java.util.*;

public class Problem1
{

    public static void main( String [] args )
    {

        Scanner in = new Scanner(System.in);

        //Taking in the input and output names of the image files we'll be processing.
        String inName = in.next();
        String outName = in.next();

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

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){

                //Here we declare an array to store the rgb values for each pixel we
                //encounter.
                int[] rgb = new int[3];
                img.getPixel(i, j, rgb);

                //Here we're calculating what the gray colour value will be for
                //the pixel we're examining.
                int gray = (rgb[0] + rgb[1] + rgb[2])/3;

                //Here we're reassigning the colour values to the proper shade of gray
                //and setting that gray pixel into the image.
                rgb[0] = gray;
                rgb[1] = gray;
                rgb[2] = gray;
                img.setPixel(i, j, rgb);
            }
        }
        img.save(outName);


    }
}
