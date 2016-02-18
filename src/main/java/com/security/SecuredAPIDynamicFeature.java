package com.security;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class SecuredAPIDynamicFeature implements DynamicFeature {

	@Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(SecuredAPIFilter.class);
    }
}
