package services;

import api.model.time_entry.TimeEntryResponse;
import com.crowdar.api.rest.APIManager;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;
import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimeEntryService extends BaseService{
    public static Response get(String jsonName) {
        return get(jsonName, TimeEntryResponse[].class,setParams());
    }
    public static Response post(String jsonName) {
        return post(jsonName, TimeEntryResponse.class,setParams());
    }
    public static Response put(String jsonName) {
        return put(jsonName, TimeEntryResponse.class,setParams());
    }
    public static Response delete(String jsonName) {
        return delete(jsonName, TimeEntryResponse[].class,setParams());
    }

    private static Map<String, String> setParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("base.url", PropertyManager.getProperty("base.api.url"));
        params.put("api-key",X_API_KEY.get());
        params.put("id-workspace",WORKSPACE_ID.get());
        if (HOURS.get() != null && !HOURS.get().isEmpty()) {
            params.put("hours", HOURS.get());
        }
        if (MINUTES.get() != null && !MINUTES.get().isEmpty()) {
            params.put("minutes", MINUTES.get());
        }
        if (SECONDS.get() != null && !SECONDS.get().isEmpty()) {
            params.put("seconds", SECONDS.get());
        }
        if (USER_ID.get() != null && !USER_ID.get().isEmpty()) {
            params.put("id-user", USER_ID.get());
        }
        if (PROJECT_ID.get() != null && !PROJECT_ID.get().isEmpty()) {
            params.put("project-id", PROJECT_ID.get());
        }
        if (TIME_ENTRY_ID.get() != null && !TIME_ENTRY_ID.get().isEmpty()) {
            params.put("timeEntry-id", TIME_ENTRY_ID.get());
        }
        if (DESCRIPTION.get() != null && !DESCRIPTION.get().isEmpty()) {
            params.put("description", DESCRIPTION.get());
        }
        if (END_OLD.get() != null && !END_OLD.get().isEmpty()) {
            params.put("end-old", END_OLD.get());
        }
        return params;
    }

    public static void addTimeEntry(String p_hours, String p_minutes, String p_seconds){
        BaseService.HOURS.set(p_hours);
        BaseService.MINUTES.set(p_minutes);
        BaseService.SECONDS.set(p_seconds);
    }

    public static void addDescription(String p_description){
        BaseService.DESCRIPTION.set(p_description);
    }

    public static void defineIdTimeEntryWithIdProject(){
        TimeEntryResponse[] timeEntryResponse = (TimeEntryResponse[]) APIManager.getLastResponse().getResponse();
        for (TimeEntryResponse timeEntry : timeEntryResponse){
            if (PROJECT_ID.get().equalsIgnoreCase(timeEntry.getProjectId())){
                Date endDate = timeEntry.getTimeInterval().getEnd();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                END_OLD.set(sdf.format(endDate));
                TIME_ENTRY_ID.set(timeEntry.getId());
                return;
            }
        }
        Assert.fail("No se encontró el id del time entry");
    }

}
