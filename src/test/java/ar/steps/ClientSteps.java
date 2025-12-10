package ar.steps;

import ar.validator.ClientValidator;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import services.BaseService;
import services.ClientService;

public class ClientSteps extends PageSteps {

    @And("I get clients in the Workspace")
    public void iGetClientsInTheWorkspace() {
        ClientValidator.validateClientsWorkspace();
    }

    @And("^I have the client name '(.*)'$")
    public void iHaveTheClientNameNameClient(String p_nameClient) {
        BaseService.NAME_CLIENT.set(p_nameClient);
    }

    @And("^The created client has the name '(.*)'$")
    public void theCreatedClientHasTheName(String p_nameClient) {
        ClientValidator.validateNameClient(p_nameClient);
    }

    @And("^I get the client ID with the name '(.*)'$")
    public void iGetTheClientIDWithTheName(String p_nameClient) {
        ClientService.getIDClient(p_nameClient);
    }

    @And("^The client '(.*)' was successfully removed$")
    public void theClientNameClientWasSuccessfullyRemoved(String p_nameClient) {
        ClientValidator.validateDeleteClientWorkspace(p_nameClient);
    }
}
