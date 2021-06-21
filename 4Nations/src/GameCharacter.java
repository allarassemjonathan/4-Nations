import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameCharacter {
	private char affinity; //Egyptian, Persian, Roman, or Greek
	private String name;
	private int attack;
	private int maxVitality;
	private int currentVitality;
	private int speed;
	private Attack[] Attacks;

	public GameCharacter() {
		this.affinity = 'X';
		this.name = "x";
		this.attack = 0;
		this.maxVitality = 0;
		this.currentVitality = 0;
		this.speed = 0;
		this.Attacks = null;
	}
	
	public GameCharacter(char affinity, String name, int attack, int vitality, int speed, Attack[] Attacks) {
		this.affinity = affinity;
		this.name = name;
		this.attack = attack;
		this.maxVitality = vitality;
		this.currentVitality = vitality;
		this.speed = speed;
		this.Attacks = new Attack[Attacks.length];
		for (int i = 0; i < Attacks.length; i++) {
			this.Attacks[i] = Attacks[i];
		}
	}
	/**
	 * This method build a character based on the data 
	 * contained in a file. Ultimately the numWin and 
	 * the affinity are the properties used to do this 
	 * operation.
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 */
	public static GameCharacter getGameCharacter(File f) throws FileNotFoundException {

		Scanner in = new Scanner(f);
		for (int i = 0; i < 2; i++) {
			in.nextLine();
		}

		String winsData = in.nextLine();
		in.nextLine();
		String affinityData = in.nextLine();

		Scanner parseWins = new Scanner(winsData);
		Scanner parseAffinity = new Scanner(affinityData);

		for(int i = 0; i < 2; i++) {
			parseWins.next();
			parseAffinity.next();
		}

		int numWins = Integer.valueOf(parseWins.next());
		char affinity = parseAffinity.next().charAt(0);
		
		parseWins.close();
		parseAffinity.close();

		switch (affinity) {
		case 'E':
			if(numWins < 5) {
				return new Footsoldier();
			}
			else if(numWins < 10) {
				return new Ramses();
			}
			else {
				return new Sphinx();
			}
		case 'P':
			if(numWins < 5) {
				return new Immortals();
			}
			else if(numWins < 10) {
				return new Cyrus();
			}
			else {
				return new Manticore();
			}
		case 'G':
			if(numWins < 5) {
				return new Hoplite();
			}
			else if(numWins < 10) {
				return new Alexander();
			}
			else {
				return new Minotaur();
			}
		case 'R':
			if(numWins < 5) {
				return new Legionary();
			}
			else if(numWins < 10) {
				return new JuliusCaesar();
			}
			else {
				return new Pegasus();
			}
		default:
			return new DoctorHutchins();
		}
		
	}

	public GameCharacter(GameCharacter that) {
		this.affinity = that.getAffinity();
		this.name = that.getName();
		this.attack = that.getAttack();
		this.maxVitality = that.getMaxVitality();
		this.currentVitality = that.getCurVitality();
		this.speed = that.getSpeed();
		Attack[] thatAttacks = that.getAttacks();
		this.Attacks = new Attack[thatAttacks.length];
		for (int i = 0; i < Attacks.length; i++) {
			this.Attacks[i] = thatAttacks[i];
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getCurVitality() {
		return currentVitality;
	}
	public void setCurVitality(int vitality) {
		this.currentVitality = vitality;
	}
	public int getMaxVitality() {
		return maxVitality;
	}
	public void setMaxVitality(int vitality) {
		this.maxVitality = vitality;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public char getAffinity() {
		return affinity;
	}
	public void setAffinity(char affinity) {
		this.affinity = affinity;
	}
	/** Simulates using an attack.
	 * @param attackNum - the index of the attack within Attacks
	 * @return number of damage to be dealt; negative values are self-restoring rather than damaging
	 */
	public int useAttack(int attackNum) {
		int force = getAttacks()[attackNum].findAttackDamage();
		return ((force > 0) ? getAttack() + force : getAttack() - force);
	}
	/**
	 * Remove a certain amount of health from the attacked 
	 * character, depending on both random and predictable variables.
	 * @param attackNum
	 * @param o
	 * @return
	 */
	public boolean useAttack(int attackNum, GameCharacter o) {

		if(!this.getAttacks()[attackNum].alwaysHits) {
			int roll = Die.roll(12);
			if(roll == 1) {
				return false;
			}
			int accuracy = this.getSpeed() + roll;
			if(accuracy < o.getSpeed()) {
				return false;
			}
		}

		int force = getAttacks()[attackNum].findAttackDamage();
		if (force >= 0) {
			o.takeDamage(force + getAttack());
		}
		else {
			this.takeDamage(force - getAttack());
		}

		return true;
	}

	/** Simulates taking damage by reducing vitality by set amount.
	 * @param numDamage - the amount of damage to be taken
	 */
	public void takeDamage(int numDamage) {
		if(numDamage > currentVitality) {
			setCurVitality(0);
		}
		else if(numDamage < 0 && Math.abs(numDamage) > (maxVitality - currentVitality)){
			setCurVitality(maxVitality);
		}
		else {
			setCurVitality(getCurVitality() - numDamage);
		}
	}

	public String getAttackInfo(int attackNum) {
		return Attacks[attackNum].getInfo(this);
	}
	
	public String getAllAttackInfo() {
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < getAttacks().length; i++) {
			out.append("\t" + (i + 1) + "." + " " + getAttackInfo(i) + "\n");
		}
		return out.toString();
	}

	public Attack[] getAttacks() {
		return this.Attacks;
	}
}
