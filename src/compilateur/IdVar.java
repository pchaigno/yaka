package compilateur;

/**
 * Represent an identificator of variable.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class IdVar extends Ident {
	protected int offset;
	
	/**
	 * Constructor
	 * @param type The type.
	 * @param offset The offset.
	 */
	public IdVar(Type type, int offset) {
		super(type);
		this.offset = offset;
	}

	public int getOffset() {
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

	@Override
	public boolean isParam() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}
}
