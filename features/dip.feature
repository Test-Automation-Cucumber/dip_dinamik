@dipHooks
Feature: Dip Test Cases

  Background: 
    Given Emirler - I login with "hisse_ve_viop"

  @dipTest
  Scenario: Yeni emir fiyat degisim
    #1
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "0,00" and Sembol "GARAN.E"
    And Press button "increase"
    Then Fiyat field must be ",01"
    #2
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "0,00" and Sembol "GARAN.E"
    And Press button "decrease"
    Then Fiyat field must be ",00"
    #3
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "5,55" and Sembol "GARAN.E"
    And Press button "keyboard_up"
    Then Fiyat field must be "5,56"
    #4
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "5,55" and Sembol "GARAN.E"
    And Press button "keyboard_down"
    Then Fiyat field must be "5,54"
    #7
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "19,99" and Sembol "GARAN.E"
    And Press button "increase"
    Then Fiyat field must be "20,00"
    #8
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "20,00" and Sembol "GARAN.E"
    And Press button "decrease"
    Then Fiyat field must be "19,99"
    #9
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "20,00" and Sembol "TAVHL.E"
    And Press button "increase"
    Then Fiyat field must be "20,02"
    #10
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "20,00" and Sembol "TAVHL.E"
    And Press button "decrease"
    Then Fiyat field must be "20,02"
    #11
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "25,56" and Sembol "TAVHL.E"
    And Press button "keyboard_up"
    Then Fiyat field must be "25,58"
    #12
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "25,58" and Sembol "TAVHL.E"
    And Press button "keyboard_down"
    Then Fiyat field must be "25,56"
    #17
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "49,98" and Sembol "TAVHL.E"
    And Press button "increase"
    Then Fiyat field must be "50,00"
    #18
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "50,00" and Sembol "TAVHL.E"
    And Press button "decrease"
    Then Fiyat field must be "49,98"
    #21
    When I click Al-Sat button
    Then I should see Yeni Emir popup
    When Set Fiyat value "50,00" and Sembol "PGSUS.E"
    And Press button "decrease"
    Then Fiyat field must be "50,05"
    
    
    
    