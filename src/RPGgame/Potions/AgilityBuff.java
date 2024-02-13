package RPGgame.Potions;

import RPGgame.Player;

public class AgilityBuff extends Potion {
	public AgilityBuff() {
		super("Agility-buff", "Gives you bonus strength", 50, 5);
	}

	public void consume(Player player){
		player.setStrength(player.getStrength() + effect);
		System.out.println(player);

	}
}
