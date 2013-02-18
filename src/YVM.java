/**
 * 
 */
public class YVM {

	/**
	 * 
	 * @param obj
	 * @return
	 */
	String iconst(String obj) {
		return "iconst"+obj+"\n";
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
	// TODO Check that it's ior.
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
}
