package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	public int width, height;
	public int[] pixels;
	public int[] colors;
	
	public static final int TILE_COUNT = 32;
	public static final int TILE_WIDTH = 8;
	public static final int SHEET_WIDTH = 256;
	
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
	
	public int checkInList(int val, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (val == arr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public void setColors(int tile, int[][] colors) {
		int[] originalColors = new int[colors.length];
		int[] newColors = new int[colors.length];
		int red, green, blue;
		int[] tempColor;
		//Converting RGB to bitwise
		for (int i = 0; i < colors.length; i++) {
			tempColor = colors[i];
			
			red = tempColor[0] << 16;
	    	green = tempColor[1] << 8;
	    	blue = tempColor[2];
			
			if (tempColor[0] == -1 && tempColor[1] == -1 && tempColor[2] == -1) {
				newColors[i] = -1;
			}
			else {
				newColors[i] = red + green + blue;
			}
			
		}
		int pixelX = (tile % 32) * 8;
		int pixelY = (tile / 32) * 8;
		int inc = 0;
		for(int y = pixelY; y < (pixelY + 8); y++) {
			for(int x = pixelX; x < (pixelX + 8); x++) {
				int currColor = pixels[x + y * SHEET_WIDTH];
				int colorIndex = checkInList(currColor, originalColors);
				
				if (colorIndex < 0) { //Add the new color to the list
					originalColors[inc] = currColor;
					pixels[x + y * SHEET_WIDTH] = newColors[inc++];
				}
				else {
					pixels[x + y * SHEET_WIDTH] = newColors[colorIndex];			
				}
				
			}
		}
	}
	//Sprites more than one tile big
	public void setColors(int tiles[], int[][] colors, int spriteWidth, int spriteHeight) {
		int[] originalColors = new int[colors.length];
		int[] newColors = new int[colors.length];
		int red, green, blue;
		int[] tempColor;
		//Converting RGB to bitwise
		for (int i = 0; i < colors.length; i++) {
			tempColor = colors[i];
			
			red = tempColor[0] << 16;
	    	green = tempColor[1] << 8;
	    	blue = tempColor[2];
			
			if (tempColor[0] == -1 && tempColor[1] == -1 && tempColor[2] == -1) {
				newColors[i] = -1;
			}
			else {
				newColors[i] = red + green + blue;
			}
			
		}
		int inc = 0;
		int pixelX = (tiles[0] % 32) * 8;
		int pixelY = (tiles[0] / 32) * 8;
		for(int y = pixelY; y < (pixelY + (TILE_WIDTH * spriteHeight)); y++) {
			for(int x = pixelX; x < (pixelX + TILE_WIDTH * spriteWidth); x++) {
				int currColor = pixels[x + y * SHEET_WIDTH];
				int colorIndex = checkInList(currColor, originalColors);
				
				if (colorIndex < 0) { //Add the new color to the list
					originalColors[inc] = currColor;
					pixels[x + y * SHEET_WIDTH] = newColors[inc++];
				}
				else {
					pixels[x + y * SHEET_WIDTH] = newColors[colorIndex];			
				}
				
			}
		}
		
	}
	
	
	
}
