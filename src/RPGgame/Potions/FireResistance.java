package RPGgame.Potions;

import RPGgame.Player;

public class FireResistance extends Potion{

	public FireResistance() {
		super("Fire-resist potion", "gives you fire resist for 50%", 100, 0);
	}

	@Override
	public void consume(Player player) {
		player.setFireResist(true);
	}
}
