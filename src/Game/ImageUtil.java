package Game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static BufferedImage makeColorTransparent(BufferedImage ref, Color color) {  
        BufferedImage dimg = new BufferedImage(ref.getWidth(), ref.getHeight(), BufferedImage.TYPE_INT_ARGB);  
        Graphics2D g = dimg.createGraphics();  
        g.setComposite(AlphaComposite.Src);  
        g.drawImage(ref, null, 0, 0);  
        g.dispose();  
        for(int i = 0; i < dimg.getHeight(); i++) {  
            for(int j = 0; j < dimg.getWidth(); j++) {  
                if(dimg.getRGB(j, i) == color.getRGB()) {  
                dimg.setRGB(j, i, 0x8F1C1C);  
                }  
            }  
        }  
        return dimg;  
    }  
	
	public static BufferedImage[][] splitImage(BufferedImage img, int cols, int rows) {  
		int w = 80;  
        int h = 80;  
        int row = img.getWidth()/w;
        int col = img.getHeight()/h; 
        BufferedImage imgs[][] = new BufferedImage[row][col];  
        for(int y = 0; y < col; y++) {  
            for(int x = 0; x < row; x++) {  
                imgs[x][y] = new BufferedImage(w, h, img.getType());
                Graphics2D g = imgs[x][y].createGraphics();
                g.drawImage(img, 0, 0, w, h, w*x, h*y+1, w*x+w, h*y+h, null);
                g.dispose();  
            }  
        }  
        return imgs;  
    }
	
	public static boolean isPixelCollide (double x1, double y1, BufferedImage image1, double x2, double y2, BufferedImage image2) 
	{
		// initialization
		double width1 = x1 + image1.getWidth() -1,
				height1 = y1 + image1.getHeight() -1,
				width2 = x2 + image2.getWidth() -1,
				height2 = y2 + image2.getHeight() -1;

		int xstart = (int) Math.max(x1, x2),
				ystart = (int) Math.max(y1, y2),
				xend   = (int) Math.min(width1, width2),
				yend   = (int) Math.min(height1, height2);
		// intersection rect
		int toty = Math.abs(yend - ystart);
		int totx = Math.abs(xend - xstart);
		for (int y=1;y < toty-1;y++)
		{
			int ny = Math.abs(ystart - (int) y1) + y;
			int ny1 = Math.abs(ystart - (int) y2) + y;

			for (int x=1;x < totx-1;x++) 
			{
				int nx = Math.abs(xstart - (int) x1) + x;
				int nx1 = Math.abs(xstart - (int) x2) + x;
				if (((image1.getRGB(nx,ny) & 0xFF000000) != 0x00) && ((image2.getRGB(nx1,ny1) & 0xFF000000) != 0x00)) 
				{
						// collide!!
					    System.out.println("s1 = "+nx+","+ny+"  -  s2 = "+nx1+","+ny1);
						return true;		
				}
			}
		}
		return false;
	}
	public static BufferedImage [] GetCurrentFrameSet(BufferedImage [][] set, int [][] cord)
	{
		BufferedImage [] oneset = new BufferedImage [cord.length];
		int count = 0;
		int x = 0;
		int y = 0;

			for(int j=0; j<(cord.length);j++)
			{
				x = cord[j][0];
				y = cord[j][1];
				oneset[count] = set[y][x];
				count++;
		
			}
		
		return oneset;
	}

	public static BufferedImage loadImage(String ref) {  
        BufferedImage bimg = null;  
        try {  
  
            bimg = ImageIO.read(new File(ref));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bimg;  
    }  

}
