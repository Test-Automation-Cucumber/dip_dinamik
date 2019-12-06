$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./features/dip.feature");
formatter.feature({
  "name": "Dip Test Cases",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@dipHooks"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I login with \"hisse_ve_viop\"",
  "keyword": "Given "
});
formatter.match({
  "location": "LoginStepDef.I_login_with(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Yeni emir fiyat degisim",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@dipHooks"
    },
    {
      "name": "@dipTest"
    }
  ]
});
formatter.step({
  "name": "I click Al-Sat button",
  "keyword": "When "
});
formatter.match({
  "location": "EmirlerStepDef.I_click_Al_Sat_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should see Yeni Emir popup",
  "keyword": "Then "
});
formatter.match({
  "location": "EmirlerStepDef.I_should_see_Yeni_Emir_popup()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});