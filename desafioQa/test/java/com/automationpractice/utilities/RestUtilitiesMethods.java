package com.automationpractice.utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.jayway.restassured.path.json.JsonPath;

public class RestUtilitiesMethods {

	public static ResponseEntity<String> runPostRequest(String baseUrl, String userFullName, String cpfNumber, String cpfDataNascimento){

		try {
			HttpEntity<Map<String, Object>> payload = new HttpEntity<>(createJsonPayload(userFullName, cpfNumber, cpfDataNascimento),
					createRequestHeader());

			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, payload, String.class);
			return response;
		} catch (RestClientResponseException e) {
			return ResponseEntity
					.status(e.getRawStatusCode())
					.body(e.getResponseBodyAsString());
		}

	}
	
	public static ResponseEntity<String> runPostRequestWithoutToken(String baseUrl, String userFullName, String cpfNumber, String cpfDataNascimento){

		try {
			HttpEntity<Map<String, Object>> payload = new HttpEntity<>(createJsonPayload(userFullName, cpfNumber, cpfDataNascimento));

			CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, payload, String.class);
			return response;
		} catch (RestClientResponseException e) {
			return ResponseEntity
					.status(e.getRawStatusCode())
					.body(e.getResponseBodyAsString());
		}
	}

	public static ResponseEntity<String> runGetRequest(String baseUrl) {
		HttpEntity<Map<String, Object>> payload = new HttpEntity<>(createRequestHeader());

		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		ResponseEntity<String> response = null;

		int counter = 0;

		do {
			try {
				counter++;
				Thread.sleep(1000);
				response = restTemplate.exchange(baseUrl, HttpMethod.GET, payload, String.class);

			} catch (Exception e) {
				System.out.println(e);
			}

		} while (JsonPath.from(response.getBody()).get("result.status") != "CONCLUIDO" && (counter < 300));

		return response;
	}

	private static HttpHeaders createRequestHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", System.getProperty("token"));

		return headers;
	}

	private static Map<String, Object> createJsonPayload(String userFullName, String cpfNumber, String cpfDataNascimento){
		Map<String, Object> userData = new HashMap<>();
		userData.put("cpf_data_de_nascimento", cpfDataNascimento);
		userData.put("cpf_nome", userFullName);
		userData.put("cpf_numero", cpfNumber);

		Map<String, Object> matrizData = new HashMap<>();
		matrizData.put("matriz", "consultaPessoaDefault");
		matrizData.put("parametros", userData);

		return matrizData;
	}
}
