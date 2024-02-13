package RPGgame;

import RPGgame.Monsters.Dragon;
import RPGgame.Monsters.Goblin;
import RPGgame.Monsters.Monster;
import RPGgame.Monsters.Skeleton;
import RPGgame.NPC.Blacksmith;
import RPGgame.NPC.Shopkeeper;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static RPGgame.DragonDrawing.readImage;
import static RPGgame.DragonDrawing.writeToFile;

public class World {


	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException, IOException {
		Shopkeeper shopkeeper = new Shopkeeper(40, 25, 10, 30, 30, "Samuel", 20, 10);
		Blacksmith blacksmith = new Blacksmith(40, 25, 10, 30, 30, "Samuel", 20, 10);

		dividingLine();
		System.out.println("Enter your name");
		String name = scanner.nextLine();
		Player player = new Player(50, 20, 20, 30, name, 12, 1);
		System.out.println(name + " came to save us from the dragon.");
		dividingLine();

		while (true) {
			System.out.println(player);
			//восстанавливаем currentHP игрока в размер максимального HP (аналогия отдыха)
			player.setHp(player.getMaxHp());
			dividingLine();
			System.out.println("You're at village, where do you wanna go?");
			System.out.print("""
					1.Go to the shop
					2.Go to the forest
					3.Go to the dragon's cave(boss)
					4.Go to the blacksmith
					5.Player interaction
					6.Exit
					Type number (1/2/3/4/5/6):""");
			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					shopkeeper.sellOrBuy(player);
					break;
				case 2:
					System.out.println("Going to the forest");
					forest(player);
					break;
				case 3:
					dragonCave(player);
					//🐲
				case 4:
					blacksmith.sellOrBuy(player);
					break;
				case 5:
					playerInteraction(player);
					break;
				case 6:
					System.exit(0);
				default:
					System.out.println("Неправильный ввод. Пожалуйста, введите значение по новой.");
			}
		}

	}

	static void playerInteraction(Player player) {

		System.out.print("""
				1.Potion bag
				2.Weapon bag
				3.Back to village
				Choose:""");

		int choice = scanner.nextInt();
		switch (choice){
			case 1 -> player.drink();
			case 2 -> player.swapGun();
			case 3 -> {
				return;
			}

		}



	}

	//битва с драконом
	static void dragonCave(Player player) throws IOException, InterruptedException {
		System.out.println("You face the fire dragon");
		char[][] chars = readImage("D:\\Dragon\\1.png", 21, 8);
		writeToFile("D:\\Dragon\\1.txt", chars);
		Dragon dragon = new Dragon();
		System.out.println("Fight against a " + dragon.getName() + " with " + dragon.getHp() + " hp");
		fight(player, dragon);
	}

	//лес
	static void forest(Player player) throws InterruptedException, IOException {

		//двойной массив для представления в консоли карты леса
		Entity[][] forestMap = new Entity[10][10];
		System.out.println("....");
		Thread.sleep(500);
		System.out.println("You are in the forest");
		System.out.print("\uD83D\uDC82 - player");
		System.out.print("\t\uD83D\uDC80 - skeleton");
		System.out.print("\t\uD83D\uDC7A - goblin\n");
		//заполнение леса врагами - рандомным образом.
		for (int i = 0; i < forestMap.length - 1; i++) {
			if (i % 2 == 0) {
				forestMap[i][new Random().nextInt(3, 7)] = new Skeleton();
			} else {
				forestMap[i][new Random().nextInt(3, 7)] = new Goblin();
			}
		}

		//позиция игрока начинается на 4 9
		int playerX = 4;
		int playerY = 9;

		while (true) {
			forestMap[playerY][playerX] = player;

			// Выводим карту леса в консоль
			for (Entity[] entities : forestMap) {
				for (int j = 0; j < entities.length; j++) {

					if (j == 0 || j == 1 || j == 2 || j == 7 || j == 8 || j == 9) {
						//деревья по бокам леса
						System.out.print("\uD83C\uDF32");
					} else if (entities[j] == player) {
						//иконка игрока
						System.out.print("\uD83D\uDC82");
					} else if (entities[j] != null) {
						//иконка скелета
						if (entities[j].getName().equals("Skeleton")) {
							System.out.print("\uD83D\uDC80");
						}
						// иконка гоблина
						else if (entities[j].getName().equals("Goblin")) {
							System.out.print("\uD83D\uDC7A");
						}
					} else {
						System.out.print("_");
					}
				}
				System.out.println();
			}

			System.out.println("""
					You can move:
					1.Left
					2.Top
					3.Right
					4.Return to the village
					5.Use potion
					6.Swap gun""");
			int move = scanner.nextInt();
			scanner.nextLine();

			forestMap[playerY][playerX] = null;

			//движение персонажа по карте
			switch (move) {
				//движение влево
				case 1 -> {
					if (playerX - 1 < 3) {
						System.out.println("Can't move into tree");
						forestMap[playerY][playerX] = player;
					} else {
						int newX = playerX - 1;
						if (forestMap[playerY][newX] != null) {
							dividingLine();
							System.out.println("Fight against a " + forestMap[playerY][newX].getName() + " with " + forestMap[playerY][newX].getHp() + " hp");
							fight(player, (Monster) forestMap[playerY][newX]);
						}
						playerX = newX;
					}
				}

				//движение вверх
				case 2 -> {
					if (playerY - 1 < 0) {
						dividingLine();
						System.out.println("you went through the forest and gained 100 experience and 100 gold");
						player.upExp(100);
						player.setGold(player.getGold() + 100);
						return;
					} else {
						int newY = playerY - 1;
						if (forestMap[newY][playerX] != null) {
							dividingLine();
							System.out.println("Fight against a " + forestMap[newY][playerX].getName() + " with " + forestMap[newY][playerX].getHp() + " hp");

							fight(player, (Monster) forestMap[newY][playerX]);
						}
						playerY = newY;
					}
				}

				//движение вправо
				case 3 -> {
					if (playerX + 1 > 6) {
						System.out.println("Can't move into tree");
						forestMap[playerY][playerX] = player;
					} else {
						int newX = playerX + 1;
						if (forestMap[playerY][newX] != null) {
							dividingLine();
							System.out.println("Fight against a " + forestMap[playerY][newX].getName() + " with " + forestMap[playerY][newX].getHp() + " hp");

							fight(player, (Monster) forestMap[playerY][newX]);
						}
						playerX = newX;
					}
				}

				//возвращение в деревню (саске, вернись в коноху)
				case 4 -> {
					return;
				}
				case 5 -> player.drink();
				case 6 -> player.swapGun();
				default -> throw new IllegalArgumentException();
			}

		}
	}

	//метод битвы
	static void fight(Player player, Monster monster) throws InterruptedException, IOException {
		int round = 1;
		while (player.dead != true && monster.dead != true) {
			System.out.println("-------Ход " + round + "-------");
			System.out.println("""
					Choose:
					1.Attack
					2.Use potion
					3.Swap gun
					3.Run""");
			int choice;
			choice = scanner.nextInt();
			dividingLine();
			switch (choice) {
				case 1 -> player.attack(monster);
				case 2 -> player.drink();
				case 3 -> player.swapGun();
				case 4 -> {
					return;
				}
				default -> throw new IllegalArgumentException();
			}
			dividingLine();
			if (monster.dead != true) {
				System.out.println(monster.getName() + "'s move");
				monster.attack(player);
				round++;
			}
			Thread.sleep(500);
		}
		dividingLine();
		Thread.sleep(500);

		if (monster.getName().equals("Arion")) {
			System.out.println();
			System.out.println("You've defeated the elder dragon Orion and saved the village");
			char[][] chars = readImage("D:\\Dragon\\1.png", 21, 8);
			writeToFile("D:\\Dragon\\1.txt", chars);
		} else {
			System.out.println("You won against " + monster.getName() + " and gained 30 exp and 10 gold");
			player.upExp(30);
			player.setGold(player.getGold() + 10);
			System.out.println(player);
			dividingLine();
		}

	}


	public static void dividingLine() {
		System.out.println("------------------");
	}

}

