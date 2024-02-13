package RPGgame.Monsters;

import RPGgame.Entity;
import RPGgame.Weapons.Weapon;

public class Skeleton extends Monster {
	public Skeleton(int hp, int strength, int agility, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, gold, name, armorClass, lvl);
		this.currentWeapon = new Weapon("Bow", 15);
	}

	public Skeleton() {
		super(15, 10, 12, 0, "Skeleton", 10, 1);
		this.currentWeapon = new Weapon("Bow", 8);
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

