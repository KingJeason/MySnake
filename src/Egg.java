import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	private static Random r = new Random();
	int row;
	int col;
	public static int num = 0;
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
		
	}
	public Egg(){
		
		this(r.nextInt(Yard.ROWS-3)+2,r.nextInt(Yard.COLS-2)+1);
		
		
	}
	public Rectangle getRectangle(){
		return new Rectangle(this.col*Yard.BLOCKS,this.row*Yard.BLOCKS,Yard.BLOCKS,Yard.BLOCKS);
	}
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.green);
		g.fillOval(col * Yard.BLOCKS, row * Yard.BLOCKS, Yard.BLOCKS, Yard.BLOCKS);
		
		g.setColor(c);
		
	}
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS-3)+2;
		this.col = r.nextInt(Yard.COLS-2)+1;
		num++;
		System.out.println("num=="+num);
	}


}
