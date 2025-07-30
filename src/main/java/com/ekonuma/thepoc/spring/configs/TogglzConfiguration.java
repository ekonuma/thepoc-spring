package com.ekonuma.thepoc.spring.configs;

import com.ekonuma.thepoc.spring.models.enums.FeatureFlag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.UserProvider;

@Configuration
public class TogglzConfiguration implements TogglzConfig {

    private final HttpServletRequest request;

    public TogglzConfiguration(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return FeatureFlag.class;
    }

    @Override
    public StateRepository getStateRepository() {
        return null;
    }

    @Override
    public UserProvider getUserProvider() {
        return null;
    }
}

