package com.banque.metier;

public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String code, double soldeInitial, double decouvert){
        super(code, soldeInitial);
        this.decouvert=decouvert;
    }

    public void retirer(double montant, String destination){
        if (this.solde - montant >= -this.decouvert){
            this.solde -= montant;
            Retrait r = new Retrait(montant, destination);
            this.listeOperations.add(r);
        } else {
            System.out.println("Opération impossible : Solde insuffisant et découvert dépassé.");
        }
    }

    public double calculerInteret(){
        return 0;
    }
    
    public double getDecouvert(){
        return decouvert;
    }

    public void afficherDetails() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String RED = "\u001B[31m";
        String BOLD = "\u001B[1m";
        
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println(CYAN + "|" + BOLD + "           COMPTE COURANT - DETAILS            " + RESET + CYAN + "|" + RESET);
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println(CYAN + "|" + RESET);
        System.out.println(CYAN + "| " + BLUE + "[#] Code du compte      : " + YELLOW + this.getCode() + RESET);
        System.out.println(CYAN + "| " + GREEN + "[$] Solde actuel        : " + BOLD + String.format("%.2f", this.getSolde()) + " EUR" + RESET);
        System.out.println(CYAN + "| " + RED + "[!] Decouvert autorise  : " + YELLOW + String.format("%.2f", this.decouvert) + " EUR" + RESET);
        
        double soldeDisponible = this.getSolde() + this.decouvert;
        System.out.println(CYAN + "| " + GREEN + "[=] Solde disponible    : " + BOLD + String.format("%.2f", soldeDisponible) + " EUR" + RESET);
        System.out.println(CYAN + "|" + RESET);
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println();
    }
}
