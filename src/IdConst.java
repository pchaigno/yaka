
/**
 * Represent an identificator of constant.
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

	@Override
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
}
