package RPGgame.Monsters;

import RPGgame.Entity;
import RPGgame.Weapons.Weapon;

public class Goblin extends Monster {
	public Goblin(int hp, int strength, int agility, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, gold, name, armorClass, lvl);
		this.currentWeapon = new Weapon("baton", 10);
	}
	public Goblin() {
		super(20,11, 10, 0, "Goblin", 10, 1);
		this.currentWeapon = new Weapon("baton", 5);
	}

	@Override
	public void attack(Entity entity) {
		super.attack(entity);
	}

	@Override
	public void death() {
		super.death();
	}

}
