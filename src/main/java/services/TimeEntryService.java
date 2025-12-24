package services;

import api.model.time_entry.TimeEntryResponse;
import com.crowdar.api.rest.Response;
import com.crowdar.core.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public class TimeEntryService extends BaseService{
    public static Response get(String jsonName) {
        return get(jsonName, TimeEntryResponse[].class,setParams());
    }
    public static Response post(String jsonName) {
        return post(jsonName, TimeEntryResponse.class,setParams());
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
        return params;
    }

    public static void addTimeEntry(String p_hours, String p_minutes, String p_seconds){
        BaseService.HOURS.set(p_hours);
        BaseService.MINUTES.set(p_minutes);
        BaseService.SECONDS.set(p_seconds);
    }
}
