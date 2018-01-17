import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rotate {

	public static BufferedImage Image;
	public static BufferedImage NewImage;
	public static int width = 1200, height = 800; //Height and Width of Image.
	public static void main(String[] args) {
		
		Image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //The loaded image.
		NewImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //The image to be exported
		try {
			Image = ImageIO.read(new File("logang.png"));
			System.out.println("It read!");
		}catch(IOException e){e.printStackTrace();}

		//Go through every pixel.
		int row = 0;
		int col = 0;
		int [] ihat = new int [] {2,0}; //Basis vectors.
		int [] jhat = new int [] {0,2}; //Program is actually sheering.
		int [] vector = new int [] {0,0}; //Created by ci, and cj. Is the scalar of the basis vector.
		//Components contain the vectors of the col and row.
		paint(); //Paints the background.
		
		
		for(row = 0; row < height; row++) //Y coordinate.
		{	
			for(col = 0; col < width; col++) //X coordinate.
			{
				
				for(int i = 0; i < 2; i++)
				{
					//Create the vector from ihat and jhat.
					//v = (5,2) = 5ihat + 2jhat
					vector[i] = (col * ihat[i]) + (row * jhat[i]); 
				}
				
				if(vector[0] < width && vector[1] < height&&
				   vector[0] >= 0 && vector[1] >= 0)
				{
					//Place pixels inside the newImage.
					NewImage.setRGB(col, row,  Image.getRGB(vector[0], vector[1]));
					
				}
				
			}
		}
		
		try {
			ImageIO.write(NewImage, "png", new File("shrink.png"));
			System.out.println("Sucess!");
		}catch(IOException e){e.printStackTrace();}
	}
	
	public static void paint()
	{
		
		for(int row = 0; row < height; row++)
		{	
			for(int col = 0; col < width; col++)
			{
				NewImage.setRGB(col, row, new Color(97, 217, 97).getRGB()); //Set pixel color
			}
		}
	}

}
