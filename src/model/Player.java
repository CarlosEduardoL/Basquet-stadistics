package model;

public class Player {
	
	private String name;
	private int age,production;
	private double defense,offense;
	
	
	
	public Player(String name, int age, int production, double defense, double offense) {
		super();
		this.name = name;
		this.age = age;
		this.production = production;
		this.defense = defense;
		this.offense = offense;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getProduction() {
		return production;
	}
	public void setProduction(int production) {
		this.production = production;
	}
	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public double getOffense() {
		return offense;
	}
	public void setOffense(double offense) {
		this.offense = offense;
	}

}
