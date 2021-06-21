import java.util.Random;

public class Rome extends Arena {
	final static String[] ROMAN_ALIASES = {"Romulus", "Gallus", "Remus", "Magnus",
			"Laurentius", "Athanasius", "Sulla", "Marius", "Heracles", "Gaius", 
			"Lucius", "Marcus", "Maximus", "Luca", "Aquila", "Nero", "Brutus"};
	public Rome(GameCharacter player) {
		super(ROMAN_ALIASES, 'R', player);
	}
	@Override
	public TypicalGameCharacter getOpponent() {
		Random r = new Random();
		String name = ROMAN_ALIASES[(r.nextInt(ROMAN_ALIASES.length))];
		TypicalRoman tr = new TypicalRoman(player, name);
		return tr;
	}
	
	@Override
	public String toString() {
		return "Rome";
	}

}
