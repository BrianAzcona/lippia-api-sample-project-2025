package api.model.workspace;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkspacesResponse {
    private String id;
    private String name;
    private HourlyRate hourlyRate;
    private List<Membership> memberships;
    private WorkspaceSettings workspaceSettings;
    private String imageUrl;
    private String featureSubscriptionType;

}
