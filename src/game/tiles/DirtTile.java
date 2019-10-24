package game.tiles;

import game.SpriteSheet;

public class DirtTile extends Tile {
	
	public static final int[][] colors = {{185, 135, 61}, {220, 169, 93}};
	public static final int tile = 33;
	public SpriteSheet sheet;
	
	public DirtTile(SpriteSheet sheet) {
		super(sheet, tile, colors);
		this.sheet = sheet;
		System.out.println(sheet);
		setColors();
	}

}
