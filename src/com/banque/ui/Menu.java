package com.banque.ui;

import com.banque.metier.Compte;
import com.banque.metier.CompteCourant;
import com.banque.metier.CompteEpargne;
import com.banque.metier.Operation;
import com.banque.metier.Versement;
import com.banque.metier.Retrait;
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
            System.out.println("Entrez votre choix:");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    creerCompte();
                    break;
                case 2:
                    effectuerVersement();   
                    break;
                case 3:
                    effectuerRetrait();
                    break;
                case 4:
                    effectuerVirement();
                    break;
                case 5:
                    consulterSolde();
                    break;
                case 6:
                    consulterOperations();
                    break;
                case 7:
                    listerTousLesComptes();
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
        System.out.println("7. Lister tous les comptes");
        System.out.println("0. Quitter");
        System.out.println("=======================================");
    }

    private void creerCompte(){

        System.out.println("--- Création d'un nouveau compte ---");
        System.out.println("Choisissez le type de compte :");
        System.out.println("1. Compte Courant");
        System.out.println("2. Compte Épargne");

        int typeCompte = -1;
        while(typeCompte != 1 && typeCompte != 2 ){
            System.out.print("Votre choix : ");
            if(scanner.hasNextInt()){
                typeCompte = scanner.nextInt();
                if (typeCompte != 1 && typeCompte != 2) {
                    System.out.println("Erreur : Veuillez choisir 1 ou 2.");
                }
            }else{
                System.out.println("Erreur : Entree invalide. Veuillez choisir 1 ou 2.");
                scanner.next();
            }
        }
        scanner.nextLine();

        double soldeInitial=-1;
        while (soldeInitial < 0) {
            System.out.print("Entrez le solde initial : ");
            if(scanner.hasNextInt()){
                soldeInitial = scanner.nextDouble();
                if (soldeInitial < 0) {
                    System.out.println("Erreur : Le solde initial ne peut pas etre negatif.");
                }
            }else{
                System.out.println("Erreur : Veuillez entrer une valeur numerique valide.");
                scanner.next();
            }
        }
        scanner.nextLine();

        Random rand = new Random();
        String code;
        do {
            int numero = 10000 + rand.nextInt(90000);   
            code = "CPT-"+numero;
        }while (comptes.containsKey(code));

        if(typeCompte==1){
            double decouvert = -1;
            while (decouvert < 0) {
                System.out.print("Entrez le decouvert: ");
                if (scanner.hasNextDouble()) {
                    decouvert = scanner.nextDouble();
                    if (decouvert < 0) {
                        System.out.println("Erreur : Le decouvert ne peut pas etre negatif.");
                    }
                } else {
                    System.out.println("Erreur : Veuillez entrer une valeur numerique valide.");
                    scanner.next();
                }
            }
            scanner.nextLine();

            CompteCourant cc = new CompteCourant(code, soldeInitial, decouvert);
            comptes.put(code, cc);
            System.out.println("Compte Courant cree avec succes ! Votre code de compte est : " + code);

        }else if(typeCompte==2){
            double taux = -1;
            while (taux <= 0) {
                System.out.print("Entrez le taux d'interet : ");
                if (scanner.hasNextDouble()) {
                    taux = scanner.nextDouble();
                    if (taux <= 0) {
                        System.out.println("Erreur : Le taux d'interet doit etre strictement positif.");
                    }
                } else {
                    System.out.println("Erreur : Veuillez entrer une valeur numerique valide.");
                    scanner.next();
                }
            }
            scanner.nextLine();

            CompteEpargne cp = new CompteEpargne(code, soldeInitial, taux);
            comptes.put(code, cp);
            System.out.println("Compte Epargne cree avec succes ! Votre code de compte est : " + code);

        }else {
            System.out.println("Type de compte invalide.");
        }

    }

    private void effectuerVersement(){

        System.out.println("--- Effectuer un versement ---");
        System.out.print("Entrez le code du compte destinataire (ex: CPT-12345) : ");
        String code = scanner.nextLine();
        Compte compte=comptes.get(code);

        if(compte != null){
            System.out.print("Entrez le montant à verser : ");
            double montant=scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Entrez la source du versement ");
            String source=scanner.nextLine();
            compte.verser(montant, source);

        }else{
            System.out.println("Erreur : Aucun compte trouvé avec le code " + code);
        }
    }

    private void effectuerRetrait(){
        System.out.println("--- Effectuer un retrait ---");
        System.out.print("Entrez le code de votre compte (ex: CPT-12345) : ");
        String code = scanner.nextLine();

        Compte compte=comptes.get(code);
        
        if (compte != null){
            System.out.print("Entrez le montant à retirer : ");
            double montant=scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Entrez la destination du retrait: ");
            String destination = scanner.nextLine();

            compte.retirer(montant, destination);
        }else {
            System.out.println("Erreur : Aucun compte trouvé avec le code " + code);
        }

    }

    private void effectuerVirement(){
        System.out.println("--- Effectuer un virement ---");

        System.out.print("Entrez le code du compte source (celui qui envoie) : ");
        String codeSource=scanner.nextLine();
        Compte compteSource= comptes.get(codeSource);

        System.out.print("Entrez le code du compte destinataire (celui qui reçoit) : ");
        String codeDestinataire=scanner.nextLine();
        Compte compteDestinataire= comptes.get(codeDestinataire);

        if (compteSource==null){
            System.out.println("Erreur : Compte source introuvable.");
            return;
        }

        if (compteDestinataire==null){
            System.out.println("Erreur : Compte  destinataire introuvable.");
            return;
        }

        System.out.print("Entrez le montant à virer : ");
        double montant= scanner.nextDouble();
        scanner.nextLine();

        double soldeSourceApres = compteSource.getSolde() - montant;
        boolean retraitPossible = false;

        if (compteSource instanceof CompteCourant) {
            if (soldeSourceApres >= -((CompteCourant) compteSource).getDecouvert()) {
                retraitPossible = true;
            }
        } else if (compteSource instanceof CompteEpargne) {
            if (soldeSourceApres >= 0) {
                retraitPossible = true;
            }
        }

        if (retraitPossible) {
            compteSource.retirer(montant, "Virement vers " + codeDestinataire);
            compteDestinataire.verser(montant, "Virement depuis " + codeSource);
            System.out.println("Virement effectué avec succès !");
        } else {
            System.out.println("Virement impossible : Solde insuffisant sur le compte source.");
        }

    }

    private void consulterSolde() {
        System.out.println("--- Consulter le solde et les détails d'un compte ---");
        System.out.print("Entrez le code du compte à consulter (ex: CPT-12345) : ");
        String code = scanner.nextLine();
    
        Compte compte = comptes.get(code);
    
        if (compte != null) {
            compte.afficherDetails();
        } else {
            System.out.println("Erreur : Aucun compte trouvé avec le code " + code);
        }
    }

    private void consulterOperations() {
        System.out.println("--- Consulter l'historique des opérations ---");
        System.out.print("Entrez le code du compte (ex: CPT-12345) : ");
        String code = scanner.nextLine();
    
        Compte compte = comptes.get(code);
    
        if (compte != null) {
            System.out.println("--- Historique des opérations pour le compte " + code + " ---");
            for (Operation op : compte.getListeOperations()) {
                String typeOperation = "";
                String details = "";
    
                if (op instanceof Versement) {
                    typeOperation = "[+] Versement";
                    Versement v = (Versement) op;
                    details = " | Source: " + v.getSource();
                } else if (op instanceof Retrait) {
                    typeOperation = "[-] Retrait";
                    Retrait r = (Retrait) op;
                    details = " | Destination: " + r.getDestination();
                }
    
                System.out.println(
                    op.getDate() + " | " + 
                    typeOperation + 
                    " | Montant: " + String.format("%.2f", op.getMontant()) + " EUR" +
                    details
                );
            }
            System.out.println("--------------------------------------------------");
            System.out.println("Solde actuel : " + String.format("%.2f", compte.getSolde()) + " EUR");
    
        } else {
            System.out.println("Erreur : Aucun compte trouvé avec le code " + code);
        }
    }

    private void listerTousLesComptes() {
        System.out.println("--- Liste de tous les comptes enregistrés ---");
    
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte n'a été créé pour le moment.");
        } else {
            for (Compte compte : comptes.values()) {
                compte.afficherDetails();
                System.out.println();
            }
        }
    }

    
}
