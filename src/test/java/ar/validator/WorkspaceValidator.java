package ar.validator;

import api.model.workspace.WorkspacesResponse;
import com.crowdar.api.rest.APIManager;
import junit.framework.Assert;

public class WorkspaceValidator {
    public static void validate(){
        WorkspacesResponse[] response = (WorkspacesResponse[]) APIManager.getLastResponse().getResponse();
        Assert.assertNotNull(response[0].getId(),"El campo ID es nulo");
    }
}
