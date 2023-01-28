
public class Players extends Constants{

	private int  board;
	public  String side; //about group, zorde vs calliance 
	private String playerId;
	private int column;
	private int row;
	private int healthPoint;
	private int attackPoint;
	
	public Players(int board,String s,String pID,int c, int r,int  health, int attack) {
		this.board=board;
		playerId=pID;
		setColumn(c);
		setRow(r);
		healthPoint=health;
		attackPoint=attack;
		side=s;
	}
	
	
	public  int getHP() {return 0;};
	public  int getAP() {return 0;};
	public  int maxMove() {return 0;};
	

	
	public int getBoard() {
		return board;
	}


	public void setBoard(int board) {
		this.board = board;
	}

	public int getColumn() {
		return column;
	}
	
	public void setColumn(int c)
	{
		column=c;
	}
	
	public boolean setNewColumn(int move){
		
			int c =getColumn()+move;
			if(c>=getBoard()|| c<0)
				return false;
			else
				column=c;
			return true;
			
	}
	

	public int getRow() {
		return row;
	}
	
	public void setRow(int r)
	{
		row=r;
	}

	public boolean setNewRow(int move) {
	
			
			int r =getRow()+move;

			if(r>=getBoard()|| r<0)
			{
				return false;
			}
			else
				row=r;
			return true;		
	}

	public String getSide()
	{
		return side;
	}
	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	public void setHealth(int health) {
		
			healthPoint=health;
	};
	
	public int newHealth(int h) {
		int newHealth=getHealth()-h;
		if(newHealth<0)
			return 0;
		else
			return newHealth;
	};
	
	public int getHealth()
	{
		return healthPoint;
	}

	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}
	
	public String toString()
	{
		return String.format( getPlayerId()+"\t"+getHealth());
	}
}
