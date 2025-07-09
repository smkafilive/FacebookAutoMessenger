import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalTime;

public class FacebookAutoMessenger {

    public static void main(String[] args) throws InterruptedException {

        // ✅ ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");

        // ✅ Message send time: 1:07 AM
        LocalTime targetTime = LocalTime.of(1, 7);
        System.out.println("🕒 অপেক্ষা করা হচ্ছে ১:০৭ AM পর্যন্ত...");

        // ✅ Time wait loop
        while (true) {
            LocalTime now = LocalTime.now();
            if (!now.isBefore(targetTime)) break;
            Thread.sleep(1000);
        }

        // ✅ Clean Profile setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // important for latest selenium
        options.addArguments("user-data-dir=C:/Users/HP/selenium-profile");  // নতুন clean profile path

        WebDriver driver = new ChromeDriver(options);

        try {
            // ✅ Open Chat
            driver.get("https://www.facebook.com/messages/t/md.mirajkhondokarchoyon");
            System.out.println("✅ ফেসবুক চ্যাট লোড হচ্ছে...");
            Thread.sleep(7000);  // পেজ লোডের সময়

            // ✅ Message Box select
            By messageBox = By.xpath("//div[@contenteditable='true' and @role='combobox']");
            driver.findElement(messageBox).sendKeys("হ্যালো চয়ন ভাই 🌟 এটা SM Kafi ভাইয়ের পক্ষ থেকে একটি অটো-ম্যাসেজ ✨");
            Thread.sleep(1000);
            driver.findElement(messageBox).sendKeys("\n");  // Enter key
            System.out.println("📨 ম্যাসেজ পাঠানো হয়েছে!");

        } catch (Exception e) {
            System.out.println("❌ কোনো সমস্যা হয়েছে:");
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
