import exceptions.NegativeStockException;

// MEDICAMENTS AVEC ORDONNANCE
public class MedWithPrescr extends Medicin {

	private double refund;
	
	public MedWithPrescr() {
		
	}
	
	public MedWithPrescr(String name, double price, int stock, double refund) throws NegativeStockException {
		
		super(name, price, stock);
		this.refund = refund;
	}
	
	
	// CALCUL DE PRIX
	@Override
	public Double CalculatePrice(double price, int qte, double refund) {
			
		System.out.println("Methode enfant.");
		
		double discount = (price / 100) * refund;
		
		return price = (price - discount) * qte;
	}
	
	
	/**********     GETTERS / SETTERS     **********/
	
	public double getRefund() {
		
		return this.refund;
	}
	
	public void setRefund(double refund) {
		
		this.refund = refund;
	}
}
