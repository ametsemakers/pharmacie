import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import exceptions.NegativeStockException;
import exceptions.NoStockException;

public class Medicin {
	
	protected int id, stock;
	protected String name;
	protected double price;
	private static int counter = 0;
	private int number;
	
	public Medicin() {
		
	}
	
	public Medicin(String name, double price, int stock) throws NegativeStockException {
		
		this.id    = ++counter;
		this.name  = name;
		this.price = price;
		
		if (stock < 0) {
			
			throw new NegativeStockException();
		} 
		else {
			
			this.stock = stock;
		}
	}
	
	/**********           METHODS         **********/
	
	
	// APPROVISIONNER
	public void storeMedicine(ArrayList<Medicin> med) {
		
		Medicin medicine = null;
		
		medicine = this.medicineExists(med);
			
		System.out.println("Le stock actuel est : " + medicine.getStock());
		System.out.println("Saississez le nombre � ajouter : ");
		int stock = Lire.i();
		
		medicine.setStock(medicine.getStock() + stock);
		
		System.out.println("Le stock a bien �t� mis � jour (" + medicine.getStock() + ").");
	}
	
	// REDUIRE LE STOCK APRES UNE COMMANDE
	public int reduceStock(Medicin med) throws NoStockException {
		
		System.out.println("Saississez le nombre de bo�tes : ");
		this.number = Lire.i();
		
		if ((med.getStock() - this.number) >= 0) {
			
			int prevNumber = med.getStock();
			med.setStock(med.getStock() - this.number);		
			
			System.out.println("Le stock a �t� mis � jour de " + prevNumber + " � " + med.getStock() + " bo�tes.");
			return this.number;
		}
		else {
		
			throw new NoStockException();
		}
	}
	
	// ANNULE LA REDUCTION DE STOCK APRES UNE COMMANDE SANS CREDIT
	public void restoreStock(Medicin med) {
		
		int currentStock = med.getStock();
		med.setStock(med.getStock() + this.number);
		
		System.out.println("La commande a �t� annul�e. \nLes " + this.number + " bo�tes ont �t� remis dans le stock (de " + currentStock + " � " + med.getStock() + " bo�tes).");
	}
	
	// LIREMEDICAMENTS
	public Medicin medicineExists(ArrayList<Medicin> med) {
		
		System.out.println("Saississez le nom du m�dicament : ");
		String name = Lire.S();
		
		Medicin medicine = new Medicin();
		Medicin medicinChosen = new Medicin();
		boolean found = false;
		
		Iterator<Medicin> it = med.iterator();
		
		while (it.hasNext()) {
			
			medicine = it.next();
			if (Objects.equals(medicine.getName(), name)) {
				
				System.out.println("Le m�dicament '" + medicine.getName() + "' est trouv� dans la base de donn�es.");
				
				medicinChosen = medicine;
				found = true;
			}
		}
		if (!found) {
			
			System.out.println("Le m�dicament '" + name + "' est inconnu dans la base de donn�es.");
			medicineExists(med);
		}
		return medicinChosen;
	}
	
	// CALCUL DE PRIX
	public Double CalculatePrice(double price, int qte) {
		
		System.out.println("M�thode parent.");
		return price = price * qte;
	}
	
	// CALCUL DE PRIX - METHODE FILLE
	public Double CalculatePrice(double price, int qte, double refund) {
		
		return null;
	}
	
	// VERIFIE SI L'OBJET EST DE TYPE MERE OU FILLE
	public boolean isMedWPrescrType(Medicin med) {
		
		boolean instance = false;
		
		if (med instanceof MedWithPrescr) {
			
			instance = true;
		}
		else if (med instanceof Medicin) {
			
			instance = false;
		}
		return instance;
	}
	

	/**********     GETTERS / SETTERS     **********/

	public int getId() {
		
		return this.id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public double getPrice() {
		
		return this.price;
	}
	
	public void setPrice(double price) {
		
		this.price = price;
	}
	
    public int getStock() {
		
		return this.stock;
	}
	
	public void setStock(int stock) {
		
		this.stock = stock;
	}
}
