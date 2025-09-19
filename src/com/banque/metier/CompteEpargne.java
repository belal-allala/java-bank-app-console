package com.banque.metier;

public class CompteEpargne extends Compte {
    
    private double tauxInteret;

    public CompteEpargne(String code, double soldeInitial, double tauxInteret){
        super(code, soldeInitial);
        this.tauxInteret= tauxInteret;
    }

    public void retirer(double montant, String destination){

        if(this.solde>=montant){
            this.solde-=montant;
            Retrait r = new Retrait(montant, destination);
            this.listeOperations.add(r);
        }else{
            System.out.println("Opération impossible : Solde insuffisant.");
        }
    }

    public double calculerInteret(){
        return this.solde*this.tauxInteret;
    }

    public void afficherDetails(){
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String BOLD = "\u001B[1m";
        
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println(CYAN + "|" + BOLD + "           COMPTE EPARGNE - DETAILS            " + RESET + CYAN + "|" + RESET);
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println(CYAN + "|" + RESET);
        System.out.println(CYAN + "| " + BLUE + "[#] Code du compte    : " + YELLOW + this.getCode() + RESET);
        System.out.println(CYAN + "| " + GREEN + "[$] Solde actuel      : " + BOLD + String.format("%.2f", this.getSolde()) + " EUR" + RESET);
        System.out.println(CYAN + "| " + BLUE + "[%] Taux d'interet    : " + YELLOW + String.format("%.2f", this.tauxInteret * 100) + " %" + RESET);
        System.out.println(CYAN + "| " + GREEN + "[+] Interets calcules : " + BOLD + String.format("%.2f", this.calculerInteret()) + " EUR" + RESET);
        System.out.println(CYAN + "|" + RESET);
        System.out.println(CYAN + "+================================================+" + RESET);
        System.out.println();
    }

    public double getTauxInteret(){
        return tauxInteret;
    }
}
