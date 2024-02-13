package RPGgame.Potions;

import RPGgame.Player;

public class ArmorBuff extends Potion {
	public ArmorBuff() {
		super("Armor-buff potion", "Gives you +2 points on class armor", 60, 3);
	}

	public void consume(Player player) {
		player.setArmorClass(player.getArmorClass() + effect);
		System.out.println(player);
	}


}
