package ar.steps;

import ar.validator.ProjectValidator;
import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import services.BaseService;
import services.ProjectService;

public class ProjectSteps extends PageSteps {

    @And("I have the name available '(.*)'")
    public void tengoDisponibleElNombre(String p_nameProject) {
        BaseService.NAME_PROJECT.set(p_nameProject);
    }

    @And("The project created has the name '(.*)'")
    public void elProjectoCreadoTieneElNombreProjectName(String p_nameProject) {
        ProjectValidator.validateNameProjectCreated(p_nameProject);
    }

    @And("^I get the project ID '(.*)'$")
    public void obtengoElIdDelProyectoNameProject(String p_nameProject) {
        ProjectService.defineProjectId(p_nameProject);

    }

    @And("The project found has the name '(.*)'")
    public void elProyectoEncontradoTieneElNombreNameProject(String p_nameProject) {
        ProjectValidator.ValidateProjectNameFound(p_nameProject);
    }

    @And("^I replaced the project name with '(.*)'$")
    public void reemplazoElNombreDelProyectoPorNameReplace(String p_nameProject) {
        BaseService.NAME_PROJECT_REPLACE.set(p_nameProject);
    }

    @And("^The project name was successfully replaced by '(.*)'$")
    public void theProjectNameWasSuccessfullyReplacedByNameReplace(String p_nameProjectReplace) {
        ProjectValidator.ValidateProjectNameReplace(p_nameProjectReplace);
    }

    @And("^The project '(.*)' is successfully eliminated$")
    public void theProjectNameProjectIsSuccessfullyEliminated(String p_nameProject) {
        ProjectValidator.ValidateProjectDelete(p_nameProject);
    }
}
