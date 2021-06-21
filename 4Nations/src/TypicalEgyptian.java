import java.util.ArrayList;
import java.util.Random;

public class TypicalEgyptian extends TypicalGameCharacter{
	
	private final static int BASE_ATTACK = 0;
	private final static int BASE_VITALITY = 22;
	private final static int BASE_SPEED = 1;
	private final static char NATURAL_AFFINITY = 'E';

	final static int[][] D1 = {{4,2}};
	final static int[][] D2 = {{10,1}};
	final static int[][] D3 = {{12,2}};

	final static Attack A1 = new Attack(D1, false, false);
	final static Attack A2 = new Attack(D2, false, true);
	final static Attack A3 = new Attack(D3, true, false);

	final static Attack[] ATTACKS = {A1, A2, A3};

	public TypicalEgyptian(GameCharacter o, String name) {
		super(NATURAL_AFFINITY, name, BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);

		setOpponent(o);

		setAttack(BASE_ATTACK);
		setMaxVitality(BASE_VITALITY);
		setCurVitality(BASE_VITALITY);
		setSpeed(BASE_SPEED);

	}

	public Attack findBestAttack() {

		ArrayList<Attack> possibleAttacks = new ArrayList<>();

		if(getCurVitality() < 10) {
			for (int i = 0; i < 13 - getCurVitality(); i++) {
				possibleAttacks.add(A2);
			}
		}

		for (int i = 0; i < 5; i++) {
			possibleAttacks.add(A1);
		}

		if(getOpponent().getCurVitality() == getOpponent().getMaxVitality()) {
			possibleAttacks.add(A3);
		}

		Random r = new Random();
		int rand = r.nextInt(possibleAttacks.size());
		return possibleAttacks.get(rand);

	}
}
