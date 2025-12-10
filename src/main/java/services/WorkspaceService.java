package services;

import api.model.workspace.WorkspacesResponse;
import com.crowdar.api.rest.APIManager;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;

public class WorkspaceService extends BaseService{

    public static Response get(String jsonName) {
        return get(jsonName, WorkspacesResponse[].class,setParams());
    }


    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key",X_API_KEY.get());
        return params;
    }

    public static void defineWorkspaceId(String p_nameWorkspace){
        WorkspacesResponse[] workspacesResponses = (WorkspacesResponse[]) APIManager.getLastResponse().getResponse();
        for (WorkspacesResponse workspace : workspacesResponses){
            if (p_nameWorkspace.equalsIgnoreCase(workspace.getName())){
                WORKSPACE_ID.set(workspace.getId());
                return;
            }
        }
        Assert.fail("No se encontró el id del workspace: " + p_nameWorkspace);
    }
}
