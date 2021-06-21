public abstract class TypicalGameCharacter extends GameCharacter {
	private GameCharacter opponent;
	public TypicalGameCharacter(char naturalAffinity, String name, int baseAttack, int baseVitality, int baseSpeed,
			Attack[] attacks) {
		super(naturalAffinity, name, baseAttack, baseVitality, baseSpeed, attacks);
	}

	abstract public Attack findBestAttack();

	public boolean useAttack() {

		Attack choice = findBestAttack();
		if(!choice.alwaysHits) {
			int roll = Die.roll(12);
			if(roll == 1) {
				return false;
			}
			int accuracy = this.getSpeed() + roll;
			if(accuracy < opponent.getSpeed()) {
				return false;
			}
		}

		int force = choice.findAttackDamage();

		if (force >= 0) {
			opponent.takeDamage(force + getAttack());
		}
		else {
			this.takeDamage(force - getAttack());
		}

		return true;
	}
	
	public void setOpponent(GameCharacter o) {
		opponent = o;
	}
	public GameCharacter getOpponent() {
		return opponent;
	}
}
