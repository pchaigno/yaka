
public class IdVar extends Ident {
	private int offset;
	
	public IdVar(Type t, int o) {
		super(t);
		this.offset = o;
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
