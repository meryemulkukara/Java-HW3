
public class Troll  extends Players  {

	public Troll(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}

	
	public int getHP() {return Constants.trollHP;}
	public int getAP() {return Constants.trollAP;}
	public int maxMove() {return Constants.trollMaxMove;}
	
	public String toString()
	{
		return String.format( super.toString() +"\t("+ getHP() + ")");
	}
	
	//public void setHealth(int health,int attack) {};
}
 