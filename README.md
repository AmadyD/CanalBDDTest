
Difficultés rencontrées:
Framework Serinity non compatible avec TestNG (Rapport Serinity prenant pas en compte les tests générés)

Lancement des tests: ouvrir une console dans le répertoire du projet et saisissez la commande *mvn clean verify* ou *mvn clean test*

*Test Cucumber* : Simulation de la modification de l'adresse d'un abonné canal par un conseiller, résultats attendus: "enregistrée" lorsque l'adresse de l'abonné est modifié sur tous ses contrats; "mouvement de modification créé" lorsque le mouvement de modification est lancé .

*Simulation appel API avec WireMockServer*:  SImulation d'une requête post sur le serveur localhost avec un numéro de port dynamiquement généré "http://localhost:" + wireMockServer.port() + "/modifyAdresse", vérification du contenu du json envoyer dans le body; résultat entendu: un statut 200 validant la requête ( PS: plus haut au niveau des logs )

