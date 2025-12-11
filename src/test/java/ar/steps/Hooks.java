package ar.steps;
import com.crowdar.core.PropertyManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.*;
import org.apache.log4j.Logger;
import services.BaseService;
import services.ProjectService;
import services.ProjectsService;
import services.WorkspaceService;

import java.util.concurrent.ThreadLocalRandom;


public class Hooks {
    private static final Logger LOGGER = Logger.getLogger(Hooks.class);


    private Scenario scenario;

    @Before("@CreateProjectBeforeScenario")
    public void createProjectBeforeScenario() {
        LOGGER.info("----- Creating PROJECT BEFORE scenario -----");

        try {
            BaseService.X_API_KEY.set(PropertyManager.getProperty("clockify.api.key"));

            // 1. Crear nombre de proyecto
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);
            String projectName = "AutoProject_" + randomNumber;

            BaseService.NAME_PROJECT.set(projectName);

            LOGGER.info("Generated project name: " + projectName);

            // 2. GET Workspaces
            WorkspaceService.get("request/getAllWorkspaces");

            // 3. Obtener workspaceId (buscando por nombre o tomando el primero)
            WorkspaceService.defineWorkspaceId("Crowdar");
            // O si querés el primero: WorkspaceService.setFirstWorkspaceId();

            LOGGER.info("Workspace ID stored: " + BaseService.WORKSPACE_ID.get());

            // 4. Crear proyecto (POST)
            ProjectService.post("request/addProject");

            LOGGER.info("Project created successfully");

            // 5. Obtener lista de proyectos
            ProjectsService.get("request/getAllProjects");

            // 6. Buscar ID del proyecto recién creado
            ProjectsService.defineProjectId(projectName);

            LOGGER.info("Project ID stored: " + BaseService.PROJECT_ID.get());

        } catch (Exception e) {
            LOGGER.error("Error creating project BEFORE scenario", e);
        }
    }


    @After("@DeleteProjectAfterScenario")
    public void deleteProjectAfterScenario() {
        LOGGER.info("----- Cleaning project AFTER scenario -----");

        // 1. Validar que exista un nombre de proyecto creado
        if (BaseService.NAME_PROJECT.get() == null) {
            LOGGER.warn("No project NAME found, cannot search ID.");
            return;
        }

        String projectName = BaseService.NAME_PROJECT.get();
        LOGGER.info("Looking for project ID for: " + projectName);

        try {
            // 2. Obtener lista de proyectos
            CommonSteps commonSteps = new CommonSteps();
            commonSteps.doRequest(
                    "GET",
                    "PROJECTS",
                    "getAllProjects",
                    ""
            );

            // 3. Buscar y guardar el ID
            ProjectsService.defineProjectId(projectName);

        } catch (Exception e) {
            LOGGER.error("Error getting the project ID automatically", e);
            return;
        }

        // 4. Revisar si el ID fue encontrado
        if (BaseService.PROJECT_ID.get() == null) {
            LOGGER.warn("Project ID not found, cannot archive/delete.");
            return;
        }

        LOGGER.info("Project ID found: " + BaseService.PROJECT_ID.get());

        // 5. Archivar
        ProjectService.put("request/updateProjectArchived");
        LOGGER.info("Project archived: " + BaseService.PROJECT_ID.get());

        // 6. Eliminar
        ProjectService.delete("request/deleteProject");
        LOGGER.info("Project deleted: " + BaseService.PROJECT_ID.get());
    }

}
