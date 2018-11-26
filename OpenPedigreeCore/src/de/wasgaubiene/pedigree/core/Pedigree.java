package de.wasgaubiene.pedigree.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import util.Loader;

public class Pedigree {

	private static final Logger log = Logger.getLogger(Pedigree.class.getName());

	// Map<String, Queen> num = new HashMap()<String, Queen>();

	private HashMap<String, Queen> queens = new HashMap<>(1);

	private HashMap<String, Breeder> breeder = new HashMap<>(1);

	private HashMap<String, Queen> rootqueens = new HashMap<String, Queen>();

	private LinkedList<Queen> list = new LinkedList<>();

	public Pedigree() {
		log.info("init Pedigree-Database");

		new Loader(this);

		determineMaxDepth();
		determineRootQueensCount();

		// log.info(this.toString());
	}

	public boolean add(Queen q) {
		queens.put(q.getKey(), q);
		return true;
	}

	public boolean add(Breeder b) {
		log.info("add new Breeder:" + b.getBreederkey());
		breeder.put(b.getBreederkey(), b);
		return true;
	}
	
	public Breeder getBreeder(String key) {
		
		return (Breeder)breeder.get(key);
	}

	@Override
	public String toString() {

		StringBuffer b = new StringBuffer();

		Iterator it = breeder.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			// System.out.println(pair.getKey() + " = " + pair.getValue());
			b.append("\nBREEDER:" + breeder.get(pair.getKey()).toString());
			// it.remove(); // avoids a ConcurrentModificationException
		}

		return b.toString();
	}

	public static void main(String[] args) {
		Pedigree p = new Pedigree();

		/*
		 * Iterator it = p.queens.entrySet().iterator(); while (it.hasNext()) {
		 * Map.Entry pair = (Map.Entry) it.next(); // System.out.println(pair.getKey() +
		 * " = " + pair.getValue()); // b.append("\nBREEDER:" +
		 * breeder.get(pair.getKey()).toString());
		 * 
		 * tree = p.printQueenParentTree(p.queens.get(pair.getKey()), 0); it.remove();
		 * // avoids a ConcurrentModificationException
		 * 
		 * //System.out.println(tree); }
		 */

		// System.out.println("depth " + p.determineMaxDepth());
		// System.out.println("count of root-Queens: " + p.determineRootQueensCount());

		p.dumpPedigreeChildTree();
	}

	private String printQueenParentTree(Queen q, int level) {

		StringBuffer s = new StringBuffer();
		// s.append("\n--------------------------------------------------");

		if (q.getMother() == null) {

			// this is one of the root queens
			list.addFirst(q);

		} else {
			level++;

			// s.append("queen [" + q.getKey() + "]");
			// s.append(" is dauther of \n");
			// s.append(addLevelSpace(level, " "));

			// continue with mother
			s.append(printQueenParentTree(q.getMother(), level));

		}
		s.append(addLevelSpace(level, "  "));
		s.append("queen [");
		s.append(q.getKey());
		s.append("]");

		return s.toString();
	}

	private String addLevelSpace(int l, String ls) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < l; i++) {
			s.append(ls);
		}
		return s.toString();
	}

	public int determineMaxDepth() {

		int r = 0;
		Iterator it = queens.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			int r1 = determineDepth(1, queens.get(pair.getKey()));
			// it.remove(); // avoids a ConcurrentModificationException

			if (r1 > r) {
				r = r1;
			}
		}

		return r;
	}

	private int determineDepth(int level, Queen q) {
		System.out.println("check queen " + q.getKey() + " - level:" + level + " - hasMother:"
				+ (q.getMother() == null ? "false" : q.getMother().getKey()));
		if (q.getMother() == null) {
			// end of search

			return level;
		} else {
			level++;
			return determineDepth(level, q.getMother());
		}
	}

	public int determineRootQueensCount() {

		System.out.println("determineRootQueensCount for " + this.queens.size() + " entrys");

		Iterator it = this.queens.entrySet().iterator();
		while (it.hasNext()) {

			Map.Entry pair = (Map.Entry) it.next();

			Queen q = findRootQueens((Queen) queens.get(pair.getKey()), 1);
			rootqueens.put(q.getKey(), q);

		}

		return rootqueens.size();
	}

	private Queen findRootQueens(Queen q, int level) {

		if (level == 1) {
			System.out.println("findRootQueen for queen [" + q.getKey() + "] - searchlevel:" + level);
		}

		if (level > 10) {
			throw new IllegalArgumentException("MAXIMUM LEVEL REACHED! - findRootQueens");
		}

		if (q.getMother() == null) {
			System.out.println("found root-mother: " + q.getKey());
			return q;
			// root found
		} else {
			level++;
			return findRootQueens(q.getMother(), level);
		}

	}

	public void dumpPedigreeChildTree() {

		Iterator it = this.rootqueens.entrySet().iterator();
		while (it.hasNext()) {

			Map.Entry pair = (Map.Entry) it.next();
			Queen rq = (Queen) rootqueens.get(pair.getKey());

			System.out.println("\n\n-Königin Nr [" + rq.getKey() + "] ");
			dumpChildrenBranch(rq,0);

		}
	}

	private void dumpChildrenBranch(Queen root, int level) {
		Queen[] childre = findChildren(root, 0);
		if (childre != null) {
			
			//for all direct children of root-queens
			for (int i = 0; i < childre.length; i++) {
				System.out.println(addLevelSpace(level, "     ")+ "|-"+  "-- Nachkommen " + childre[i].getKey());
				
				dumpChildrenBranch(childre[i], ++level);
			}
		} else {
			return;
		}

	}

	private Queen[] findChildren(Queen mother, int level) {
		List<Queen> children = new ArrayList<Queen>();

		// search over the whole pedigreelist an find the direct childs from the given
		// mother
		Iterator it = this.queens.entrySet().iterator();
		while (it.hasNext()) {

			Map.Entry pair = (Map.Entry) it.next();
			Queen rq = (Queen) queens.get(pair.getKey());

			if (rq.getMother() != null && rq.getMother().equals(mother)) {
				children.add(rq);
			}

		}

		return children.toArray(new Queen[children.size()]);
	}

}
