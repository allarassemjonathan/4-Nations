import java.util.Random;

public class Die {
	
	private static Random r;
	
	/** Mimics the result of rolling a die
	 * @param numSides - the number of sides of the simulated die
	 * @return - numerical result of rolling die
	 */
	public static int roll(int numSides) {
		r = new Random();

		return r.nextInt(numSides) + 1;
	}

}
