public abstract class Pet {

	private String name = "";
	private int foodLevel = 7;
	private int sleepLevel = 7;
	private int happinessLevel = 7;
	private String sound;
	private String picture;

	protected Pet(String sound, String picture) {
		this.sound = sound;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public String getSound() {
		return sound;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public int getSleepLevel() {
		return sleepLevel;
	}

	public int getHappinessLevel() {
		return happinessLevel;
	}

	public ActionOutcome feed() {
		if (sleepLevel < 3) return ActionOutcome.tooTired;
		if (happinessLevel < 3 && foodLevel >= 3) return ActionOutcome.tooUnhappy;
		if (foodLevel == 10) return ActionOutcome.tooFull;
		foodLevel++;
		return ActionOutcome.success;
	}

	public ActionOutcome sleep() {
		if (sleepLevel >= 3) {
			if (foodLevel < 3) return ActionOutcome.tooTired;
			if (happinessLevel < 3) return ActionOutcome.tooHungry;
		}
		if (sleepLevel == 10) return ActionOutcome.notTired;
		sleepLevel++;
		return ActionOutcome.success;
	}

	public ActionOutcome play() {
		if (sleepLevel < 3) return ActionOutcome.tooTired;
		if (foodLevel < 3) return ActionOutcome.tooHungry;
		if (happinessLevel != 10) happinessLevel++;
		return ActionOutcome.success;
	}

	public void decrementLevels() throws DeadPetException {
		if (happinessLevel != 0) happinessLevel--;
		if (sleepLevel != 0) sleepLevel--;
		if (foodLevel != 0) foodLevel--;
		if (foodLevel == 0) throw new DeadPetException();
	}

}