package ar.validator;

import api.model.client.ClientResponse;
import com.crowdar.api.rest.APIManager;
import io.cucumber.java.bs.A;
import junit.framework.Assert;
import services.BaseService;

import java.util.ArrayList;
import java.util.Arrays;

public class ClientValidator {
    public static void validateNameClient(String p_nameClient){
        ClientResponse clientResponse = (ClientResponse) APIManager.getLastResponse().getResponse();


        Assert.assertEquals("El nombre del nuevo cliente no es el esperado", p_nameClient,clientResponse.getName());
    }

    public static void validateNameInList(String p_nombre){
        ClientResponse[] clientResponse = (ClientResponse[]) APIManager.getLastResponse().getResponse();
        ArrayList<ClientResponse> array = (ArrayList<ClientResponse>) Arrays.stream(clientResponse).toList();
        boolean existe = false;
        for (ClientResponse cr: array){
            if (cr.name.equalsIgnoreCase(p_nombre)){
                existe = true;
                break;
            }
        }
        Assert.assertTrue("no se encontró el nombre del cliente", existe);
    }

    public static void validateClientsWorkspace(){
        ClientResponse[] clientResponse = (ClientResponse[]) APIManager.getLastResponse().getResponse();
        Assert.assertTrue("La lista de workspaces está vacía",clientResponse.length > 0 );
        Assert.assertNotNull(clientResponse[0].getId(), "El campo ID del cliente es nulo");
    }

    public static void validateDeleteClientWorkspace(String p_nameClient){
        ClientResponse clientResponse = (ClientResponse) APIManager.getLastResponse().getResponse();

        Assert.assertEquals("El nombre del cliente eliminado no es el esperado",p_nameClient, clientResponse.getName() );
    }
}


