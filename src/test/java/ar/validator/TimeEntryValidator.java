package ar.validator;

import api.model.time_entry.TimeEntryResponse;
import com.crowdar.api.rest.APIManager;
import junit.framework.Assert;
import services.BaseService;

public class TimeEntryValidator {

    public static void validateDescriptionUpdate(String p_descripcion) {
        TimeEntryResponse response = (TimeEntryResponse) APIManager.getLastResponse().getResponse();
        Assert.assertEquals("La descripcion actual no es la esperada", p_descripcion, response.description);
    }

    public static void validateDeleteTimeEntry() {
        TimeEntryResponse[] response = (TimeEntryResponse[]) APIManager.getLastResponse().getResponse();
        for (TimeEntryResponse timeEntry : response){
            if (BaseService.TIME_ENTRY_ID.get().equalsIgnoreCase(timeEntry.getId())){
                Assert.assertTrue(true);
                return;
            }
        }
        Assert.fail("No se encontro el id de la hora a eliminar");
    }

}
