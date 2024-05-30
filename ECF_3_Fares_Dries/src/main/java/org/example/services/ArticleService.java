package org.example.services;

import org.example.entity.Article;
import org.example.entity.Categorie;
import org.example.entity.Vente;
import org.example.interfaces.Repository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.List;

public class ArticleService extends BaseService implements Repository<Article> {

    public ArticleService(){
        super();
    }
    @Override
    public boolean create(Article a) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(a);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Article a) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(a);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Article a) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(a);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Article findById(int id) {
        session = sessionFactory.openSession();
        Article article = session.get(Article.class,id);
        session.close();
        return article;
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = null;
        session = sessionFactory.openSession();
        Query<Article> produitQuery = session.createQuery("from Article ");
        articleList = produitQuery.list();
        session.close();
        return articleList;
    }
    public void close(){
        sessionFactory.close();
    }



    public int getNumberOfTimeSold(int id){
        session = sessionFactory.openSession();
        Query<Vente> getProductAbove = session.createQuery("select c.vente from Article as c where c.id = :id ");
        getProductAbove.setParameter("id",id);

        List<Vente> produitsNotedAboveFour = getProductAbove.list();
        session.close();
        return produitsNotedAboveFour.size();
    }

    public List<Article> getStock(){
        session = sessionFactory.openSession();
        Criteria cr = session.createCriteria(Article.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("stock"), "stock"))
                .setResultTransformer(Transformers.aliasToBean(Article.class));

        List<Article> list = cr.list();
        return list;
    }

    public int getNumberOfTimeSoldByCategorie(Categorie categorie){
        session = sessionFactory.openSession();
        Query<Vente> getTimeSoldByCategorie = session.createQuery("select c.vente from Article as c where c.categorie = :categorie ");

        getTimeSoldByCategorie.setParameter("categorie", categorie);
        List<Vente> productsSoldByCategorie = getTimeSoldByCategorie.list();
        session.close();
        return productsSoldByCategorie.size();
    }

    public int getNumberofSales(){
        session = sessionFactory.openSession();
        Query<Vente> getTimeSoldByCategorie = session.createQuery("select vente from Article ");


        List<Vente> productsSoldByCategorie = getTimeSoldByCategorie.list();
        session.close();
        return productsSoldByCategorie.size();

    }


}
