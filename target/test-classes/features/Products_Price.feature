@isProductPageDisplayed

Feature: Anzeige von Produktnamen und Preisen

  Background:
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt
  
  @ProductPriceCheck
  Scenario Outline: Überprüfung der Produktanzeige mit Preisen
    Given der Benutzer sieht das Produkt "<Produktname>"
    Then wird der "<Preis>" für das "<Produktname>" angezeigt

     Examples:
     | Produktname             | Preis  |
     | Sauce Labs Backpack     | $29.99 |
     | Sauce Labs Bike Light   | $9.99  |
     | Sauce Labs Bolt T-Shirt | $15.99 |

  @CartPriceCheck
  Scenario: Überprüfung der Produktanzeige und Preis im Warenkorb
    Given der Benutzer wählt das "<Produkt>" aus
    When der Benutzer klickt auf "Add to cart"
    And der Benutzer klickt auf den Warenkorb
    Then befindet sich das "<Produkt>" im Warenkorb
    And wird der "<Preis>" für das "<Produkt>" im Warenkorb angezeigt
     Examples:
     | Produkt                 | Preis  |
     | Sauce Labs Backpack     | $29.99 |
     | Sauce Labs Bike Light   | $9.99  |
     | Sauce Labs Bolt T-Shirt | $15.99 |

     