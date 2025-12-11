package ar.validator;

import api.model.project.ProjectResponse;
import api.model.project.ProjectsResponse;
import com.crowdar.api.rest.APIManager;
import junit.framework.Assert;
import services.BaseService;

public class ProjectValidator {
    public static void validateNameProjectCreated(String p_nameProject){
        ProjectsResponse response = (ProjectsResponse)APIManager.getLastResponse().getResponse();
        Assert.assertEquals("el nombre del projecto creado no es igual al esperado",p_nameProject ,response.name );
    }
    public static void ValidateProjectNameFound(String p_nameProject){

        ProjectResponse response = (ProjectResponse)APIManager.getLastResponse().getResponse();

        Assert.assertEquals("el nombre del projecto no es igual al esperado",p_nameProject , response.name);
    }
    public static void ValidateProjectNameReplace(String p_nameProjectReplace){
        ProjectsResponse response = (ProjectsResponse)APIManager.getLastResponse().getResponse();

        Assert.assertEquals("el nombre del projecto no fue reemplazado por" + p_nameProjectReplace, p_nameProjectReplace, response.name);
    }

    public static void ValidateProjectDelete(){
        ProjectsResponse response = (ProjectsResponse)APIManager.getLastResponse().getResponse();
        System.out.println("ESTE NOMBRE VIENE DEL RESPONSE " + response.name);
        Assert.assertEquals("El proyecto  no fue eliminado", BaseService.NAME_PROJECT.get(), response.name);
    }


}
