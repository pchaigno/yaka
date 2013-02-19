/**
 * 
 */
public class YVM {
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	String iconstInt(int obj) {
		return "iconst "+obj+"\n";
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	String iconstBool(boolean obj) {
		if(obj) {
			return "iconst VRAI\n";
		}
		return "iconst FAUX\n";
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	String iload(String str) {
		return "iload "+str+"\n";
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	String iconst(String str) {
		return "iconst "+str+"\n";
	}
	
	/**
	 * 
	 * @return
	 */
	String iadd() {
		return "iadd\n";
	}

	/**
	 * 
	 * @return
	 */
	String imul() {
		return "imul\n";
	}

	/**
	 * 
	 * @return
	 */
	String isub() {
		return "isub\n";
	}

	/**
	 * 
	 * @return
	 */
	String iand() {
		return "iand\n";
	}

	/**
	 * 
	 * @return
	 */
	String ior() {
		return "ior\n";
	}

	/**
	 * 
	 * @return
	 */
	String idiv() {
		return "idiv\n";
	}

	String iinf() {
		return "iinf\n";
	}

	String iinfegal() {
		return "iinfegal\n";
	}

	String isupegal() {
		return "isupegal\n";
	}

	String isup() {
		return "isup\n";
	}

	String iegal() {
		return "iegal\n";
	}

	String idiff() {
		return "idiff\n";
	}
}
