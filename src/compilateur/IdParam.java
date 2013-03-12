package compilateur;

/**
 * Represent an identificator of variable.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class IdParam extends IdVar {
	
	/**
	 * Constructor
	 * @param type The type.
	 * @param offset The offset.
	 */
	public IdParam(Type type, int offset) {
		super(type, offset);
	}

	@Override
	public boolean isConst() {
		return false;
	}

	@Override
	public boolean isVar() {
		return false;
	}

	@Override
	public boolean isParam() {
		return true;
	}

	@Override
	public boolean isFunction() {
		return false;
	}
}