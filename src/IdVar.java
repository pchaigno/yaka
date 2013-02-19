/**
 * Represent an identificator of variable.
 */
public class IdVar extends Ident {
	private int offset;
	
	/**
	 * Constructor
	 * @param type The type.
	 * @param offset The offset.
	 */
	public IdVar(Type type, int offset) {
		super(type);
		this.offset = offset;
	}

	@Override
	public int getValue() {
		return this.offset;
	}

	@Override
	public boolean isConst() {
		return false;
	}

	@Override
	public boolean isVar() {
		return true;
	}
}
