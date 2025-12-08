package ar.validator;

import api.model.project.ProjectResponse;
import com.crowdar.api.rest.APIManager;
import junit.framework.Assert;

public class ProjectValidator {
    public static void validateNameProjectCreated(String p_nameProject){
        ProjectResponse response = (ProjectResponse)APIManager.getLastResponse().getResponse();
        Assert.assertEquals("el nombre del projecto creado no es igual al esperado",p_nameProject ,response.name );
    }
    public static void ValidateProjectNameFound(String p_nameProject){
        ProjectResponse response = (ProjectResponse)APIManager.getLastResponse().getResponse();
        Assert.assertEquals("el nombre del projecto no es igual al esperado",p_nameProject , response.name);
    }
    public static void ValidateProjectNameReplace(String p_nameProjectReplace){
        ProjectResponse response = (ProjectResponse)APIManager.getLastResponse().getResponse();
        Assert.assertEquals("el nombre del projecto no fue reemplazado por" + p_nameProjectReplace, p_nameProjectReplace, response.name);
    }

    public static void ValidateProjectDelete(String p_nameProject){
        ProjectResponse response = (ProjectResponse)APIManager.getLastResponse().getResponse();
        Assert.assertEquals("El proyecto " + p_nameProject + " no fue eliminado", p_nameProject, response.name);
    }


}
