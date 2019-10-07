package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	public int width, height;
	public int[] pixels;
	public int[] colors;
	
	public SpriteSheet(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		int[][] colors = new int[pixels.length][1];
		for(int i = 0; i < 256; i++) {
			int tempColor = pixels[i];
			int red = (tempColor & 0x00ff0000) >> 16;
		    int green = (tempColor & 0x0000ff00) >> 8;
		    int blue = tempColor & 0x000000ff;
			int[] rgb = {red, green, blue};
		    colors[i] = rgb;
			
		}
	}
	
}
