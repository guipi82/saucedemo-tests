@Login
Feature: Login Feature

  Scenario: Erfolgreiches Login
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "standard_user" und "secret_sauce" ein
    Then wird die Produkte-Seite angezeigt
  
  Scenario Outline: Erfolgreiches Login mit mehreren Benutzer
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "<username>" und "<password>" ein
    Then wird die Produkte-Seite angezeigt

    Examples:
      | username                | password      |
      | standard_user           | secret_sauce  |
      | problem_user            | secret_sauce  |
      | performance_glitch_user | secret_sauce  |

  Scenario: Fehlgeschlagenes Login
    Given der Benutzer befindet sich auf der Login-Seite
    When der Benutzer gibt "locked_out_user" und "secret_sauce" ein
    Then wird das error_Message angezeigt