import java.awt.*;
import java.awt.event.*;
public class Yard extends Frame {
	
	//�������С��Լ����
	public static final int ROWS = 20;
	public static final int COLS = 20;
	public static final int BLOCKS = 20;
	Mypaint gogogo = new Mypaint();
	private boolean gameOver = false;
	Snake s = new Snake(this);
	Egg e = new Egg();
	BigEgg b = new BigEgg();
	Image offScreenImage = null;
	private Font fontGameOver = new Font("����", Font.BOLD, 50);
	private Font isDefault = new Font(null);
	public static void main(String[] args) {
		new Yard().go();
	}
	public void go(){
		this.setBounds(200, 200, COLS * BLOCKS, ROWS * BLOCKS);
		this.setVisible(true);
		this.addWindowListener(new myMonitor());
		this.addKeyListener(new myMonitor2());
		this.setBackground(Color.GRAY);
		
		 new Thread(gogogo).start();
	}
	public void stop(Mypaint g){
		gameOver = true;
	}
	class myMonitor extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
		
	}
	class myMonitor2 extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_F3) {
				if(gogogo.pause == true)
					gogogo.pause = false;
				else
					gogogo.pause = true;
			}
			s.keypressed(e);
		}
		
	}
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		// 17*18����
		g.fillRect(0, 0, COLS * BLOCKS, ROWS * BLOCKS);
		g.setColor(Color.red);
		for(int i = 1; i < ROWS; i++){
			g.drawLine( BLOCKS, i * BLOCKS, (COLS-1) * BLOCKS, i * BLOCKS);//��������.
			//System.out.println("i==1 "+ i);
		}
		for(int j = 1; j < COLS; j++){
			g.drawLine(j * BLOCKS, 2 * BLOCKS, j * BLOCKS, (ROWS-1) * BLOCKS);//�������� .
			//System.out.println("j==" +j);
		}
		g.setColor(Color.black);
		g.drawString("��F2������Ϸ", 120, 40);
		g.drawString("��F3��ͣ/����", 240, 40);
		if(gameOver) {
			g.setFont(fontGameOver);
			g.setColor(Color.PINK);
			g.drawString("��Ϸ����", 100, 180);	
			g.setFont(isDefault);
			gogogo.pause();
		}
		s.eat(e);
		s.eat(b,e,g);
		s.draw(g);
		e.draw(g);
		//b.draw(g);
		if(e.num % 5 == 0 && e.num != 0){
			
			b.draw(g);
		}
			
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
	public class Mypaint implements Runnable{

		
		private boolean running =  true;
		private boolean pause = false;
		public void run() {
			while(running){
			if(pause)
				continue;
			else
					repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			
		}
		public void pause(){
			this.pause = true;
		}
		public void reStart() {
			this.pause = false;
			s = new Snake(Yard.this);
			e.num=0;
			gameOver = false;
		}
		
	}
	
	}
	


