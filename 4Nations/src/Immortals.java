public class Immortals extends GameCharacter{
	
	private final static int BASE_ATTACK = 2;
	private final static int BASE_VITALITY = 30;
	private final static int BASE_SPEED = 2;
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
	
	private final static Attack[] ATTACKS = {ATTACK1, ATTACK2};	
	
	public Immortals() {
		super(NATURAL_AFFINITY, "Immortals", BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);
		
	}

}