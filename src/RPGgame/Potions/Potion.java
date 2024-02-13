package RPGgame.Potions;

import RPGgame.Player;

public class Potion implements Drinkable{
	String name, description;
	int price;
	int effect;

	public Potion(String name,String description, int price, int effect) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.effect = effect;
	}

	@Override
	public void consume(Player player) {
		System.out.println("got some effect");
	}

	public String toString(){
		return "Name:" + name +
				", Price:" + price +
				", Description: " + description;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
