import java.util.ArrayList;
import java.util.Random;

public class TypicalPersian extends TypicalGameCharacter {

	private final static int BASE_ATTACK = 6;
	private final static int BASE_VITALITY = 40;
	private final static int BASE_SPEED = 9;
	private final static char NATURAL_AFFINITY = 'P';

	final static int[][] D1 = {{6,1}, {4,1}};
	final static int[][] D2 = {{4,2}};
	final static int[][] D3 = {{8,2}};
	
	final static Attack A1 = new Attack(D1, false, false);
	final static Attack A2 = new Attack(D2, true, false);
	final static Attack A3 = new Attack(D3, false, true);
	
	final static Attack[] ATTACKS = {A1, A2, A3};
	
	public TypicalPersian(GameCharacter o, String name) {
		super(NATURAL_AFFINITY, name, BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);
		
		setOpponent(o);
		
		setAttack(BASE_ATTACK);
		setMaxVitality(BASE_VITALITY);
		setCurVitality(BASE_VITALITY);
		setSpeed(BASE_SPEED);
	
	}
	
	public Attack findBestAttack() {
		
		ArrayList<Attack> possibleAttacks = new ArrayList<>();
		
		if(getCurVitality() < 12) {
			for (int i = 0; i < 15 - getCurVitality(); i++) {
				possibleAttacks.add(A3);
			}
		}
		
		for (int i = 0; i < 6; i++) {
			possibleAttacks.add(A1);
		}
		
		if(getOpponent().getCurVitality() < 10) {
			for (int i = 0; i < 10; i++) {
				possibleAttacks.add(A2);
			}
		}
		
		Random r = new Random();
		int rand = r.nextInt(possibleAttacks.size());
		return possibleAttacks.get(rand);
		
	}

}
