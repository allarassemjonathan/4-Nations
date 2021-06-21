
public class DoctorHutchins extends GameCharacter {
	private final static int BASE_ATTACK = 18;
	private final static int BASE_VITALITY = 120;
	private final static int BASE_SPEED = 18;
	private final static char NATURAL_AFFINITY = 'A';
	
	private final static boolean ATTACK1_ALWAYSHITS = false;
	private final static boolean ATTACK1_ISHEALING = false;
	
	private final static int[][] ATTACK1_DICE = {{4,6}};
	private final static Attack ATTACK1 = new Attack(ATTACK1_DICE, ATTACK1_ALWAYSHITS, ATTACK1_ISHEALING);
	
	private final static boolean ATTACK2_ALWAYSHITS = false;
	private final static boolean ATTACK2_ISHEALING = true;
	
	private final static int[][] ATTACK2_DICE = {{12,2}};
	private final static Attack ATTACK2 = new Attack(ATTACK2_DICE, ATTACK2_ALWAYSHITS, ATTACK2_ISHEALING);
	
	private final static boolean ATTACK3_ALWAYSHITS = true;
	private final static boolean ATTACK3_ISHEALING = false;
	
	private final static int[][] ATTACK3_DICE = {{12,2}};
	private final static Attack ATTACK3 = new Attack(ATTACK3_DICE, ATTACK3_ALWAYSHITS, ATTACK3_ISHEALING);
	
	private final static boolean ATTACK4_ALWAYSHITS = false;
	private final static boolean ATTACK4_ISHEALING = false;
	
	private final static int[][] ATTACK4_DICE = {{20,1},{12,1}};
	private final static Attack ATTACK4 = new Attack(ATTACK4_DICE, ATTACK4_ALWAYSHITS, ATTACK4_ISHEALING);
	
	private final static Attack[] ATTACKS = {ATTACK1, ATTACK2, ATTACK3, ATTACK4};


	public DoctorHutchins() {
		super(NATURAL_AFFINITY, "Dr. Hutchins", BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);
		
	}
	
	@Override
	public String getAllAttackInfo() {
		return "\t1. Pop quiz! Deals 18 + 6d4 damage.\n\t2. Sip coffee: Restores 18 + 2d12 vitality.\n\t3. Poignant Google anecdote: Deals 18 + 2d12 damage"
				+ " This attack always hits.\n\t4. Recursion exercise: Deals massive damage! 18 + 1d20 + 1d12...watch out.";
	}
}
