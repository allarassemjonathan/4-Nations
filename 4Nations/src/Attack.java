
public class Attack {
	private int[][] dice;
	public boolean alwaysHits;
	public boolean isHealing;

	public Attack(int[][] dice, boolean alwaysHits, boolean isHealing) {
		this.dice = new int[dice.length][dice[0].length];
		for(int i = 0; i < dice.length; i++) {
			this.dice[i][0] = dice[i][0];
			this.dice[i][1] = dice[i][1];
		}
		this.alwaysHits = alwaysHits;
		this.isHealing = isHealing;

	}

	/** Finds amount of damage dealt or vitality healed by attack
	 * @return - said amount -  positive value is damage dealt; negative value is vitality healed
	 */
	public int findAttackDamage() {

		int damage = 0;

		for (int i = 0; i < dice.length; i++) {
			for (int j = 0; j < dice[i][1]; j++) {
				damage += Die.roll(dice[i][0]);
			}
		}

		if(isHealing) {
			return (-damage);
		}

		return damage;
	}
	/** Provides description of effects of attack
	 * @return - String description
	 */
	public String getInfo(GameCharacter c) {
		StringBuilder outText = new StringBuilder();
		if(isHealing) {
			outText.append("Restores ");
		}
		else {
			outText.append("Deals ");
		}

		if(c.getAttack() != 0) {
			outText.append(c.getAttack() + " + ");
		}

		if(dice.length != 0) {

			for(int i = 0; i < dice.length; i++) {
				outText.append(dice[i][1] + "d" + dice[i][0]);
				if(i < dice.length - 1) {
					outText.append(" + ");
				}
			}

		}

		if(isHealing) {
			outText.append(" vitality.");
		}
		else {
			outText.append(" damage.");
		}

		if(alwaysHits) {
			outText.append(" This attack always hits.");
		}

		return outText.toString();
	}

}
