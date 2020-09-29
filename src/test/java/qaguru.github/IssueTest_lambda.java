package qaguru.github;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static Data.ReadData.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;



@Owner("BflyStasy")
@Feature("Работа с задачами")
public class IssueTest_lambda {
    private static final String
            REPOSITORY = "BflyStasy/qa_guru_tests",
            NAME_ISSUE = "name_02",
            BODY_ISSUE = "test_02",
            USER = "BflyStasy";

    @Test
    @DisplayName("Пользователь должен иметь возможность создать Issue")
    public void testAddNewIssue2() {
        String login = loadProperty(LOGIN);
        String password = loadProperty(PASSWORD);

        parameter("Репозиторий", REPOSITORY);
        parameter("Название Issue", NAME_ISSUE);
        parameter("Имя пользователя на которого назначено Issue", USER);
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Авторизуемся", () -> {
            $(withText("Sign in")).click();
            $(byName("login")).click();
            $(byName("login")).setValue(login);
            $(byName("password")).click();
            $(byName("password")).setValue(password);
            $(byName("commit")).click();
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем страницу с задачами", () -> {
            $(By.xpath("//span[contains(text(),'Issues')]")).click();
        });
        step("Создаем новую задачу", () -> {
            $(".d-md-block").click();
            $(".input-lg").click();
            $(".input-lg").setValue(NAME_ISSUE);
            $(By.id("issue_body")).click();
            $(By.id("issue_body")).setValue(BODY_ISSUE);
            $(".js-issue-assign-self").click();
            $(".js-hovercard-left .assignee .css-truncate-target").shouldHave(text(USER));
            $(withText("Submit new issue")).click();
        });

        step("Проверяем имя созданной задачи", () -> {
            $(".js-issue-title").shouldHave(text(NAME_ISSUE));
        });

        step("Проверяем на кого назначена задача",() -> {
            $(".js-hovercard-left .assignee .css-truncate-target").shouldHave(text(USER));
        });


    }

    @AfterEach
    public void closeDriver() {
        closeWebDriver();
    }

}