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

📦 Ausführbare Tests
    • Login (gültig & ungültig)
    • Produktauswahl & Warenkorb
    • Checkout-Prozess
    • Preis- & Produktanzeige
    • Logout
    • Seitennavigation
    • Sortieren der Produkte

## ▶️ Tests ausführen
```bash
mvn clean test -Dbrowser=chrome
⚠️ Hinweis: Stelle sicher, dass du Chrome installiert hast und dein chromedriver zur verwendeten Chrome-Version passt

Auf GitHub:
1. Gehe zu **Actions**
2. Wähle **Run Cucumber Tests and Publish HTML Report**
3. Klicke auf **Run workflow**
4. Wähle Branch: `main` > **Run workflow**

📊 HTML-Testreport anzeigen
1. Nach der Ausführung der GitHub Actions:

2. Gehe auf GitHub > Actions

3. Klicke auf Run Cucumber Tests and Publish HTML Report

4. Wähle den letzten erfolgreichen Run aus

5. Klicke auf Artifacts > cucumber-html-report

6. Lade den Report herunter und öffne die Datei cucumber-html-reports.html im Browser


