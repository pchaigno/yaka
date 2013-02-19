
public class IdConst extends Ident {
	private int value;
	
	public IdConst(Type t, int v) {
		super(t);
		this.value = v;
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
