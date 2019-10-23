package game.tiles;

import java.util.Random;

import game.SpriteSheet;

public class Tile {
	
	public static final int TILE_COUNT = 32;
	public int tile;
	public int[][] colors;
	public SpriteSheet sheet;
	public Random random = new Random();
	
	public Tile(SpriteSheet sheet, int tile, int[][] colors) {
		this.sheet = sheet;
		this.tile = tile;
		this.colors = colors;
	}
	
	public void setColors() {
		sheet.setColors(tile, colors);
	}
	
	public int getTile() {
		return tile;
	}
	
	public void render() {
		
	}
	
	
	
	
	
	
	
}
