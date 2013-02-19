
public abstract class Ident {
	protected Type type;
	
	public Ident(Type t){
		this.type = t;
	}
	
	public abstract int getValue();
	
	public Type getType() {
		return this.type;
	}
	
	public abstract boolean isConst();
	
	public abstract boolean isVar();
}
