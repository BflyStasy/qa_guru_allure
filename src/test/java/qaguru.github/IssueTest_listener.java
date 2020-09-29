package qaguru.github;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static Data.ReadData.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static qaguru.github.NamedBy.css;
import static qaguru.github.NamedBy.named;

@Feature("Работа с задачами")
public class IssueTest_listener {
    private static final String
            REPOSITORY = "BflyStasy/qa_guru_tests",
            NAME_ISSUE = "name_01",
            BODY_ISSUE = "test_01",
            USER = "BflyStasy";

    @BeforeEach
    public void initLogger()
    {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
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

        $(named(withText("Sign in")).as("Sign in")).click();
        $(named(byName("login")).as("Поле login")).click();
        $(named(byName("login")).as("Поле login")).setValue(login);
        $(named(byName("password")).as("Поле password")).click();
        $(named(byName("password")).as("Поле password")).setValue(password);

        $(named(byName("commit")).as("Кнопка Sign in")).click();

        $(css(".header-search-input").as("Поисковая строка в заголовке")).click();
        $(css(".header-search-input").as("Поисковая строка в заголовке")).sendKeys(REPOSITORY);
        $(css(".header-search-input").as("Поисковая строка в заголовке")).submit();

        $(named(By.linkText(REPOSITORY)).as("Ссылка на репозиторий")).click();

        $(named(By.xpath("//span[contains(text(),'Issues')]")).as("Issues")).click();

        $(css(".d-md-block").as("Кнопка New Issue")).click();
        $(css(".input-lg").as("Заголовок для ISSUE")).click();
        $(css(".input-lg").as("Заголовок для ISSUE")).setValue(NAME_ISSUE);
        $(named(By.id("issue_body")).as("Тело ISSUE")).click();
        $(named(By.id("issue_body")).as("Тело ISSUE")).setValue(BODY_ISSUE);
        $(css(".js-issue-assign-self").as("Ссылка назначить себе")).click();

        $(css(".js-hovercard-left .assignee .css-truncate-target").as("поле Assignes")).shouldHave(text(USER));
        $(named(withText("Submit new issue")).as("Кнопка Submit new issue")).click();

        $(css(".js-issue-title").as("Имя Issue")).shouldHave(text(NAME_ISSUE));

        $(css(".js-hovercard-left .assignee .css-truncate-target").as("поле Assignes")).shouldHave(text(USER));
    }

    @AfterEach
    public void closeDriver() {
        closeWebDriver();
    }
}
