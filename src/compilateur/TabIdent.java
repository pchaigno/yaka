package compilateur;

import java.util.HashMap;

/**
 * The identificators' table.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class TabIdent {
	private HashMap<String, Ident> table;
	
	/**
	 * Constructor
	 * @param size The initial size of the table.
	 */
	public TabIdent(int size) {
		this.table = new HashMap<String, Ident>(); 
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return The value corresponding to the key.
	 */
	public Ident getIdent(String key) {
		return this.table.get(key);
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return True if the map contain the key key.
	 */
	public boolean containsIdent(String key) {
		return this.table.containsKey(key);
	}
	
	/**
	 * Add a couple (key, value).
	 * @param key The key.
	 * @param id The value.
	 */
	public void setIdent(String key, Ident id) {
		this.table.put(key, id);
	}
	
	/**
	 * @return The number of variables in the table.
	 */
	public int getNumberOfVariables() {
		int nbVariables = 0;
		for(String key: this.table.keySet()) {
			if(this.table.get(key).isVar()) {
				nbVariables++;
			}
		}
		return nbVariables;
	}
	
	@Override
	public String toString() {
		return this.table.toString();
	}
}