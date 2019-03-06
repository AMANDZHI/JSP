//package com.company;
//
//import com.company.actions.*;
//import com.company.api.*;
//import com.company.model.Project;
//import com.company.model.Task;
//import com.company.repository.ProjectRepositoryImpl;
//import com.company.repository.SessionRepositoryImpl;
//import com.company.repository.TaskRepositoryImpl;
//import com.company.repository.UserRepositoryImpl;
//import com.company.service.*;
//import com.company.ui.Menu;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Initializer implements ServiceLocator {
//    private final Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
//    private final Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
//    private final UserRepository userRepository = UserRepositoryImpl.getUserRepository();
//    private final SessionRepository sessionRepository = new SessionRepositoryImpl();
//    private final Service<String, Project> projectService = new ProjectServiceImpl(projectRepository, taskRepository);
//    private final Service<String, Task> taskService = new TaskServiceImpl(taskRepository);
//    private final UserService userService = new UserServiceImpl(userRepository);
//    private final SessionService sessionService = new SessionServiceImpl(sessionRepository);
//    private final ServiceLocator serviceLocator = this;
//    private final AppSecurity appSecurity = new AppSecurity(serviceLocator);
//    private final Map<String, Action> map = new HashMap<>();
//    private final Map<String, AuthAction> mapAuth = new HashMap<>();
//    private final Map<String, Action> mapAdmAction = new HashMap<>();
//    private final Menu menu = new Menu(map, mapAuth, mapAdmAction, serviceLocator);
//    private final List<Action> listForAction = new ArrayList<>();
//    private final List<AuthAction> listForAuth = new ArrayList<>();
//    private final List<Action> listForAdmAction = new ArrayList<>();
//    private static Initializer instance = new Initializer();
//
//    private Initializer() {
//    }
//
//    public static Initializer getInstance() {
//        if (instance == null) {
//            instance = new Initializer();
//        }
//        return instance;
//    }
//
//    @Override
//    public Service<String, Project> getProjectService() {
//        return projectService;
//    }
//
//    @Override
//    public Service<String, Task> getTaskService() {
//        return taskService;
//    }
//
//    @Override
//    public UserService getUserService() {
//        return userService;
//    }
//
//    @Override
//    public SessionService getSessionService() {
//        return sessionService;
//    }
//
//    @Override
//    public AppSecurity getAppSecurity() {
//        return appSecurity;
//    }
//
//    {
//        init();
//    }
//
//    public void run() throws IOException {
//        menu.startMenu();
//    }
//
//    public void init() {
//        try {
//            listForAction.add(ProjectCreateAction.class.newInstance());
//            listForAction.add(ProjectFindAction.class.newInstance());
//            listForAction.add(ProjectListAction.class.newInstance());
//            listForAction.add(ProjectRemoveAction.class.newInstance());
//            listForAction.add(ProjectUpdateAction.class.newInstance());
//            listForAction.add(TaskCreateAction.class.newInstance());
//            listForAction.add(TaskFindAction.class.newInstance());
//            listForAction.add(TaskListAction.class.newInstance());
//            listForAction.add(TaskRemoveAction.class.newInstance());
//            listForAction.add(TaskUpdateAction.class.newInstance());
//
//            listForAuth.add(LoginAction.class.newInstance());
//            listForAuth.add(RegistrationAction.class.newInstance());
//
//            listForAdmAction.add(LogOutAction.class.newInstance());
//            listForAdmAction.add(UserCreateAction.class.newInstance());
//            listForAdmAction.add(UserFindAction.class.newInstance());
//            listForAdmAction.add(UserListAction.class.newInstance());
//            listForAdmAction.add(UserRemoveAction.class.newInstance());
//            listForAdmAction.add(UserUpdateAction.class.newInstance());
//            listForAdmAction.addAll(listForAction);
//
//
//            for (Action action: listForAdmAction) {
//                action.setServiceLocator(serviceLocator);
//            }
//
//            for (AuthAction action: listForAuth) {
//                action.setServiceLocator(serviceLocator);
//            }
//
//            for (Action action: listForAction) {
//                map.put(action.getName(), action);
//            }
//
//            for (AuthAction action: listForAuth) {
//                mapAuth.put(action.getName(), action);
//            }
//
//            for (Action action: listForAdmAction) {
//                mapAdmAction.put(action.getName(), action);
//            }
//
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//}