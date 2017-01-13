import java.util.*;

public abstract class UserInterface {

	protected Pet pet = null;

	public static void main(String[] args) {
		UserInterface UI = new TextUI();
		UI.play();
	}

	public abstract void createPet();
	public abstract void drawPet();
	public abstract void displayLevels();
	public abstract boolean performAction();
	public abstract void gameOver();
	public void play() {
		drawPet();
		displayLevels();
		int countToUpdate = (int) ((Math.random() * 5) + 1);
		int count = 0;
		try {
			while (performAction()) {
				drawPet();
				displayLevels();
				if (++count == countToUpdate) {
					pet.decrementLevels();
					count = 0;
					countToUpdate = (int) ((Math.random() * 4) + 2);
					drawPet();
					displayLevels();
				}
			}
		}
		catch (DeadPetException e) {
			gameOver();
		}
	}

}