@Project
Feature: Project
  
  @GetAllProjectsWorkspace
  Scenario Outline: Get all projects on workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId with name '<nameWorkspace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    
    Examples:
      | operation | entity   | jsonName       | statusCode | nameWorkspace |
      | GET       | PROJECTS | getAllProjects | 200        | Crowdar       |
  
  @AddNewProject
  Scenario Outline: Add new project
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId with name '<nameWorkspace>'
    And I have the name available '<projectName>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project created has the name '<projectName>'
    
    
    Examples:
      | operation | entity  | jsonName   | statusCode | projectName           | nameWorkspace |
      | POST      | PROJECT | addProject | 201        | ProjectoAutomatizado9 | Crowdar       |
  
  
  @DeleteProject
  Scenario Outline: Delete project from workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'PROJECTS' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProject>'
    And I perform a 'PUT' to 'PROJECT' endpoint with the 'updateProjectArchived' and ''
    And status code 200 is obtained
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project '<nameProject>' is successfully eliminated
    
    Examples:
      | operation | entity  | jsonName      | statusCode | nameProject           | nameWorkspace |
      | DELETE    | PROJECT | deleteProject | 200        | ProjectoAutomatizado9 | Crowdar       |
  
  @GetFindProjectByID
  Scenario Outline: Find project by ID
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'PROJECTS' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProject>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project found has the name '<nameProject>'
    
    
    Examples:
      | nameProject           | entity  | operation | jsonName         | statusCode | nameWorkspace |
      | ProjectoAutomatizado6 | PROJECT | GET       | getFindProjectID | 200        | Crowdar       |
  
  @UpdateProjectWorkspace
  Scenario Outline: Update project on workspace
    Given An account created in Clockify and x-api-key generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId with name '<nameWorkspace>'
    And I perform a 'GET' to 'PROJECTS' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProjectActual>'
    And I replaced the project name with '<nameReplace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project name was successfully replaced by '<nameReplace>'
    
    
    
    Examples:
      | entity  | operation | jsonName      | statusCode | nameWorkspace | nameProjectActual   | nameReplace           |
      | PROJECT | PUT       | updateProject | 200        | Crowdar       | ProyectoModificado2 | ProjectoAutomatizado6 |
      
      