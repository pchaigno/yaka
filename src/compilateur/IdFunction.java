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
	private List<Type> parametersTypes;
	
	/**
	 * Constructor.
	 * @param tag The name of the function
	 * @param type The type.
	 */
	public IdFunction(String tag, Type type) {
		super(type);
		this.parametersTypes = new ArrayList<Type>();
	}
	
	/**
	 * Add a parameter to the definition of the function
	 * @param type The parameter type.
	 */
	public void addParam(Type type) {
		this.parametersTypes.add(type);
	}
	
	/**
	 * @return The number of parameters.
	 */
	public int getNbParameters() {
		return this.parametersTypes.size();
	}
	
	/**
	 * @param i The rank of the argument.
	 * @return The type of the i-ieme parameter.
	 */
	public Type getTypeOfParameter(int i) {
		return this.parametersTypes.get(i);
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