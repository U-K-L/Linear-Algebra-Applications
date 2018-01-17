import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Julia {

	public static void main(String[] args) {
		int width = 1920, height = 1080, max = 2000;
		
		BufferedImage Image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		double rad = 16.0; //radius of entire set.
		
		//Color map.
		int[] colors = new int[max];
		for(int i = 0; i < max; i++)
		{
			colors[i] =  Color.HSBtoRGB((i+120)/256f, 1, i/(i+8f));
		}
		
		for(int row = 0; row < height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				//put in center, then make entire graph size of 4.
				double i_csr = (col - width/2.0)*rad/width; //real number
				double i_csi = (row - height/2.0)*rad/height; //imaginary
				
				//Coordinates for graph
				double x = 0, y = 0;
				
				//How many times to iterate for each set.
				int iterations = 0;
				
				//While iterations is less than max. And X/Y values are below radius.
				//Calculating small values a lot of times.
				while(Math.pow(x, 2) + Math.pow(y, 2) < rad && iterations < max)
				{
					double twoab = 2*x*y;
					x = Math.pow(x, 2) - Math.pow(y, 2) + 0.285 +i_csr; //ploted point for real. //a
					y = twoab  +0.01 +i_csi; //ploted point for imaginary. //b
					iterations++;
					
				}
				//if blew outside set? set white. If not? Set black
				if(iterations < max) 
					Image.setRGB(col, row, colors[iterations]);
				else
					Image.setRGB(col, row, 0x000000);
			}
			
		}
		
		try {
			ImageIO.write(Image, "png", new File("Julia.png"));
		}catch(IOException e){e.printStackTrace();}

		System.out.println("done");
	}

}
