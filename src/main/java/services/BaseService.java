package services;

import com.crowdar.api.rest.MethodsService;

public class BaseService extends MethodsService {
    public static ThreadLocal<String> X_API_KEY = new ThreadLocal<>();
    public static ThreadLocal<String> WORKSPACE_ID = new ThreadLocal<>();

    public static ThreadLocal<String> NAME_CLIENT = new ThreadLocal<>();
    public static ThreadLocal<String> NAME_PROJECT = new ThreadLocal<>();
    public static ThreadLocal<String> PROJECT_ID = new ThreadLocal<>();
    //public static ThreadLocal<String> NAME_PROJECT_REPLACE = new ThreadLocal<>();

    public static ThreadLocal<String> CLIENT_ID = new ThreadLocal<>();

}
