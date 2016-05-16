import java.awt.*;
import java.awt.event.KeyEvent;


public class Snake {
	Node head = null;
	Node tail = null;
	int size = 1;
	private Node  n = new Node(15,15,Dir.L);
	Snake(){
		head = n;
		tail = n;
		size = 1;		
	}
	public void addTotail(){
		Node node = null;//这样先定义就可以不必担心重复定义变量名字了
		switch(tail.dir){
		case L:
			 node = new Node(tail.row,tail.col + 1, tail.dir);
			 break;
		case R:
			 node = new Node(tail.row,tail.col - 1, tail.dir);
			 break;
		case U:
			 node = new Node(tail.row  + 1, tail.col, tail.dir);
			break;
		case D:
			 node = new Node(tail.row - 1, tail.col, tail.dir);
		
		}
		node.prev = tail;
		tail.next = node;
		tail = node;
		size++;
		
	}
	public void addTohead(){
		Node node = null;
		switch(head.dir){
		case L:
			if(head.col == 1)
				node = new Node(head.row, Yard.COLS-2, head.dir);
			else
				node = new Node(head.row,head.col -1, head.dir);
			 break;
		case R:
			if(head.col == Yard.COLS-2)
				node = new Node(head.row, 1, head.dir);
			else
				node = new Node(head.row,head.col + 1, head.dir);
			 break;
		case U:
			if(head.row == 2)
				node = new Node(Yard.ROWS-2, head.col, head.dir);
			else
				node = new Node(head.row - 1, head.col, head.dir);
			break;
		case D:
			if(head.row == Yard.ROWS -2)
				node = new Node(2, head.col, head.dir);
			else
				node = new Node(head.row + 1, head.col, head.dir);
		}
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}
	public void draw(Graphics g){
		if(size <= 0) return;
		move();
		for(Node n = head; n != null; n = n.next) {
			n.draw(g);
		}
	} 	
	
	private void move() {
		addTohead();
		deletefromtail();
		
	}

	private void deletefromtail() {
		if(size ==0)return;
		tail = tail.prev;
		tail.next = null;
		
	}

	private class Node{
		 
		int row;
		int col;
		Node next = null;
		Node prev = null;
		Dir dir = null;
		Node(int row, int col,Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		 void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.black);
			g.fillRect(col * Yard.BLOCKS, row * Yard.BLOCKS, Yard.BLOCKS, Yard.BLOCKS);
			g.setColor(c);
			
		}
	}
	public void eat(Egg e){
		if(this.getRectangle().intersects(e.getRectangle())){
			System.out.println("重合了");
			System.out.println("row :=="+e.row+"col :== "+e.col);
			e.reAppear();
			this.addTohead();
			
		}
	}
	public Rectangle getRectangle(){
		return  new Rectangle(head.col*Yard.BLOCKS,head.row*Yard.BLOCKS,Yard.BLOCKS,Yard.BLOCKS);
	}

	public void keypressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			 this.head.dir = Dir.L;	
			 break;
		case KeyEvent.VK_RIGHT:
			 this.head.dir = Dir.R;	
			 break;
		case KeyEvent.VK_UP:
			 this.head.dir = Dir.U;	
			 break;
		case KeyEvent.VK_DOWN:
			 this.head.dir = Dir.D;	
			 break;
		}
	}
	

}
