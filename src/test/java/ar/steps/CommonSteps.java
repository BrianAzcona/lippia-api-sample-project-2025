package ar.steps;

import api.config.EntityConfiguration;
import ar.validator.ClientValidator;
import ar.validator.WorkspaceValidator;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.*;
import com.google.api.client.repackaged.com.google.common.base.Splitter;
import cucumber.api.java.en.When;
import org.apache.commons.lang.StringUtils;
import services.BaseService;
import services.WorkspaceService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CommonSteps extends PageSteps {

    @Given("^An account created in Clockify and x-api-key '(.*)' generated$")
    public static void AnAccountCreatedInClockifyAndTokenGenerated(String token){
        BaseService.X_API_KEY.set(token);
    }

    @When("^I perform a '(.*)' to '(.*)' endpoint with the '(.*)' and '(.*)'$")
    public void doRequest(String methodName, String entity, String jsonName, String jsonReplacementValues) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Class entityService = EntityConfiguration.valueOf(entity).getEntityService();
        Map<String, String> parameters = getParameters(jsonReplacementValues);
        String jsonPath = "request/".concat(jsonName);
        if (parameters == null) {
            entityService.getMethod(methodName.toLowerCase(), String.class).invoke("", jsonPath);
        } else {
            entityService.getMethod(methodName.toLowerCase(), String.class, Map.class).invoke("", jsonPath, parameters);
        }
    }

    private Map<String, String> getParameters(String jsonReplacementValues) {
        Map<String, String> parameters = null;
        if (!StringUtils.isEmpty(jsonReplacementValues)) {
            parameters = Splitter.on(",").withKeyValueSeparator(":").split(jsonReplacementValues);
        }
        return parameters;
    }

    @And("Obtengo los datos de mi Workspace")
    public void obtengoLosDatosDeMiWorkspace() {
        WorkspaceValidator.validate();
    }

    @And("^tengo un nombre de cliente '(.*)'$")
    public void tengoUnNombreDeCliente() {

    }



    @And("^tengo un cliente con el nombre (.*)$")
    public void tengoUnClienteConElNombreName(String p_nameClient) {
        ClientValidator.validateNameInList(p_nameClient);
    }

    @And("^I get the workspaceId at the position '(.*)'$")
    public void obtengoElIdEnLaPosicion(int p_indice) {
        WorkspaceService.defineWorkspaceId(p_indice);
    }
}
