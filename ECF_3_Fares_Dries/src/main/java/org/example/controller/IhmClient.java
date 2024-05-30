package org.example.controller;

import org.example.entity.Article;
import org.example.entity.Categorie;
import org.example.entity.Client;
import org.example.entity.Vente;
import org.example.services.ArticleService;
import org.example.services.ClientServices;

import java.util.List;
import java.util.Scanner;

public class IhmClient {

    private Scanner sc ;
    private ClientServices cs;

    public IhmClient(Scanner scanner) {
        sc = scanner;
        cs = new ClientServices();

    }

    public void start(){
        int entry;

        while (true){
            System.out.println("--- Client Options ---");
            System.out.println("1/ Create a client");
            System.out.println("2/ Delete an existing client");
            System.out.println("3/ Update an existing client");
            System.out.println("4/ Get All client");
            System.out.println("5/ Get client By Id");
            entry = sc.nextInt();
            sc.nextLine();

            switch (entry){
                case 1:
                    createClient();
                    break;
                case 2 :
                    deleteClient();
                    break;
                case 3  :
                    updateClient();
                    break;
                case 4 :
                    getAllClient();
                    break;
                case 5 :
                    getClientById();
                    break;
                default:
                    return;
            }
        }
    }

    private void getClientById() {
        System.out.println("--- Get A Choosen Client ---");
        System.out.println("Veuillez indiquez l'id du client que vous voulez obtenir");
        int id = sc.nextInt();
        sc.nextLine();
        while (cs.findById(id) == null){
            System.out.println("Aucun client avec cet id veuillez réesayez");
            id = sc.nextInt();
            sc.nextLine();
        }
        Client client = cs.findById(id);

        System.out.println(client.toString());
        System.out.println(client.getVentes());
    }

    private void getAllClient() {
        System.out.println("--- Get All Client ---");
        List<Client> clients = cs.findAll();
        for (Client c : clients){
            System.out.println(c.toString());
            System.out.println(c.getVentes());
        }
    }

    private void updateClient() {
        System.out.println("--- Update An Article ---");
        System.out.println("Veuiilez indiquer l'id du client que vous voulez modifier");
        int id = sc.nextInt();
        sc.nextLine();
        if (cs.findById(id) == null) {
            System.out.println("Il n'y a aucun article avec l'id : " + id);
        } else {
            Client client = cs.findById(id);
            System.out.println("Veuillez entrer le nouveau nom (Laisser vide si vous ne voulez pas le modifier)");
            String name = sc.nextLine();
            if (name.equals("")) {
                System.out.println("Aucun changement");
            } else {
                client.setName(name);
            }

                System.out.println("Veuillez entrer le nouveau mail du client (Laisser vide si vous ne voulez pas le modifier");
                String mail = sc.nextLine();
                if (mail.equals("")) {
                    System.out.println("Aucun changement");
                } else {
                    client.setMail(mail);
                }



            cs.update(client);
            System.out.println("Client modifier");

        }
    }

    private void deleteClient() {

        System.out.println("--- Delete A Client ---");
        System.out.println("Veuiilez indiquer l'id du client que vous voulez supprimer");
        int id = sc.nextInt();
        sc.nextLine();
        if (cs.findById(id) == null){
            System.out.println("Il n'y a aucune vente avec l'id : " + id);
        }else {
            cs.delete(cs.findById(id));
            System.out.println("Client supprimer");
        }
    }

    private void createClient() {
        System.out.println("--- Client Creation");
        System.out.println("Veuillez indiquer le nom du client");
        String name = sc.nextLine();
        System.out.println("Veuillez indiquer l'email du client");
        String mail = sc.nextLine();
        Client client = Client.builder().name(name).mail(mail).build();
        cs.create(client);
    }
}
