@Workspace
Feature: Workspace
  
  @GetAllMyWorkspaces
  Scenario Outline: Get All My Workspaces
    Given An account created in Clockify and x-api-key '<token>' generated
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And Obtengo los datos de mi Workspace
    
    Examples:
      | operation | entity    | jsonName         | statusCode | token                                            |
      | GET       | WORKSPACE | getAllWorkspaces | 200        | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |