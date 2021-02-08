import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
	private static Scanner sc = new Scanner(System.in);
	private static int racine = 0 ;
	private static int[] tabValeurFils; 
	private static int[] tabValeurPere; 
	private static int hauteurArbre; 
	private static Set<Integer> listeNoeuds;
	private static List<Integer> listFeuilleArbre;
	
	public static void main(String[] args) {
		System.out.println("Saisissez votre jeu de donnée : ");
		//Création de notre liste 2D 
		ArrayList<ArrayList<Integer> > liste = new ArrayList<ArrayList<Integer> >(); 
		//Tableau des valeurs saisie par l'utilisateur pour l'affichage des noeuds methode : afficherNoeuds()
		ArrayList<Integer> listeValeur = new ArrayList<Integer>();
		//Tant que l'utilisateur souhaite saisir un jeu d'entrée 
		boolean isStop = false; //Boolean pour sortir de ma boucle WHILE
		while (!isStop){
			boolean saisiePereNOK = false;
			int valeurPere = 0;
			boolean saisieFilsNOK = false;
			int valeurFils = 0;
			do {
				System.out.println("Valeur du père : ");
				String str = sc.nextLine();
				try {
					valeurPere = Integer.parseInt(str);
					listeValeur.add(valeurPere);
					saisiePereNOK = true;
				}catch (NumberFormatException e) {
					System.err.println("ERREUR : Cette valeur n'est pas un chiffre ! ");
				}
			}while(!saisiePereNOK);
			do {
				System.out.println("Valeur du fils : ");
				String str = sc.nextLine();
				try {
					valeurFils = Integer.parseInt(str);
					listeValeur.add(valeurFils);
					saisieFilsNOK = true;
				}catch (NumberFormatException e) {
					System.err.println("ERREUR : Cette valeur n'est pas un chiffre ! ");
				}
			}while(!saisieFilsNOK);
			//Ajoute à notre liste les valeurs récupérées
			liste.add(new ArrayList<>(Arrays.asList(valeurPere, valeurFils))); 
			System.out.println("Nouvelle saisie ? (O/N)");
			//Récuperd le feedback utilisateur. 
			char feedback = sc.next().toUpperCase().charAt(0);
			if(feedback == 'N') {
				//L'utilisateur a terminé de saisir son jeu d'entrée 
				isStop = true;
				//Affichage du jeu d'entrée 
				System.out.println("Voici votre jeu d'entrée : ");
				System.out.println(liste);
				//Affiche tous les noeuds de notre arbre 
				afficherNoeuds(listeValeur);
				trouverRacine(liste);
				trouverFeuilles(liste);
				boolean isTrue = true;
				while(isTrue) {
					System.out.println("\n\rVoulez-vous tester une valeur de votre arbre ? (O/N)");
					feedback = sc.next().toUpperCase().charAt(0);
					if(feedback == 'O' || feedback == 'N') {
						if(feedback == 'O') {
							System.out.println("L'utilisateur veut tester une valeur ");
							trouverRangValeur();
						}else {
							System.out.println("Au revoir !");
							isTrue = false; 
						}
					}else {
						System.err.println("Erreur de saisie. ");
					}
				}
				
				
			}
		}
		
	}
	
	private static boolean verifierSaisieUtilisateur() {
		boolean saisieOK = false;
		return saisieOK; 
	}
	
	/*
	 * Méthode pour afficher tout les noeuds de notre arbre. 
	 * */
	private static void afficherNoeuds(ArrayList<Integer> listeValeur) {
		// Test : affiche les valeurs enregistré dans listeValeur
		//System.out.println(listeValeur);
		System.out.println("Voici tous les noeuds de votre arbre : ");
		//Supprime les doublons de notre liste
		listeNoeuds = new HashSet<Integer>(listeValeur);
		System.out.println(listeNoeuds);
		System.out.println(" ");
	}
	
	private static void trouverRacine(ArrayList<ArrayList<Integer>> liste) {
		//Trouver la racine de l'arbre
		//Information : La racine de notre arbre est uniquement Pere de ce fait
		//				elle ne peut être présente dans tabValeurFils

		tabValeurFils = new int[liste.size()];
		String racineArbre = "";
		//On parcourt notre liste 
		for (int i = 0; i < liste.size(); i++) {
			//On commence à j=1 car on veut récupérer la valeurFils de notre liste [valeurPere, valeurFils]
			for (int j = 1; j < liste.get(i).size(); j++) {
				//Enregistre la valeurFils dans notre tableau
				tabValeurFils[i] = liste.get(i).get(j);
			}
		}
		//On parcourt à nouveau notre tableau 
		for (int i = 0; i < liste.size(); i++) {
			//On parcourt nos valeurPere
			for (int j = 0; j < liste.get(i).size()-1; j++) {
				int dontMatch = 0;
				//On parcourt nos valeurFils
				for (int k = 0; k < tabValeurFils.length; k++) {
					//Si pas de concordance entre les valeursFils et Pere 
					if (tabValeurFils[k] != liste.get(i).get(j)) {
						dontMatch++;
						if(dontMatch == tabValeurFils.length) {
							//La Racine aura forcement aucune concordance avec le tableau de valeurFils
							racineArbre = liste.get(i).get(j).toString();
						}
					}
				}
			}
		}
		racine = Integer.parseInt(racineArbre) ; 
		System.out.println("La racine de l'arbre est : " + racineArbre);
		System.out.println(" ");
	}
	
	private static void trouverFeuilles(ArrayList<ArrayList<Integer>> liste) {
		//trouver les feuilles de l'arbre 
		//Information : Les feuilles de l'arbre sont forcement que Fils 
		tabValeurPere = new int[liste.size()];
		listFeuilleArbre = new ArrayList<Integer>();
		//On parcourt notre liste 
		for (int i = 0; i < liste.size(); i++) {
			//On commence à j=0 car on veut récupérer la valeurPere de notre liste [valeurPere, valeurFils]
			for (int j = 0; j < liste.get(i).size()-1; j++) {
				//Enregistre la valeurFils dans notre tableau
				tabValeurPere[i] = liste.get(i).get(j);
			}
		}
		//Parcour notre liste
		for (int i = 0; i < liste.size(); i++) {
			//Parcour nos ValeurFils
			for (int j = 1; j < liste.get(i).size(); j++) {
				int dontMatch = 0;
				for (int k = 0; k < tabValeurPere.length; k++) {
					if(tabValeurPere[k] != liste.get(i).get(j)) {
						dontMatch++;
						if(dontMatch == tabValeurPere.length) {
							listFeuilleArbre.add(liste.get(i).get(j));
						}
					}
				}
			}
		}
		System.out.println("Le / Les feuille(s) de l'arbre est / sont : ");
		for (int i = 0; i < listFeuilleArbre.size(); i++) {
			System.out.print(listFeuilleArbre.get(i) + " ");
		}
		System.out.println(" ");
		//getHauteurArbre
		getHauteurArbre();
	}
	
	private static void getHauteurArbre() {
		// But : Pour chaque feuille, on parcour les valeur fils -> père -> fils -> père ... 
		//       jusqu'a trouver la racine en incrementant à chaque fois qu'on trouve le père 
		//		 la valeur la plus haute sera donc la hauteur de notre arbre. 
		
		//Arguments 
		int valeurPere = 0;
		int valeurFils = 0; 
		//int hauteurMax = 0; 
		int hauteur = 0;
		
		//Parcour le tableau de feuille 
		for (int i = 0; i < listFeuilleArbre.size(); i++) {
			//Cherche la feuille dans le tableau de fils
			hauteur = 0;
			for (int j = 0; j < tabValeurFils.length; j++) {
				//Si la valeur de la feuille = valeur Fils 
				if(tabValeurFils[j] == listFeuilleArbre.get(i)) {
					//Recuperd l'index dans le tableau de fils
					//Recuperd la valeur du père dans le tableau père grâce à l'index
					valeurPere = tabValeurPere[j]; 
					//hauteur++
					hauteur++;
					
				}
				//Cherche la valeur du père dans le tableau de fils (le père devient le fils)
				// etc ... jusqu'a ce que le père = racine 
				valeurFils = valeurPere;
				for (int k = 0; k < tabValeurFils.length; k++) {
					if (valeurFils == tabValeurFils[k]) {
						valeurPere = tabValeurPere[k]; 
						hauteur++;
					}
				}
				//Verifie hauteurMax 
				if(hauteur > hauteurArbre) {
					hauteurArbre = hauteur;
				}
			}
		}
		//Affiche hauteur du tableau
		System.out.println("L'arbre à une hauteur de " + hauteurArbre + " rang.");
		
	}
	
	private static void trouverRangValeur() {
		//Trouve le rang d'une valeur dans l'arbre
		System.out.println(" ");
		System.out.println("Information : Entrez une valeur pour connaître son rang."); 
		if(sc.hasNextInt()) {
			//test : 
			int valeur = Integer.parseInt(sc.next());
			//Commentaire test 
			System.out.println(valeur + " est une valeur correcte !");
			int valeurPere = 0;
			int valeurFils = 0; 
			int rang = 0; 
			boolean isTrue = true;
			//Vérifie que la valeur saisie est dans notre arbre
			if(listeNoeuds.contains(valeur)) {
				//La valeur saisie est bien présente dans notre liste de noeuds. 
				System.out.println(valeur + " est présente dans votre arbre.");
				//Vérifie si la valeur a tester est la racine 
				if(valeur == racine) {
					System.out.println(valeur + " est la racine de l'arbre, elle a pour rang 0");
				}else {
					//valeur est forcement un fils (dans tout les cas) 
					valeurFils = valeur;
					while(isTrue) {
						for (int k = 0; k < tabValeurFils.length; k++) {
							//Recherche le fils
							if(tabValeurFils[k] == valeurFils) {
								valeurPere = tabValeurPere[k];
								//Le père devient le fils
								valeurFils = valeurPere;
								rang++;
								//On arrête la boucle quand on trouve la racine. 
								if(valeurPere == racine) {
									System.out.println(valeur +" est une valeur de rang " + rang);
									isTrue = false;
								}
							}
						}
					}
				}
			}else {
				System.out.println(valeur + " n'est pas présente dans votre arbre.");
			}
		}else {
			//Gestion de l'erreur de saisie utilisateur. Relance la méthode. 
			System.err.println("ERREUR : " + sc.next() + " n'est pas correcte.");
			trouverRangValeur();
		}

	}
}
