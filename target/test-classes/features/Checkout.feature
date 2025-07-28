@checkout
Feature: Checkout-Prozess

  Hintergrund:
    Given der Benutzer befindet sich auf der Produkt-Seite

  @SingleProductCheckout
  Scenario Outline: Erfolgreicher Checkout
    Given der Benutzer wählt das "<Produkt>" aus
    When der Benutzer klickt auf "Add to cart"
    And der Benutzer klickt auf den Warenkorb
    Then befindet sich das "<Produkt>" im Warenkorb
    And der Benutzer klickt auf den Button "Checkout"
    When der Benutzer gibt "<FirstName>", "<LastName>" und "<PostalCode>" ein
    And klickt auf den Button "Continue"
    Then werden das "<Produkt>" und die Einkaufsinformationen angezeigt
    When der Benutzer klickt auf den Button "Finish"
    Then erhält der Benutzer eine Bestätigung seines Einkaufs
    And klickt auf den Button "Back Home"
    Then der Benutzer befindet sich der Benutzer auf der Produkt-Seite und der Warenkorb ist leer

  Examples:
    | Produkt                  | FirstName | LastName | PostalCode |
    | Sauce Labs Backpack      | Max       | Muster   | 12345      |
    | Sauce Labs Bolt T-Shirt  | Lisa      | Schmidt  | 98765      |
