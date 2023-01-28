
public class Dwarf  extends Players  {

	public Dwarf(int  b,String s,String pID,int LR, int UD,int  health, int attack ) {
		// TODO Auto-generated constructor stub
		super(b, s,pID,LR , UD, health, attack );
	}


	
	public int getHP() {return Constants.dwarfHP;}
	public int getAP() {return Constants.dwarfAP;}
	public int maxMove() {return Constants.dwarfMaxMove;}
	
	public String toString()
	{
		return String.format(super.toString()+"\t("+ getHP() + ")");
	}
}