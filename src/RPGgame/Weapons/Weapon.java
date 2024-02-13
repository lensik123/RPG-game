package RPGgame.Weapons;

public class Weapon {
	private String name;
	private int price;
	private int agilNeedstoUse;
	private int damage;

	public Weapon(String name, int price, int agilNeedstoUse, int damage) {
		this.name = name;
		this.price = price;
		this.agilNeedstoUse = agilNeedstoUse;
		this.damage = damage;
	}
	public Weapon(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAgilNeedstoUse() {
		return agilNeedstoUse;
	}

	public void setAgilNeedstoUse(int agilNeedstoUse) {
		this.agilNeedstoUse = agilNeedstoUse;
	}

	public Weapon() {
		this.name = "Fists";
		this.agilNeedstoUse = 10;
		this.damage = 5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String toString() {

		return "Name:" + name +
				", Price:" + price +
				", needsAgility:" + agilNeedstoUse +
				", Damage:" + damage;

	}
}
