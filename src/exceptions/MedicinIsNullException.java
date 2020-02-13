package exceptions;

public class MedicinIsNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 979849044282477337L;

	public MedicinIsNullException() {
		
		System.out.println("Le médicament demandé est null.");
	}
}
