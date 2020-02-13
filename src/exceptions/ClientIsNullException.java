package exceptions;

public class ClientIsNullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 215243442596227448L;

	public ClientIsNullException() {
		
		
		System.out.println("Le client demandé est null.");
	}

}
