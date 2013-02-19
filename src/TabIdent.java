import java.util.HashMap;

public class TabIdent {
	private HashMap<String, Ident> table;
	
	/**
	 * Constructor
	 * @param taille
	 */
	public TabIdent(int size) {
		this.table = new HashMap<String, Ident>(size); 
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return The value corresponding to the key.
	 */
	public Ident searchIdent(String key) {
		return this.table.get(key);
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return True if the map contain the key key.
	 */
	public boolean existIdent(String key) {
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
	
	
}
