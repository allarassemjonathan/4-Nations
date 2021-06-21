import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 
 * @author ALLARASSEMJJ20
 *
 */
public class GameManager {
	
	private final File db = new File("database.txt");
	private HashMap<String,String> credentials;
	private Session currentSession;
	private String dbcontent;
	private boolean isLogged;
	private boolean isFirstTime;
	private Music music;
	
	public GameManager () {
		this.isFirstTime = true;
		isLogged = false;
		music = new Music();
	}
	
	/**
	 * 
	 * @param username of the session's owner
	 * @param password of the session's owner
	 * @return
	 */
	public 	Session createNewSession(String username, String password) {
		Session session = new Session(username,password);
		return session;
	}
	
	/**
	 * 
	 * @param file from where we load the data
	 * @throws FileNotFoundException
	 */
	public void LoadDataUser(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Scanner in = new Scanner(file);
		this.dbcontent = "";
		String line = "";
		this.credentials = new HashMap<>();
		
		while(in.hasNext()) {
			
			/*
			 * Load the data in the HashMap
			 */
			
			String un = "";
			String pw = "";
			line = in.nextLine();
			this.dbcontent += line + "\n";
			Scanner mapSc = new Scanner(line);
			
			if(mapSc.next().equals("username")) {
				un = mapSc.next();
			}
			if(mapSc.next().equals("password")) {
				pw = mapSc.next();
			}
			this.credentials.put(un, pw);
		}
	}
	
	/**
	 * 
	 * @param file the data of the user
	 */
	public Session resumeSession(File file) {
		try {
			this.currentSession = new Session(file);
		} catch (FileNotFoundException e) {
			System.out.println("An error occured; please restart the game.");;
		}
		return currentSession;
		
	}
	
	
	/**
	 * 
	 * @param username of user
	 * @param password of user
	 * @return true if login successful | false if the login failed
	 * @throws FileNotFoundException if the file is not found
	 */
	public void Login(String username,String password) throws FileNotFoundException {
		
		if(this.HasAccount(username,password)) {
			File file = new File(username + "&" + password + ".txt");
			this.resumeSession(file);
			this.isLogged = true;
		}
		else {
			System.out.println("Try again or register if you dont have an account.");
		}
	}
	
