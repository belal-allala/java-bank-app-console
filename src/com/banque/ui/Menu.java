package com.banque.ui;

import com.banque.metier.Compte;
import com.banque.metier.CompteCourant;
import com.banque.metier.CompteEpargne;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Menu {

    private HashMap<String, Compte> comptes;
    private Scanner scanner;

    public Menu(HashMap<String, Compte> comptes){
        this.comptes=comptes;
        this.scanner=new Scanner(System.in);
    }

    public void demarrer(){
        int choix;
        do {
            afficherMenuPrincipal();
            System.out.println("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    creerCompte();
                    break;
                case 2:
                    System.out.println("Effectuer un versement");   
                    break;
                case 3:
                    System.out.println("Effectuer un retrait");
                    break;
                case 4:
                    System.out.println("Effectuer un virement");
                    break;
                case 5:
                    System.out.println("Consulter le solde");
                    break;
                case 6:
                    System.out.println("Consulter les opérations");
                    break;
                case 0:
                System.out.println("Merci d'avoir utilisé Javalution Banque. Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }

        }while (choix != 0);
    }

    private void afficherMenuPrincipal() {
        System.out.println("========== JAVALUTION BANQUE ==========");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter le solde d'un compte");
        System.out.println("6. Consulter la liste des opérations d'un compte");
        System.out.println("0. Quitter");
        System.out.println("=======================================");
    }

    private void creerCompte(){

        System.out.println("--- Création d'un nouveau compte ---");
        System.out.println("Choisissez le type de compte :");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");
        System.out.print("Votre choix : ");

        int typeCompte = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Entrez le solde initial : ");
        double soldeInitial = scanner.nextDouble();
        scanner.nextLine();

        Random rand = new Random();
        String code;
        do {
            int numero = 10000 + rand.nextInt(90000);   
            code = "CPT-"+numero;
        }while (comptes.containsKey(code));

        if(typeCompte==1){
            System.out.print("Entrez le découvert autorisé : ");
            double decouvert = scanner.nextDouble();
            scanner.nextLine();

            CompteCourant cc = new CompteCourant(code, soldeInitial, decouvert);
            comptes.put(code, cc);
            System.out.println("Compte Courant créé avec succès ! Votre code de compte est : " + code);

        }else if(typeCompte==2){
            System.out.print("Entrez le taux d'intérêt (ex: 0.03 pour 3%) : ");
            double taux = scanner.nextDouble();
            scanner.nextLine();

            CompteEpargne cp = new CompteEpargne(code, soldeInitial, taux);
            comptes.put(code, cp);
            System.out.println("Compte Épargne créé avec succès ! Votre code de compte est : " + code);

        }else {
            System.out.println("Type de compte invalide.");
        }


    }
    
}
