Feature: Modifictaion adresse abonné
  modifier adresse des abonnés sans ou avec date de modification

  Scenario: Modifier adresse
    Given Un abonne avec une adresse principale "<active>"
    When Le conseiller connecté à "<canal>" modifie adresse
    Then la nouvelle adresse abonné modifiée devrait être "enregistrée"
    And on devrait avoir "mouvement de modification créé"

    Examples: 
      | canal | active |
      | FACE |  inactive |
      | ECF |   active |
