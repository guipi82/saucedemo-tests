
@Cart
Feature: Produktauswahl und Warenkorb

  Background:
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt


  @SingleProduct
  Scenario Outline: Einzelnes Produkt zum Warenkorb hinzufügen
    Given der Benutzer wählt das "<Produkt>" aus
    When der Benutzer klickt auf "Add to cart"
    And der Benutzer klickt auf den Warenkorb
    Then befindet sich das "<Produkt>" im Warenkorb
    When der Benutzer entfernt das "<Produkt>" aus Warenkorb
    Then ist der Warenkorb leer
    
    Examples:
      | Produkt                       |
      | Sauce Labs Backpack           | 
      | Sauce Labs Bolt T-Shirt       |
  
  @WarenkorbTabelle
  Scenario Outline: Mehrere Produkte in Warenkorb hinzufügen und entfernen
    Given der Benutzer wählt die folgenden Produkte aus:
      | Sauce Labs Backpack       |
      | Sauce Labs Bolt T-Shirt   |
    When der Benutzer klickt auf den Warenkorb
    Then enthält der Warenkorb folgende Produkte:
      | Sauce Labs Backpack       |
      | Sauce Labs Bolt T-Shirt   |
    When der Benutzer entfernt die Produkte aus Warenkorb
      | Sauce Labs Backpack       |
      | Sauce Labs Bolt T-Shirt   |
    Then ist der Warenkorb leer