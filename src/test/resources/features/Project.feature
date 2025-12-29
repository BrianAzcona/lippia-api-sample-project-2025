@Project
Feature: Project
  
  @GetAllProjectsWorkspace @Smoke
  Scenario Outline: Get all projects on workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    
    Examples:
      | operation | entity   | jsonName       | statusCode | nameWorkspace |
      | GET       | PROJECTS | getAllProjects | 200        | Crowdar       |
  
  @AddNewProject @DeleteProjectAfter @Regression
  Scenario Outline: Add new project
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    And I have the name of the new project available '<projectName>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project created has the name '<projectName>'
    
    Examples:
      | operation | entity  | jsonName   | statusCode | projectName           | nameWorkspace |
      | POST      | PROJECT | addProject | 201        | ProjectoAutomatizado3 | Crowdar       |
  
  
  @DeleteProject @AddProjectBefore @Regression
  Scenario Outline: Delete project from workspace
    Given I perform a 'PUT' to 'PROJECT' endpoint with the 'updateProjectArchived' and ''
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project is successfully eliminated
    
    Examples:
      | operation | entity  | jsonName      | statusCode |
      | DELETE    | PROJECT | deleteProject | 200        |
  
  @GetFindProjectByID @Smoke
  Scenario Outline: Find project by ID
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'PROJECTS' endpoint with the 'getAllProjects' and ''
    And I get the project ID '<nameProject>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project found has the name '<nameProject>'
    
    
    Examples:
      | nameProject          | entity  | operation | jsonName         | statusCode | nameWorkspace |
      | Software Development | PROJECT | GET       | getFindProjectID | 200        | Crowdar       |
  
  @UpdateProjectWorkspace @AddProjectBefore @DeleteProjectAfter @Regression
  Scenario Outline: Update name project on workspace
    Given I replaced the project name with '<nameReplace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project name was successfully replaced by '<nameReplace>'
    
    Examples:
      | entity  | operation | jsonName      | statusCode | nameReplace    |
      | PROJECT | PUT       | updateProject | 200        | ProjectReplace |
      
      