package compilateur;

/**
 * Represent an identificator of constant.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class IdConst extends Ident {
	private int value;
	
	/**
	 * Constructor
	 * @param type The type.
	 * @param value The value.
	 */
	public IdConst(Type type, int value) {
		super(type);
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public boolean isConst() {
		return true;
	}

	@Override
	public boolean isVar() {
		return false;
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