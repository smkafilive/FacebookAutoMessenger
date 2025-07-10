import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FacebookAutoMessenger {

    public static void main(String[] args) throws Exception {


        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");


        Scanner scanner = new Scanner(System.in);
        System.out.print("🕒 Enter time to send message (HH:mm): ");
        String timeInput = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime targetTime = LocalTime.parse(timeInput, formatter);

        System.out.print("💬 Enter your message: ");
        String message = scanner.nextLine();

        System.out.print("🔁 How many times to send? ");
        int repeat = scanner.nextInt();
        scanner.nextLine(); //  newline


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-data-dir=C:/Users/HP/AppData/Local/Google/Chrome/User Data/AutoMessageProfile");
        options.addArguments("profile-directory=Default");


        System.out.println("⌛ Waiting for exact time: " + targetTime);
        while (LocalTime.now().isBefore(targetTime)) {
            Thread.sleep(1000);
        }

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.get("https://www.facebook.com/messages/t/md.mirajkhondokarchoyon");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement messageBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Message']"))
            );


            for (int i = 1; i <= repeat; i++) {
                System.out.println("📨 Sending message " + i + " of " + repeat);
                for (char c : message.toCharArray()) {
                    messageBox.sendKeys(Character.toString(c));
                    Thread.sleep(100);
                }
                messageBox.sendKeys(Keys.ENTER);
                Thread.sleep(1500);
            }

            System.out.println("✅ All messages sent successfully!");

        } catch (Exception e) {
            System.out.println("❌ Something went wrong:");
            e.printStackTrace();
        } finally {
            Thread.sleep(3000); 
            driver.quit();
        }
    }
}
