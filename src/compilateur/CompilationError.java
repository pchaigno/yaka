package compilateur;

import java.util.LinkedList;
import java.util.List;

public class CompilationError {
	private List<String> errors;
	
	/**
	 * Constructor
	 */
	public CompilationError() {
		this.errors = new LinkedList<String>();
	}
	
	/**
	 * @return True if errors have occurred.
	 */
	public boolean errorsOccurred() {
		return this.errors.size()==0;
	}
	
	/**
	 * @return A string containing all the error messages.
	 */
	public String getErrorMessages() {
		String messages = "";
		for(String message: this.errors) {
			messages += message+"\n\n";
		}
		return messages;
	}
	
	/**
	 * Add an error message to the list.
	 * @param message The error message.
	 */
	public void addError(String message) {
		this.errors.add(message);
	}
}