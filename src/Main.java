import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Constants{

	//Reads the file and saves the lines into a String[] array.Then returns it
    public static String[] readFile(String path){
        try {
            int i=0;
            int lenght= Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[lenght];
            for (String line : Files.readAllLines(Paths.get(path))) {
            	if(line.equals("\\n"))
            		continue;
                results[i++]=line;
            }
            return results;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
    //READ_PLAYER 
    public static void read_player(int board,String line,ArrayList <Players>players,  ArrayList <String> output)
    {
    	String [] PlayerArr=line.split(" "); 
    	int p1=Integer.parseInt(PlayerArr[2]);
    	int p2=Integer.parseInt(PlayerArr[3]);
    	try{
    		if (p1<0 || p1>=board || p2<0 || p2>=board)
    			throw new NotBoardIndexException();
    		else   add_player(board,PlayerArr[0],PlayerArr[1],p1,p2,players); 
    			
    	} catch  		(NotBoardIndexException e) 
    	{ 
    		output.add("Error : Game board boundaries are exceeded. Input line ignored.");
    		}
    	}
    
    //ADD_PLAYER method add line by line playerID, int leftRight, int upDown, int healthPoint
    public static void add_player(int board,String class_name,String id,int column,int row,ArrayList <Players>players	)
    {
    	//add ELF character
    	if(class_name.equals("ELF"))
    		players.add(new Elf(board,"CALLIANCE",id,column,row,Constants.elfHP,Constants.elfAP));
    	//add DWARF character
    	if(class_name.equals("DWARF"))
    		players.add(new Dwarf(board,"CALLIANCE",id,column,row,Constants.dwarfHP,Constants.dwarfAP));
    	if(class_name.equals("ORK"))
    				players.add(new Ork(board,"ZORDE",id,column,row,Constants.orkHP,Constants.orkHP));
    	if(class_name.equals("TROLL"))
        				players.add(new Troll(board,"ZORDE",id,column,row,Constants.trollHP,Constants.trollAP));
        if(class_name.equals("GOBLIN"))
            				players.add(new Goblin(board,"ZORDE",id,column,row,Constants.goblinHP,Constants.goblinAP));
        if(class_name.equals("HUMAN"))
    		players.add(new Human(board,"CALLIANCE",id,column,row,Constants.humanHP,Constants.humanAP));
       }  	
           
    public static void read_command(int board,String line,ArrayList <Players>players, ArrayList <String> output)
    {
    	String [] CommandArr=line.split(" "); 
        String[] number_of_move= CommandArr[1].split(";");
        int cont_error=5;
        String error_message="";
        try{
        	for(Players p1: players)
        	{
        		//find which character
        		if(p1.getPlayerId().equals(CommandArr[0]))
        		{	//control MoveCountCheckException 
        			if(number_of_move.length/2!=p1.maxMove())
        			{   throw new MoveCountCheckException();}
        			else
        			{	cont_error=movement(/*class_name,*/CommandArr[0],number_of_move,players);
        				if(cont_error!=-1)
        					error_message+="Error : Game board boundaries are exceeded. Input line ignored.\n";	
        				break;
        			}	
        		}	
        	} 		
        } catch   (MoveCountCheckException e) 
       {	cont_error=0;
       			error_message +="Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n";
       		}
        
        if(cont_error!=0 )
        {	output.add(printOut(board,players));
        	output.add(error_message);
        	}
        if(cont_error==0)
        {	 output.add(error_message);        	}
        
        		
    }
    	
    public static int movement(String id, String [] move,ArrayList <Players>players )
    {
    	
    	int[] arrColumn=new int[move.length/2];
    	int[] arrRow=new int[move.length/2];
    	int j=0;
    	//row and column move arrays
    	for(int i=0;i< move.length; i=i+2)
    	{	
    		arrColumn[j]=Integer.parseInt(move[i]);
    		arrRow[j]=Integer.parseInt(move[i+1]);
    		j++;
    	}
     	boolean move_onto=true;
   		int i1=0;
   		for(i1=0;i1<players.size();i1++)
    	{	
   			Players p=players.get(i1);
			if( p.getPlayerId().equals(id))
    		{	
				//Before they start a move sequence, Orks will first heal any friendly character in one of
    			//the neighboring cells.
    			if(p.getClass().getName()=="Ork")
    			{
    				try {
    					boolean newOrkRow=p.setNewRow( arrRow[0]);
    					boolean newOrkColumn=p.setNewColumn( arrColumn[0]);
						if(newOrkRow==false|| newOrkColumn==false)
						{
							throw new NotBoardIndexException();
						}
    					else {
    						p.setNewRow(- arrRow[0]);
    						p.setNewColumn(- arrColumn[0]);
    						for(Players cont_ork:players)
    						{
    							
    							if(cont_ork.getRow()<=(p.getRow()+1) && cont_ork.getRow()>=(p.getRow()-1) 
    									&& cont_ork.getColumn()>=(p.getColumn()-1) &&cont_ork.getColumn()<=(p.getColumn()+1) && cont_ork.side.equals(p.side) )
    							{
    								if( cont_ork.newHealth(-Constants.orkHealPoints) >= cont_ork.getHP()  )//if HP is bigger than constant hit point
    								{   	
    									cont_ork.setHealth(cont_ork.getHP());    }  // Hit Point must be maximum constant hit
    								else cont_ork.setHealth(cont_ork.newHealth(-Constants.orkHealPoints));
    							}//if for add ork heal points
    						}
    					}
					}catch	(NotBoardIndexException e) 
	    			{	return 0;
	    			}
    			}
    			int return_val=0;
    			int k=0;
    			for(k=0; move_onto&&(k< arrColumn.length);k++)
    			{	
    				try{
    					boolean newRow=p.setNewRow( arrRow[k]);
    					boolean newColumn=p.setNewColumn( arrColumn[k]);
    					//control NotBoardIndexException
    					if(newRow==false|| newColumn==false)
    					{
    						return_val=k;
    						throw new NotBoardIndexException();
    					}
    					else {
    					
    						int move_onto_1=0;
							for(move_onto_1=0;move_onto_1<players.size();move_onto_1++)
							{	
								Players p_move_onto=players.get(move_onto_1);
								//Characters can move onto the enemy characters. If such a situation occurs ; 
								if(!p_move_onto.equals(p)&&p_move_onto.getRow()==p.getRow() && p_move_onto.getColumn()==p.getColumn() && move_onto && p_move_onto.side!=p.side )
								{
									move_onto=false;
									if(p.getHealth()>p_move_onto.newHealth(p.getAP()))
									{
										//System.out.println()
										int pNewHealth=p.newHealth(p_move_onto.newHealth(p.getAP()));
										p.setHealth(pNewHealth);
										players.remove(players.indexOf(p_move_onto)); 
										break;
									}
									else{
										if(p.getHealth()<p_move_onto.newHealth(p.getAP()))
										{
											int p1NewHealth=p_move_onto.newHealth(p.getAP()+ p.getHealth()) ;
											p_move_onto.setHealth(p1NewHealth);
											players.remove(players.indexOf(p)); 
											break;
										}	
										else{
    											players.remove(players.indexOf(p)); 
    											players.remove(players.indexOf(p_move_onto));
    											break;
										}	
									}
								}	
    							
								//Characters cannot move onto the friendly characters. If a character tries to move onto a friendly character,
								//its move is finalized at the current location and the rest of its move sequence will be ignored.
								if(	!p_move_onto.equals(p)&&	p_move_onto.getRow()==(p.getRow()) && p_move_onto.getColumn()== (p.getColumn()) && p_move_onto.side==p.side)
								{
									p.setNewRow(- arrRow[k]);
									p.setNewColumn(- arrColumn[k]);
									k= arrColumn.length+1;
									move_onto=false;
									break;
								}// end cannot move onto friendly character control
    					
							}
    						int j1=0;
    						for(j1=0;j1<players.size()&&move_onto;j1++)
    						{	
    							Players p1=players.get(j1);
    							//If an Elf’s move sequence is interrupted by moving through an enemy character...
    							if(p.getClass().getName()=="Elf"&& k==3)
    							{
    								for(Players cont_elf:players)
    								{
    									if(!cont_elf.equals(p)&& cont_elf.getRow()<=(p.getRow()+2) && cont_elf.getRow()>=(p.getRow()-2) 
    											&& cont_elf.getColumn()>=(p.getColumn()-2) &&cont_elf.getColumn()<=(p.getColumn()+2) && !cont_elf.side.equals(p.side) )
    									{
    										if( cont_elf.newHealth(Constants.elfRangedAP) <= 0  )
    										{   	
    											players.remove(players.indexOf(cont_elf));
    										}  
    										else {
    											int NewHealth=cont_elf.newHealth(Constants.elfRangedAP);
    											cont_elf.setHealth(NewHealth);
    										}
    									}//if for add ork heal points
    		    					
    								}
    								break;
    							
    							}
    							
    							//normal attack
    							if(!p1.equals(p) && p1.getRow()<=(p.getRow()+1) && p1.getRow()>=(p.getRow()-1) 
    									&& p1.getColumn()>=(p.getColumn()-1) && p1.getColumn()<=(p.getColumn()+1) 
    										&& !p1.side.equals(p.side))
    							{
    								//human have 3 move step but attacks after the final step. Control final step
    								if(p.getClass().getName().equals("Human")&& k!=2)
    								{	continue;	}
    								else { //others
    									if(p1.newHealth(p.getAP())<=0 )
    									{	players.remove(players.indexOf(p1));
    										if(i1>j1)
    											i1--;
    										j1--;
    									}
    									else {
    										int NewHealth=p1.newHealth(p.getAP());
    										p1.setHealth(NewHealth);				}
    								}
    							} 		
    						}
    					}
    				}catch	(NotBoardIndexException e) 
    	    		{	 return return_val;		}				
    			}//end of for loop, move step
    		return -1;
    		}	//end of player id control if 
    	}
   		return -1;
    }
    	
    //this method create string(characters situations and characters information) for output file. 
    public static String printOut(int board,ArrayList <Players> players)
    {
    	String out="";
    	for(int i=0;i<=board;i++)
    		out +="**";
    	out+="\n";
    	String arr[][]=new String[board][board];
    	for(Players p:players)
    		arr[p.getRow()][p.getColumn()]=p.getPlayerId();
    	for(int i=0;i<board;i++)
    	{	out+="*";
    		for(int j=0;j<board;j++)
    		{
    			if(arr[i][j]==null)
    				out +="  ";
    			else
    				out +=arr[i][j];
    		}
    		out +="*\n";
    	}
    	for(int i=0;i<=board;i++)
    		out +="**";
    	out+="\n\n";
    	players.sort(new PlayerIDSorter());
    	Collections.reverse(players);
    	for(Players p:players)
    	{
    		out +=p.toString();
    		out +="\n";
    	}
    	out +="\n";
    	return out;
    }
    
    //method checks all players if they are on the same side
    public static boolean game_finishes_control(ArrayList<Players> players)
    {
    	
    	Players cont_p=players.get(0);
    	for(Players p:players)
    	{	if(!p.side.equals(cont_p.side))
    			return false;
    	}
    	return true;	
    }
    
    //create output file
    public static void output_file(String file_name,String winner_team,ArrayList <String> output)
    {
    	try {
            File file = new File(file_name);
            if (file.createNewFile()) {
              //System.out.println("File created: " + file.getName());
            } else {
              //System.out.println("File already exists.");
            }
            FileWriter writer = new FileWriter(file);
            for(String out :output)
            {     writer.write(out);    }     
            writer.write("\n\nGame Finished\n");
            if(winner_team.toLowerCase().equals("calliance"))
            	writer.write("Calliance Wins\n");
            else 
            	writer.write("Zorde Wins\n");
    	    writer.close();
               
    	}catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList <Players>players=new ArrayList<>();
		ArrayList <String>output=new ArrayList<>();
        //read initials.txt
        String[] lines_initials=readFile(args[0]);//read author.txt
        String b[]=lines_initials[1].split("x");        
        int board=Integer.parseInt(b[0]);
        for(int i=2;i<lines_initials.length;i++)
        {  
        	String[] lines_first= lines_initials[i].split(" ");
        	if( lines_first[0].equals("ELF") || lines_first[0].equals("DWARF") || lines_first[0].equals("HUMAN")
        			|| lines_first[0].equals("GOBLIN") || lines_first[0].equals("TROLL") || lines_first[0].equals("ORK"))
        	{	
        		read_player(board,lines_initials[i],players,output);        	}        	
        }
        
        output.add(printOut(board,players));
        //read commands.txt
        String[] lines_commands=readFile(args[1]);//read film.txt
        for(String line:lines_commands)
        {   
        	read_command(board,line,players,output);
        	if(game_finishes_control(players))
        		break;
        }
        output_file( args[2],players.get(0).side, output);
        
	}

}
