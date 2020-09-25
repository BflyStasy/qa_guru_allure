package qaguru.github;

import io.qameta.allure.Step;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class BasicSteps {

    private static final String
            BASE_URL ="https://github.com" ;
    @Step("Открываем главную страницу")
    public void openMainPage()
    {
        open(BASE_URL);
    }

    @Step("Авторизуемся")
    public void authorization(final String login, final String password)
    {
        $(withText("Sign in")).click();
        $(byName("login")).click();
        $(byName("login")).setValue(login);
        $(byName("password")).click();
        $(byName("password")).setValue(password);
        $(byName("commit")).click();

    }

    @Step("Ищем репозиторий")
    public void searchRepository(final String name)
    {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
    }

    @Step("Переходим по ссылке репозитория")
    public void openRepository(final String name)
    {
        $(By.linkText(name)).click();
    }

    @Step("Открываем страницу с задачами")
    public void openIssuesPage()
    {
        $(By.xpath("//span[contains(text(),'Issues')]")).click();
    }

    @Step("Создаем новую задачу")
    public void addNewIssue(final String name, final String body)
    {
        $(".d-md-block").click();
        $(".input-lg").click();
        $(".input-lg").setValue(name);
        $(By.id("issue_body")).click();
        $(By.id("issue_body")).setValue(body);
        $(".js-issue-assign-self").click();
        //$(withText("assign yourself")).click();
        sleep(1000);
        $(withText("Submit new issue")).click();
    }

    @Step("Проверяем имя созданной задачи")
    public void shouldSeeNameIssue(final String name)
    {
        $(".js-issue-title").shouldHave(text(name));
    }

    @Step("Проверяем на кого назначена задача")
    public void shouldSeeAssignToIssue(final String user)
    {
        $(".js-hovercard-left").$(".assignee").$(".css-truncate-target").shouldHave(text(user));
    }
}
