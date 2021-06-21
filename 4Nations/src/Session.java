import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author ALLARASSEMJJ20
 *
 */
public class Session {

	private int numWins;
	private Arena currentArena;
	private String username;
	private String password;
	private int indexArena;
	private ArrayList <Arena> ArenaList;
	private GameCharacter persona;
	private final int SIZE = 5;
	public Session() {
		
	}
	/**
	 * 
	 * @param username of the new user
	 * @param password of the new user 
	 */
	public Session(String username,String password) {
		this.numWins = 0;
		this.username = username;
		this.password = password;
		this.indexArena = 0;
//		this.setcurrentArena(indexArena);
		this.ArenaList = new ArrayList<Arena>();
		this.persona = new GameCharacter();
	}
	
	/**
	 * 
	 * @param file containing the user's data
	 * @throws FileNotFoundException 
	 */
	public Session(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
			this.ArenaList = new ArrayList<Arena>();
			this.username = sc.nextLine().substring(11);
			this.password = sc.nextLine().substring(11);
			this.numWins = Integer.parseInt(sc.nextLine().substring(10));
			this.indexArena = Integer.parseInt(sc.nextLine().substring(8));
			this.persona = GameCharacter.getGameCharacter(file);
			this.ArenaList.add(new Egypt(persona));
			this.ArenaList.add(new Persia(persona));
			this.ArenaList.add(new Greece(persona));
			this.ArenaList.add(new Rome(persona));
			this.setcurrentArena(this.indexArena);
	}
	/**
	 * 
	 */
	public void flushInfo() {
		try {
		File file = new File (this.username + "&" + this.password + ".txt");
		Scanner sc = new Scanner(file);
		String content = "";
		while(sc.hasNext()) {
			String line = sc.nextLine();
			if(line.substring(0, 2).equals("us")) {
				line = line.substring(0,11) + this.getUsername();
			}else if(line.substring(0,2).equals("pa")) {
				line = line.substring(0,11) + this.getPassword();
			}else if(line.substring(0,2).equals("nu")) {
				line = line.substring(0,10) + this.getNumWins();
			}else if(line.substring(0,2).equals("Ar")) {
				line = line.substring(0,8) + this.getArenaIndex();
			}
			content += line + "\n";
		}
		
			PrintWriter pen = new PrintWriter(file);
			pen.println(content);
			pen.close();
		} catch (FileNotFoundException e) {
			System.out.println("A file is missing in your computer. Contact the developers");;
		}
		
	}
	
	public void fillArena(ArrayList<Arena> list) {
		
		for (int i = 0; i < list.size(); i++) {
			this.ArenaList.add(list.get(i));
		}
	}
	/**
	 * 
	 * @param NumWins of the session's user to set
	 */
	public void setNumWins(int NumWins) {
		this.numWins = NumWins;
	}
	
	/**
	 * 
	 * @return the number of victories in the session
	 */
	public int getNumWins() {
		return this.numWins;
	}
	
	/**
	 * 
	 * @param password of the session's user to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return the password of the session's user
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 
	 * @param username of the session's user to set
	 */
	public void setUsername(String username) {
		
	}
	
	/**
	 * 
	 * @return the username of the session's user
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * 
	 * @param persona of the session's user to set
	 */
	public GameCharacter getCharacter() {
		return this.persona;
	}
	
	/**
	 * 
	 * @param list of personas of the session's user to set
	 * @throws FileNotFoundException 
	 */
	public void setCharacter(File file) throws FileNotFoundException {
		this.persona = GameCharacter.getGameCharacter(file);
	}
	
	/**
	 * 
	 * @param arena of the session's user to set
	 */
	public void setcurrentArena(Integer indexArena) {
		this.currentArena = this.ArenaList.get(indexArena);
	}
	
	/**
	 * 
	 * @return the Arena of the session's user
	 */
	public Arena getcurrentArena() {
		return this.currentArena;
	}
	
	/**
	 * 
	 * @return the arena index
	 */
	public int getArenaIndex() {
		return this.indexArena;
	}
	/**
	 * 
	 */
	public void setArenaIndex(int numWins) {
		this.indexArena = numWins/5;
	}
	/**
	 * 
	 * @param numWins
	 */
	public void updateMapArena() {
		
	}

}

