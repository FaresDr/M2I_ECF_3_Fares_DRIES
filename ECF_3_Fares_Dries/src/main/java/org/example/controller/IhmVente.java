package org.example.controller;

import org.example.entity.Article;
import org.example.entity.Status;
import org.example.entity.Vente;
import org.example.services.ArticleService;
import org.example.services.ClientServices;
import org.example.services.VenteServices;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class IhmVente {

    private Scanner sc ;
    private ArticleService as;
    
    private VenteServices vs;
    private ClientServices cs;

    public IhmVente(Scanner scanner) {
        sc = scanner;
        as = new ArticleService();
        vs = new VenteServices();
        cs = new ClientServices();

    }

    public void start(){
        int entry;

        while (true){
            System.out.println("--- Sell Options ---");
            System.out.println("1/ Create a new Sell (You can modify it later)");
            System.out.println("2/ Delete an existing Sell");
            System.out.println("3/ Get All Sells");
            System.out.println("4/ Get Sells By Id");
            System.out.println("5/ Add Article To Sell");
            System.out.println("6/ Finish a choosen Sell");
            System.out.println("7/ Void a choosen Sell");
            entry = sc.nextInt();
            sc.nextLine();

            switch (entry){
                case 1:
                    createSell();
                    break;
                case 2 :
                    deleteSell();
                    break;
                case 3 :
                    getAllSells();
                    break;
                case 4 :
                    getSellById();
                    break;
                case 5 :
                    addArticleToSell();
                    break;
                case 6 :
                    finishSell();
                    break;
                case 7 :
                    voidSell();
                    break;
                default:
                    return;
            }
        }
    }

    private void voidSell() {
        System.out.println("--- Void a sell ---");
        System.out.println("Veuillez indiquer l'id de la vente souhaité");
        int id =sc.nextInt();
        sc.nextLine();
        Vente vente = vs.findById(id);
        if(vente.getStatus() == Status.ONGOING) {
            vente.setStatus(Status.VOIDED);
            double prix = 0;
            for (Article a : vente.getArticles()) {
                prix += a.getPrix();
            }
            vente.setTotalValue(prix);
            vs.update(vente);
        }else{
            System.out.println("La Vente est déja terminé");
        }
    }

    private void finishSell() {
        System.out.println("--- Finish a sell ---");
        System.out.println("Veuillez indiquer l'id de la vente souhaité");
        int id =sc.nextInt();
        sc.nextLine();
        Vente vente = vs.findById(id);
        if(vente.getStatus() == Status.ONGOING) {
            vente.setStatus(Status.DONE);
            double prix = 0;
            for (Article a : vente.getArticles()) {
                prix += a.getPrix();
            }
            vente.setTotalValue(prix);
            vs.update(vente);
        }else{
            System.out.println("La Vente est déja terminé");
        }
    }

    private void addArticleToSell() {
        System.out.println("--- Add An Article To A Choosen Sell ---");
        System.out.println("Veuillez indiquer l'id de la vente souhaité");
        int id =sc.nextInt();
        sc.nextLine();
        Vente vente = vs.findById(id);

        int choix = 1;
        if(vente.getStatus() == Status.ONGOING) {
            while (choix != 0) {
                System.out.println("Vous allez ajouter les articles à une ventes");
                System.out.println("Veuillez entrer l'id de l'article à ajouter à la vente ");
                int idA = sc.nextInt();
                sc.nextLine();
                Article a = as.findById(idA);
                if (a.getStock()>0) {
                    vente.add(a);
                    a.setStock(a.getStock() - 1);
                    as.update(a);
                }else {
                    System.out.println("L'article a l'id " + id + "n'est plus en stock");
                }
                System.out.println("Enter 0 pour terminer ou entre une autre valeur pour continuer");
                choix = sc.nextInt();
            }
            vs.update(vente);
        }else {
            System.out.println("La Vente étant terminé il est impossible de la modifier");
        }
    }

    private void getSellById() {
        System.out.println("--- Get A Choosen Sell ---");
        System.out.println("Veuillez indiquez l'id de la vente que vous voulez obtenir");
        int id = sc.nextInt();
        sc.nextLine();
        while (vs.findById(id) == null){
            System.out.println("Aucune vente avec cet id veuillez réesayez");
            id = sc.nextInt();
            sc.nextLine();
        }
        Vente vente = vs.findById(id);
        if(vente.getArticles() == null){
            vente.setArticles(null);
        }
        System.out.println(vente.toString());
    }

    private void getAllSells() {
        System.out.println("--- Get All Sells ---");
        List<Vente> ventes = vs.findAll();
        for (Vente v : ventes){
            System.out.println(v.toString());
        }
    }

    private void updateSell() {
    }

    private void deleteSell() {
        System.out.println("--- Delete A Sell ---");
        System.out.println("Veuiilez indiquer l'id de la vente que vous voulez supprimer");
        int id = sc.nextInt();
        sc.nextLine();
        if (vs.findById(id) == null){
            System.out.println("Il n'y a aucune vente avec l'id : " + id);
        }else {
            vs.delete(vs.findById(id));
            System.out.println("Vente supprimer");
        }
    }

    private void createSell() {
        System.out.println("--- Sell Creation ---");
        System.out.println("Veuillez indiquer l'id du client pour la vente ");
        int id = sc.nextInt();
        sc.nextLine();
        Vente v = Vente.builder().dateVente(new Date()).client(cs.findById(id)).status(Status.ONGOING).build();

        System.out.println(v.toString());
        vs.create(v);
    }
}
