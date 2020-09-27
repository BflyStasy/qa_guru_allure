package qaguru.github;

import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static Data.ReadData.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.parameter;

@Feature("Работа с задачами")
public class IssueTest_listener {
    private static final String
            REPOSITORY = "BflyStasy/qa_guru_tests",
            NAME_ISSUE = "name_02",
            BODY_ISSUE = "test_02",
            USER = "BflyStasy";

    @BeforeEach
    public void initLogger()
    {
        SelenideLogger.addListener("allure", new LogEventListener() {
            @Override
            public void afterEvent(LogEvent currentLog) {
                System.out.printf("Start %s%n",currentLog.toString());
            }

            @Override
            public void beforeEvent(LogEvent currentLog) {
                System.out.printf("Stop %s%n",currentLog.toString());
            }
        });
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность создать Issue")
    public void testAddNewIssue1() {
        String login = loadProperty(LOGIN);
        String password = loadProperty(PASSWORD);

        parameter("Репозиторий", REPOSITORY);
        parameter("Название Issue", NAME_ISSUE);
        parameter("Имя пользователя на которого назначено Issue", USER);
            open("https://github.com");

            $(withText("Sign in")).click();
            $(byName("login")).click();
            $(byName("login")).setValue(login);
            $(byName("password")).click();
            $(byName("password")).setValue(password);
            $(byName("commit")).click();

            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();

            $(By.linkText(REPOSITORY)).click();

            $(By.xpath("//span[contains(text(),'Issues')]")).click();

            $(".d-md-block").click();
            $(".input-lg").click();
            $(".input-lg").setValue(NAME_ISSUE);
            $(By.id("issue_body")).click();
            $(By.id("issue_body")).setValue(BODY_ISSUE);
            $(".js-issue-assign-self").click();
            sleep(1000);
            $(withText("Submit new issue")).click();

            $(".js-issue-title").shouldHave(text(NAME_ISSUE));

            $(".js-hovercard-left").$(".assignee").$(".css-truncate-target").shouldHave(text(USER));
    }
}
