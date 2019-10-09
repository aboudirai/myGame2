package game;

import java.util.Random;

public class Screen {

	public final int width, height;
	private SpriteSheet sheet;
	public int[] pixels;
	public int[] tiles;
	private static final int TILE_COUNT = 32;
	private static final int TILE_WIDTH = 8;
	private static final int SHEET_WIDTH = 256;
	private Random random = new Random();
	
	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		
		//Manually setting colors 
		
		for(int i = 0; i < 4; i++) {
			int[][] grassColors = {{40, 224, 16}, {18, 130, 12}};
			sheet.setColors(i, grassColors);
		}
		
		int[][] flowerColors = {{40, 224, 16},{250, 0, 117},{238, 242, 17}, {0,0,0}};
		sheet.setColors(33, flowerColors);
		
		pixels = new int[width*height];
		tiles = new int[width*height];
		setTiles();
	}

	//this render only narrows it down to the tile
	//loop with increments of TILE_WIDTH
	//
	
	public void setTiles(){
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt(4);
		}
		for (int i = 0; i < tiles.length; i++) {
			if(random.nextInt(100) % 7 == 0) {
				tiles[i] = 33;
			}
		}
	}

	
	public void render() {
		int tileIndex = 0;
		for(int yTile = 0; yTile < height; yTile += 8) {
			for(int xTile = 0; xTile < width; xTile += 8) {
				render(xTile, yTile, tiles[tileIndex++]); //Create tile arr
			}
		}
		
	}
	
	//loop with increments of 1 pixel
	//create color function that changes color of sprite sheet depending on order of colors
			//that appear when scrubbing through tile
	public void render(int x0, int y0, int tile) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		for(int y = y0; y < (y0 + 8); y++) {
			for(int x = x0; x < (x0 + 8); x++) {
				pixels[x + y * width] = sheet.pixels[((tileX * TILE_WIDTH) + (x - x0)) + (SHEET_WIDTH * ((tileY * TILE_WIDTH) + (y - y0)))];
				//pixels[x + y * width] = sheet.pixels[380];
			} 
		}
	}

}
