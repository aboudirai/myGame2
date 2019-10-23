package game.sprites;

import java.util.Random;

import game.SpriteSheet;

public class Sprite {
	
	public static final int TILE_COUNT = 32;
	public static final int SCREEN_WIDTH = 160;
	public static final int SCREEN_HEIGHT = 120;
	public int[] tiles;
	public int[] spriteStates = new int[8]; // used for sprite animation
	public int[][] colors;
	public int spriteWidth;
	public int spriteHeight;
	public SpriteSheet sheet;
	public Random random = new Random();
	
	public Sprite(SpriteSheet sheet, int[] tiles, int[][] colors, int spriteWidth, int spriteHeight) {
		this.tiles = tiles;
		this.colors = colors;
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		setColors();
		
		initSpriteStates(tiles[0]);
	}
	
	//arr with the start corners of every Sprite State (walking1, walking2, standing)
	//CHANGE TO arr of arrs of the diff tile combos for each sprite
	public void initSpriteStates(int startTile) {
		int i = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 2; x++) {
				spriteStates[i++] = startTile + (x * spriteWidth) + (y * sheet.TILE_COUNT * spriteHeight);
			}
		}
	}
	
	
	public void setColors() {
		//for (int i )
		sheet.setColors(tiles, colors, spriteWidth, spriteHeight);
		int[] tiles2 = {162, 163, 194, 195};
		sheet.setColors(tiles2, colors, spriteWidth, spriteHeight);
		
	}
	
	public int[] getTiles() {
		return tiles;
	}
	
	//potentially only for main player
	public void renderSprite(int[] pixels, int x0, int y0, int xScroll, int yScroll) {
		if (Math.abs(yScroll) % 16 == 0){
			int[] newTiles = {162, 163, 194, 195};
			tiles = newTiles;
			
		}
		if (Math.abs(yScroll) % 16 == 8){
			int[] newTiles = {160, 161, 192, 193};
			tiles = newTiles;
		}
		int i = 0;
		for(int y = 0; y < 2; y++) {
			for(int x = 0; x < 2; x++) {
				renderTile(pixels, tiles[i++], x0 + (8 * x), y0 + (8 * y));
			}
		}
	}
	
	public void renderTile(int[] pixels, int tile, int x0, int y0) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		int sheetPixel;
		for(int y = y0; y < (y0 + 8); y++) {
			for(int x = x0; x < (x0 + 8); x++) {
				//if (x + y * width < pixels.length - 1) {
				sheetPixel = sheet.pixels[((tileX * sheet.TILE_WIDTH) + (x - x0)) + (sheet.SHEET_WIDTH * ((tileY * sheet.TILE_WIDTH) + (y - y0)))];
				if (sheetPixel != -1) { //handling transparency
					pixels[x + y * SCREEN_WIDTH] = sheetPixel;
				}
				
			} 
		}
	}
	
}
