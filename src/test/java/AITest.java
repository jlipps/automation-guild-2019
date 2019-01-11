import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AITest extends BaseTest {

    private By menu = MobileBy.custom("menu");
    private By images = MobileBy.xpath("//android.widget.TextView[@text='Images']");

    @Override
    protected DesiredCapabilities getCaps() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.documentsui");
        caps.setCapability("appActivity", ".files.FilesActivity");
        caps.setCapability("mjpegScreenshotUrl", "http://localhost:8080/stream.mjpeg");
        caps.setCapability("customFindModules", ImmutableMap.of("ai", "test-ai-classifier"));
        caps.setCapability("shouldUseCompactResponses", false);

        return caps;
    }

    @Test
    public void testAIElementDetection() {
        driver.findElement(menu).click();
        driver.findElement(images);
    }
}
