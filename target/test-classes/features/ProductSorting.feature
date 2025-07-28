@Sorting
Feature: Sortierung der Produkte auf der Produkteseite

  Background:
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt

  @SortAZ
  Scenario: Produkte von A bis Z sortieren
    When der Benutzer sortiert die Produkte nach "Name (A to Z)"
    Then sind die Produktnamen alphabetisch aufsteigend sortiert

  @SortZA
  Scenario: Produkte von Z bis A sortieren
    When der Benutzer sortiert die Produkte nach "Name (Z to A)"
    Then sind die Produktnamen alphabetisch absteigend sortiert

  @SortLowHigh
  Scenario: Produkte nach Preis aufsteigend sortieren
    When der Benutzer sortiert die Produkte nach "Price (low to high)"
    Then sind die Produktpreise aufsteigend sortiert

  @SortHighLow
  Scenario: Produkte nach Preis absteigend sortieren
    When der Benutzer sortiert die Produkte nach "Price (high to low)"
    Then sind die Produktpreise absteigend sortiert
