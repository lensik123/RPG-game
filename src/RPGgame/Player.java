package RPGgame;

import RPGgame.Potions.*;
import RPGgame.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static RPGgame.World.dividingLine;

public class Player extends Entity implements Fighter {

	ArrayList<String> characterInfo = new ArrayList<>();
	ArrayList<Potion> potionBag = new ArrayList<>();
	ArrayList<Weapon> weaponBag = new ArrayList<>();
	Weapon currentWeapon = new Weapon();

	private boolean fireResist = false;

	public boolean isFireResist() {
		return fireResist;
	}

	public void setFireResist(boolean fireResist) {
		this.fireResist = fireResist;
	}

	Random rand = new Random();
	Scanner scanner = new Scanner(System.in);

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	private int maxHp;

	@Override
	public void plusHP(int hp) {
		if ((this.hp + hp) > maxHp) {
			this.hp = maxHp;
		} else {
			this.hp += hp;
		}
	}

	public Player(int hp, int strength, int agility, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, 1, name, armorClass, lvl);
		this.gold = gold;
		this.maxHp = hp;

		potionBag.add(new Heal());
		potionBag.add(new AgilityBuff());
		potionBag.add(new ArmorBuff());
	}


	//Сумка с зельями
	public void addToPotionBag(Potion potion) {
		potionBag.add(potion);
	}

	public void removeFromPotionBag(int index) {
		potionBag.remove(index);
	}

	public void displayPotionBag() {
		int index = 0;
		for (Potion potion : potionBag) {
			System.out.println("[" + ++index + "]" + potion);
		}
	}

	public ArrayList<Potion> getPotionBag() {
		return potionBag;
	}


	//Сумка с оружием
	public void addToWeaponBag(Weapon weapon) {
		weaponBag.add(weapon);
	}

	public void removeFromWeaponBag(int index) {
		weaponBag.remove(index);
	}

	public void displayWeaponBag() {
		int index = 0;
		for (Weapon weapon : weaponBag) {
			System.out.println("[" + ++index + "]" + weapon);
		}
	}

	public ArrayList<Weapon> getWeaponBag() {
		return weaponBag;
	}

	//Смерть персонажа
	@Override
	public void death() {
		System.out.println(this.name + " died.");
		this.dead = true;
		System.exit(0);
	}

	//Получение опыта
	public void upExp(int exp) {
		this.exp += exp;
		if (this.exp > 100) {
			lvlUp(this.exp - 100);
		}
	}

	//Поднятие уровня
	public void lvlUp(int overExp) {
		System.out.println("Lvl up. +1 bonus point for strength, agility, +10 hp");
		this.lvl += 1;
		int bonusFromLvl = 2;
		this.maxHp += 15;
		this.strength += bonusFromLvl;
		this.agility += bonusFromLvl;
		this.exp = overExp;
		this.hp = maxHp;
	}

	//Метод атаки персонажа
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
				System.out.println("Dealt with " + currentWeapon.getName() + " " + currentWeapon.getDamage() +
						" damage to the " + entity.getName());
				System.out.println("The " + entity.getName() + " has " + entity.getHp() + " hp remaining");
				if (entity.getHp() <= 0) {
					entity.death();
				}
			} else {
				System.out.println(name + " missed");
			}

		}
	}

	//Метод для выпивания зелий
	public void drink() {
		if (getPotionBag().isEmpty()) {
			System.out.println("Your potion bag is empty");
		} else {
			System.out.println("Your potion bag: ");
			displayPotionBag();
			dividingLine();
			System.out.print("Which one you want to use: (type 0 to exit)");
			int index = (scanner.nextInt() - 1);
			if (index == -1){
				return;
			}
			dividingLine();
			System.out.println("You used " + getPotionBag().get(index).getName());
			potionBag.get(index).consume(this);
			removeFromPotionBag(index);
		}
	}

	public void swapGun() {
		if (weaponBag.isEmpty()) {
			System.out.println("Your weapon bag is empty");
		} else {
			dividingLine();
			System.out.println("Your weapon bag: ");
			displayWeaponBag();
			weaponBag.add(currentWeapon);
			dividingLine();
			System.out.print("Choose weapon, that you want to equip: (type 0 to exit)");
			int choice = scanner.nextInt() - 1;
			if (choice == -1){
				return;
			}
			currentWeapon = weaponBag.get(choice);
			weaponBag.remove(choice);
			System.out.println("You swapped your weapon to " + currentWeapon.getName());
			dividingLine();
		}
	}

	public String toString() {
		//super(hp, strength, agility, 0, gold, name, armorClass, 1);
		return "Player info [" +
				"Name:" + this.name + ", " +
				"HP:" + this.hp + "/" + this.maxHp + ", " +
				"Strength:" + this.strength + ", " +
				"Agility:" + this.agility + ", " +
				"Gold:" + this.gold + ", " +
				"ArmorClass:" + this.armorClass + ", " +
				"Lvl:" + this.lvl + ", " +
				"exp:" + this.exp + "/100 ]";
	}


}
