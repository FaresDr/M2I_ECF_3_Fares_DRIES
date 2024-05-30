package org.example.services;

import org.example.entity.Client;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class ClientServices extends BaseService implements Repository<Client> {
    @Override
    public boolean create(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Client o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Client findById(int id) {
        session = sessionFactory.openSession();
        Client client = session.get(Client.class,id);
        session.close();
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = null;
        session = sessionFactory.openSession();
        Query<Client> clientQuery = session.createQuery("from Client ");
        clients = clientQuery.list();
        session.close();
        return clients;
    }
}
