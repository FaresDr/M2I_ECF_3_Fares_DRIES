package org.example.controller;

import org.example.entity.Article;
import org.example.entity.Categorie;
import org.example.services.ArticleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;

public class IhmArticle {

    private Scanner sc ;
    private ArticleService as;

    public IhmArticle(Scanner scanner) {
        sc = scanner;
        as = new ArticleService();

    }

    public void start(){
        int entry;

        while (true){
            System.out.println("--- Article Options ---");
            System.out.println("1/ Create an article");
            System.out.println("2/ Delete an existing article");
            System.out.println("3/ Update an existing article");
            System.out.println("4/ Get All article");
            System.out.println("5/ Get Article By Id");
            entry = sc.nextInt();
            sc.nextLine();

            switch (entry){
                case 1:
                    createArticle();
                    break;
                case 2 :
                    deleteArticle();
                    break;
                case 3  :
                    updateArticle();
                    break;
                case 4 :
                    getAllArticle();
                    break;
                case 5 :
                    getArticleById();
                break;
                default:
                    return;
            }
        }
    }

    private void getArticleById() {
        System.out.println("--- Get A Choosen Article ---");
        System.out.println("Veuillez indiquez l'id de l'article que vous voulez obtenire");
        int id = sc.nextInt();
        sc.nextLine();
        while (as.findById(id) == null){
            System.out.println("Aucun article avec cet id veuillez réesayez");
            id = sc.nextInt();
            sc.nextLine();
        }
        Article article = as.findById(id);
        System.out.println(article.toString());

    }

    private void getAllArticle() {
        System.out.println("--- Get All Article ---");
        List<Article> articles = as.findAll();
        for (Article a : articles){
            System.out.println(a.toString());
        }
    }

    private void updateArticle() {
        System.out.println("--- Update An Article ---");
        System.out.println("Veuiilez indiquer l'id de l'article que vous voulez modifier");
        int id = sc.nextInt();
        sc.nextLine();
        if (as.findById(id) == null){
            System.out.println("Il n'y a aucun article avec l'id : " + id);
        }else {
            Article nouv = as.findById(id);
            System.out.println("Veuillez entrer la nouvelle description de votre article (Laisser vide si vous ne voulez pas le modifier)");
            String description = sc.nextLine();
            if(description.equals("")){
                System.out.println("Aucun changement");
            }else {
                nouv.setDescription(description);
            }
            System.out.println("Veuillez choisir la catégorie de l'article : 'HOMME,''FEMME,''ENFANT'");
            String  categorie = sc.nextLine();
            if(categorie.equals("")){
                System.out.println("Aucun changement");
            }else {
                nouv.setCategorie(Categorie.valueOf(categorie));
            }
            System.out.println("Veuillez entrer la taille de l'article");
            String taille = sc.nextLine();
            if(taille.equals("")){
                System.out.println("Aucun changement");
            }else {
                nouv.setTaille(taille);
            }
            System.out.println("Veuillez indiquer le prix de votre nouvel article (Entrer 0 si aucun changement)");
            double prix = sc.nextDouble();
            sc.nextLine();
            if(prix == 0){
                System.out.println("Aucun changement");
            }else {
                nouv.setPrix(prix);
            }
            System.out.println("Veuillez entrer le stock de l'article en magasin (Entrer 0 si aucun changement)");
            int stock = sc.nextInt();
            sc.nextLine();
            if (stock==0){
                System.out.println("Aucun changement");
            }else {
                nouv.setStock(stock);
            }

            as.update(nouv);
            System.out.println("Article modifier");

        }
    }

    private void deleteArticle() {

        System.out.println("--- Delete An Article ---");
        System.out.println("Veuiilez indiquer l'id de l'article que vous voulez supprimer");
        int id = sc.nextInt();
        sc.nextLine();
        if (as.findById(id) == null){
            System.out.println("Il n'y a aucun article avec l'id : " + id);
        }else {
            Article a = as.findById(id);
            a.setVente(null);
            as.update(a);
            System.out.println(a.getVente());
            as.delete(as.findById(id));
            System.out.println("Article supprimer");
        }
    }

    private void createArticle() {
        System.out.println("--- Create Your New Article ----");
        System.out.println("Veuillez entrer la description de votre article");
       String description = sc.nextLine();
        System.out.println("Veuillez choisir la catégorie de l'article : 'HOMME,''FEMME,''ENFANT'");
       String  categorie = sc.nextLine();
        System.out.println("Veuillez entrer la taille de l'article");
        String taille = sc.nextLine();
        System.out.println("Veuillez indiquer le prix de votre nouvel article");
        double prix = sc.nextDouble();
        System.out.println("Veuillez entrer le stock de l'article en magasin");
        int stock = sc.nextInt();
        sc.nextLine();
        Article a = Article.builder().stock(stock).prix(prix).taille(taille).description(description).categorie(Categorie.valueOf(categorie)).build();
        System.out.println(a.toString());
        as.create(a);

    }
}
