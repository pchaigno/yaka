import java.util.HashMap;

public class TabIdent {
	private HashMap<String, Ident> table;
	
	/**
	 * Constructor
	 * @param taille
	 */
	public TabIdent(int taille) {
		this.table = new HashMap<String, Ident>(taille); 
	}
	
	/**
	 * Search a key in the map.
	 * @param clef The key to search.
	 * @return The value corresponding to the key.
	 */
	public Ident chercheIdent(String clef) {
		return this.table.get(clef);
	}
	
	/**
	 * Search a key in the map.
	 * @param clef The key to search.
	 * @return True if the map contain the key clef.
	 */
	public boolean existeIdent(String clef) {
		return this.table.containsKey(clef);
	}
	
	/**
	 * Add a couple (key, value).
	 * @param clef The key.
	 * @param id The value.
	 */
	public void rangeIdent(String clef, Ident id) {
		this.table.put(clef, id);
	}
}
