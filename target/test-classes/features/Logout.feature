@Logout
Feature: Logout Feature

  Background:
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt

  Scenario: Erfolgreiches Logout
    Given der Benutzer klickt auf das Burger-Menü
    When der Benutzer wählt "Logout" aus
    Then wird die Login-Seite angezeigt
