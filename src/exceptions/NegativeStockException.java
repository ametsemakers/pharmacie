package exceptions;

public class NegativeStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3503764329595590950L;

	public NegativeStockException() {
		
		System.out.println("Le stock ne peut pas être négatif.");
	}
}
