@Project
Feature: Project
  
  @GetAllProjectsWorkspace
  Scenario Outline: Get all projects on workspace
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    
    Examples:
      | operation | entity  | jsonName       | statusCode | index | token                                            |
      | GET       | PROJECT | getAllProjects | 201        | 0     | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  @AddNewProject
  Scenario Outline: Add new project
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I have the name available '<projectName>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project created has the name '<projectName>'
    
    Examples:
      | operation | entity  | jsonName   | statusCode | projectName           | index | token                                            |
      | POST      | PROJECT | addProject | 201        | ProjectoAutomatizado5 | 0     | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  
  @DeleteProject
  Scenario Outline: Delete project from workspace
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I perform a 'GET' to 'PROJECT' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProject>'
    And I perform a 'PUT' to 'PROJECT' endpoint with the 'updateProjectArchived' and ''
    And status code 200 is obtained
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project '<nameProject>' is successfully eliminated
    
    Examples:
      | operation | entity  | jsonName      | statusCode | nameProject         | index | token                                            |
      | DELETE    | PROJECT | deleteProject | 200        | ProjectoAutomatizado6 | 0     | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  @GetFindProjectByID
  Scenario Outline: Find project by ID
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I perform a 'GET' to 'PROJECT' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProject>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project found has the name '<nameProject>'
    
    
    Examples:
      | nameProject           | entity  | operation | jsonName         | statusCode | index | token                                            |
      | ProjectoAutomatizado5 | PROJECT | GET       | getFindProjectID | 200        | 0     | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
  
  @UpdateProjectWorkspace
  Scenario Outline: Update project on workspace
    Given An account created in Clockify and x-api-key '<token>' generated
    And I perform a 'GET' to 'WORKSPACE' endpoint with the 'getAllWorkspaces' and ''
    And status code 200 is obtained
    And I get the workspaceId at the position '<index>'
    And I perform a 'GET' to 'PROJECT' endpoint with the 'getAllProjects' and ''
    And status code 200 is obtained
    And I get the project ID '<nameProjectActual>'
    And I replaced the project name with '<nameReplace>'
    When I perform a '<operation>' to '<entity>' endpoint with the '<jsonName>' and ''
    Then status code <statusCode> is obtained
    And The project name was successfully replaced by '<nameReplace>'
    
    
    
    Examples:
      | entity  | operation | jsonName      | statusCode | index | nameProjectActual     | nameReplace           | token                                            |
      | PROJECT | PUT       | updateProject | 200        | 0     | ProjectoAutomatizado5 | ProjectoAutomatizado6 | Y2MwOTE0OTYtMWVjNC00ZDdhLTkzYWYtZmE5Mjc1Yjc4ZGZj |
      
      