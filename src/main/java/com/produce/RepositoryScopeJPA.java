package com.produce;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class RepositoryScopeJPA {

    @Inject
    private EntityManager context;

    public EntityManager getContext() {
        return context;
    }

    public void shutdown() throws IOException {
        if (Objects.nonNull(context) && context.isOpen()) {
            context.close();
        }
    }

    public void setTenant(String tenantId) {
        context.setProperty(PersistenceUnitProperties.MULTITENANT_PROPERTY_DEFAULT, tenantId);
    }
}
