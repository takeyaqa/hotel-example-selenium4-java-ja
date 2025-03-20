package hotel.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPage {

  private final WebDriver driver;

  private final WebDriverWait wait;

  public MyPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.wait.until(ExpectedConditions.titleContains("マイページ"));
    if (this.driver.getTitle() == null || !this.driver.getTitle().startsWith("マイページ")) {
      throw new IllegalStateException("現在のページが間違っています: " + this.driver.getTitle());
    }
  }

  public PlansPage goToPlansPage() {
    var planLink = driver.findElement(By.linkText("宿泊予約"));
    planLink.click();
    return new PlansPage(driver);
  }

  public IconPage goToIconPage() {
    var iconLink = driver.findElement(By.id("icon-link"));
    iconLink.click();
    return new IconPage(driver);
  }

  public String getHeaderText() {
    var header = driver.findElement(By.tagName("h2"));
    return header.getText();
  }

  public String getEmail() {
    var email = driver.findElement(By.id("email"));
    return email.getText();
  }

  public String getUsername() {
    var username = driver.findElement(By.id("username"));
    return username.getText();
  }

  public String getRank() {
    var rank = driver.findElement(By.id("rank"));
    return rank.getText();
  }

  public String getAddress() {
    var address = driver.findElement(By.id("address"));
    return address.getText();
  }

  public String getTel() {
    var tel = driver.findElement(By.id("tel"));
    return tel.getText();
  }

  public String getGender() {
    var gender = driver.findElement(By.id("gender"));
    return gender.getText();
  }

  public String getBirthday() {
    var birthday = driver.findElement(By.id("birthday"));
    return birthday.getText();
  }

  public String getNotification() {
    var notification = driver.findElement(By.id("notification"));
    return notification.getText();
  }

  public boolean existsIconImage() {
    var images = driver.findElements(By.cssSelector("#icon-holder > img"));
    return !images.isEmpty();
  }

  public int getIconImageWidth() {
    var image = driver.findElement(By.cssSelector("#icon-holder > img"));
    var width = image.getDomProperty("width");
    return width != null ? Integer.parseInt(width) : -1;
  }

  public Color getIconImageBorder() {
    var image = driver.findElement(By.cssSelector("#icon-holder > img"));
    String backgroundColor = image.getCssValue("backgroundColor");
    if (backgroundColor.isEmpty()) {
      backgroundColor = image.getCssValue("background-color");
    }
    return Color.fromString(backgroundColor);
  }

  public void deleteUser() {
    var deleteButton = driver.findElement(By.cssSelector("#delete-form > button"));
    deleteButton.click();
  }
}
