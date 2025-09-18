package com.banque.ui;

import com.banque.metier.Compte;
import java.util.HashMap;
import java.util.Scanner;

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
                    System.out.println("Créer un compte");
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
    
}
