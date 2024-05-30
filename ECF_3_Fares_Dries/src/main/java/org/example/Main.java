package org.example;

import org.example.controller.Ihm;
import org.example.entity.Article;
import org.example.entity.Categorie;
import org.example.entity.Status;
import org.example.entity.Vente;
import org.example.services.ArticleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

//        ArticleService as = new ArticleService();
//
//
//
//
//
//        Vente v = Vente.builder().dateVente(new Date()).articles(new ArrayList<>()).status(Status.ONGOING).build();
//
//        Article a = Article.builder().stock(10).prix(1).taille("XL").description("Totot").categorie(Categorie.ENFANT).build();
//        as.create(a);
//
//
//
//        System.out.println(v);
//
//
//        StandardServiceRegistry registre = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(v);
//        session.getTransaction().commit();
//        session.close();
//
//        session = sessionFactory.openSession();
//        Vente vente = session.get(Vente.class,1);
//        vente.add(as.findById(2));
//        session.close();
//
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.update(vente);
//        session.getTransaction().commit();
//        session.close();


        Ihm ihm = new Ihm();
        ihm.start();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}