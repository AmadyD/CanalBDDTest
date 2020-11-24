package com.ModificateurAdresse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import org.testng.Assert;

class ModificateurAdresse{
	static String adresseModifie(String ancienneAdresse, String nouvelleAdresse) {
		return ancienneAdresse.equals(nouvelleAdresse)?"enregistrée":"adresse non modifiée";
	}
	
	static String CreationMouvementDeModification() {
		return "mouvement de modification créé";
	}
}

public class StepDefinitions {
	private String adresse;
	private String nouvelleAdresse = "test";
	private String reponse,reponse2;

  @Given("Un abonne avec une adresse principale {string}")
  public void un_abonne_avec_une_adresse_principale(String adresse) throws Throwable {
	  if(! adresse.isEmpty() && adresse !=null ) {
		  this.adresse = adresse;
	  }else {
		  throw new IllegalArgumentException("L'adresse de l'abonné fournie est nulle ou vide");
	  }

  }

  @When("Le conseiller connecté à {string} modifie adresse")
  public void le_conseiller_connecté_à_modifie_adresse(String conseiller) throws Throwable {
	  if(! conseiller.isEmpty() && conseiller !=null ) {

		  this.adresse = this.nouvelleAdresse;
		  reponse = ModificateurAdresse.adresseModifie(adresse, nouvelleAdresse);
		  reponse2 = ModificateurAdresse.CreationMouvementDeModification();
	  }else {
		  throw new IllegalArgumentException("Pas de conseiller retrouvé");
	  }

  }

  @Then("la nouvelle adresse abonné modifiée devrait être {string}")
  public void la_nouvelle_adresse_abonné_modifiée_devrait_être(String reponseAttendue) throws Throwable {
	  Assert.assertEquals(reponse, reponseAttendue);
  }

  @And("on devrait avoir {string}")
  public void on_devrait_avoir(String reponseAttendue) throws Throwable {
	  Assert.assertEquals(reponse2, reponseAttendue);
  }

}
