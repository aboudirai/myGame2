package game;

import java.util.Random;

public class Screen {

	public final int width, height;
	private SpriteSheet sheet;
	public int[] pixels;
	public int[] tiles;
	public int[] mapPixels;
	private static final int TILE_COUNT = 32;
	private static final int TILE_WIDTH = 8;
	private static final int SHEET_WIDTH = 256;
	private static final int MAP_SCALE = 8;
	private int mapWidth;
	private int mapPixelsWidth;
	private Random random = new Random();
	protected int xScroll = 0;
	protected int yScroll = 0;
	
	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.mapWidth = MAP_SCALE * width;
		//Manually setting colors 
		
		for(int i = 0; i < 4; i++) {
			int[][] grassColors = {{40, 224, 16}, {36, 181, 50}};
			sheet.setColors(i, grassColors);
		}
		
		int[][] flowerColors = {{40, 224, 16},{250, 0, 117},{238, 242, 17}, {0,0,0}};
		sheet.setColors(33, flowerColors);
		
		pixels = new int[width*height];
		tiles = new int[(mapWidth) * (mapWidth)]; //each element represents 8x8p tile
		mapPixels = new int[tiles.length * 8 * 8];
		mapPixelsWidth =  mapWidth*8;
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
			if(random.nextInt(20) == 2) {
				tiles[i] = 33;
			}
		}
		
		//paints entire map
		setMapPixels();
	}
	
	public void setMapPixels() {
		int tileIndex = 0;
		for(int yTile = 0; yTile < mapPixelsWidth; yTile += 8) {
			for(int xTile = 0; xTile < mapPixelsWidth; xTile += 8) {
				renderMap(xTile, yTile, tiles[tileIndex++]); //Create tile arr
			}
		}
	}
	
	public void render() {
		int renderStartX = mapPixelsWidth / 2;
		int renderStartY = mapPixelsWidth / 2;
		render(renderStartX, renderStartY);
	}
	
	/*
	public void render() {
		int i = 0;
		int tileIndex = ((int)(i/32) + 1)*(i % 32) + (((mapWidth - width) / 2) / TILE_WIDTH); //****TILE INDEX IS WHAT HAS TO BE SET IN THE CENTER OF THE MAP -- xTile AND yTile REPRESENT TILES ON THE SCREEN
		for(int yTile = 0; yTile < height; yTile += 8) {
			for(int xTile = 0; xTile < width; xTile += 8) {
				tileIndex = ((int)(i/32) + 1)*(i % 32) + (((mapWidth - width) / 2) / TILE_WIDTH);
				render(xTile, yTile, tiles[tileIndex]); //Create tile arr
				i++;
			}
		}
		xScroll++;
		
	}
	*/
	
	//just cuz ur here, lemme say u should add powerups on the ground that make you faster
	//mapPixels has been initialized -- now render should be shifting mapPixels and sets it to the screen pixels
	//need to draw out the dimensions of tiles[] and mapPixels[] cuz its hard to be sure the width is legit
	public void render(int x0, int y0) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = mapPixels[(x + x0 + xScroll) + (y + y0 + yScroll) * mapPixelsWidth];
			} 
		}
	}
	/*
	public void render(int x0, int y0, int tile) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		for(int y = y0; y < (y0 + 8); y++) {
			for(int x = x0; x < (x0 + 8); x++) {
				//if (x + y * width < pixels.length - 1) {
				pixels[x + y * width] = sheet.pixels[((tileX * TILE_WIDTH) + (x - x0)) + (SHEET_WIDTH * ((tileY * TILE_WIDTH) + (y - y0)))];
				
			} 
		}
	}
	*/
	
	
	public void renderMap(int x0, int y0, int tile) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		for(int y = y0; y < (y0 + 8); y++) {
			for(int x = x0; x < (x0 + 8); x++) {
				//if (x + y * width < pixels.length - 1) {
				mapPixels[x + y * mapPixelsWidth] = sheet.pixels[((tileX * TILE_WIDTH) + (x - x0)) + (SHEET_WIDTH * ((tileY * TILE_WIDTH) + (y - y0)))];
				
			} 
		}
	}

}
