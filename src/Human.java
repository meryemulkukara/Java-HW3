
public class Human extends Players  {

	
	public Human(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}

	
	public int getHP() {return Constants.humanHP;}
	public int getAP() {return Constants.humanAP;}
	public int maxMove() {return Constants.humanMaxMove;}
	
	public String toString()
	{
		return String.format( super.toString() +"\t("+ getHP() + ")");
	}
	
	//public void setHealth(int health,int attack) {};
} 