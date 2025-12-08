package api.model.workspace; 
import java.util.ArrayList;
import java.util.List;
public class Root{
    public String id;
    public String name;
    public HourlyRate hourlyRate;
    public CostRate costRate;
    public ArrayList<Membership> memberships;
    public WorkspaceSettings workspaceSettings;
    public String imageUrl;
    public String featureSubscriptionType;
    public ArrayList<String> features;
    public ArrayList<Currency> currencies;
    public Subdomain subdomain;
    public String cakeOrganizationId;
}
