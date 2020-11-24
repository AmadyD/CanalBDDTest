package com.ModificateurAdresse;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = {"pretty"},
features = {"src/test/ressources/features"},
tags = "")
@Test
public class RunCucumberTest extends AbstractTestNGCucumberTests{

	static WireMockServer wireMockServer= new WireMockServer(options()
            .dynamicPort());
	static CloseableHttpClient httpClient = HttpClients.createDefault();

	@BeforeClass
	public static void initWireMockServer() throws JsonProcessingException, JSONException {
		String jsonString = new JSONObject().put("Adresse", "Test").toString();
		//produces the string {"Adresse": "Test"}.
		wireMockServer.start();
		configureFor("localhost", wireMockServer.port());
		stubFor(post(urlEqualTo("/modifyAdresse"))
		  .withHeader("content-type", equalTo("application/json"))
		  .withRequestBody(containing("Adresse"))
		  .willReturn(aResponse().withStatus(200)));
		
		HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/modifyAdresse");
		StringEntity entity;
		try {
			entity = new StringEntity(jsonString);
			request.addHeader("content-type", "application/json");
			request.setEntity(entity);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			HttpResponse response = httpClient.execute(request);
			Assert.assertEquals(200, response.getStatusLine().getStatusCode());
			verify(postRequestedFor(urlEqualTo("/modifyAdresse"))
			  .withHeader("content-type", equalTo("application/json")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@AfterClass
	public static void stopWireMockServer() {
		wireMockServer.stop();

	}
}

