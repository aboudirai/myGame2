package game.tiles;

import game.SpriteSheet;

public class GrassTile extends Tile {
	
	public static final int[][] colors = {{0, 204, 102}, {36, 181, 50}};
	public static final int[] tiles = {0,1,2,3};
	public SpriteSheet sheet;
	
	public GrassTile(SpriteSheet sheet) {
		super(sheet, tiles[0], colors);
		this.sheet = sheet;
		setColors();
	}
	
	@Override
	public void setColors() {
		for(int i = 0; i < tiles.length; i++) {
			sheet.setColors(tiles[i], colors);
		}
		
	}
	
	
	public int getTile() {
		return tiles[random.nextInt(tiles.length)];
	}
	
	
	

}


