@TimeEntry
Feature: Time entry
  
  @GetTimeEntryUserWorkspace
  Scenario Outline: Get time entries for a user on workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'USER' endpoint with the 'getAllUserWorkspace' and ''
    And I get the userId with name <nameUser>
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    
    Examples:
      | nameWorkspace | nameUser      | operation | entity     | jsonName             | statusCode |
      | Crowdar       | brianazcona25 | GET       | TIME_ENTRY | getTimeUserWorkspace | 200        |
  
  @AddTimeProject
  Scenario Outline: Add a new time entry
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'USER' endpoint with the 'getAllUserWorkspace' and ''
    And I get the userId with name <nameUser>
    And I perform a 'GET' to 'PROJECTS' endpoint with the 'getAllProjects' and ''
    And I get the project ID '<nameProject>'
    And I add hours <hours>, minutes <minutes> and seconds <seconds> to the project
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    
    Examples:
      | hours | minutes | seconds | nameWorkspace | nameUser      | nameProject  | operation | entity     | jsonName        | statusCode |
      | 02     | 00      | 00      | Crowdar       | brianazcona25 | pruebaManual | POST      | TIME_ENTRY | addNewTimeEntry | 201        |