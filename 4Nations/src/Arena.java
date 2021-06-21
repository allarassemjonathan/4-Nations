public class Arena {
	//private GameCharacter typicalCombatant;
	private char affinity;
	private String[] aliases;
	protected GameCharacter player;
	final static String[] PERSIAN_ALIASES = {};
	final static String[] GREEK_ALIASES = {};
	final static String[] ROMAN_ALIASES = {};

	public Arena(String[] pa, char affinity, GameCharacter player) {

		this.affinity = affinity;
		this.player = player;
		
		aliases = new String[pa.length];
		for(int i = 0; i < pa.length; i++) {
			aliases[i] = pa[i];
		}

	}
	
	public TypicalGameCharacter getOpponent() {
		return new TypicalEgyptian(player, "Aroa");
	}

	public char getAffinity() {
		return this.affinity;
	}

}
