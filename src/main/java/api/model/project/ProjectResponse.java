package api.model.project; 
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ProjectResponse {
    public String id;
    public String name;
    public HourlyRate hourlyRate;
    public String clientId;
    public String workspaceId;
    public boolean billable;
    public ArrayList<Membership> memberships;
    public String color;
    public Estimate estimate;
    public boolean archived;
    public String duration;
    public String clientName;
    public String note;
    public Object costRate;
    public TimeEstimate timeEstimate;
    public Object budgetEstimate;
    public Object estimateReset;
    @JsonProperty("public") 
    public boolean mypublic;
    public boolean template;
}
