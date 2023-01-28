import java.util.Comparator;
 

public class PlayerIDSorter  implements Comparator<Players>{
	
	public int compare(Players p1, Players p2) {
        return p2.getPlayerId().compareToIgnoreCase(p1.getPlayerId());
    }

}
