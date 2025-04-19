package hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {

  public static final String BASE_URL = Objects.requireNonNullElse(System.getenv("BASE_URL"),
      "https://hotel-example-site.takeyaqa.dev/ja");

  private Utils() {
    throw new AssertionError();
  }

  public static WebDriver createWebDriver() {
    var githubActions = Boolean.parseBoolean(System.getenv("GITHUB_ACTIONS"));
    var remoteContainers = Boolean.parseBoolean(System.getenv("REMOTE_CONTAINERS"));
    var codespaces = Boolean.parseBoolean(System.getenv("CODESPACES"));

    Map<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("credentials_enable_service", false);
    chromePrefs.put("profile.password_manager_enabled", false);
    chromePrefs.put("profile.password_manager_leak_detection", false);

    var options = new ChromeOptions();
    options.setExperimentalOption("prefs", chromePrefs);
    if (githubActions) {
      options.addArguments("--headless");
    } else if (remoteContainers || codespaces) {
      options.addArguments("--headless", "--no-sandbox");
    }
    var driver = new ChromeDriver(options);
    driver.manage().window().setSize(new Dimension(1920, 1080));
    return driver;
  }

  public static String getNewWindowHandle(Collection<String> handlesBeforeOpen, Collection<String> handlesAfterOpen) {
    var handles = new ArrayList<>(handlesAfterOpen);
    handles.removeAll(handlesBeforeOpen);
    if (handles.isEmpty()) {
      throw new RuntimeException("新しいウィンドウが見つかりません");
    } else if (handles.size() > 1) {
      throw new RuntimeException("新しいウィンドウが複数あります");
    } else {
      return handles.getFirst();
    }
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ign) {
      // ignore
    }
  }
}
