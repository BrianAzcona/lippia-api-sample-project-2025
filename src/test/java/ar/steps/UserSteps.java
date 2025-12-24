package ar.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import services.UserService;

public class UserSteps extends PageSteps {

    @And("^I get the userId with name (.*)$")
    public void iGetTheUserIdWithName(String p_nameUser) {
        UserService.defineUserId(p_nameUser);
    }
}
