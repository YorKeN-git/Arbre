import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Arbre {
	 
	/*
	 * Tableau light : 
	 * 0 -> 1;
	 * 0 -> 128;
	 * 1 -> 2;
	 * 1 -> 65;
	 * 128 -> 129;
	 * 128 -> 190;
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez votre jeu de donn�e : ");
		//Cr�ation de notre liste 2D 
		ArrayList<ArrayList<Integer> > liste = new ArrayList<ArrayList<Integer> >(); 
		//Tableau des valeurs saisie par l'utilisateur pour l'affichage des noeuds methode : afficherNoeuds()
		ArrayList<Integer> listeValeur = new ArrayList<Integer>();
		//Tant que l'utilisateur souhaite saisir un jeu d'entr�e 
		boolean isStop = false; //Boolean pour sortir de ma boucle WHILE
		while (!isStop){
			System.out.println("Valeur du p�re : ");
			//Recuperd la valeur du p�re 
			int valeurPere = sc.nextInt();
			listeValeur.add(valeurPere);
			System.out.println("Valeur du fils :");
			//Recuperd la valeur du fils
			int valeurFils = sc.nextInt();
			listeValeur.add(valeurFils);
			//Ajoute � notre liste les valeurs r�cup�r�es
			liste.add(new ArrayList<>(Arrays.asList(valeurPere, valeurFils))); 
			System.out.println("Nouvelle saisie ? (O/N)");
			//R�cuperd le feedback utilisateur. 
			char feedback = sc.next().toUpperCase().charAt(0);
			if(feedback == 'N') {
				//L'utilisateur a termin� de saisir son jeu d'entr�e 
				isStop = true;
				//Affichage du jeu d'entr�e 
				System.out.println(liste);
				//Affiche tous les noeuds de notre arbre 
				afficherNoeuds(listeValeur);
			}
		}
		
	}
	
	/*
	 * M�thode pour afficher tout les noeuds de notre arbre. 
	 * */
	private static void afficherNoeuds(ArrayList<Integer> listeValeur) {
		// Test : affiche les valeurs enregistr� dans listeValeur
		//System.out.println(listeValeur);
		System.out.println("Voici tous les noeuds de votre arbre : ");
		//Supprime les doublons de notre liste
		Set<Integer> listeNoeuds = new HashSet<Integer>(listeValeur);
		System.out.println(listeNoeuds);
	}
}
