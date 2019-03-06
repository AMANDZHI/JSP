package com.company.api;

import com.company.model.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface AuthAction {
    String getName();
    String getDescription();
    User execute() throws IOException;
    void setServiceLocator(ServiceLocator serviceLocator);
}
