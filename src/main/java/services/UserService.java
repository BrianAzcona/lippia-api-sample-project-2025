package services;

import api.model.user.UserResponse;
import com.crowdar.api.rest.APIManager;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService{
    public static Response get(String jsonName) {
        return get(jsonName, UserResponse[].class,setParams());
    }

    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key",X_API_KEY.get());
        params.put("id-workspace",WORKSPACE_ID.get());
        return params;
    }

    public static void defineUserId(String p_nameUser){
        UserResponse[] userResponses = (UserResponse[]) APIManager.getLastResponse().getResponse();
        for (UserResponse user : userResponses){
            if (p_nameUser.equalsIgnoreCase(user.getName())){
                USER_ID.set(user.getId());
                return;
            }
        }
        Assert.fail("No se encontró el id del workspace: " + p_nameUser);
    }
}
