package compilateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent an identificator of function.
 * @author Paul Chaignon
 * @author Damien Le Guen
 * @author Antoine Lejeune
 * @author Benoit Travers
 */
public class IdFunction extends Ident {
	private String tag;
	private List<Type> listParamType;
	
	/**
	 * Constructor.
	 * @param tag The name of the function
	 * @param type The type.
	 */
	public IdFunction(String tag, Type type) {
		super(type);
		this.tag = tag;
		this.listParamType = new ArrayList<Type>();
	}
	
	/**
	 * Add a parameter to the definition of the function
	 * @param t The parameter type.
	 */
	public void addParam(Type t) {
		this.listParamType.add(t);
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
		return false;
	}

	@Override
	public boolean isFunction() {
		return true;
	}
}