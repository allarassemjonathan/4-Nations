import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * 
 * @author ALLARASSEMJJ20
 *
 */
public class Game {
	
	/**
	 * Play the game
	 * @throws LineUnavailableException 
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws InterruptedException 
	 */
	public void GameLoop() {
		try {
		GameManager gm = new GameManager();
		gm.Manager();
		}
		catch(InterruptedException i ) {
			System.out.println("Something went wrong, the program got interupted. Please restart the game.");
		}catch(UnsupportedAudioFileException u) {
			System.out.println("This program supported only java wav files");
		}catch(IOException io) {
			System.out.println("File is missing or not accessible. Check the path");
		}catch(LineUnavailableException l) {
			System.out.println("You have a unavailable line");
		}catch(Exception e) {
			System.out.println("Something went wrong. Check with the developers.");
		}
	}
	
	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
			Game g = new Game();
			g.GameLoop();
	}
	}
