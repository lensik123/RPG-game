package RPGgame.Monsters;

import RPGgame.Entity;
import RPGgame.Fighter;
import RPGgame.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Monster extends Entity implements Fighter {
	ArrayList<String> monsterInfo = new ArrayList<>();
	Random rand = new Random();
	Weapon currentWeapon = new Weapon();

	public Monster(int hp, int strength, int agility, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, 0, name, armorClass, lvl);
	}




	@Override
	public void attack(Entity entity) {
		if (this.dead != true) {
			int agilityModifier;

			//реализовал бросок атаки как в днд, а именно взял логику из Baldur's Gate3
			if (agility > 10) {
				agilityModifier = (agility - 10) / 2;
			} else agilityModifier = 0;

			if (((rand.nextInt(20) + 1) + agilityModifier) > entity.getArmorClass()) {
				entity.minusHP(currentWeapon.getDamage());
				System.out.println("Dealt with " + currentWeapon.getName() + " " + currentWeapon.getDamage() + " damage to the " + entity.getName());
				System.out.println("The " + entity.getName() + " has " + entity.getHp() + " hp remaining");
				if (entity.getHp() <= 0) {
					entity.death();
				}
			} else {
				System.out.println(name + " missed");
			}

		}
	}

	public void displayInfo() {
		System.out.println(monsterInfo);
	}
}
