import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import exceptions.ClientIsNullException;
import exceptions.NoCreditException;
import exceptions.NoStockException;

public class Client extends User implements ClientInterface {

	private int socSecNumber;
	private double credit;
	
	public Client() {
		
	}
	
	public Client(String firstname, String lastname, int socSecNumber, double credit) {
		
		super(firstname, lastname);
		this.socSecNumber = socSecNumber;
		this.credit       = credit;
	}
	
	/**********           METHODS            **********/
	
	// METHODE ACHAT
	public void buyMedicine(ArrayList<Client> arrCli, ArrayList<Medicin> arrMed) {
		
		// Si l'ArrayList arrCli n'est pas null, on continue
		if (arrCli != null) {
			
			// vérifie si le client demandé exists dans la liste de clients 
			Client client = null;
			try {
				client = this.clientExists(arrCli);
			} catch (ClientIsNullException e2) {
				
				e2.printStackTrace();
			}
			
			// Si l'ArrayList arrMed n'est pas null, on continue
			if (arrMed != null) {
				
				Medicin medicinTemp = new Medicin();
				Medicin medicin = null;
				// vérifie si le médicament demandé exists dans la liste
				medicin = medicinTemp.medicineExists(arrMed);
					
				int qte = 0;
				try {
					// réduction de stock des médicaments
					qte = medicin.reduceStock(medicin);
				} catch (NoStockException e) {
						
					e.printStackTrace();
				}
					
				double price = 0;
				
				// si le médicament demandé est du type fille MedWithPrescr
				if (medicin.isMedWPrescrType(medicin)) {
						
					MedWithPrescr medWithPrescr = (MedWithPrescr) medicin;
					
					// on calcule le prix selon la méthode fille
					price = medicin.CalculatePrice(medWithPrescr.getPrice(), qte, medWithPrescr.getRefund());
				}
				else {
					
					// sinon, on calcule le prix selon la méthode mère
					price = medicin.CalculatePrice(medicin.getPrice(), qte);
				}
							
				try {
					// essaye de facturer le client
					client.updateCredit(client, price, medicin);
				} catch (NoCreditException e) {
					
					e.printStackTrace();
				} 
					
				// écrire la transaction dans un fichier
				client.writeFile(client, medicin, price);
					
				System.out.println("Le fichier est écrit. \nLa commande est terminée avec succès.");
			}
			else {
				
				System.out.println("Le médicament demandé est inconnu.");
			}
		}
		else {
			
			System.out.println("Le client demandé est inconnu.");
		}
	}
	
	
	// MISE A JOUR DU CREDIT
	public void updateCredit(Client cli, double price, Medicin medicin) throws NoCreditException {
		
		if (cli != null) {
			
			// si le client a assez d'argent dans sa compte pour faire la transaction
			if ((cli.getCredit() - price) >= 0) {
				
				double oldCredit = cli.getCredit();
				cli.setCredit(cli.getCredit() - price);
				System.out.println("Le compte a été débité de " + oldCredit + " à " + cli.getCredit() + " euro.");
			}
			else {
				
				// sinon, on annule la transaction et on remet le stock au point d'avant la transaction
				medicin.restoreStock(medicin);
				
				throw new NoCreditException();
			}
		}
		else {
			System.out.println("Le client n'est pas connu.");
		}
	}
	
	// LIRECLIENT
	public Client clientExists(ArrayList<Client> cli) throws ClientIsNullException {
			
		System.out.println("Saississez le nom du client : ");
		String name = Lire.S();
			
		Iterator<Client> it = cli.iterator();
		Client client = new Client();
		Client clientSelect = new Client();
		boolean found = false;
			
		while (it.hasNext()) {
				
			client = it.next();
			if (Objects.equals(client.getLastname(), name)) {
					
				System.out.println("Le client '" + client.getLastname() + "' est trouvé dans la base de données.");
				clientSelect = client;
				found = true;
			}
		}
		
		if (!found) {
					
			System.out.println("Le client '" + name + "' est inconnu dans la base de données. Veuillez saisir un autre : ");
			clientExists(cli);
		}
		return clientSelect;
			
	}
	
	// ECRIRE DES DONNEES DANS UN FICHIER
	public void writeFile(Client cli, Medicin med, double price) {
		
		FileWriter fileTransaction;
		PrintWriter write;
		
		try {
			
			write = new PrintWriter(fileTransaction = new FileWriter("transaction.csv", true));
			
			write.print("Nom client;");
			write.print(cli.getLastname());
			write.print(";prénom client;");
			write.print(cli.getFirstname());
			write.print(";Nom médicament;");
			write.print(med.getName());
			write.print(";Montant payé;");
			write.print(price + "; \n");
			
			fileTransaction.close();
		}
		catch (IOException ioe) {
			
			ioe.printStackTrace();
			System.out.println("Erreur d'access au fichier.");
			
			
		}
	}
	
	// LIRE UN FICHIER (hard codé dans ce cas)
	public void ReadFile() {
		
		FileReader read;
		
		try {
			
			read = new FileReader("transaction.csv");
			String line;
			
			BufferedReader entry = new BufferedReader(read);
			
			while (entry.ready()) {
				
				line = entry.readLine();
				System.out.println(line);
			}
			
			read.close();
		}
		catch (FileNotFoundException e) {
			
			System.out.println("Erreur d'accès au fichier");
			e.printStackTrace();
		}
		catch (IOException ioe) {
			
			System.out.println("Le fichier ne peut pas être lu.");
			ioe.printStackTrace();
		}
	}
	
	
	/**********      GETTERS / SETTERS       **********/
	
    public int getSocSecNumber() {
		
		return this.socSecNumber;
	}
	
	public void setSocSecNumber(int socSecNumber) {
		
		this.socSecNumber = socSecNumber;
	}
	
	public double getCredit() {
		
		return this.credit;
	}
	
	public void setCredit(double credit) {
		
		this.credit = credit;
	}

	@Override
	public void readFile() {
		// TODO Auto-generated method stub
		
	}
	
}
