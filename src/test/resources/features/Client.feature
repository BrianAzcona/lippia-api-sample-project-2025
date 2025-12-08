@Client
Feature: Client
  
  @FindClientsWorkspace
  Scenario Outline: Find clients on workspace
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And I get clients in the Workspace
    
    Examples:
      | operation | entity | jsonName             | statusCode | index | token                                            |
      | GET       | CLIENT | findClientsWorkspace | 200        | 0     | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  @AddNewClient
  Scenario Outline: Add a new client
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I have the client name '<nameClient>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The created client has the name '<nameClient>'
    
    
    Examples:
      | operation | entity | jsonName  | statusCode | index | nameClient           | token                                            |
      | POST      | CLIENT | addClient | 201        | 0     | ClienteAutomatizado3 | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  @DeleteClient
  Scenario Outline: Delete client
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I perform a 'GET' to 'CLIENT' endpoint with the 'findClientsWorkspace' and ''
    And status code 200 is obtained
    And I get the client ID with the name '<nameClient>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The client '<nameClient>' was successfully removed
    
    Examples:
      | operation | entity | jsonName     | statusCode | index | nameClient | token                                            |
      | DELETE    | CLIENT | deleteClient | 200        | 0     | newClient1 | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
    
    