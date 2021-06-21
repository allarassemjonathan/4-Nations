import java.util.Random;

public class Egypt extends Arena {
	final static String[] EGYPTIAN_ALIASES = {"Ahmose", "Ammon", "Akhraten", "Amenhotep"
			, "Bek", "Cheops", "Djedi", "Hannu", "Imhotep", "Khayu", "Khufu",
			"Meketre", "Menkare", "Nebet", "Neferu", "Pabasa", "Senedj", "Seti"
			, "Thutmose"};
	public Egypt(GameCharacter player) {
		super(EGYPTIAN_ALIASES, 'E', player);
	}
	public Egypt() {
		super(EGYPTIAN_ALIASES, 'E', null);
	}
	@Override
	public TypicalGameCharacter getOpponent() {
		Random r = new Random();
		String name = EGYPTIAN_ALIASES[(r.nextInt(EGYPTIAN_ALIASES.length))];
		TypicalEgyptian te = new TypicalEgyptian(player, name);
		return te;
	}
	
	@Override
	public String toString() {
		return "Egypt";
	}
}
