
public class Elf extends Players  {

	public Elf(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}

	
	public int getHP() {return Constants.elfHP;}
	public int getAP() {return Constants.elfAP;}
	public int maxMove() {return Constants.elfMaxMove;}
	

	
	public String toString()
	{
		return String.format( super.toString() +"\t("+ getHP() + ")");
	}
	
	//public void setHealth(int health,int attack) {};
} 