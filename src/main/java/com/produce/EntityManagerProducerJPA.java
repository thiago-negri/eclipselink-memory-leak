package com.produce;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;

@ApplicationScoped
public class EntityManagerProducerJPA {

    @PersistenceUnit
    private EntityManagerFactory contextFactory;

    @Produces
    @Default
    @RequestScoped
    public EntityManager createDefaultContext() {
        EntityManager entityManager = contextFactory.createEntityManager(SynchronizationType.SYNCHRONIZED);
        return entityManager;
    }
}
