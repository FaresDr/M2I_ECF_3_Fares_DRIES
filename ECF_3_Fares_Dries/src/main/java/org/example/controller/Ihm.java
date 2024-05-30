package org.example.controller;

import java.util.Scanner;

public class Ihm {

    private Scanner sc ;

    private IhmArticle ihmArticle;

    private IhmVente ihmVente;

    private IhmClient ihmClient;

    private IhmAnalyse ihmAnalyse;
    public Ihm() {
        sc = new Scanner(System.in);
        ihmArticle = new IhmArticle(sc);
        ihmVente = new IhmVente(sc);
        ihmClient = new IhmClient(sc);
        ihmAnalyse = new IhmAnalyse(sc);
    }

    public void start() throws Exception {
        int entry;

        while (true){
            System.out.println("--- Clothing Store Application ---");
            System.out.println("1/ Article IHM");
            System.out.println("2/ Vente IHM");
            System.out.println("3/ Client IHM");
            System.out.println("4/ Analyse IHM");

            entry = sc.nextInt();
            sc.nextLine();

            switch (entry){
                case 1:
                    ihmArticle.start();
                    break;
                case 2 :
                    ihmVente.start();
                    break;
                case 3 :
                    ihmClient.start();
                    break;
                case 4:
                    ihmAnalyse.start();
                    break;
                default:
                    return;
            }
        }
    }
}
