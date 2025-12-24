package api.model.time_entry;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TimeEntryResponse {
    public String id;
    public String description;
    public Object tagIds;
    public String userId;
    public boolean billable;
    public Object taskId;
    public String projectId;
    public String workspaceId;
    public TimeInterval timeInterval;
    public ArrayList<Object> customFieldValues;
    public String type;
    public Object kioskId;
    public HourlyRate hourlyRate;
    public CostRate costRate;
    public boolean isLocked;
}
