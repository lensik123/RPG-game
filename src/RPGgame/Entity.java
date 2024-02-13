package RPGgame;

public abstract class Entity {
	//➜ Создайте мир, в котором будут обитать игрок и монстры. У всех них есть параметры:
	//
	//Единицы жизни;
	//Сила;
	//Ловкость.


	public Entity(int hp, int strength, int agility, int exp, String name, int armorClass, int lvl) {
		this.hp = hp;
		this.strength = strength;
		this.agility = agility;
		this.exp = exp;
		this.gold = gold;
		this.name = name;
		this.armorClass = armorClass;
		this.dead = false;
		this.lvl = lvl;
	}


	int hp;

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isFireResist() {
		return fireResist;
	}

	public void setFireResist(boolean fireResist) {
		this.fireResist = fireResist;
	}

	protected boolean fireResist;
	protected int strength;
	protected int agility;
	protected int exp;
	protected int gold;
	protected int armorClass;
	protected int lvl;
	protected boolean dead;
	protected String name;

	public void death(){
		System.out.println(this.name + " died");
		this.dead = true;
	}

	public int getHp() {
		return hp;
	}

	public void minusHP(int hp) {
		this.hp -= hp;
	}

	public void plusHP(int hp) {
		this.hp += hp;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getFirstChar() {
		return name.charAt(0);
	}


}
