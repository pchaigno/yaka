
/**
 * Represent an identificator.
 */
public abstract class Ident {
	protected Type type;
	
	/**
	 * Constructor
	 * @param type The type of the ident.
	 */
	public Ident(Type type) {
		this.type = type;
	}
	
	/**
	 * @return The value.
	 */
	public abstract int getValue();
	
	/**
	 * @return The type
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * @return True if it is a constant.
	 */
	public abstract boolean isConst();
	
	/**
	 * @return True if it is a variable.
	 */
	public abstract boolean isVar();
}
