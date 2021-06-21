import java.util.ArrayList;
import java.util.Random;

public class TypicalRoman extends TypicalGameCharacter {

	private final static int BASE_ATTACK = 10;
	private final static int BASE_VITALITY = 100;
	private final static int BASE_SPEED = 16;
	private final static char NATURAL_AFFINITY = 'R';

	final static int[][] D1 = {{8,2}};
	final static int[][] D2 = {{4,1}};
	final static int[][] D3 = {{12,3}};

	final static Attack A1 = new Attack(D1, false, false);
	final static Attack A2 = new Attack(D2, false, false);
	final static Attack A3 = new Attack(D3, false, false);

	final static Attack[] ATTACKS = {A1, A2, A3};

	public TypicalRoman(GameCharacter o, String name) {
		super(NATURAL_AFFINITY, name, BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);

		setOpponent(o);

		setAttack(BASE_ATTACK);
		setMaxVitality(BASE_VITALITY);
		setCurVitality(BASE_VITALITY);
		setSpeed(BASE_SPEED);

	}

	public Attack findBestAttack() {

		ArrayList<Attack> possibleAttacks = new ArrayList<>();

		if(getOpponent().getCurVitality() < 30) {
			for (int i = 0; i < 6; i++) {
				possibleAttacks.add(A2);
			}
		}

		for (int i = 0; i < 5; i++) {
			possibleAttacks.add(A1);
		}

		if((this.getMaxVitality() - this.getCurVitality()) < 30) {
			for (int i = 0; i < 6; i++) {
				possibleAttacks.add(A3);
			}
		}

		Random r = new Random();
		int rand = r.nextInt(possibleAttacks.size());
		return possibleAttacks.get(rand);

	}

}
