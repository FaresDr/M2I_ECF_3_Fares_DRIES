package org.example.controller;

import org.example.entity.Article;
import org.example.entity.Categorie;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.services.ArticleService;
import org.example.services.ClientServices;
import org.example.services.VenteServices;
import org.hibernate.Criteria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IhmAnalyse {

    private Scanner sc ;
    private ArticleService as;
    
    private ClientServices cs;
    
    private VenteServices vs;

    public IhmAnalyse(Scanner scanner) {
        sc = scanner;
        as = new ArticleService();
        cs = new ClientServices();
        vs = new VenteServices();

    }
    public void start() throws Exception {
        int entry;

        while (true){
            System.out.println("--- Analyse Options ---");
            System.out.println("1/ Get all the stocks");
            System.out.println("2/ Get the number of time an article was sold");
            System.out.println("3/ Get the number of items sold by categorie");
            System.out.println("4/ Get the number of sales between two dates");
            System.out.println("5/ Get the number of sales of a client ");
            entry = sc.nextInt();
            sc.nextLine();

            switch (entry){
                case 1:
                    getStock();
                    break;
                case 2 :
                    getNbTimeSold();
                    break;
                case 3 :
                    getSoldByCategorie();
                    break;
                case 4 :
                    getNbSellsBetweenDates();
                    break;
                case 5 :
                    getNbSellByClient();
                    break;
                default:
                    return;
            }
        }
    }

    private void getNbSellByClient(){
        System.out.println("--- Get The Number of sales via client ---");
        System.out.println("Veuillez indiquer l'id du client qui vous intéresse ");
        int nbc = sc.nextInt();
        sc.nextLine();
        List<Vente> ventesByClient = vs.getTheNumberOfSalesByClient(1);
        for (Vente v : ventesByClient){
            System.out.println(v.toString());
        }
        List<Vente> totalVentes = vs.findAll();
        int fullSize = totalVentes.size();
        int size = ventesByClient.size();
        System.out.println("Le client à l'id " + nbc + " a à son actif "  +  size + " ventes");
        if(size !=0) {
            System.out.println("Les ventes du client à l'id  " + nbc + " est à l'originee " + (size/fullSize * 100) + " % des ventes");
        }

    }

    private void getNbSellsBetweenDates() throws Exception {
        System.out.println("--- Get the number of sales between two dates ---");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez saisir une premiere date (dd/MM/yyyy) :");
            String s1 = scanner.nextLine();
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
            System.out.println("Veuillez saisir une premiere date (dd/MM/yyyy) :");
            String s2 = scanner.nextLine();
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(s2);
            List<Vente> ventes= vs.getTheNumberOfSalesBetweenTwoDates(date1,date2);
            for (Vente v : ventes){
                System.out.println(ventes.toString());
            }
            List<Vente> ventes1 = vs.findAll();
            double tb =  ventes1.size();
            double size = ventes.size();
            System.out.println(size);
            if(size !=0) {
                System.out.println("Les ventes entre "+ date1 + " et  " + date2 + " représentes " + (size/tb * 100) + " % des ventes");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void getSoldByCategorie() {
        System.out.println("--- Get the number of time an article was sold by categorie ---");
        System.out.println("Veuillez enter la catégorie qui vous interesse ");
        String choix = sc.nextLine();
        Categorie categorie = Categorie.valueOf(choix);
        double tb = as.getNumberOfTimeSoldByCategorie(categorie);
        System.out.println(tb);

        double venteTotale = as.getNumberofSales();
        System.out.println(venteTotale);
        if(tb!=0) {
            double result = (tb/venteTotale)*100;
            System.out.println("Les ventes des articles de votre categorie representent : " + result + "% des ventes");
        }
        System.out.println(tb);
    }

    private void getStock() {

    List<Article> articles = as.getStock();
    for (Article a : articles){
        System.out.println("id : " + a.getId() + " Stock : " + a.getStock());

        }
    }

    private void getNbTimeSold() {
        System.out.println("--- Get Number of time sold --- ");
        System.out.println("Veuillez indiquer l'id de l'article qui vous intéresse");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("L'article à l'id " + id + "  à été vendu " + as.getNumberOfTimeSold(id) + " fois ");
    }
}
