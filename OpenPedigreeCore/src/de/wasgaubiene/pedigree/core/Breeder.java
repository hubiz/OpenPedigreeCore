package de.wasgaubiene.pedigree.core;

public class Breeder {
	
	public Breeder(String name, String breederkey) {
		super();
		this.name = name;
		this.breederkey = breederkey;
	}
	private String name;
	private String breederkey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreederkey() {
		return breederkey;
	}
	public void setBreederkey(String breederkey) {
		this.breederkey = breederkey;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breederkey == null) ? 0 : breederkey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Breeder other = (Breeder) obj;
		if (breederkey == null) {
			if (other.breederkey != null)
				return false;
		} else if (!breederkey.equals(other.breederkey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.breederkey+" - "+ this.name;
	}
	
}
