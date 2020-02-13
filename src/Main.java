import java.util.ArrayList;
import java.util.Iterator;

import exceptions.NegativeStockException;

public class Main {

	public static void main(String[] args) {
		
		// Création des listes pour tester les méthodes
		ArrayList<Medicin> arrMed = new ArrayList<Medicin>();
			
		try {
			arrMed.add(new Medicin("Paracetamol", 2.5, 1000));
			arrMed.add(new Medicin("Doliprane", 2, 2000));
			arrMed.add(new Medicin("Spasphon", 1.99, 5));
			arrMed.add(new MedWithPrescr("Flector", 4.5, 200, 75));
			arrMed.add(new MedWithPrescr("Ventoline", 6.5, 100, 80));
					
		} catch (NegativeStockException e) {

			e.printStackTrace();
			System.exit(1);
		}
				
		ArrayList<Client> arrCli =  new ArrayList<Client>();
		
		arrCli.add(new Client("Jean", "Dupont", 123456, 10.5));
		arrCli.add(new Client("Emma", "Petit", 965874, 22));
		arrCli.add(new Client("Jacques", "Durand", 367812, 6));
		arrCli.add(new Client("Pièrre", "Martin", 124965, 50));
		arrCli.add(new Client("Hugo", "Thomas", 739584, 8.99));
	
		// instanciation des objets
		Medicin m1 = new Medicin();
		Client c1 = new Client();
		
		
		
		/*** Les méthodes sont à décommenter (et de nouveau commenter) ***/
		
		// METHODE PRINCIPALE
		// décleche le processus d'achat
		c1.buyMedicine(arrCli, arrMed);
		
		
		// méthode pour vérifier si le medicament exist :
		//m1.medicineExists(arrMed);
		
		
		// méthode pour mettre à jour les stock :
		// m1.storeMedicine(arrMed);
		
		
		// méthode pour vérifier si le client exist :
		//c1.clientExists(arrCli);
		
		
		// méthode pour lire le fichier de commandes
		// l'écriture est optimisé pour lire en Excel, alors mois lisible dans la console
		//c1.ReadFile();
		
		// appèl à la connexion de la bdd par une instance de la classe Singleton
		// la méthode getInfos() renvoie un String pour tester la classe, biensûr à éviter en situation réelle
		//ConnectionDb conn = ConnectionDb.getInstance();
		//conn.getInfos();
	}
	
	

}
