package api.model.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientResponse {
    public String id;
    public String name;
    public String email;
    public Object ccEmails;
    public String workspaceId;
    public boolean archived;
    public String address;
    public String note;
    public String currencyId;
    public String currencyCode;
}