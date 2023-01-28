
public class Goblin  extends Players  {

	public Goblin(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}

	
	public int getHP() {return Constants.goblinHP;}
	public int getAP() {return Constants.goblinAP;}
	public int maxMove() {return Constants.goblinMaxMove;}
	
	public String toString()
	{
		return String.format( super.toString() +"\t("+ getHP() + ")");
	}
	
	
	//public void setHealth(int health,int attack) {};
}
