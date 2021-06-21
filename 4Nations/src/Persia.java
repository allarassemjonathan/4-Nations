import java.util.Random;

public class Persia extends Arena {
	final static String[] PERSIAN_ALIASES = {"Raja", "Arash", "Babak", "Esfandiyar", "Garshasp", "Giv", "Rostam", "Kaveh"};
	public Persia(GameCharacter player) {
		super(PERSIAN_ALIASES, 'P', player);
	}
	@Override
	public TypicalGameCharacter getOpponent() {
		Random r = new Random();
		String name = PERSIAN_ALIASES[(r.nextInt(PERSIAN_ALIASES.length))];
		TypicalPersian tp = new TypicalPersian(player, name);
		return tp;
	}
	@Override
	public String toString() {
		return "Persia";
	}
}
