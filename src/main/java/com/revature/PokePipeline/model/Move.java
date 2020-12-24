package com.revature.PokePipeline.model;

public class Move {
	// constructors: all fields
	private String name; // primary key
	private Type type;
	private int power;
	private boolean isPhysical;
	
	public Move() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Move(String name, Type type, int power, boolean isPhysical) {
		super();
		this.name = name;
		this.type = type;
		this.power = power;
		this.isPhysical = isPhysical;
	}
	@Override
	public String toString() {
		return "Move [name=" + name + ", type=" + type + ", power=" + power + ", isPhysical=" + isPhysical + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isPhysical ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + power;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (isPhysical != other.isPhysical)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (power != other.power)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isPhysical() {
		return isPhysical;
	}
	public void setPhysical(boolean isPhysical) {
		this.isPhysical = isPhysical;
	}
	

}
