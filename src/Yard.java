import java.awt.*;
import java.awt.event.*;
public class Yard extends Frame {
	
	//设置行列。以及间隔
	public static final int ROWS = 20;
	public static final int COLS = 20;
	public static final int BLOCKS = 20;
	Snake s = new Snake();
	Egg e = new Egg();
	Image offScreenImage = null;
	public static void main(String[] args) {
		new Yard().go();
	}
	public void go(){
		this.setBounds(200, 200, COLS * BLOCKS, ROWS * BLOCKS);
		this.setVisible(true);
		this.addWindowListener(new myMonitor());
		this.addKeyListener(new myMonitor2());
		this.setBackground(Color.GRAY);
		 new Thread(new Mypaint()).start();
	}
	class myMonitor extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
		
	}
	class myMonitor2 extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			s.keypressed(e);
		}
		
	}
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		// 17*18矩阵
		g.fillRect(0, 0, COLS * BLOCKS, ROWS * BLOCKS);
		g.setColor(Color.red);
		for(int i = 1; i < ROWS; i++){
			g.drawLine( BLOCKS, i * BLOCKS, (COLS-1) * BLOCKS, i * BLOCKS);//绘制行线.
			//System.out.println("i==1 "+ i);
		}
		for(int j = 1; j < COLS; j++){
			g.drawLine(j * BLOCKS, 2 * BLOCKS, j * BLOCKS, (ROWS-1) * BLOCKS);//绘制列线 .
			//System.out.println("j==" +j);
		}
		s.eat(e);
		s.draw(g);
		e.draw(g);
		g.setColor(c);
	
	}
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCKS, ROWS * BLOCKS);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	private class Mypaint implements Runnable{

		@Override
		public void run() {
			while(true){
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			
		}
		
	}
	}
	