	/**
	 * 
	 * @param username of the new user
	 * @param password of the new user
	 * @return true if registration successful | false if the registration failed
	 * @throws FileNotFoundException 
	 */
	public boolean Register(String username,String password) {
		try {
		if(!(this.HasAccount(username, password)) && !this.checkDuplicate(username)){
			/*
			 * Add the user in the database
			 */
			PrintWriter pen = new PrintWriter(db);
			pen.println(dbcontent  + "username " + username + " password " + password);
			pen.close();
			
			/*
			 * Create a file to hold the data of the user
			 */
			
			OutputStream ou = new FileOutputStream(username + "&" + password + ".txt");
			PrintWriter bic = new PrintWriter(ou);
			bic.println("username : " + username);
			bic.println("password : " + password);
			bic.println("numWins : " + 0);
			bic.println("Arena : " + 0);
			bic.println("Affinity : ");
			System.out.println("\t\t\t\t\t\t\t\tRegistration successful!");
		
			// you have a level for your progression
			bic.close();
			return true;
		}
		else if(this.HasAccount(username, password)){
			System.out.println("It looks like you forgot that you already have an account. Enter 2 to log in.");
			this.Login(username, password);
			return false;
		}else if(this.checkDuplicate(username)) {
			System.out.println("This username is already used. Please choose another username.");
			return false;
		}
		}catch(FileNotFoundException f) {
			System.out.println("Sorry a file is missing in your computer. Contact developers to fix it.");
			return false;
		}catch(InputMismatchException e) {
			System.out.println("Enter a correct input.");
			return false;
		}catch(Exception e) {
			System.out.println("An error occured.");
			return false;
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param username of the connecting user
	 * @param password of the connecting user
	 * @return true if user has an account | false if user does not have an account
	 * @throws FileNotFoundException is thrown if the file is not found
	 */
	public boolean HasAccount(String username, String password) {
		try {
			this.LoadDataUser(db);
			String dbpass = credentials.get(username);
			if(dbpass == null) {
				return false;
			}
			return dbpass.equals(password);
		}catch(FileNotFoundException f) {
			System.out.println("We were unable to load your progress; please restart the game.");
			return false;
		}catch(Exception e) {
			System.out.println("An error occured while logging in; please try again.");
			return false;
		}
		
	}
	public boolean checkDuplicate(String username) {
		try {
			this.LoadDataUser(db);
			return credentials.containsKey(username);
		}catch(FileNotFoundException f) {
			System.out.println("We were unable to load your progress; please restart the game.");
			return false;
		}catch(Exception e) {
			System.out.println("An error occured while logging in; please try again.");
			return false;
		}
	}
	 public void CreateCharacter() {
		try {
			 System.out.println("Set up your character!\n");
			 System.out.println("\tEnter E to be a Egyptian");
			 System.out.println("\tEnter P to be a Persian");
			 System.out.println("\tEnter G to be a Greek");
			 System.out.println("\tEnter R to be a Roman");
			 Scanner sc = new Scanner(System.in);
			 char in = sc.next().toUpperCase().charAt(0);
			 String string = "";
			 while(true) {
				 if(in == 'R') {
					 string = "Rome";
					 break;
				 }
				 else if(in == 'E') {
					 string = "Egypt";
					 break;
				 }
				 else if(in == 'G') {
					 string = "Greece";
					 break;
				 }
				 else if(in == 'P') {
					 string = "Persia";
					 break;
				 }
				 else {
					 System.out.println("Enter E, P, G, or R.");
					 in = sc.next().toUpperCase().charAt(0);
				 }
			 }
			
			 System.out.println("Welcome to " + string + " !\nPlease expand the console to see content completely.");
			 String fileName = this.currentSession.getUsername() + "&" + this.currentSession.getPassword() + ".txt";
			 File file = new File(fileName);
			 Scanner temp = new Scanner(file);
			 String content = "";
			 while(temp.hasNext()) {
				 String line = temp.nextLine();
				 if(line.substring(0,8).equals("Affinity")) {
					 line += in;
				 }
				 content += line + "\n";
			 } 
			 PrintWriter pen = new PrintWriter(fileName);
			 pen.println(content);
			 pen.close();
			 this.currentSession.setCharacter(file);
			 GameCharacter gm = this.currentSession.getCharacter();
			 ArrayList<Arena> ta = new ArrayList<Arena>(List.of(new Egypt(gm), new Persia(gm), new Greece(gm), new Rome(gm)));
			 this.currentSession.fillArena(ta);
			 this.currentSession.setcurrentArena(this.currentSession.getArenaIndex());
		}catch(FileNotFoundException f) {
			System.out.println("A file is missing.");
		}catch(Exception e) {
			System.out.println("An error occured.");
		}
			 
	 }
	/**
	 * 
	 * @param username of the new user
	 * @param password of the new user
	 */
	public void addCredential(String username,String password) {
		this.credentials.put(username, password);
	}
	
	/**
	 * 
	 * @return the credential HashMap 
	 * containing the username and 
	 * password of people
	 */
	public HashMap<String, String> getCredentials() {
		return this.credentials;
	}
	/**
	 * Connect the user to the file
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void Connection() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
			this.music.skip("Retro.wav");
			System.out.println(Design.BLINE(5,"\n") + 
							   Design.BLINE(5,"\t") + "Welcome to 4 Nations\n");
			System.out.println(Design.BLINE(6,"\t") + "1 Register\n"
							 + Design.BLINE(6,"\t")+  "2 Login\n"
							 + Design.BLINE(6,"\t")+  "3 About us\n");
			Scanner sc = new Scanner(System.in);
			int entry = 0;
			do {
				try{
				entry = sc.nextInt();
				switch(entry) {
					case 1 :
						System.out.println("\nRegistration\nUsername: ");
						String username1 = sc.next();
						System.out.println("\nPassword: ");
						String password1 = sc.next();
						if(!this.Register(username1,password1)) {
							break;
						}
						this.currentSession = new Session(username1,password1);
						this.CreateCharacter();
						isLogged = true;
						entry = -1;
						break;
					case 2 :
						System.out.println("\nLogin\nUsername: ");
						String username2 = sc.next();
						System.out.println("\nPassword: ");
						String password2 = sc.next();
						this.Login(username2, password2);
						entry = -1;
						break;
					case 3 :
						System.out.println("Coded by:\n\n\tLowryEC17@gcc.edu -- aka the BIG BRAIN"
								+ "\n\tSteigerNA20@gcc.edu -- aka the MOST ATHLETIC OF THE GROUP!!!"
								+ "\n\tAllarassemjj20@gcc.edu --  aka the SUPER COOL NERD");
						System.out.println("\nInorder to play the game, you must enter 1 to register."
								+ "\nIf you already have an account, enter 2 to log in.\n");
						System.out.println("Music Introduction by Eli Lowry; Music Fight from : https://www.bensound.com/bensound-epic.mp3"
								+ "\navailable to use under the Free license with attribution section https://www.bensound.com/licensing");
						break;
					default:
						System.out.println("Press 1,2 or 3");
				}
				}catch(FileNotFoundException f) {
					System.out.println("Sorry you are not able to access your account.");
					sc.next();
				}catch(InputMismatchException i) {
					System.out.println("Enter a correct input.");
					sc.next();
				}catch(Exception e) {
					System.out.println("Something went wrong.");
					sc.next();
				}
	}while(!(entry ==-1));
	}
	/**
	 * Delete user's data when they ask for it.
	 */
	public void deleteData() {
		try {
		System.out.println(Design.BLINE(100, "-"));
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your username");
		String us = in.next();
		System.out.println("Enter your password");
		String ps = in.next();
		
		if(us.equals(this.currentSession.getUsername()) && ps.equals(this.currentSession.getPassword())) {
			String content = "";
			Scanner s = new Scanner(db);
			while(s.hasNext()) {
				String line = s.nextLine();
				if(!line.equals("username " + us + " password " + ps)) {
					content += line + "\n";
				}
			}
			PrintWriter pen = new PrintWriter(db);
			pen.println(content);
			pen.close();
			System.out.println("We are sorry to see you leave! Come back soon");
		}
		}catch(FileNotFoundException f) {
			System.out.println("A file is missing please reboot your computer and run the game\n"
					+ "if the error persist contact the developers");
			return;
		}catch(Exception e) {
			System.out.println("Something went wrong; restart the game and try again.");
			return;
		}
	}
	/**
	 * Print the inner menu which is the menu popping up after the battle
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void MenuInner() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.music.skip("Retro.wav");
		System.out.println("Press 1,2 or 3\n\n");
		System.out.println( 
				Design.BLINE(4, "\t") + "1  Next battle\n"
				+ Design.BLINE(4, "\t") + "2  My Character\n"
				+  Design.BLINE(4, "\t") + "3  Delete my Account\n"
				);
	}
	/**
	 * Print the characteristics of the current combattant
	 */
	public void PageCharacter() {
		System.out.println(Design.BLINE(100, "-"));
		System.out.println(Design.BLINE(4, "\t") + currentSession.getUsername() + "'s Character Info");
		System.out.println("Victories : " + this.currentSession.getNumWins());
		String nation = "";
		char aff = this.currentSession.getCharacter().getAffinity();
		GameCharacter gm = this.currentSession.getCharacter();
		if(aff == 'R') nation = "Roman";
		if(aff == 'E') nation = "Egypt";
		if(aff == 'P') nation = "Persia";
		if(aff == 'G') nation = "Greece";
		
		System.out.println("Nation  : " +   nation);
		System.out.println("Speed   : " +   gm.getSpeed());
		System.out.println("Vitality: " +   gm.getMaxVitality());
		System.out.println("Attacks : \n" +   gm.getAllAttackInfo());
		System.out.println("Arena   : " +   this.currentSession.getcurrentArena().toString());
	}
	/**
	 * Print and manage all the information necessary 
	 * for the menu page to appear.
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void MenuGeneral() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(isLogged) {
			System.out.println(Design.BLINE(9, "\t") + "You are logged in.");
			System.out.println(Design.BLINE(100, "-"));
			System.out.println(Design.BLINE(4, "\t") + "Welcome " + currentSession.getUsername() + " !"
					+ Design.BLINE(4, "\t") + this.currentSession.getNumWins() + " victories" );
			System.out.println("\n\nPress numbers 1,2 or 3\n"
					+ "\n" + Design.BLINE(4, "\t") + "1 Start/Continue Adventure"
					+ "\n" + Design.BLINE(4, "\t") + "2 My Character"
					+ "\n" + Design.BLINE(4, "\t") + "3 Delete my Account"
					+ "\n" + Design.BLINE(4, "\t") + "-1 Exit the game at anytime");
			
			
			Scanner sc = new Scanner(System.in);
			int entry = 0;
			do {
				try {
				entry = sc.nextInt();
				if(entry == 1) {
					this.music.skip("battle.wav");
					if(isFirstTime) {
						System.out.println(Design.BLINE(100, "-"));
						System.out.print(Design.BLINE(4, "\t") + "Stage " + (this.currentSession.getArenaIndex() + 1) + ": ");
						System.out.println(this.currentSession.getcurrentArena().toString());
						System.out.println(Design.BLINE(4, "\t") + "Fight for the freedom of your people,\n" + Design.BLINE(4, "\t") + "And become a legend in your time...");
					}
					
					TimeUnit.SECONDS.sleep(1);
					Arena arena = this.currentSession.getcurrentArena();
					Battle battle = new Battle(this.currentSession.getCharacter(), arena.getOpponent());
					isFirstTime = false;
					System.out.println("\n\n"+ Design.BLINE(4, "\t") + "Your time has come to rise " + this.currentSession.getUsername()
					+ "\n" + Design.BLINE(3, "\t"));
					System.out.println( "\n\n" + Design.BLINE(4, "\t") + "FIGHT: " + this.currentSession.getUsername().toUpperCase() + " As "+ battle.getCharacter()+ " vs " + arena.getOpponent().getName().toUpperCase()
							+ "\n\n");

					
					int oldIndex = this.currentSession.getArenaIndex();
					int newIndex = 0;
					if(battle.battleSequence()) {
						this.music.skip("yeahahaha.wav");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("Nice win!");
						this.currentSession.setNumWins(this.currentSession.getNumWins() + 1);
						
						if(this.currentSession.getNumWins() == 1) {
							System.out.println("Congratulation on your first victory!");
						}
						
						System.out.println("You've proven yourself to be a Courageous warrior of " + this.currentSession.getcurrentArena().toString());
						this.currentSession.setArenaIndex(this.currentSession.getNumWins());
						newIndex = this.currentSession.getArenaIndex();
						
						if(oldIndex != newIndex) {
							this.currentSession.setcurrentArena(this.currentSession.getArenaIndex());
							System.out.println("\nYOU'VE REACHED A NEW ARENA!!");
							System.out.print("\nWELCOME TO " + this.currentSession.getcurrentArena().toString() + "\n");
						}
						
						this.currentSession.flushInfo();
						String fileName = this.currentSession.getUsername() + "&" + this.currentSession.getPassword() + ".txt";
						File file = new File(fileName);
						this.currentSession.setCharacter(file);	
						}
					else if(this.currentSession.getNumWins() == 1) {
						System.out.println("The path toward freedom is full of challenge, but with determination, you can succeed.");
					}
					else {
						System.out.println("You lost the battle, but you have not lost the war!");
					}
					this.MenuInner();
				}else if(entry == 2) {
//					this.music.play("something");
					this.PageCharacter();
					this.MenuInner();
				}else if(entry == 3) {
//					this.music.play("something");
					this.deleteData();
					System.exit(0);;
				}else if(entry == -1) {
//					this.music.play("something");
					System.out.println("Ending session");
					System.exit(0);
				}
				else if(!(entry == -1)){
					System.out.println("Enter 1,2 or 3 to choose!");
				}
			}catch( InterruptedException i) {
				System.out.println("The process got interrupted. The game is shutting down.");
				sc.next();
			}catch( ArrayIndexOutOfBoundsException e) {
				System.out.println("Only 1,2 or 3 are allowed");
				sc.next();
			}catch(FileNotFoundException f) {
				System.out.println("A file is missing in your computer. Contact the developers for help.");
				sc.next();
			}catch(InputMismatchException e) {
				System.out.println("Enter an appropriate number.");
				sc.next();
			}catch(IndexOutOfBoundsException e) {
				System.out.println("YOU WON THE GAME. NICE JOB, YOU SAVED YOUR PEOPLE!!");
				System.exit(0);
			}catch(Exception e) {
					System.out.println("Something went wrong.");
					sc.next();
			}
				
			}while(!(entry==-1));
			
		}else {
			System.out.println("\nYou are allowed to view info about the developers,"
					+ "\nbut you must have a valid account to access the game.");
		}
}
	/**
	 * This method is pretty much running the game. It is calling only
	 * two other methods the connection, to connect with user's and the MenuGeneral
	 * that take care of the battle and the other options.
	 * @throws InterruptedException
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public void Manager() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.Connection();
		this.MenuGeneral();
		
}
}


