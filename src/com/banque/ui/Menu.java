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

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\u001B[1m";

    public Menu(HashMap<String, Compte> comptes){
        this.comptes=comptes;
        this.scanner=new Scanner(System.in);
    }

    public void demarrer(){
        afficherEcranBienvenue();
        
        int choix;
        do {
            afficherMenuPrincipal();
            System.out.print(CYAN + "💰 Entrez votre choix: " + RESET);
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
                    afficherEcranAuRevoir();
                    break;

                default:
                    System.out.println(RED + "[X] Choix invalide. Veuillez réessayer." + RESET);
                    break;
            }

        }while (choix != 0);
    }

    private void afficherEcranBienvenue() {
        clearScreen();
        System.out.println(BOLD + BLUE + 
            "+==============================================================+\n" +
            "|                                                              |\n" +
            "|        $$$$$$\\   $$$$$$\\  $$\\    $$\\  $$$$$$\\  $$\\           |\n" +
            "|        \\__$$ |  $$  __$$\\ $$ |   $$ |$$  __$$\\ $$ |          |\n" +
            "|           $$ |  $$ /  $$ |$$ |   $$ |$$ /  $$ |$$ |          |\n" +
            "|           $$ |  $$$$$$$$ |\\$$\\  $$  |$$$$$$$$ |$$ |          |\n" +
            "|     $$\\   $$ |  $$  __$$ | \\$$\\$$  / $$  __$$ |$$ |          |\n" +
            "|     \\$$$$$$  |  $$ |  $$ |  \\$$$  /  $$ |  $$ |$$ |          |\n" +
            "|      \\______/   \\__|  \\__|   \\___/   \\__|  \\__|\\__|          |\n" +
            "|                                                              |\n" +
            "|                    ** EVOLUTION BANQUE **                    |\n" +
            "|                                                              |\n" +
            "|              ** Votre partenaire financier de confiance **   |\n" +
            "|                                                              |\n" +
            "+==============================================================+" + RESET);
        
        System.out.println(YELLOW + "\n*** Bienvenue dans l'experience bancaire nouvelle generation! ***" + RESET);
        System.out.println(GREEN + "Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void afficherMenuPrincipal() {
        clearScreen();
        System.out.println(BOLD + PURPLE + 
            "+================================================================+\n" +
            "|                    ** JAVALUTION BANQUE **                     |\n" +
            "|                      *** MENU PRINCIPAL ***                    |\n" +
            "+================================================================+" + RESET);
        
        System.out.println(GREEN + "|  1. [+] Creer un compte                                        |");
        System.out.println("|  2. [$] Effectuer un versement                                 |");
        System.out.println("|  3. [-] Effectuer un retrait                                   |");
        System.out.println("|  4. [>] Effectuer un virement                                  |");
        System.out.println("|  5. [?] Consulter le solde d'un compte                         |");
        System.out.println("|  6. [=] Consulter la liste des operations d'un compte          |");
        System.out.println("|  7. [*] Lister tous les comptes                                |" + RESET);
        System.out.println(RED + "|  0. [X] Quitter                                                |" + RESET);
        System.out.println(PURPLE + 
            "+================================================================+" + RESET);
    }

    private void afficherEcranAuRevoir() {
        clearScreen();
        System.out.println(BOLD + CYAN + 
            "+==============================================================+\n" +
            "|                                                              |\n" +
            "|                    *** MERCI ET A BIENTOT! ***               |\n" +
            "|                                                              |\n" +
            "|        Merci d'avoir utilise Javalution Banque               |\n" +
            "|                                                              |\n" +
            "|                 ** Votre confiance nous honore **            |\n" +
            "|                                                              |\n" +
            "|                        Au revoir! ***                        |\n" +
            "|                                                              |\n" +
            "+==============================================================+" + RESET);
    }

    private void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    private void afficherSeparateur(String titre) {
        System.out.println(BOLD + YELLOW + 
            "\n+==============================================================+");
        System.out.println("|  " + titre + "  ");
        System.out.println("+==============================================================+" + RESET);
    }

    private void creerCompte(){
        afficherSeparateur("[+] CREATION D'UN NOUVEAU COMPTE");
        
        System.out.println(CYAN + "Choisissez le type de compte :" + RESET);
        System.out.println(GREEN + "1. [B] Compte Courant");
        System.out.println("2. [$] Compte Epargne" + RESET);

        int typeCompte = -1;
        while(typeCompte != 1 && typeCompte != 2 ){
            System.out.print(YELLOW + "[?] Votre choix : " + RESET);
            if(scanner.hasNextInt()){
                typeCompte = scanner.nextInt();
                if (typeCompte != 1 && typeCompte != 2) {
                    System.out.println(RED + "[X] Erreur : Veuillez choisir 1 ou 2." + RESET);
                }
            }else{
                System.out.println(RED + "[X] Erreur : Entree invalide. Veuillez choisir 1 ou 2." + RESET);
                scanner.next();
            }
        }
        scanner.nextLine();

        double soldeInitial=-1;
        while (soldeInitial < 0) {
            System.out.print(CYAN + "[$] Entrez le solde initial : " + RESET);
            if(scanner.hasNextInt()){
                soldeInitial = scanner.nextDouble();
                if (soldeInitial < 0) {
                    System.out.println(RED + "[X] Erreur : Le solde initial ne peut pas etre negatif." + RESET);
                }
            }else{
                System.out.println(RED + "[X] Erreur : Veuillez entrer une valeur numerique valide." + RESET);
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
                System.out.print(PURPLE + "[~] Entrez le decouvert: " + RESET);
                if (scanner.hasNextDouble()) {
                    decouvert = scanner.nextDouble();
                    if (decouvert < 0) {
                        System.out.println(RED + "[X] Erreur : Le decouvert ne peut pas etre negatif." + RESET);
                    }
                } else {
                    System.out.println(RED + "[X] Erreur : Veuillez entrer une valeur numerique valide." + RESET);
                    scanner.next();
                }
            }
            scanner.nextLine();

            CompteCourant cc = new CompteCourant(code, soldeInitial, decouvert);
            comptes.put(code, cc);
            System.out.println(GREEN + "[+] Compte Courant cree avec succes ! Votre code de compte est : " + BOLD + code + RESET);

        }else if(typeCompte==2){
            double taux = -1;
            while (taux <= 0) {
                System.out.print(BLUE + "[%] Entrez le taux d'interet : " + RESET);
                if (scanner.hasNextDouble()) {
                    taux = scanner.nextDouble();
                    if (taux <= 0) {
                        System.out.println(RED + "[X] Erreur : Le taux d'interet doit etre strictement positif." + RESET);
                    }
                } else {
                    System.out.println(RED + "[X] Erreur : Veuillez entrer une valeur numerique valide." + RESET);
                    scanner.next(); 
                }
            }
            scanner.nextLine();

            CompteEpargne cp = new CompteEpargne(code, soldeInitial, taux);
            comptes.put(code, cp);
            System.out.println(GREEN + "[+] Compte Epargne cree avec succes ! Votre code de compte est : " + BOLD + code + RESET);

        }else {
            System.out.println(RED + "[X] Type de compte invalide." + RESET);
        }

        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void effectuerVersement(){
        afficherSeparateur("[$] EFFECTUER UN VERSEMENT");
        
        System.out.print(CYAN + "[B] Entrez le code du compte destinataire : " + RESET);
        String code = scanner.nextLine();
        Compte compte=comptes.get(code);

        if(compte != null){

            double montant = -1;
            while(montant < 0){
                System.out.print(GREEN + "[$] Entrez le montant a verser : " + RESET);
                if(scanner.hasNextInt()){
                    montant=scanner.nextDouble();
                    if(montant<0){
                        System.out.println(RED + "[X] Erreur : Le montant du versement doit etre strictement positif." + RESET);
                    }
                }else{
                    System.out.println(RED + "[X] Erreur : Entree invalide. Veuillez entrer une valeur numerique valide." + RESET);
                    scanner.next();
                }
            }
            scanner.nextLine();

            System.out.print(PURPLE + "[=] Entrez la source du versement : " + RESET);
            String source=scanner.nextLine();
            compte.verser(montant, source);
            
            System.out.println(GREEN + "[+] Versement effectue avec succes!" + RESET);

        }else{
            System.out.println(RED + "[X] Erreur : Aucun compte trouve avec le code " + code + RESET);
        }
        
        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void effectuerRetrait(){
        afficherSeparateur("[-] EFFECTUER UN RETRAIT");
        
        System.out.print(CYAN + "[B] Entrez le code de votre compte (ex: CPT-12345) : " + RESET);
        String code = scanner.nextLine();

        Compte compte=comptes.get(code);
        
        if (compte != null){
            double montant = -1;
            while (montant <= 0) {
                System.out.print(RED + "[-] Entrez le montant a retirer: " + RESET);
                if (scanner.hasNextDouble()) {
                    montant = scanner.nextDouble();
                    if (montant <= 0) {
                        System.out.println(RED + "[X] Erreur : Le montant du retrait doit etre strictement positif." + RESET);
                    }
                } else {
                    System.out.println(RED + "[X] Erreur : Entree invalide. Veuillez entrer une valeur numerique valide." + RESET);
                    scanner.next(); 
                }
            }
            scanner.nextLine();

            System.out.print(PURPLE + "[=] Entrez la destination du retrait: " + RESET);
            String destination = scanner.nextLine();

            compte.retirer(montant, destination);
            System.out.println(GREEN + "[+] Retrait effectue avec succes!" + RESET);
        }else {
            System.out.println(RED + "[X] Erreur : Aucun compte trouve avec le code " + code + RESET);
        }

        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void effectuerVirement(){
        afficherSeparateur("[>] EFFECTUER UN VIREMENT");

        System.out.print(BLUE + "[>] Entrez le code du compte source (celui qui envoie) : " + RESET);
        String codeSource=scanner.nextLine();
        Compte compteSource= comptes.get(codeSource);

        System.out.print(GREEN + "[<] Entrez le code du compte destinataire (celui qui recoit) : " + RESET);
        String codeDestinataire=scanner.nextLine();
        Compte compteDestinataire= comptes.get(codeDestinataire);

        if (compteSource==null){
            System.out.println(RED + "[X] Erreur : Compte source introuvable." + RESET);
            return;
        }

        if (compteDestinataire==null){
            System.out.println(RED + "[X] Erreur : Compte destinataire introuvable." + RESET);
            return;
        }

        double montant = -1;
        while (montant <= 0) {
            System.out.print(YELLOW + "[$] Entrez le montant a virer: " + RESET);
            if (scanner.hasNextDouble()) {
                montant = scanner.nextDouble();
                if (montant <= 0) {
                    System.out.println(RED + "[X] Erreur : Le montant du virement doit etre strictement positif." + RESET);
                }
            } else {
                System.out.println(RED + "[X] Erreur : Entree invalide. Veuillez entrer une valeur numerique valide." + RESET);
                scanner.next(); 
            }
        }
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
            System.out.println(GREEN + "[+] Virement effectue avec succes !" + RESET);
        } else {
            System.out.println(RED + "[X] Virement impossible : Solde insuffisant sur le compte source." + RESET);
        }

        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void consulterSolde() {
        afficherSeparateur("[?] CONSULTER LE SOLDE ET LES DETAILS D'UN COMPTE");
        
        System.out.print(CYAN + "[B] Entrez le code du compte a consulter (ex: CPT-12345) : " + RESET);
        String code = scanner.nextLine();

        if (!code.matches("^CPT-\\d{5}$")) {
            System.out.println(RED + "[X] Erreur : Le format du code est invalide. Le format attendu est CPT-XXXXX." + RESET);
            return; 
        }
    
        Compte compte = comptes.get(code);
    
        if (compte != null) {
            System.out.println(GREEN + "[+] Details du compte trouve :" + RESET);
            compte.afficherDetails();
        } else {
            System.out.println(RED + "[X] Erreur : Aucun compte trouve avec le code " + code + RESET);
        }
        
        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void consulterOperations() {
        afficherSeparateur("[=] CONSULTER L'HISTORIQUE DES OPERATIONS");
        
        System.out.print(CYAN + "[?] Entrez le code du compte (ex: CPT-12345) : " + RESET);
        String code = scanner.nextLine();
    
        if (!code.matches("^CPT-\\d{5}$")) {
            System.out.println(RED + "[X] Erreur : Le format du code est invalide. Le format attendu est CPT-XXXXX." + RESET);
            return;
        }

        Compte compte = comptes.get(code);
    
        if (compte != null) {
            System.out.println(BOLD + BLUE + "[=] Historique des operations pour le compte " + code + RESET);
            System.out.println(PURPLE + "===============================================================" + RESET);
            
            for (Operation op : compte.getListeOperations()) {
                String typeOperation = "";
                String details = "";
    
                if (op instanceof Versement) {
                    typeOperation = GREEN + "[+] [$] Versement" + RESET;
                    Versement v = (Versement) op;
                    details = CYAN + " | Source: " + v.getSource() + RESET;
                } else if (op instanceof Retrait) {
                    typeOperation = RED + "[-] [$] Retrait" + RESET;
                    Retrait r = (Retrait) op;
                    details = PURPLE + " | Destination: " + r.getDestination() + RESET;
                }
    
                System.out.println(
                    YELLOW + op.getDate() + RESET + " | " + 
                    typeOperation + 
                    " | " + BOLD + "Montant: " + String.format("%.2f", op.getMontant()) + " EUR" + RESET +
                    details
                );
            }
            System.out.println(PURPLE + "===============================================================" + RESET);
            System.out.println(BOLD + GREEN + "[$] Solde actuel : " + String.format("%.2f", compte.getSolde()) + " EUR" + RESET);
    
        } else {
            System.out.println(RED + "[X] Erreur : Aucun compte trouve avec le code " + code + RESET);
        }
        
        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }

    private void listerTousLesComptes() {
        afficherSeparateur("[*] LISTE DE TOUS LES COMPTES ENREGISTRES");
    
        if (comptes.isEmpty()) {
            System.out.println(YELLOW + "[!] Aucun compte n'a ete cree pour le moment." + RESET);
        } else {
            System.out.println(GREEN + "[+] Comptes trouves :" + RESET);
            System.out.println(BLUE + "===============================================================" + RESET);
            
            for (Compte compte : comptes.values()) {
                compte.afficherDetails();
                System.out.println(CYAN + "---------------------------------------------------------------" + RESET);
            }
        }
        
        System.out.println(YELLOW + "\n[*] Appuyez sur Entree pour continuer..." + RESET);
        scanner.nextLine();
    }
}
