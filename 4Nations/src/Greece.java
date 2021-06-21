import java.util.Random;

public class Greece extends Arena {
	final static String[] GREECIAN_ALIASES = {"Achilles" ,"Agamemnon", "Hector", "Jason", "Odysseus", "Perseus", "Prometheus", "Aeneas", "Orpheus", "Theseus"};
	public Greece(GameCharacter player) {
		super(GREECIAN_ALIASES, 'G', player);
	}
	@Override
	public TypicalGameCharacter getOpponent() {
		Random r = new Random();
		String name = GREECIAN_ALIASES[(r.nextInt(GREECIAN_ALIASES.length))];
		TypicalGreek tp = new TypicalGreek(player, name);
		return tp;
	}
	
	@Override
	public String toString() {
		return "Greece";
	}
}
