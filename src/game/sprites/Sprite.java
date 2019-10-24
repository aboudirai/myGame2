package game.sprites;

import java.util.Random;

import game.SpriteSheet;

public class Sprite {

	public static final int TILE_COUNT = 32;
	public static final int SCREEN_WIDTH = 160;
	public static final int SCREEN_HEIGHT = 120;
	public int[] tiles;
	public int[][] spriteStates = new int[8][4]; // used for sprite animation
	public int spriteState; // current state in SpriteStates sprite is in

	public int[][] colors;
	public int spriteWidth;
	public int spriteHeight;
	public SpriteSheet sheet;
	public Random random = new Random();

	public Sprite(SpriteSheet sheet, int[] tiles, int[][] colors, int spriteWidth, int spriteHeight) {
		this.tiles = tiles;
		this.colors = colors;
		this.sheet = sheet;
		this.spriteState = 0;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;		
		initSpriteStates(tiles[0]);
		setColors();

	}

	public void initSpriteStates(int startTile) {
		int i = 0;
		spriteStates[i] = tiles;
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 4; x++) {
				int[] tempArr = new int[4];
				for (int j = 0; j < tempArr.length; j++){
					tempArr[j] = spriteStates[0][j] + (x * 2) + (y * TILE_COUNT * 2);
				}
				spriteStates[i++] = tempArr;
			}
		}
	}

	public void setColors() {
		// for (int i )
		sheet.setColors(spriteStates[0], colors, spriteWidth, spriteHeight);	
		
		
	}

	public int[] getTiles() {
		return tiles;
	}

	// potentially only for main player
	public void renderSprite(int[] pixels, int x0, int y0, int xScroll, int yScroll, int dir) {
		int dirState = dir * 2;
		spriteState = dirState; 
		if (dir == 0 || dir == 2) {
			if (Math.abs(yScroll) % 20 == 0) {
				tiles = spriteStates[spriteState];
			}
			if (Math.abs(yScroll) % 20 == 10) {
				tiles = spriteStates[spriteState + 1];
			}
		}
		if (dir == 1 || dir == 3) {
			if (Math.abs(xScroll) % 20 == 0) {
				tiles = spriteStates[spriteState];
			}
			if (Math.abs(xScroll) % 20 == 10) {
				tiles = spriteStates[spriteState + 1];
			}
		}
		
		int i = 0;
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 2; x++) {
				renderTile(pixels, tiles[i++], x0 + (8 * x), y0 + (8 * y));
			}
		}
	}

	public void renderTile(int[] pixels, int tile, int x0, int y0) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		int sheetPixel;
		for (int y = y0; y < (y0 + 8); y++) {
			for (int x = x0; x < (x0 + 8); x++) {
				// if (x + y * width < pixels.length - 1) {
				sheetPixel = sheet.pixels[((tileX * sheet.TILE_WIDTH) + (x - x0)) + (sheet.SHEET_WIDTH * ((tileY * sheet.TILE_WIDTH) + (y - y0)))];
				if (sheetPixel != -1) { // handling transparency
					pixels[x + y * SCREEN_WIDTH] = sheetPixel;
				}

			}
		}
	}

}
