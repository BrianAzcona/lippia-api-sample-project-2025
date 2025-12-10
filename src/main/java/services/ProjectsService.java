package services;


import api.model.project.ProjectsResponse;
import api.model.workspace.WorkspacesResponse;
import com.crowdar.api.rest.APIManager;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public class ProjectsService extends BaseService {


    public static Response get(String jsonName) {
        return get(jsonName, ProjectsResponse[].class,setParams());
    }



    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key", X_API_KEY.get());
        params.put("id-workspace",WORKSPACE_ID.get());

        if (NAME_PROJECT.get() != null && !NAME_PROJECT.get().isEmpty()) {
            params.put("name-project", NAME_PROJECT.get());
        }
        if (PROJECT_ID.get() != null && !PROJECT_ID.get().isEmpty()) {
            params.put("project-id", PROJECT_ID.get());
        }
        if (NAME_PROJECT_REPLACE.get() != null && !NAME_PROJECT_REPLACE.get().isEmpty()) {
            params.put("name-replace", NAME_PROJECT_REPLACE.get());
        }

        return params;
    }

    public static void defineProjectId(String p_nameProject) {
        ProjectsResponse[] projectResponseList = (ProjectsResponse[]) APIManager.getLastResponse().getResponse();
        for (ProjectsResponse project : projectResponseList) {
            if (project.name != null && project.name.equalsIgnoreCase(p_nameProject)) {
                PROJECT_ID.set(project.getId());
                return;
            }
        }

        throw new RuntimeException("No se encontró un proyecto con el nombre: " + p_nameProject);
    }

}
