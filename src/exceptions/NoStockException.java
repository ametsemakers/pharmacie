package exceptions;

public class NoStockException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4522768841155732899L;

	public NoStockException() {
		
		System.out.println("Il n'y a pas assez de stock disponible pour fournir la commande.");
		System.exit(0);
	}
}
