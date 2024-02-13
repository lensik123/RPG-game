package RPGgame.NPC;

import RPGgame.Entity;
import RPGgame.Player;
import RPGgame.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

import static RPGgame.World.dividingLine;

public class Blacksmith extends Entity {
	ArrayList<Weapon> weaponShop;
	Scanner scanner = new Scanner(System.in);


	public Blacksmith(int hp, int strength, int agility, int exp, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, exp, name, armorClass, lvl);
		this.weaponShop = new ArrayList<>();
		weaponShop.add(new Weapon("Shadow Dagger",25, 20, 10));
		weaponShop.add(new Weapon("Grand Fire Sword",40, 22, 15));
		weaponShop.add(new Weapon("Draining Katana",100, 24, 25));
	}
	@Override
	public void death() {
		System.out.println(this.name + " dead");
		this.dead = true;
	}

	public void displayItemsForSale() {
		int index = 0;
		for (Weapon weapon : weaponShop) {
			System.out.println("[" + ++index + "]" + weapon);
		}

	}

	public void sellOrBuy(Player player) {
		System.out.println("---------------------");
		System.out.println("""
				1.Buy
				2.Sell
				3.Go back to village""");
		int buyOrSell = Integer.parseInt(scanner.nextLine());

		if (buyOrSell == 1) {
			System.out.println("---------------------");
			System.out.println("Your gold: " + player.getGold() + "\nShop:");
			displayItemsForSale();
			System.out.println("Which one you wanna buy? (Type a number)");
			int choice = Integer.parseInt(scanner.nextLine());
			int index = choice - 1;

			if (player.getGold() > weaponShop.get(index).getPrice()) {
				int itemCost = weaponShop.get(index).getPrice();
				System.out.println("You bought: " + weaponShop.get(index).getName());
				dividingLine();
				System.out.println("Your weapon bag");
				player.setGold(player.getGold() - itemCost);
				player.addToWeaponBag(weaponShop.get(index));
				player.displayWeaponBag();

				this.gold += itemCost;
				weaponShop.remove(index);
			} else {
				System.out.println("isn't enough gold");
			}
		} else if (buyOrSell == 2) {

			System.out.println("---------------------");
			System.out.println("Blacksmith's gold:" + this.gold);
			if (!player.getWeaponBag().isEmpty()) {

				System.out.println("Your weapon bag: ");
				player.displayWeaponBag();
				System.out.println("You can sell each item only for 50% of his cost. Which one you wanna sell? (Type a number)");
				int choice = Integer.parseInt(scanner.nextLine());
				int index = choice - 1;

				if (this.gold >= (player.getWeaponBag().get(index).getPrice() / 2)) {
					int itemCost = player.getWeaponBag().get(index).getPrice() / 2;

					this.gold -= itemCost;
					player.setGold(player.getGold() + itemCost);
					System.out.println("You sold: " + player.getWeaponBag().get(index).getName() +
							"\nYour gold: " + player.getGold());
					player.removeFromWeaponBag(index);
				} else {
					System.out.println("Blacksmith doesn't have enough money");
				}
			} else {
				System.out.println("Your bag is empty. Nothing to sell");
			}
		} else {
			return;
		}


		System.out.println("""
				---------------------
				Wanna stay in shop? or go back to village?
				1.stay
				2.go back to village""");
		int choice = Integer.parseInt(scanner.nextLine());

		switch (choice) {
			case 1:
				sellOrBuy(player);
				break;
			case 2:
				return;
			default:
				System.out.println("something went wrong");
				return;
		}

	}
}
