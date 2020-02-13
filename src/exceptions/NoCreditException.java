package exceptions;

public class NoCreditException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9102399872665625261L;

	public NoCreditException() {
		
		System.out.println("Le client n'a pas assez de crédit (Exception!).");
		System.exit(0);
	}
}
