public class Manticore extends GameCharacter {
	
	private final static int BASE_ATTACK = 12;
	private final static int BASE_VITALITY = 80;
	private final static int BASE_SPEED = 18;
	private final static char NATURAL_AFFINITY = 'P';
	
	private final static boolean ATTACK1_ALWAYSHITS = false;
	private final static boolean ATTACK1_ISHEALING = false;
	private final static int ATTACK1_RECOIL = 0;
	
	private final static int[][] ATTACK1_DICE = {{3,2}};
	private final static Attack ATTACK1 = new Attack(ATTACK1_DICE, ATTACK1_ALWAYSHITS, ATTACK1_ISHEALING);
	
	private final static boolean ATTACK2_ALWAYSHITS = false;
	private final static boolean ATTACK2_ISHEALING = true;
	private final static int ATTACK2_RECOIL = 0;
	
	private final static int[][] ATTACK2_DICE = {{4,2}};
	private final static Attack ATTACK2 = new Attack(ATTACK2_DICE, ATTACK2_ALWAYSHITS, ATTACK2_ISHEALING);
	
	private final static boolean ATTACK3_ALWAYSHITS = false;
	private final static boolean ATTACK3_ISHEALING = false;
	private final static int ATTACK3_RECOIL = 0;
	
	private final static int[][] ATTACK3_DICE = {{12,1}};
	private final static Attack ATTACK3 = new Attack(ATTACK3_DICE, ATTACK3_ALWAYSHITS, ATTACK3_ISHEALING);
	
	private final static boolean ATTACK4_ALWAYSHITS = true;
	private final static boolean ATTACK4_ISHEALING = false;
	private final static int ATTACK4_RECOIL = 0;
	
	private final static int[][] ATTACK4_DICE = {{10,2}};
	private final static Attack ATTACK4 = new Attack(ATTACK4_DICE, ATTACK4_ALWAYSHITS, ATTACK4_ISHEALING);
	
	private final static Attack[] ATTACKS = {ATTACK1, ATTACK2, ATTACK3, ATTACK4};


	public Manticore() {
		super(NATURAL_AFFINITY, "Manticore", BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);
		
	}

}