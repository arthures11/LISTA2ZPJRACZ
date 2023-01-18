package com.bryja;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import com.bryja.User;
import com.bryja.HibernateUtil;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {



    }
}