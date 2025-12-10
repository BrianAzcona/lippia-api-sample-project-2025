package ar.steps;

import ar.validator.WorkspaceValidator;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import services.WorkspaceService;

public class WorkspaceSteps extends PageSteps {
    @And("I get the data from my Workspace")
    public void IGetTheDataFromMyWorkspace() {
        WorkspaceValidator.validate();
    }

    @And("^I get the workspaceId with name '(.*)'$")
    public void IGetTheWorkspaceIdWithName(String p_nameWorkspace) {
        WorkspaceService.defineWorkspaceId(p_nameWorkspace);
    }
}
