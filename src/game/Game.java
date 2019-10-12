package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static final long serialVersionUID = 1L;
	
	public static final String NAME = "Untitled Game";
	public static final int WIDTH = 160;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	public boolean running = false;
	public int xScroll = 0;
	public int yScroll = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	
	public void start() {
		running = true;
		System.out.println("here1");
		new Thread(this).start();
		System.out.println("here2");
	}
	
	public void stop() {
		running = false;
	}
	
	public void init() {
		
		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/resources/textureMap.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		while(running) {
			System.out.println(xScroll);
			render();
			tick();
		}
	}
	
	public void tick() {
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		screen.render();
		
		for (int y = 0; y < screen.height; y++) {
			for(int x = 0; x < screen.width; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.width];
			}
		}
		
		
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE ));
		
		JFrame frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		System.out.println("here");
		game.start();
	}

	
	
}
