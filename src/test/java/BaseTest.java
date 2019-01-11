import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    protected AndroidDriver driver;

    protected DesiredCapabilities getCaps() throws Exception {
        throw new Exception("Must implement getCaps");
    }

    @Before
    public void setUp() throws Exception {
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), getCaps());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected Path getResource(String fileName) throws URISyntaxException {
        URL refImgUrl = getClass().getClassLoader().getResource(fileName);
        return Paths.get(refImgUrl.toURI()).toFile().toPath();
    }

    protected String getReferenceImageB64(String fileName) throws URISyntaxException, IOException {
        Path refImgPath = getResource(fileName);
        return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgPath));
    }

}
