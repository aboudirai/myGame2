package game.sprites;

import game.SpriteSheet;

public class Tiger extends Enemy {

	public static int[] tiles = {288, 289, 320, 321};
	public static int spriteWidth = 8;
	public static int spriteHeight = 4;
	public static int[][] colors = {{-1, -1, -1}, {255, 95, 2}, {0,0,0}, {255, 255, 255}, {255, 148, 17}};
	
	public Tiger(SpriteSheet sheet) {
		super(sheet, tiles, colors, spriteWidth, spriteHeight);	
	}
	
}
