package qaguru.github;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Owner("BflyStasy")
@Feature("Работа с задачами")

public class IssueTest_step {

    private static final String
            REPOSITORY = "BflyStasy/qa_guru_tests",
            NAME_ISSUE = "name_03",
            BODY_ISSUE = "test_03",
            USER = "BflyStasy";

    private final BasicSteps steps = new BasicSteps();

    @Test
    @DisplayName("Пользователь должен иметь возможность создать Issue")
    public void testAddNewIssue3() {

/*
        String login = loadProperty(LOGIN);
        String password = loadProperty(PASSWORD);
*/
        steps.openMainPage();
        steps.authorization();
        steps.searchRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuesPage();
        steps.addNewIssue(NAME_ISSUE, BODY_ISSUE);
        steps.shouldSeeNameIssue(NAME_ISSUE);
        steps.shouldSeeAssignToIssue(USER);

    }

    @AfterEach
    public void closeDriver() {
        closeWebDriver();
    }
}
