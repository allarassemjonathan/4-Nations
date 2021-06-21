import java.util.InputMismatchException;
import java.util.Scanner;

public class battletest {

		private boolean turn;
		private boolean loop;
		private GameCharacter player;
		private TypicalGameCharacter cpu;
		Scanner input = new Scanner(System.in);
		Design d;
		char field;
		/**
		 * constructs Battle
		 * sets advantages and the first turn
		 */
		public battletest() {

		}
		public battletest(GameCharacter hero, TypicalGameCharacter villian) {
			turn = false;
			player = hero;
			cpu = villian;
			loop = false;
			d = new Design();
			field = villian.getAffinity();

		}
		/**
		 * The turn method determines who plays next depending on who played last
		 * also governs the current turn
		 * @return true or false depending on who plays next 
		 */
		public boolean turn() {
			if (this.turn == true) {
				this.turn = false;
				return this.turn;
			}
			if (this.turn == false) {
				this.turn = true;
				return this.turn;
			}
			return this.turn;
		}

		public boolean getTurn() {
			return this.turn;
		}
		/**
		 * The hasWon method determines a winner which will end the battle
		 * @return false if there is no loser and true when there is a winner
		 */
		public boolean hasWon() {
			if (player.getCurVitality() == 0) {

				return false;
			}
			else if (cpu.getCurVitality() == 0) {
				System.out.println("You won");
				return true;
			}
			else {
				return false;
			}
		}
		/**
		 * The hasAdvantage takes the affinity of the player and computer
		 * and gives the necessary advantages to either of them
		 * @param af1 - the player's affinity i.e. Roman, Greek, Persian, or Egyptian
		 * @param af2 - the computer's affinity
		 * @return true if the player has an advantage or false if the computer 
		 * has an advantage
		 */
		public void hasAdvantage(char af1, char af2) {//TODO
			if (af1==af2) {
				turn = true;
			}

		}

		/**
		 * This is the method where the actual battle will commence
		 * It will call upon the hasAdvantage() class to determine advantages given to
		 * the fighters.
		 * It will also call upon turn() to keep track of who is currently attacking
		 * It will also call upon hasWon() at the end of each turn to determine
		 * if there is a winner
		 * It will handle the attacks, speed, and success of the player's attack
		 */
		public boolean battleSequence() throws InputMismatchException{

			cpu.setOpponent(player);
			Attack[] output = player.getAttacks(); 
			boolean winner;
			int choice = 0;
			System.out.println(d.BLINE(100, "-"));
			boolean hit = true;


			System.out.println(cpu.getName() + ": \t" + cpu.getCurVitality() + "\n" + player.getName() + ":\t" + player.getCurVitality() + "\n\n");
			hasAdvantage(player.getAffinity(), cpu.getAffinity());

			while (true) {
				if(!(cpu.getCurVitality() > 0 && player.getCurVitality() > 0)) break;
			
				if (turn == false) {
					hit = cpu.useAttack();
					System.out.println(cpu.getName() + "'s turn");
					if(hit) {System.out.println(cpu.getName() + ": \t" + cpu.getCurVitality() + "\n" + player.getName() + ":\t" + player.getCurVitality() + "\n\n");}
					else {System.out.println(cpu.getName() + " missed!\n\n");}
					turn = turn();
					System.out.println(d.BLINE(100, "-"));
				}
				else if (turn == true) {
					System.out.println("IT IS YOUR TURN");
					System.out.println("CHOOSE AN ATTACK:");
					System.out.println(player.getAllAttackInfo());
					Scanner in = new Scanner(System.in);	
					



					
						choice = in.nextInt();

						while((choice == 0) || (choice > output.length) || (choice < -1)) {
						try {
							System.out.println("Please enter a valid number between 1 and " + output.length + " for your attack");
							choice = in.nextInt();
					
						}catch(InputMismatchException n) {
							System.out.println("Please enter a valid number between 1 and " + output.length + " for your attack");
							choice = in.nextInt();
						}
						}
					
					
					hit = player.useAttack(choice - 1, cpu);
					System.out.println(player.getName() + "'s turn");
					if(hit) {System.out.println(cpu.getName() + ": \t" + cpu.getCurVitality() + "\n" + player.getName() + ":\t" + player.getCurVitality() + "\n\n");}
					else {System.out.println(player.getName() + " missed!\n");}
					turn = turn();
					System.out.println(d.BLINE(100, "-"));
				}


			}
			winner = hasWon();
			player.setCurVitality(player.getMaxVitality());
			cpu.setCurVitality(cpu.getMaxVitality());

			return winner;
		}
		public String getCharacter() {
			// TODO Auto-generated method stub
			return player.getName();
		}
	}
