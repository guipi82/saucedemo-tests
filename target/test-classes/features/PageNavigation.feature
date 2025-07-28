@Navigation
Feature: Seitennavigation über das Menü

  Background:
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt

  @About
  Scenario: Navigation zur About-Seite
    Given der Benutzer klickt auf das Burger-Menü
    When der Benutzer wählt "About" aus
    Then wird die About-Seite geöffnet

  @Reset
  Scenario Outline: Navigation über "Reset App State"
    Given der Benutzer wählt das "<Produkt>" aus
    When der Benutzer klickt auf "Add to cart" im Warenkorb
    And der Benutzer klickt auf das Burger-Menü im Warenkorb
    When der Benutzer wählt "Reset App State" aus
    Then ist der Warenkorb leer

    Examples:
      | Produkt                       |
      | Sauce Labs Backpack           | 
      | Sauce Labs Bolt T-Shirt       |
