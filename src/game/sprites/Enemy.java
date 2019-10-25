package game.sprites;

import game.SpriteSheet;

public class Enemy extends Sprite {
	
	public Enemy(SpriteSheet sheet, int[] tiles, int[][] colors, int spriteWidth, int spriteHeight) {
		super(sheet, tiles, colors, spriteWidth, spriteHeight);
	}
	
	//mapPixels in here should work
	//xScroll and yScroll now directly effect sprite being drawn on the screen
	public void renderSprite(int[] mapPixels, int x0, int y0, int xScroll, int yScroll, int dir) {
		int dirState = dir * 2;
		spriteState = dirState; 
		if (direction == dirState) {
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
		}
		else {
			direction = dirState;
			if (dir == 0 || dir == 2) {
				tiles = spriteStates[spriteState];
			}
			if (dir == 1 || dir == 3) {
				tiles = spriteStates[spriteState];
			}
		}
			
		int i = 0;
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 2; x++) {
				renderTile(mapPixels, tiles[i++], x0 + (TILE_WIDTH * x), y0 + (TILE_WIDTH * y));
			}
		}
	}

	public void renderTile(int[] pixels, int tile, int x0, int y0) {
		int tileX = tile % 32;
		int tileY = tile / 32;
		int sheetPixel;
		for (int y = y0; y < (y0 + 8); y++) {
			for (int x = x0; x < (x0 + 8); x++) {
				sheetPixel = sheet.pixels[((tileX * sheet.TILE_WIDTH) + (x - x0)) + (sheet.SHEET_WIDTH * ((tileY * sheet.TILE_WIDTH) + (y - y0)))];
				if (sheetPixel != -1) { // handling transparency
					pixels[x + y * SCREEN_WIDTH] = sheetPixel;
				}

			}
		}
	}

	
}
