import java.util.ArrayList;
import java.util.Random;

public class TypicalGreek extends TypicalGameCharacter {

	private final static int BASE_ATTACK = 8;
	private final static int BASE_VITALITY = 78;
	private final static int BASE_SPEED = 12;
	private final static char NATURAL_AFFINITY = 'G';

	final static int[][] D1 = {{4,3}};
	final static int[][] D2 = {{20,1}};
	final static int[][] D3 = {{10,1}, {8,1}, {6,1}, {4,1}};

	final static Attack A1 = new Attack(D1, false, false);
	final static Attack A2 = new Attack(D2, false, true);
	final static Attack A3 = new Attack(D3, false, false);

	final static Attack[] ATTACKS = {A1, A2, A3};

	public TypicalGreek(GameCharacter o, String name) {
		super(NATURAL_AFFINITY, name, BASE_ATTACK, BASE_VITALITY, BASE_SPEED, ATTACKS);

		setOpponent(o);

		setAttack(BASE_ATTACK);
		setMaxVitality(BASE_VITALITY);
		setCurVitality(BASE_VITALITY);
		setSpeed(BASE_SPEED);

	}

	public Attack findBestAttack() {

		ArrayList<Attack> possibleAttacks = new ArrayList<>();

		if(this.getCurVitality() < 40) {
			for (int i = 0; i < 3; i++) {
				possibleAttacks.add(A2);
			}
		}

		for (int i = 0; i < 7; i++) {
			possibleAttacks.add(A1);
		}

		if(getOpponent().getCurVitality() > this.getCurVitality()) {
			for (int i = 0; i < 5; i++) {
				possibleAttacks.add(A3);
			}
		}

		Random r = new Random();
		int rand = r.nextInt(possibleAttacks.size());
		return possibleAttacks.get(rand);

	}
}
