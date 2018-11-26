package util;

import de.wasgaubiene.pedigree.core.Breeder;
import de.wasgaubiene.pedigree.core.Pedigree;
import de.wasgaubiene.pedigree.core.Queen;

public class Loader {

	public Loader(Pedigree p) {
		load(p);
	}

	private int load(Pedigree p) {

		int loaded = 0;

		// load some stuff from disk

		// load the breeders
		p.add(new Breeder("Zwick, Hubert", "ZWH"));
		loaded++;
		p.add(new Breeder("Menges, Markus", "MM"));
		loaded++;
		
		Queen q1=new Queen("B1", p.getBreeder("MM"), 2010);
		
		Queen b3=new Queen("B3", p.getBreeder("MM"), 2012);
		Queen b2=new Queen("B2", p.getBreeder("MM"), 2014);
		Queen b22=new Queen("B22", p.getBreeder("MM"), 2016);
		Queen b43=new Queen("B43", p.getBreeder("MM"), 2010);
		Queen b431=new Queen("B431", p.getBreeder("ZWH"), 2017);
		Queen b5=new Queen("B5");
		Queen b52=new Queen("B52");
		
		b3.setMother(q1);
		b3.setBreeder(p.getBreeder("ZWH"));
		b3.setYearofBirth(new Integer(2017));
		
		b2.setMother(q1);
		b43.setMother(b3);
		b431.setMother(q1);
		b52.setMother(b5);
		b22.setMother(b3);
		q1.setMother(b5);
		
		p.add(b22);
		p.add(b5);
		p.add(b52);
		p.add(b431);
		p.add(b3);
		p.add(b2);
		p.add(b43);
		p.add(q1);
		
		return loaded;
	}
}
