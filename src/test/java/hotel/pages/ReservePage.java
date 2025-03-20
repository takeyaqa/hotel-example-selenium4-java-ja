package hotel.pages;

import java.time.Duration;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservePage {

  public enum Contact {
    希望しない("no"), メールでのご連絡("email"), 電話でのご連絡("tel");

    private final String value;

    Contact(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  private final WebDriver driver;

  private final WebDriverWait wait;

  public ReservePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.wait.until(ExpectedConditions.titleContains("宿泊予約"));
    if (this.driver.getTitle() == null || !this.driver.getTitle().startsWith("宿泊予約")) {
      throw new IllegalStateException("現在のページが間違っています: " + this.driver.getTitle());
    }
  }

  public void setReserveDate(String date) {
    var dateInput = driver.findElement(By.id("date"));
    dateInput.clear();
    dateInput.sendKeys(date);
    var datePickerClose = driver.findElement(By.className("ui-datepicker-close"));
    datePickerClose.click();
  }

  public void setReserveTerm(String term) {
    var termInput = driver.findElement(By.id("term"));
    termInput.clear();
    termInput.sendKeys(term);
  }

  public void setHeadCount(String headCount) {
    var headCountInput = driver.findElement(By.id("head-count"));
    headCountInput.clear();
    headCountInput.sendKeys(headCount);
  }

  public void setBreakfastPlan(boolean checked) {
    var breakfastCheck = driver.findElement(By.id("breakfast"));
    if (breakfastCheck.isSelected() != checked) {
      breakfastCheck.click();
    }
  }

  public void setEarlyCheckInPlan(boolean checked) {
    var earlyCheckInCheck = driver.findElement(By.id("early-check-in"));
    if (earlyCheckInCheck.isSelected() != checked) {
      earlyCheckInCheck.click();
    }
  }

  public void setSightseeingPlan(boolean checked) {
    var sightseeingCheck = driver.findElement(By.id("sightseeing"));
    if (sightseeingCheck.isSelected() != checked) {
      sightseeingCheck.click();
    }
  }

  public void setUsername(String username) {
    var usernameInput = driver.findElement(By.id("username"));
    usernameInput.clear();
    usernameInput.sendKeys(username);
  }

  public void setContact(Contact contact) {
    var contactSelect = new Select(driver.findElement(By.id("contact")));
    contactSelect.selectByValue(contact.getValue());
  }

  public void setEmail(String email) {
    var emailInput = driver.findElement(By.id("email"));
    emailInput.clear();
    emailInput.sendKeys(email);
  }

  public boolean isEmailDisplayed() {
    var emailInput = driver.findElement(By.id("email"));
    return emailInput.isDisplayed();
  }

  public void setTel(String tel) {
    var telInput = driver.findElement(By.id("tel"));
    telInput.clear();
    telInput.sendKeys(tel);
  }

  public boolean isTelDisplayed() {
    var telInput = driver.findElement(By.id("tel"));
    return telInput.isDisplayed();
  }

  public void setComment(String comment) {
    var commentTextArea = driver.findElement(By.id("comment"));
    commentTextArea.clear();
    commentTextArea.sendKeys(comment);
  }

  public ConfirmPage goToConfirmPage() {
    var submitButton = driver.findElement(By.cssSelector("button[data-test=\"submit-button\"]"));
    submitButton.click();
    return new ConfirmPage(driver);
  }

  public void goToConfirmPageExpectingFailure() {
    var submitButton = driver.findElement(By.cssSelector("button[data-test=\"submit-button\"]"));
    submitButton.click();
  }

  public String getPlanName() {
    wait.until(ExpectedConditions.textMatches(By.id("plan-name"), Pattern.compile("\\H+")));
    var planName = driver.findElement(By.id("plan-name"));
    return planName.getText();
  }

  public String getReserveDate() {
    var dateInput = driver.findElement(By.id("date"));
    return dateInput.getDomProperty("value");
  }

  public String getReserveTerm() {
    var termInput = driver.findElement(By.id("term"));
    return termInput.getDomProperty("value");
  }

  public String getHeadCount() {
    var headCountInput = driver.findElement(By.id("head-count"));
    return headCountInput.getDomProperty("value");
  }

  public String getUsername() {
    var headCountInput = driver.findElement(By.id("username"));
    return headCountInput.getDomProperty("value");
  }

  public String getEmail() {
    var headCountInput = driver.findElement(By.id("email"));
    return headCountInput.getDomProperty("value");
  }

  public String getTel() {
    var headCountInput = driver.findElement(By.id("tel"));
    return headCountInput.getDomProperty("value");
  }

  public String getReserveDateMessage() {
    var reserveDateMessage = driver.findElement(By.cssSelector("#date ~ .invalid-feedback"));
    return reserveDateMessage.getText();
  }

  public String getReserveTermMessage() {
    var reserveTermMessage = driver.findElement(By.cssSelector("#term ~ .invalid-feedback"));
    return reserveTermMessage.getText();
  }

  public String getHeadCountMessage() {
    var headCountMessage = driver.findElement(By.cssSelector("#head-count ~ .invalid-feedback"));
    return headCountMessage.getText();
  }

  public String getUsernameMessage() {
    var usernameMessage = driver.findElement(By.cssSelector("#username ~ .invalid-feedback"));
    return usernameMessage.getText();
  }

  public String getEmailMessage() {
    var emailMessage = driver.findElement(By.cssSelector("#email ~ .invalid-feedback"));
    return emailMessage.getText();
  }

  public String getTelMessage() {
    var telMessage = driver.findElement(By.cssSelector("#tel ~ .invalid-feedback"));
    return telMessage.getText();
  }
}
