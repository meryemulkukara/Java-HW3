
public class Ork extends Players  {

	public Ork(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}

	
	public int getHP() {return Constants.orkHP;}
	public int getAP() {return Constants.orkAP;}
	public int maxMove() {return Constants.orkMaxMove;}
	
	public String toString()
	{
		return String.format(super.toString() +"\t("+ getHP() + ")");
	}

	//public void setHealth(int health,int attack) {};
}
