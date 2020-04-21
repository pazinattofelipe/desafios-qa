package com.automationpractice.steps;

import java.text.Collator;

import org.apache.commons.lang3.StringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import com.automationpractice.utilities.RestUtilitiesMethods;
import com.jayway.restassured.path.json.JsonPath;


public class RestStepsClass extends Steps {
	
	final static String BASE_URL = "https://api-v2.idwall.co/relatorios";
	ResponseEntity<String> responseSteps = null;

	@Given("I create a request to create a report for $userFullName, $cpfNumber and $dataNascimento")
	public void createReportRequest(@Named("userFullName") String userFullName,
									@Named("cpfNumber") String cpfNumber,
									@Named("dataNascimento") String dataNascimento) {
		responseSteps = RestUtilitiesMethods.runPostRequest(BASE_URL, userFullName, cpfNumber, dataNascimento);
	}
	
	@Given("I create a request without token to create a report for $userFullName, $cpfNumber and $dataNascimento")
	public void createReportRequestWithoutToken(@Named("userFullName") String userFullName,
												@Named("cpfNumber") String cpfNumber,
												@Named("dataNascimento") String dataNascimento) {
		responseSteps = RestUtilitiesMethods.runPostRequestWithoutToken(BASE_URL, userFullName, cpfNumber, dataNascimento);
	}
	
	@When("I consult data in Policia Federal database")
	public void validateGetResponse() {
		responseSteps = RestUtilitiesMethods.runGetRequest(BASE_URL + "/" + JsonPath.from(responseSteps.getBody()).get("result.numero"));
	}
	
	@Then("the report should present correct data")
	public void validateReportResponse() {
		Assert.assertTrue(responseSteps.getStatusCodeValue() == 200);
		Assert.assertTrue(JsonPath.from(responseSteps.getBody()).get("result.status").equals("CONCLUIDO")
				&& JsonPath.from(responseSteps.getBody()).get("result.resultado").equals("VALID"));
	}
	
	@Then("I should receive a message $errorMessage")
	public void validateMessageGetResponse(@Named("errorMessage") String errorMessage) {
		final Collator instance = Collator.getInstance();
		instance.setStrength(Collator.NO_DECOMPOSITION);
		
		Assert.assertTrue(instance.compare(JsonPath.from(responseSteps.getBody()).get("message").toString().trim(), errorMessage) == 0);
	}
	
	@Then("I should receive $errorMessage message in the report")
	public void validateMessageGetResponseReport(@Named("errorMessage") String errorMessage) {	
		Assert.assertTrue(compareStringAccent(JsonPath.from(responseSteps.getBody()).get("result.mensagem").toString().trim(), errorMessage));
	}
	
	
	public boolean compareStringAccent(String a, String b) {
	    return StringUtils.stripAccents(a).contains(StringUtils.stripAccents(b));
	}
}
