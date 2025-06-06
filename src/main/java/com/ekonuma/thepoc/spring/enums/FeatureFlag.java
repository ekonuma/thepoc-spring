package com.ekonuma.thepoc.spring.enums;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum FeatureFlag implements Feature {

    @EnabledByDefault
    @Label("Endpoints SSE")
    SSE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
