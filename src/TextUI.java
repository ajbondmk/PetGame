import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextUI extends UserInterface {

	BufferedReader br;

	public TextUI() {
		br = new BufferedReader(new InputStreamReader(System.in));
		createPet();
	}

	@Override
	public void createPet() {
		System.out.println("Welcome!");
		while (pet == null) {
			try {
				System.out.println("Which type of pet would you like to create?");
				System.out.println("Enter 'd' for dog, 'f' for fish, or 'n' for ninja.");
				String input = br.readLine();
				switch (input.toLowerCase()) {
					case "d":
						pet = new Dog();
						break;
					case "f":
						pet = new Fish();
						break;
					case "n":
						pet = new Ninja();
						break;
					default:
						System.out.println("Invalid input.");
						break;
				}
			}
			catch (IOException e) {
				System.out.println("Invalid input");
			}
		}
		while (pet.getName() == "") {
			try {
				System.out.println("What would you like to name your new pet?");
				pet.setName(br.readLine());
			}
			catch (IOException e) {
				System.out.println("Invalid input.");
			}
		}
		System.out.println();
		System.out.println(pet.getSound() + " My name is " + pet.getName() + "!");
	}

	@Override
	public void drawPet() {
		System.out.println(pet.getPicture());
		sleep();
	}

	@Override
	public void displayLevels() {
		System.out.println("Food level: " + pet.getFoodLevel() + "/10");
		System.out.println("Sleep level: " + pet.getSleepLevel() + "/10");
		System.out.println("Happiness level: " + pet.getHappinessLevel() + "/10");
		sleep();
		System.out.println();
	}

	@Override
	public boolean performAction() {
		while (true) {
			try {
				System.out.println("What would you like to do now?");
				System.out.println("Enter 'f' to feed, 's' to sleep, 'p' to play, or 'q' to quit.");
				String input = br.readLine();
				System.out.println();
				switch (input.toLowerCase()) {
					case "q":
						return false;
					case "f":
						switch (pet.feed()) {
							case tooTired:
								System.out.println(pet.getSound() + " I'm too tired to eat...");
								break;
							case tooUnhappy:
								System.out.println(pet.getSound() + " I'm too unhappy to eat...");
								break;
							case tooFull:
								System.out.println(pet.getSound() + " I'm too full!");
								break;
							case success:
								System.out.println(pet.getSound() + " Thanks for feeding me!");
								break;
						}
						sleep();
						return true;
					case "s":
						switch (pet.sleep()) {
							case tooHungry:
								System.out.println(pet.getSound() + " I'm too hungry to sleep...");
								break;
							case tooUnhappy:
								System.out.println(pet.getSound() + " I'm too unhappy to sleep...");
								break;
							case notTired:
								System.out.println(pet.getSound() + " I'm not tired!");
								break;
							case success:
								System.out.println(pet.getSound() + " That was a good sleep!");
								break;
						}
						sleep();
						return true;
					case "p":
						switch (pet.play()) {
							case tooTired:
								System.out.println(pet.getSound() + " I'm too tired to play...");
								break;
							case tooHungry:
								System.out.println(pet.getSound() + " I'm too hungry to play...");
								break;
							case success:
								System.out.println(pet.getSound() + " Thanks for playing, that was fun!");
								break;
						}
						sleep();
						return true;
					default:
						System.out.println("Invalid input.");
						break;
				}
			}
			catch (IOException e) {
				System.out.println("Invalid input.");
			}
		}
	}

	private void sleep() {
		try { Thread.sleep(750); }
		catch (InterruptedException e) { }
	}

	public void gameOver() {
		System.out.println("Unfortunately, " + pet.getName() + " died of hunger...");
		drawPet();
		displayLevels();
		System.out.println("R.I.P. " + pet.getName() + "... Game over!");
	}

}