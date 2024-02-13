package RPGgame.Monsters;

import RPGgame.Entity;
import java.util.Random;

public class Dragon extends Monster {

	Random rand = new Random();

	public Dragon() {
		super(100, 20, 20, 0, "Arion", 14, 10);
	}

	int dragonFireDamage = 30;
	int agilityModifier = 0;

	public void attack(Entity entity) {

		if (entity.isFireResist()) {
			dragonFireDamage = 15;
		} else {
			dragonFireDamage = 30;
		}
		if (this.dead != true) {
			if (agility > 10) {
				agilityModifier = (agility - 10) / 2;
			} else agilityModifier = 0;

			if (((rand.nextInt(20) + 1) + agilityModifier) > entity.getArmorClass()) {

				int random = rand.nextInt(2);

				if (random == 0) {
					entity.minusHP(dragonFireDamage);
					System.out.println("The dragon used fire breath and dealt " + dragonFireDamage + " damage");
					if (entity.isFireResist()) {
						System.out.println("You got only half damage from fire, because of fire resist potion");
					}

				} else {
					entity.minusHP(this.strength);
					System.out.println("The dragon struck with its claws and dealt " + this.strength + " damage");
				}
				System.out.println("The " + entity.getName() + " has " + entity.getHp() + " hp remaining");
				System.out.println(entity);
				if (entity.getHp() <= 0) {
					entity.death();
				}
			} else {
				System.out.println(name + " missed");
			}

		} else {
			System.out.println("dead");
		}

	}



}
