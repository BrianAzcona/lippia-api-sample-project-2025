package services;


import api.config.EntityConfiguration;
import com.google.api.client.repackaged.com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import api.model.project.ProjectResponse;
import api.model.project.ProjectsResponse;
import com.crowdar.api.rest.CommonSteps;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ProjectService extends BaseService {

    public static Response delete(String jsonName) {
        return delete(jsonName, ProjectsResponse.class, setParams());
    }

    public static Response put(String jsonName) {
        return put(jsonName, ProjectsResponse.class, setParams());
    }
    public static Response get(String jsonName) {
        return get(jsonName, ProjectResponse.class,setParams());
    }
    public static Response post(String jsonName) {
        return post(jsonName, ProjectsResponse.class, setParams());
    }



    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key", X_API_KEY.get());
        params.put("id-workspace",WORKSPACE_ID.get());

        //Esta parte utiliza el parametro si lo necesita
        if (NAME_PROJECT.get() != null && !NAME_PROJECT.get().isEmpty()) {
            params.put("name-project", NAME_PROJECT.get());
        }
        if (PROJECT_ID.get() != null && !PROJECT_ID.get().isEmpty()) {
            params.put("project-id", PROJECT_ID.get());
        }
        /*
        if (NAME_PROJECT_REPLACE.get() != null && !NAME_PROJECT_REPLACE.get().isEmpty()) {
            params.put("name-replace", NAME_PROJECT_REPLACE.get());
        }*/

        return params;
    }

}




