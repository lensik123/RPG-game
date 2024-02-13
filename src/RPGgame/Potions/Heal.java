package RPGgame.Potions;

import RPGgame.Player;

public class Heal extends Potion {
	public Heal() {
		super("healing potion","Heals you for 30 points", 15, 30);
	}

	public void consume(Player player){
		player.plusHP(this.effect);
		System.out.println(player);
	}
}
