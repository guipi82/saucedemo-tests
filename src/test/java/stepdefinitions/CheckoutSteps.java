package stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutPage;
import utils.DriverFactory;
import utils.Hooks;

public class CheckoutSteps {
    // This class will contain step definitions related to the checkout process
    // such as entering shipping information, completing the purchase, etc.

    WebDriver driver; // Get the WebDriver instance from DriverFactory
    CartPage cartPage; // Create an instance of CartPage
    CheckoutPage checkoutPage; // Create an instance of CheckoutPage

    @And("der Benutzer klickt auf den Button \"Checkout\"")
    public void clickCheckoutButton() {
        driver = Hooks.getDriver();
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout(); // Click the checkout button on the cart page
    }

    // method to enter checkout information
    @When("der Benutzer gibt {string}, {string} und {string} ein")
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        checkoutPage.enterFirstName(firstName); // Enter first name
        checkoutPage.enterLastName(lastName); // Enter last name
        checkoutPage.enterPostalCode(postalCode); // Enter postal code
    }

    // Click the continue button to proceed
    @And("klickt auf den Button \"Continue\"")
    public void clickContinueButton() {
        checkoutPage.clickContinue();
    }

    @Then("werden das {string} und die Einkaufsinformationen angezeigt")
    public void verifyCheckoutInformation(String ProductName) {
        cartPage = new CartPage(driver);
        // Verify that the checkout information is displayed correctly
        String description = checkoutPage.getDescriptionLabel();
        String paymentInfo = checkoutPage.getPaymentInfoLabel();
        String shippingInfo = checkoutPage.getShippingInfoLabel();
        String priceTotal = checkoutPage.getTotalLabel();

        // check if the information is as expected
        assertEquals("Description", description);
        assertEquals("Payment Information:", paymentInfo);
        assertEquals("Shipping Information:", shippingInfo);
        assertEquals("Price Total", priceTotal);

        // You can also verify the product name if needed       
        String productNameInCart = cartPage.getProductName(ProductName);
        assertEquals("Product name in cart does not match", ProductName, productNameInCart);

    }

    @When("der Benutzer klickt auf den Button \"Finish\"")
    public void clickFinishButton() {
        checkoutPage.clickFinish();
    }

    // Verify the checkout complete message
    @Then("erhält der Benutzer eine Bestätigung seines Einkaufs")
    public void verifyCheckoutCompleteMessage() {
        String message = checkoutPage.getCheckoutCompleteMessage();
        assertEquals("Thank you for your order!", message);
    }

    @And("klickt auf den Button \"Back Home\"")
    public void clickBackToProductsButton() {
        checkoutPage.clickBackToProducts(); // Click the back to products button
    }

    @Then("der Benutzer befindet sich auf der Produkt-Seite und der Warenkorb ist leer")
    public void verifyUserOnProductsPage() {
        String productsTitle = checkoutPage.getProductsTitle();
        assertEquals("Products", productsTitle); // Verify that the user is on the products page
        String badgeText = cartPage.getProductCountInCart();
        assertEquals(null, badgeText); // Verify that the cart is empty

    }

}
