package org.example.services;

import org.example.entity.Article;
import org.example.entity.Vente;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class VenteServices extends BaseService implements Repository<Vente> {
    @Override
    public boolean create(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(Vente o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Vente findById(int id) {
        session = sessionFactory.openSession();
        Vente vente = session.get(Vente.class,id);
        session.close();
        return vente;
    }

    @Override
    public List<Vente> findAll() {
        List<Vente> ventes = null;
        session = sessionFactory.openSession();
        Query<Vente> venteQuery = session.createQuery("from Vente ");
        ventes = venteQuery.list();
        session.close();
        return ventes;
    }

    public List<Vente> getTheNumberOfSalesBetweenTwoDates(Date min, Date max) throws Exception {
        if(min.before(max)){
            session = sessionFactory.openSession();
            Query<Vente> produitQuery = session.createQuery("from Vente where dateVente >= :datemin and dateVente <= :datemax");
            produitQuery.setParameter("datemin",min);
            produitQuery.setParameter("datemax",max);
            List<Vente> produitList = produitQuery.list();
            session.close();
            return produitList;
        }
        throw  new Exception("erreur date");
    }
    public List<Vente> getTheNumberOfSalesByClient(int id){

            session = sessionFactory.openSession();
            Query<Vente> produitQuery = session.createQuery("from Vente where client.id = :id");
            produitQuery.setParameter("id",id);
            List<Vente> produitList = produitQuery.list();
            session.close();
            return produitList;

    }
}
