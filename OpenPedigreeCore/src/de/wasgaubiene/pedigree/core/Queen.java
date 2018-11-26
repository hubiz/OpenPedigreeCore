package de.wasgaubiene.pedigree.core;

public class Queen {

	private String key;
	private Queen mother;
	private Queen father;
	private Breeder breeder;
	private Integer yearofBirth;
	private String belegstelle;


	public Queen(String key) {
		super();
		this.key = key;
	}
	
	

	public Queen(String key, Breeder breeder, int yearofBirth) {
		super();
		this.key = key;
		this.breeder = breeder;
		this.yearofBirth = new Integer(yearofBirth);
	}



	@Override
	public String toString() {

		if (this.mother != null) {

			return "\nQueen [key=" + key + "] is daugther from " + mother.toString();

		} else
			return "Queen [key=" + key + "]";
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Queen getMother() {
		return mother;
	}

	public void setMother(Queen mother) {
		this.mother = mother;
	}

	public String getBelegstelle() {
		return belegstelle;
	}

	public void setBelegstelle(String belegstelle) {
		this.belegstelle = belegstelle;
	}

	public Queen getFather() {
		return father;
	}

	public void setFather(Queen father) {
		this.father = father;
	}

	public Breeder getBreeder() {
		return breeder;
	}

	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}

	public Integer getYearofBirth() {
		return yearofBirth;
	}

	public void setYearofBirth(Integer yearofBirth) {
		this.yearofBirth = yearofBirth;
	}

	@Override
	public boolean equals(Object obj) {
		// System.out.println("compare "+this.getKey() +" with " + ((Queen)
		// obj).getKey() );
		return (((Queen) obj).key.equals(this.getKey()));
	}
}
