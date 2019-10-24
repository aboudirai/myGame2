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
	public static final int WIDTH = 160
			;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;
	public boolean running = false;
	public int ticks;
	public int xScroll = 0;
	public int yScroll = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private InputHandler input = new InputHandler(this);
	
	public void start() {
		running = true;
		new Thread(this).start();
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
		double bufferTime = 0;
		double nsPerTick = 1000000000.0 / 60;
		long time0 = System.nanoTime();
		ticks = 0;
		init();
		
		while(running) {
			boolean shouldRender = true;
			long time1 = System.nanoTime();
			bufferTime += (time1 - time0) / nsPerTick;
			render();
			ticks++;
			tick();
			
			while(bufferTime >= 1) {
				bufferTime -= 1;
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				render();
			}
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
		if (ticks % 4 == 0) { //slows down motion
			if (input.up) {
				screen.yScroll--;
				screen.dir = 0;
			}
			if (input.down) {
				screen.yScroll++;
				screen.dir = 2;
			}
			if (input.right) {
				screen.xScroll++;
				screen.dir = 1;
			}
			if (input.left) {
				screen.xScroll--;
				screen.dir = 3;
			}
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
		game.start();
	}

	
	
}
