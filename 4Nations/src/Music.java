import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private Clip clip;
	private boolean isPlaying;
	private String trackName;
	
	public Music() {
		this.trackName = "CelticLong.wav";
		this.isPlaying = false;
	}
	
	public void play(String musicName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File fmusic = new File(musicName);
		this.trackName = musicName;
		AudioInputStream as = AudioSystem.getAudioInputStream(fmusic);
		this.clip = AudioSystem.getClip();
		this.clip.open(as);
		this.clip.start();
		this.isPlaying = true;
	}
	
	public void skip(String musicName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(this.trackName.equals(musicName)) {
			
		}
		else {
			if(this.isPlaying) clip.stop();
			File fmusic = new File(musicName);
			this.trackName = musicName;
			AudioInputStream as = AudioSystem.getAudioInputStream(fmusic);
			this.clip = AudioSystem.getClip();
			this.clip.open(as);
			this.clip.start();
			this.isPlaying = true;
		}
	}
	
	public void pause() {
		if(isPlaying) this.clip.stop();
		this.isPlaying = false;
	}
	
	public void resume() {
		if(!isPlaying) this.clip.start();
		this.isPlaying = true;
	}
	
	public void loop(String song, String command) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		song = "CelticUnit.wav";
		while(true) {
			this.play(song);
			
			if(command.equals("stop")) break;
		}
	}
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String command = in.next();
		Music music = new Music();
		do {
		if(command.equals("play")) {
			String name = "";
			music.play(name);
			command = in.next();
		}
		else if(command.equals("pause")) {
			music.pause();
			command = in.next();
		}
		else if(command.equals("resume")) {
			music.resume();
			command = in.next();
		}
		else if(command.equals("loop")) {
			music.loop("CelticUnit.wav", command);
			command = in.next();
		}
		}while(!(command.equals("-1")));
		}
		
}
