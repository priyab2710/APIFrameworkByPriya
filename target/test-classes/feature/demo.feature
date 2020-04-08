Feature: Pet Swagger 
Scenario: Add Pet
Given add pet api is available 
When user enters categoryname, name, tagname, status
Then pet is added to store 
And returns success response