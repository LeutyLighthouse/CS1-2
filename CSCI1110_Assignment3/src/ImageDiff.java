/**
 File: ImageDiff.java
 Author: Alex Brodsky
 Date: August 26, 2018
 Purpose: Diff two images and find the first difference.

 Description: This program takes two image files names as parameters
 and determines if they store the same image.
 */

import java.util.*;

/**
 This is the main class of the program.
 The program starts running in the main() of this class.
 */
public class ImageDiff {
    public static void main( String [] args ) {
        // Instatiate ImageRW for loading the images
        ImageRW output = new ImageRW();
        ImageRW expected = new ImageRW();

        // Load the images.
        if( !output.load( args[0] ) ) {
            System.out.println( "Could not load output image file " + args[0] );
            System.exit( 1 );
        } else if( !expected.load( args[1] ) ) {
            System.out.println( "Could not load expected image file " + args[1] );
            System.exit( 1 );
        }

        // Get the image dimensions
        int height = expected.getHeight();
        int width = expected.getWidth();

        if( ( height != output.getHeight() ) || ( width != output.getWidth() ) ) {
            System.out.println( "Output image dimensions are:" + output.getWidth()
                    + " x " + output.getHeight() );
            System.out.println( "Expected image dimensions: " + width + " x "
                    + height );
            System.exit( 1 );
        }

        // Instantiate the array for storing the RGB values of a pixel.
        int [] rgbO = new int[3];
        int [] rgbE = new int[3];

        // Loop over all the pixels in the image.
        for( int i = 0; i < width; i++ ) {
            for( int j = 0; j < height; j++ ) {
                // Get the RGB values for pixel in column i, row j
                output.getPixel( i, j, rgbO );
                expected.getPixel( i, j, rgbE );

                if( ( rgbO[0] != rgbE[0] ) || ( rgbO[1] != rgbE[1] ) ||
                        ( rgbO[2] != rgbE[2] ) ) {
                    System.out.println( "Pixel mismatch at: (" + i + ", " + j + ")" );
                    System.out.println( "Output pixel: (" + rgbO[0] + ", " + rgbO[1] +
                            ", " + rgbO[2] + ")" );
                    System.out.println( "Expected pixel: (" + rgbE[0] + ", " + rgbE[1] +
                            ", " + rgbE[2] + ")" );
                    System.exit( 1 );
                }
            }
        }
        System.out.println( "Images are the same." );
    }
}