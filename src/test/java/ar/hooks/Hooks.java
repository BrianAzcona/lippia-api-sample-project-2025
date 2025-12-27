package ar.hooks;
import ar.steps.CommonSteps;
import com.crowdar.core.PropertyManager;
import io.cucumber.java.*;
import org.apache.log4j.Logger;
import services.*;

import java.util.concurrent.ThreadLocalRandom;


public class Hooks {
    private static final Logger LOGGER = Logger.getLogger(Hooks.class);



    @Before("@AddProjectBefore")
    public void AddProjectBefore() {

            BaseService.X_API_KEY.set(PropertyManager.getProperty("clockify.api.key"));

            int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);
            String projectName = "RandomProject_" + randomNumber;

            BaseService.NAME_PROJECT.set(projectName);

            WorkspaceService.get("request/getAllWorkspaces");

            WorkspaceService.defineWorkspaceId("Crowdar");

            ProjectService.post("request/addProject");

            ProjectsService.get("request/getAllProjects");

            ProjectsService.defineProjectId(projectName);


    }

    @Before("@AddClientBefore")
    public void addClientBefore() {
            BaseService.X_API_KEY.set(PropertyManager.getProperty("clockify.api.key"));

            int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);
            String clientName = "RandomClient_" + randomNumber;

            BaseService.NAME_CLIENT.set(clientName);

            WorkspaceService.get("request/getAllWorkspaces");

            WorkspaceService.defineWorkspaceId("Crowdar");

            ClientService.post("request/addClient");

            ClientService.get("request/findClientsWorkspace");

            ClientService.getIDClient(clientName);


    }


    @After("@DeleteProjectAfter")
    public void deleteProjectAfter() {

        if (BaseService.NAME_PROJECT.get() == null) {
            LOGGER.warn("No se encontró un nombre de proyecto por el cual buscar el ID");
            return;
        }

        String projectName = BaseService.NAME_PROJECT.get();

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
        ProjectService.put("request/updateProjectArchived");

        ProjectService.delete("request/deleteProject");
    }

    @After("@DeleteClientAfter")
    public void deleteClientAfter() {
        if (BaseService.NAME_CLIENT.get() == null) {
            LOGGER.warn("No se encontró un nombre de Cliente por el cual buscar el ID");
            return;
        }
        String ClientName = BaseService.NAME_CLIENT.get();

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

        ClientService.delete("request/deleteClient");
    }

    @After("@DeleteTimeEntryAfter")
    public void deleteTimeEntryAfter() {
        try {
            CommonSteps commonSteps = new CommonSteps();
            commonSteps.doRequest(
                    "GET",
                    "TIME_ENTRY",
                    "getTimeUserWorkspace",
                    ""
            );

            TimeEntryService.defineIdTimeEntryWithIdProject();

        } catch (Exception e) {
            LOGGER.error("Error al obtener el ID de las horas registradas", e);
            return;
        }
        TimeEntryService.delete("request/deleteTimeEntry");
    }



}
