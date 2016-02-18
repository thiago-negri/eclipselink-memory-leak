package com.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import com.produce.RepositoryScopeJPA;

public class SecuredAPIFilter implements ContainerRequestFilter {

	public static final String HTTP_HEADER_TENANT_NAME = "Tenant";
	
    @Inject
    private RepositoryScopeJPA repositoryScope;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String tenantId = getTenantIdOf(requestContext);
        repositoryScope.setTenant(tenantId);
    }

    private String getTenantIdOf(final ContainerRequestContext requestContext) {
        String tenantId = requestContext.getHeaderString(HTTP_HEADER_TENANT_NAME);
        return tenantId;
    }
    
}
