import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class BigEgg {
	private static Random r = new Random();
	int row;
	int col;
	private Color color = Color.GREEN;
	public BigEgg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public BigEgg(){
		
		this(0,0);
	}
	public Rectangle getRectangle(){
		return new Rectangle(this.col*Yard.BLOCKS,this.row*Yard.BLOCKS,Yard.BLOCKS,Yard.BLOCKS);
	}
	public void draw(Graphics g){
		Color c = g.getColor();
		
		//Color color = Color.GREEN;
		g.setColor(color);
		g.fillOval(Yard.BLOCKS * col, Yard.BLOCKS * row, Yard.BLOCKS, Yard.BLOCKS);
		
		if(color == Color.GREEN)
			color = Color.RED;
		else 
			color = Color.GREEN;
		g.setColor(c);
		
	}
	public void reAppear(Egg e,Graphics g) {
		if(e.num % 5==0 && e.num !=0)
		{
			this.row = r.nextInt(Yard.ROWS-3)+2;
			this.col = r.nextInt(Yard.COLS-2)+1;
			this.draw(g);
		}
		else{
		this.row = 0;
		this.col = 0;
		}
		/*System.out.println("row=="+row);
		System.out.println("col=="+col);*/
	
	}
	public void disAppera(){
		
		this.row = 0;
		this.col = 0;
	}
	
	

}
