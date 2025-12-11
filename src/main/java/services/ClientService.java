package services;

import api.model.client.ClientResponse;
import com.crowdar.api.rest.APIManager;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;

public class ClientService extends BaseService {
    public static Response get(String jsonName){return get(jsonName, ClientResponse[].class, setParams());}

    public static Response post(String jsonName) {return post(jsonName,ClientResponse.class ,setParams());}

    public static Response delete(String jsonName) {return delete(jsonName,ClientResponse.class ,setParams());}

    public static Response put(String jsonName) {return put(jsonName,ClientResponse.class ,setParams());}

    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key",X_API_KEY.get());
        params.put("id-workspace",WORKSPACE_ID.get());

        if (NAME_CLIENT.get() != null && !NAME_CLIENT.get().isEmpty()) {
            params.put("name-client", NAME_CLIENT.get());
        }
        if (CLIENT_ID.get() != null && !CLIENT_ID.get().isEmpty()) {
            params.put("id-client", CLIENT_ID.get());
        }
        return params;
    }

    public static void getIDClient(String p_nameClient){
        ClientResponse[] clientResponse =
                (ClientResponse[]) APIManager.getLastResponse().getResponse();

        for (ClientResponse cliente : clientResponse) {
            if (p_nameClient.equalsIgnoreCase(cliente.name)) {
                BaseService.CLIENT_ID.set(cliente.getId());
                return;
            }
        }

        Assert.fail("No se encontró el cliente con nombre: " + p_nameClient);
    }



}
