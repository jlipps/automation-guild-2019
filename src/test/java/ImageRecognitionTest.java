import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ImageRecognitionTest extends BaseTest {

    @Override
    protected DesiredCapabilities getCaps() throws URISyntaxException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", getResource("angrybirds.apk").toString());
        capabilities.setCapability("appWaitActivity", "com.rovio.fusion.App");
        capabilities.setCapability("mjpegScreenshotUrl", "http://localhost:8080/stream.mjpeg");

        return capabilities;
    }

    private void shootBird(AndroidDriver driver, WebElement birdEl, int xOffset, int yOffset) {
        Rectangle rect = birdEl.getRect();
        Point start = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
        Point end = start.moveBy(xOffset, yOffset);
        Duration dragDuration = Duration.ofMillis(500);

        PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
        Sequence shoot = new Sequence(finger, 0);
        shoot.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), start.x, start.y));
        shoot.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
        shoot.addAction(finger.createPointerMove(dragDuration, Origin.viewport(), end.x, end.y));
        shoot.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(shoot));
    }


    @Test
    public void testPigDestruction() throws URISyntaxException, IOException {
        String checkmark = getReferenceImageB64("checkmark.png");
        String bird = getReferenceImageB64("red-bird-in-slingshot.png");
        String victory = getReferenceImageB64("level-cleared-three-stars.png");

        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.5);

        driver.findElementByImage(checkmark).click();

        WebElement birdEl = driver.findElementByImage(bird);
        shootBird(driver, birdEl, -280, 140);

        driver.findElementByImage(victory);
    }

}
