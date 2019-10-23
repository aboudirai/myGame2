package game.tiles;

import game.SpriteSheet;

public class FlowerTile extends Tile {
	
	public static final int[][] colors = {{0, 204, 102},{250, 0, 117},{238, 242, 17}, {0,0,0}};
	public static final int tile = 33;
	public SpriteSheet sheet;
	
	public FlowerTile(SpriteSheet sheet) {
		super(sheet, tile, colors);
		this.sheet = sheet;
		System.out.println(sheet);
		setColors();
	}

}
