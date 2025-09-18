package com.banque;

import com.banque.metier.Compte;
import com.banque.ui.Menu;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Compte> comptes = new HashMap<>();
        Menu menu= new Menu(comptes);
        menu.demarrer();
    }
}

