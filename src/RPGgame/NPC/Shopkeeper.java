package RPGgame.NPC;

import RPGgame.Entity;
import RPGgame.Player;
import RPGgame.Potions.*;

import java.util.ArrayList;
import java.util.Scanner;

import static RPGgame.World.dividingLine;

public class Shopkeeper extends Entity {

	ArrayList<Potion> shop;
	Scanner scanner = new Scanner(System.in);

	public Shopkeeper(int hp, int strength, int agility, int exp, int gold, String name, int armorClass, int lvl) {
		super(hp, strength, agility, 1, name, armorClass, lvl);
		this.gold = 30;
		this.shop = new ArrayList<>();
		shop.add(new Heal());
		shop.add(new ArmorBuff());
		shop.add(new AgilityBuff());
		shop.add(new FireResistance());

	}

	@Override
	public void death() {
		System.out.println(this.name + " dead");
		this.dead = true;
	}


	public void displayItemsForSale() {
		int index = 0;
		for (Potion potion : shop) {
			System.out.println("[" + ++index + "]" + potion);
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

			if (player.getGold() > shop.get(index).getPrice()) {
				int itemCost = shop.get(index).getPrice();
				System.out.println("You bought: " + shop.get(index).getName());
				dividingLine();
				System.out.println("Your potion bag");
				player.setGold(player.getGold() - itemCost);
				player.addToPotionBag(shop.get(index));
				player.displayPotionBag();

				this.gold += itemCost;
				shop.remove(index);


			} else {
				System.out.println("isn't enough gold");
			}
		} else if (buyOrSell == 2) {

			System.out.println("---------------------");
			System.out.println("Shopkeeper's gold:" + this.gold);
			if (!player.getPotionBag().isEmpty()) {

				System.out.println("Your potion bag: ");
				player.displayPotionBag();
				System.out.println("You can sell each item only for 50% of his cost. Which one you wanna sell? (Type a number)");
				int choice = Integer.parseInt(scanner.nextLine());
				int index = choice - 1;

				if (this.gold >= (player.getPotionBag().get(index).getPrice() / 2)) {
					int itemCost = player.getPotionBag().get(index).getPrice() / 2;

					this.gold -= itemCost;
					player.setGold(player.getGold() + itemCost);
					System.out.println("You sold: " + player.getPotionBag().get(index).getName() +
							"\nYour gold: " + player.getGold());
					player.removeFromPotionBag(index);
				} else {
					System.out.println("Shopkeeper doesn't have enough money");
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
			case 2:
				return;
			default:
				System.out.println("something went wrong");
		}

	}


}
