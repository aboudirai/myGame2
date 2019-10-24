package game;

import java.util.Random;

import game.sprites.Gator;
import game.tiles.DirtTile;
import game.tiles.GrassTile;

public class Screen {

	public final int width, height;
	private SpriteSheet sheet;
	public int[] pixels;
	public int[] tiles;
	public int[] mapPixels;
	private static final int TILE_COUNT = 32;
	private static final int TILE_WIDTH = 8;
	private static final int SHEET_WIDTH = 256;
	private static final int MAP_SCALE = 1;
	private int mapWidth;
	private int mapPixelsWidth;
	private Random random = new Random();
	protected int xScroll = 0;
	protected int yScroll = 0;
	protected int dir = 2;
	
	//Create World Generator Class
	public GrassTile grass;
	public DirtTile dirt;
	public Gator gator;
	
	public Screen(int width, int height, SpriteSheet sheet) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.mapWidth = MAP_SCALE * width;
		grass = new GrassTile(sheet);
		dirt = new DirtTile(sheet);
		gator = new Gator(sheet);
		
		/*
		for(int y = 5; y < 7; y++) {
			for(int x = 0; x < 8; x++) {
				int[][] gatorColors = {{-1, -1, -1}, {35, 49, 196}, {0,0,0}, {35, 49, 196}};
				sheet.setColors(y * TILE_COUNT + x , gatorColors);
			}
		}
		*/
		
		
		pixels = new int[width * height];
		tiles = new int[(mapWidth) * (mapWidth)]; //each element represents 8x8p tile
		mapPixels = new int[tiles.length * 8 * 8];
		mapPixelsWidth =  mapWidth*8;
		setRandomTiles();
	}

	//this render only narrows it down to the tile
	//loop with increments of TILE_WIDTH
	//
	
	public void setRandomTiles(){
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = grass.getTile();
		}
		for (int i = 0; i < tiles.length; i++) {
			if(random.nextInt(20) == 2) {
				tiles[i] = dirt.getTile();
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
	
	
	//AYYYYYYYYY move the renderSprite functionality inside the (Gator / Character) Class
	
	//just cuz ur here, lemme say u should add powerups on the ground that make you faster
	//need to draw out the dimensions of tiles[] and mapPixels[] cuz its hard to be sure the width is legit
	public void render(int x0, int y0) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = mapPixels[(x + x0 + xScroll) + (y + y0 + yScroll) * mapPixelsWidth];
			} 
		}
		
		//Rendering Gator
		int centerX = width / 2 - 8;
		int centerY = height / 2 - 8;
		
		gator.renderSprite(pixels, centerX, centerY, xScroll, yScroll, dir);
		
	}
	
	/*
	public void renderSprite(int x0, int y0, int tile) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		int sheetPixel;
		for(int y = y0; y < (y0 + 8); y++) {
			for(int x = x0; x < (x0 + 8); x++) {
				//if (x + y * width < pixels.length - 1) {
				sheetPixel = sheet.pixels[((tileX * TILE_WIDTH) + (x - x0)) + (SHEET_WIDTH * ((tileY * TILE_WIDTH) + (y - y0)))];
				if (sheetPixel != -1) { //handling transparency
					pixels[x + y * width] = sheetPixel;
				}
				
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
