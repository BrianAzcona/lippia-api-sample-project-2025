package ar.steps;
import com.crowdar.core.PropertyManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.*;
import org.apache.log4j.Logger;
import services.*;

import java.util.concurrent.ThreadLocalRandom;


public class Hooks {
    private static final Logger LOGGER = Logger.getLogger(Hooks.class);


    private Scenario scenario;

    @Before("@CreateProjectBeforeScenario")
    public void createProjectBeforeScenario() {
        LOGGER.info("Creando PROYECTO ANTES del escenario");

        try {
            BaseService.X_API_KEY.set(PropertyManager.getProperty("clockify.api.key"));

            int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);
            String projectName = "AutoProject_" + randomNumber;

            BaseService.NAME_PROJECT.set(projectName);
            LOGGER.info("Nombre de proyecto generado: " + projectName);

            WorkspaceService.get("request/getAllWorkspaces");

            WorkspaceService.defineWorkspaceId("Crowdar");
            LOGGER.info("ID del Workspace guardado: " + BaseService.WORKSPACE_ID.get());

            ProjectService.post("request/addProject");
            LOGGER.info("Proyecto creado exitosamente");


            ProjectsService.get("request/getAllProjects");

            ProjectsService.defineProjectId(projectName);
            LOGGER.info("ID del Proyecto guardado: " + BaseService.PROJECT_ID.get());

        } catch (Exception e) {
            LOGGER.error("Error al crear el proyecto antes del escenario", e);
        }
    }


    @After("@DeleteProjectAfterScenario")
    public void deleteProjectAfterScenario() {
        LOGGER.info("Eliminando proyecto despues del escenario");

        if (BaseService.NAME_PROJECT.get() == null) {
            LOGGER.warn("No se encontró un nombre de proyecto por el cual buscar el ID");
            return;
        }

        String projectName = BaseService.NAME_PROJECT.get();
        LOGGER.info("Buscando ID del proyecto: " + projectName);

        try {
            CommonSteps commonSteps = new CommonSteps();
            commonSteps.doRequest(
                    "GET",
                    "PROJECTS",
                    "getAllProjects",
                    ""
            );

            ProjectsService.defineProjectId(projectName);

        } catch (Exception e) {
            LOGGER.error("Error al obtener el ID del proyecto", e);
            return;
        }

        if (BaseService.PROJECT_ID.get() == null) {
            LOGGER.warn("No se encontró el ID del proyecto y no se puede archivar");
            return;
        }

        LOGGER.info("ID del proyecto encontrado: " + BaseService.PROJECT_ID.get());

        ProjectService.put("request/updateProjectArchived");
        LOGGER.info("Proyecto archivado: " + BaseService.PROJECT_ID.get());

        ProjectService.delete("request/deleteProject");
        LOGGER.info("Proyecto eliminado: " + BaseService.PROJECT_ID.get());
    }

    @After("@DeleteClientAfterScenario")
    public void deleteClientAfterScenario() {
        LOGGER.info("Eliminando Cliente despues del escenario");

        if (BaseService.NAME_CLIENT.get() == null) {
            LOGGER.warn("No se encontró un nombre de Cliente por el cual buscar el ID");
            return;
        }

        String ClientName = BaseService.NAME_CLIENT.get();
        LOGGER.info("Buscando ID del cliente: " + ClientName);

        try {
            CommonSteps commonSteps = new CommonSteps();
            commonSteps.doRequest(
                    "GET",
                    "CLIENT",
                    "findClientsWorkspace",
                    ""
            );

            ClientService.getIDClient(ClientName);

        } catch (Exception e) {
            LOGGER.error("Error al obtener el ID del cliente", e);
            return;
        }

        if (BaseService.CLIENT_ID.get() == null) {
            LOGGER.warn("No se encontró el ID del proyecto y no se puede archivar");
            return;
        }

        LOGGER.info("ID del cliente encontrado: " + BaseService.CLIENT_ID.get());


        ClientService.delete("request/deleteClient");
        LOGGER.info("Cliente eliminado: " + BaseService.CLIENT_ID.get());
    }



}
