/*
 * Class: CSCI 1110
 * Author: Ross J. Robertson
 * Date Last Modified: June 18th
 * Purpose: This program removes any white artifacts no larger than 2x2 from
 * the edge-detection image.
 */

import java.util.*;

public class Problem3
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
        //corner of the image and ends in the bottom-right corner. Although, because
        //we must examine the pixels surrounding the potential artifact pixels the
        //starting point may not always be p(0, 0) to ensure the program doesn't
        //walk out of bounds. Despite that you can expect the for loops to still
        //move across the image in the same way.

        //Artifact Scrubber begins.

        for(int i = 0; i < (width - 1); i++){
            for(int j = 0; j < (height - 1); j++){

                //Declaring pixels that we'll be checking for artifacts.
                int[] rgb = new int[3];
                int[] rgbX1 = new int[3];
                int[] rgbY1 = new int[3];
                int[] rgbX1Y1 = new int[3];

                //Declaring pixels surrounding the pixels that we're checking for artifacts.
                int[] borderXMinus1YMinus1 = new int[3];
                int[] borderYMinus1 = new int[3];
                int[] borderXPlus1YMinus1 = new int[3];
                int[] borderXPlus2YMinus1 = new int[3];
                int[] borderXMinus1 = new int[3];
                int[] borderXPlus2 = new int[3];
                int[] borderXMinus1YPlus1 = new int[3];
                int[] borderXPlus2YPlus1 = new int[3];
                int[] borderXMinus1YPlus2 = new int[3];
                int[] borderYPlus2 = new int[3];
                int[] borderXPlus1YPlus2 = new int[3];
                int[] borderXPlus2YPlus2 = new int[3];

                //Case for if we want to examine the centre of the image.
                if(((1 <= i)&&(i <= (width - 3)))&&((1 <= j)&&(j <= (height - 3)))){

                    //Getting pixels that may have an artifact, which we'll examine for said
                    //artifact.
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);

                    //Getting pixels that border potential artifact pixels.
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);
                    img.getPixel(i + 2, j - 1, borderXPlus2YMinus1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i + 2, j, borderXPlus2);
                    img.getPixel(i - 1, j + 1, borderXMinus1YPlus1);
                    img.getPixel(i + 2, j + 1, borderXPlus2YPlus1);
                    img.getPixel(i - 1, j + 2, borderXMinus1YPlus2);
                    img.getPixel(i, j + 2, borderYPlus2);
                    img.getPixel(i + 1, j + 2, borderXPlus1YPlus2);
                    img.getPixel(i + 2, j + 2, borderXPlus2YPlus2);

                    //Checking if the potential artifact pixels are white.
                    if((rgb[0] == 255)||(rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)){

                        //Checking if the surrounding pixels are also white. If any of them are
                        //white then we won't change anything, but if all of them are black
                        //then we'll remove the artifact pixels.
                        if(((borderXMinus1YMinus1[0] == 255)||(borderYMinus1[0] == 255)||(borderXPlus1YMinus1[0] == 255)||(borderXPlus2YMinus1[0] == 255)||(borderXMinus1[0] == 255)||(borderXPlus2[0] == 255)||(borderXMinus1YPlus1[0] == 255)||(borderXPlus2YPlus1[0] == 255)||(borderXMinus1YPlus2[0] == 255)||(borderYPlus2[0] == 255)||(borderXPlus1YPlus2[0] == 255)||(borderXPlus2YPlus2[0] == 255)) == false){

                            //Here's where we set the artifact pixels colour values to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            rgbX1[0] = 0;
                            rgbX1[1] = 0;
                            rgbX1[2] = 0;
                            rgbY1[0] = 0;
                            rgbY1[1] = 0;
                            rgbY1[2] = 0;
                            rgbX1Y1[0] = 0;
                            rgbX1Y1[1] = 0;
                            rgbX1Y1[2] = 0;

                            //And here we set them using ImageRW's built-in method.
                            img.setPixel(i, j, rgb);
                            img.setPixel(i + 1, j, rgbX1);
                            img.setPixel(i, j + 1, rgbY1);
                            img.setPixel(i + 1, j + 1, rgbX1Y1);


                        }
                    }
                }


                //Now we'll be checking one pixel at a time in a manner similar to the
                //above process.
                if(((1 <= i)&&(i <= (width - 2)))&&((1 <= j)&&(j <= (height - 2)))){

                    //Here's the pixel we'll check for artifacts.
                    img.getPixel(i, j, rgb);

                    //These are the pixels surrounding the pixel we want to check.
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i - 1, j + 1, borderXMinus1YPlus1);

                    //Checking if the potential artifact pixel is white.
                    if(rgb[0] == 255){

                        //Checking the surrounding pixels.
                        if(((rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)||(borderXMinus1YMinus1[0] == 255)||(borderYMinus1[0] == 255)||(borderXPlus1YMinus1[0] == 255)||(borderXMinus1[0] == 255)||(borderXMinus1YPlus1[0] == 255)) == false){

                            //Here we change the artifact pixel to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            img.setPixel(i, j, rgb);


                        }
                    }
                }


                //Here we're checking the leftmost side.
                if((i == 0)&&((1 <= j)&&(j <= (height - 3)))){

                    //Getting pixels that may have an artifact, which we'll examine for said
                    //artifact.
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);

                    //Here are the surrounding pixels.
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);
                    img.getPixel(i + 2, j - 1, borderXPlus2YMinus1);
                    img.getPixel(i + 2, j, borderXPlus2);
                    img.getPixel(i + 2, j + 1, borderXPlus2YPlus1);
                    img.getPixel(i, j + 2, borderYPlus2);
                    img.getPixel(i + 1, j + 2, borderXPlus1YPlus2);
                    img.getPixel(i + 2, j + 2, borderXPlus2YPlus2);


                    //Checking if the potential artifact pixels are white.
                    if((rgb[0] == 255)||(rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)){

                        //Checking if the surrounding pixels are also white. If any of them are
                        //white then we won't change anything, but if all of them are black
                        //then we'll change the artifact pixels to black.
                        if(((borderYMinus1[0] == 255)||(borderXPlus1YMinus1[0] == 255)||(borderXPlus2YMinus1[0] == 255)||(borderXPlus2[0] == 255)||(borderXPlus2YPlus1[0] == 255)||(borderYPlus2[0] == 255)||(borderXPlus1YPlus2[0] == 255)||(borderXPlus2YPlus2[0] == 255)) == false){

                            //Here's where we set the artifact pixels colour values to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            rgbX1[0] = 0;
                            rgbX1[1] = 0;
                            rgbX1[2] = 0;
                            rgbY1[0] = 0;
                            rgbY1[1] = 0;
                            rgbY1[2] = 0;
                            rgbX1Y1[0] = 0;
                            rgbX1Y1[1] = 0;
                            rgbX1Y1[2] = 0;

                            //And here we set them using ImageRW's built-in method.
                            img.setPixel(i, j, rgb);
                            img.setPixel(i + 1, j, rgbX1);
                            img.setPixel(i, j + 1, rgbY1);
                            img.setPixel(i + 1, j + 1, rgbX1Y1);

                        }
                    }
                }


                //Here we're checking the rightmost side.
                if((i == (width - 2))&&((1 <= j)&&(j <= (height - 3)))){

                    //Getting pixels that may have an artifact, which we'll examine for said
                    //artifact.
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);


                    //Here are the surrounding pixels.
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i - 1, j + 1, borderXMinus1YPlus1);
                    img.getPixel(i - 1, j + 2, borderXMinus1YPlus2);
                    img.getPixel(i, j + 2, borderYPlus2);
                    img.getPixel(i + 1, j + 2, borderXPlus1YPlus2);


                    //Checking if the potential artifact pixels are white.
                    if((rgb[0] == 255)||(rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)){

                        //Checking if the surrounding pixels are also white. If any of them are
                        //white then we won't change anything, but if all of them are black
                        //then we'll change the artifact pixels to black.
                        if(((borderXMinus1YMinus1[0] == 255)||(borderYMinus1[0] == 255)||(borderXPlus1YMinus1[0] == 255)||(borderXMinus1[0] == 255)||(borderXMinus1YPlus1[0] == 255)||(borderXMinus1YPlus2[0] == 255)||(borderYPlus2[0] == 255)||(borderXPlus1YPlus2[0] == 255)) == false){

                            //Here's where we set the artifact pixels colour values to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            rgbX1[0] = 0;
                            rgbX1[1] = 0;
                            rgbX1[2] = 0;
                            rgbY1[0] = 0;
                            rgbY1[1] = 0;
                            rgbY1[2] = 0;
                            rgbX1Y1[0] = 0;
                            rgbX1Y1[1] = 0;
                            rgbX1Y1[2] = 0;

                            //And here we set them using ImageRW's built-in method.
                            img.setPixel(i, j, rgb);
                            img.setPixel(i + 1, j, rgbX1);
                            img.setPixel(i, j + 1, rgbY1);
                            img.setPixel(i + 1, j + 1, rgbX1Y1);

                        }
                    }
                }


                //Here we're checking the topmost side.
                if((j == 0)&&((1 <= i)&&(i <= (width - 3)))){

                    //Getting pixels that may have an artifact, which we'll examine for said
                    //artifact.
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);


                    //Here are the surrounding pixels.
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i + 2, j, borderXPlus2);
                    img.getPixel(i - 1, j + 1, borderXMinus1YPlus1);
                    img.getPixel(i + 2, j + 1, borderXPlus2YPlus1);
                    img.getPixel(i - 1, j + 2, borderXMinus1YPlus2);
                    img.getPixel(i, j + 2, borderYPlus2);
                    img.getPixel(i + 1, j + 2, borderXPlus1YPlus2);
                    img.getPixel(i + 2, j + 2, borderXPlus2YPlus2);


                    //Checking if the potential artifact pixels are white.
                    if((rgb[0] == 255)||(rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)){

                        //Checking if the surrounding pixels are also white. If any of them are
                        //white then we won't change anything, but if all of them are black
                        //then we'll change the artifact pixels to black.
                        if(((borderXMinus1[0] == 255)||(borderXPlus2[0] == 255)||(borderXMinus1YPlus1[0] == 255)||(borderXPlus2YPlus1[0] == 255)||(borderXMinus1YPlus2[0] == 255)||(borderYPlus2[0] == 255)||(borderXPlus1YPlus2[0] == 255)||(borderXPlus2YPlus2[0] == 255)) == false){

                            //Here's where we set the artifact pixels colour values to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            rgbX1[0] = 0;
                            rgbX1[1] = 0;
                            rgbX1[2] = 0;
                            rgbY1[0] = 0;
                            rgbY1[1] = 0;
                            rgbY1[2] = 0;
                            rgbX1Y1[0] = 0;
                            rgbX1Y1[1] = 0;
                            rgbX1Y1[2] = 0;

                            //And here we set them using ImageRW's built-in method.
                            img.setPixel(i, j, rgb);
                            img.setPixel(i + 1, j, rgbX1);
                            img.setPixel(i, j + 1, rgbY1);
                            img.setPixel(i + 1, j + 1, rgbX1Y1);

                        }
                    }
                }

                //Here we're checking the bottommost side.
                if((j == 1)&&((1 <= i)&&(i <= (width - 3)))){

                    //Getting pixels that may have an artifact, which we'll examine for said
                    //artifact.
                    img.getPixel(i, j, rgb);
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);


                    //Here are the surrounding pixels.
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);
                    img.getPixel(i + 2, j - 1, borderXPlus2YMinus1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i + 2, j, borderXPlus2);
                    img.getPixel(i - 1, j + 1, borderXMinus1YPlus1);
                    img.getPixel(i + 2, j + 1, borderXPlus2YPlus1);


                    //Checking if the potential artifact pixels are white.
                    if((rgb[0] == 255)||(rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)){

                        //Checking if the surrounding pixels are also white. If any of them are
                        //white then we won't change anything, but if all of them are black
                        //then we'll change the artifact pixels to black.
                        if(((borderXMinus1YMinus1[0] == 255)||(borderYMinus1[0] == 255)||(borderXPlus1YMinus1[0] == 255)||(borderXPlus2YMinus1[0] == 255)||(borderXMinus1[0] == 255)||(borderXPlus2[0] == 255)||(borderXMinus1YPlus1[0] == 255)||(borderXPlus2YPlus1[0] == 255)) == false){

                            //Here's where we set the artifact pixels colour values to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            rgbX1[0] = 0;
                            rgbX1[1] = 0;
                            rgbX1[2] = 0;
                            rgbY1[0] = 0;
                            rgbY1[1] = 0;
                            rgbY1[2] = 0;
                            rgbX1Y1[0] = 0;
                            rgbX1Y1[1] = 0;
                            rgbX1Y1[2] = 0;

                            //And here we set them using ImageRW's built-in method.
                            img.setPixel(i, j, rgb);
                            img.setPixel(i + 1, j, rgbX1);
                            img.setPixel(i, j + 1, rgbY1);
                            img.setPixel(i + 1, j + 1, rgbX1Y1);

                        }
                    }
                }

                //Here we're checking the top-leftmost corner.
                if((i == 0)&&(j == 0)){

                    //Here's the pixel we'll check for artifacts.
                    img.getPixel(i, j, rgb);

                    //These are the pixels surrounding the pixel we want to check.
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i + 1, j + 1, rgbX1Y1);

                    //Checking if the potential artifact pixel is white.
                    if(rgb[0] == 255){

                        //Checking the surrounding pixels.
                        if(((rgbX1[0] == 255)||(rgbY1[0] == 255)||(rgbX1Y1[0] == 255)) == false){

                            //Here we change the artifact pixel to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            img.setPixel(i, j, rgb);

                        }
                    }
                }

                //Here we're checking the top-rightmost corner.
                if(((i == (width - 1))&&(j == 0))){

                    //Here's the pixel we'll check for artifacts.
                    img.getPixel(i, j, rgb);

                    //These are the pixels surrounding the pixel we want to check.
                    img.getPixel(i, j + 1, rgbY1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);


                    //Checking if the potential artifact pixel is white.
                    if(rgb[0] == 255){

                        //Checking the surrounding pixels.
                        if(((borderXMinus1[0] == 255)||(rgbY1[0] == 255)||(borderXMinus1YMinus1[0] == 255)) == false){

                            //Here we change the artifact pixel to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            img.setPixel(i, j, rgb);

                        }
                    }
                }

                //Here we're checking the bottom-leftmost corner.
                if(((i == 0)&&(j == (height - 1)))){

                    //Here's the pixel we'll check for artifacts.
                    img.getPixel(i, j, rgb);

                    //These are the pixels surrounding the pixel we want to check.
                    img.getPixel(i + 1, j, rgbX1);
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i + 1, j - 1, borderXPlus1YMinus1);


                    //Checking if the potential artifact pixel is white.
                    if(rgb[0] == 255){

                        //Checking the surrounding pixels.
                        if(((borderXPlus1YMinus1[0] == 255)||(borderYMinus1[0] == 255)||(rgbX1[0] == 255)) == false){

                            //Here we change the artifact pixel to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            img.setPixel(i, j, rgb);

                        }
                    }
                }

                //Here we're checking the bottom-rightmost corner.
                if(((i == (width - 1))&&(j == (height - 1)))){

                    //Here's the pixel we'll check for artifacts.
                    img.getPixel(i, j, rgb);

                    //These are the pixels surrounding the pixel we want to check.
                    img.getPixel(i, j - 1, borderYMinus1);
                    img.getPixel(i - 1, j, borderXMinus1);
                    img.getPixel(i - 1, j - 1, borderXMinus1YMinus1);


                    //Checking if the potential artifact pixel is white.
                    if(rgb[0] == 255){

                        //Checking the surrounding pixels.
                        if(((borderYMinus1[0] == 255)||(borderXMinus1[0] == 255)||(borderXMinus1YMinus1[0] == 255)) == false){

                            //Here we change the artifact pixel to black.
                            rgb[0] = 0;
                            rgb[1] = 0;
                            rgb[2] = 0;
                            img.setPixel(i, j, rgb);

                        }
                    }
                }

            }
        }
        //Artifact scrubber ends.

        img.save( outName );


    }
}