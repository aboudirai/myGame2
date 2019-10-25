package game.sprites;

import game.SpriteSheet;

public class Gator extends Player {

	public static int[] tiles = {160, 161, 192, 193};
	public static int spriteWidth = 8;
	public static int spriteHeight = 4;
	public static int[][] colors = {{-1, -1, -1}, {35, 49, 220}, {255,255,255}, {0, 0, 0}, {255,255,0}};
	
	public Gator(SpriteSheet sheet) {
		super(sheet, tiles, colors, spriteWidth, spriteHeight);
	
	}
	
}
