package com.company.ui;

import com.company.api.Action;
import com.company.api.AuthAction;
import com.company.api.ServiceLocator;
import com.company.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Action> map;
    private Map<String, AuthAction> mapAuth;
    private Map<String, Action> mapAdmAction;
    private ServiceLocator serviceLocator;

    public Menu( Map<String, Action> map, Map<String, AuthAction> mapAuth, Map<String, Action> mapAdmAction, ServiceLocator serviceLocator) {
        this.map = map;
        this.mapAuth = mapAuth;
        this.mapAdmAction = mapAdmAction;
        this.serviceLocator = serviceLocator;
    }

    public void startMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите команду help");

        String answer = reader.readLine();

        if ("help".equals(answer)) {
            System.out.println("Выберите действие. Введите текст команды");

            for (Map.Entry<String, Action> e: map.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue().getDescription() );
            }

            for (Map.Entry<String, AuthAction> e: mapAuth.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue().getDescription() );
            }

            String answerAction = reader.readLine();

            if (mapAuth.get(answerAction) == null) {
                startMenu();
            }

            if (mapAuth.get(answerAction).equals(mapAuth.get("login"))) {
                User user = mapAuth.get(answerAction).execute();
                if (user != null) {
                    authMenu(user);
                } else {
                    startMenu();
                }

            } else if (mapAuth.get(answerAction).equals(mapAuth.get("registration"))) {
                User user = mapAuth.get(answerAction).execute();
                if (user != null) {
                    authMenu(user);
                } else {
                    startMenu();
                }
            }
        }
    }

    public void authMenu (User user) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите действие. Введите текст команды");
            if (user.isAdmin()) {
                for (Map.Entry<String, Action> e: mapAdmAction.entrySet()) {
                    System.out.println(e.getKey() + " : " + e.getValue().getDescription() );
                }

                String answerAction = reader.readLine();

                for (Map.Entry<String, Action> pair: mapAdmAction.entrySet()) {
                    if (pair.getKey().equals(answerAction)) {
                        pair.getValue().execute();
                    }
                }
            } else {
                for (Map.Entry<String, Action> e: map.entrySet()) {
                    System.out.println(e.getKey() + " : " + e.getValue().getDescription() );
                }

                String answerAction = reader.readLine();

                for (Map.Entry<String, Action> pair: map.entrySet()) {
                    if (pair.getKey().equals(answerAction)) {
                        pair.getValue().execute();
                    }
                }
            }

            if (serviceLocator.getSessionService().getSession().getUser() != null) {
                this.authMenu(user);
            } else {
                startMenu();
            }
    }
}