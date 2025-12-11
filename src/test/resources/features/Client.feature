@Client
Feature: Client
  
  @FindClientsWorkspace
  Scenario Outline: Find clients on workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And I get clients in the Workspace
    
    Examples:
      | operation | entity | jsonName             | statusCode | nameWorkspace |
      | GET       | CLIENT | findClientsWorkspace | 200        | Crowdar       |
  
  @AddNewClient @DeleteClientAfter
  Scenario Outline: Add a new client
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    And I have the client name '<nameClient>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The created client has the name '<nameClient>'
    
    
    Examples:
      | operation | entity | jsonName  | statusCode | nameWorkspace | nameClient           |
      | POST      | CLIENT | addClient | 201        | Crowdar       | ClienteAutomatizado3 |
  
  @DeleteClient @AddClientBefore
  Scenario Outline: Delete client
    Given I have the client name '<nameClient>'
    And I perform a 'PUT' to 'CLIENT' endpoint with the 'updateNameClient' and ''
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The client '<nameClient>' was successfully removed
    
    Examples:
      | operation | entity | jsonName     | statusCode | nameClient           |
      | DELETE    | CLIENT | deleteClient | 200        | ClienteAutomatizado3 |
    
    