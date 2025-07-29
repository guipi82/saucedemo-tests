# 🧪 Saucedemo UI Test Automation

Dieses Projekt enthält automatisierte UI-Tests für [https://www.saucedemo.com](https://www.saucedemo.com) mit **Java**, **Selenium WebDriver**, **JUnit 5** und **Cucumber**.

## 🚀 Technologien
- Java 17
- Maven
- Selenium WebDriver
- JUnit 5
- Cucumber
- Page Object Model (POM)

## 📂 Projektstruktur
saucedemo-tests/
├── src/
│ ├── test/
│ │ ├── java/pages/
│ │ ├── java/stepdefinitions/
│ │ └── java/runners/
│ └── resources/features/
├── pom.xml
└── README.md


## ▶️ Tests ausführen

```bash
mvn clean test -Dbrowser=chrome

Tests:
    • Login (gültig & ungültig)
    • Produktauswahl & Warenkorb
    • Checkout-Prozess
    • Preis- & Produktanzeige
    • Logout
    • Seitennavigation
    • Sortieren der Produkte

## 🔍 HTML-Testreport



