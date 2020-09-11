package qaguru.github;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueTest {

    @Test
    public void shouldFindIssueByNumber() {
        open("https://github.com");
        $(withText("Sign in")).click();
        $(byName("login")).click();
        $(byName("login")).setValue("");
        $(byName("password")).click();
        $(byName("password")).setValue("");
        $(byName("commit")).click();
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("BflyStasy/qa_guru_tests");
        $(".header-search-input").submit();
        $(By.linkText("BflyStasy/qa_guru_tests")).click();
        $(withText("Issues")).click();
        $(withText("New issue")).click();


        $(".input-lg").click();
        $(".input-lg").setValue("01");
        $(".issue_body").click();

    }

}
