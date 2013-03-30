package compilateur;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The identificators' table.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class TabIdent {
	private HashMap<String, IdFunction> global;
	private HashMap<String, Ident> local;
	
	/**
	 * Constructor
	 * @param size The initial size of the table.
	 */
	public TabIdent(int size) {
		this.global = new HashMap<String, IdFunction>(); 
		this.local = new HashMap<String, Ident>();
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return The value corresponding to the key.
	 */
	public Ident getIdent(String key) {
		return this.local.get(key);
	}
	
	/**
	 * Get all parameters from the table of local variables.
	 * @return The parameters.
	 */
	public List<IdParam> getParameters() {
		List<IdParam> parameters = new LinkedList<IdParam>();
		Ident value;
		for(String key: this.local.keySet()) {
			value = this.local.get(key);
			if(value.isParam()) {
				parameters.add((IdParam)value);
			}
		}
		return parameters;
	}
	
	/**
	 * Get a function by its name from the table.
	 * @param key The name of the function.
	 * @return The function (as an ident).
	 */
	public IdFunction getFunction(String key) {
		return this.global.get(key);
	}
	
	/**
	 * Search a key in the map.
	 * @param key The key to search.
	 * @return True if the map contain the key key.
	 */
	public boolean containsIdent(String key) {
		return this.local.containsKey(key);
	}
	
	/**
	 * Check if the table contains a function.
	 * @param key The name of the function.
	 * @return True if it contains the function.
	 */
	public boolean containsFunction(String key) {
		return this.global.containsKey(key);
	}
	
	/**
	 * Add a couple (key, value).
	 * @param key The key.
	 * @param id The value.
	 */
	public void setIdent(String key, Ident id) {
		this.local.put(key, id);
	}
	
	/**
	 * Add a function to the table.
	 * @param key The name of the function.
	 * @param id The function.
	 */
	public void setFunction(String key, IdFunction id) {
		this.global.put(key, id);
	}
	
	/**
	 * @return The number of variables in the table.
	 */
	public int getNumberOfVariables() {
		int nbVariables = 0;
		for(String key: this.local.keySet()) {
			if(this.local.get(key).isVar()) {
				nbVariables++;
			}
		}
		return nbVariables;
	}
	
	@Override
	public String toString() {
		return "globaux\n" + this.global.toString() + "\nlocaux\n" + this.local.toString();
	}
	
	/**
	 * Clear the table.
	 */
	public void clear() {
		this.local.clear();
	}
}