@dipHooks
Feature: Dip Test Cases


Background: 
    Given I login with "hisse_ve_viop"
    

  @dipTest
  Scenario: Yeni emir fiyat degisim
		When I click Al-Sat button
		Then I should see Yeni Emir popup
		
