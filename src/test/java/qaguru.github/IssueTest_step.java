package qaguru.github;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Data.ReadData.*;
import static io.qameta.allure.Allure.parameter;

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
    public void addNewIssue_3() {
        String login = loadProperty(LOGIN);
        String password = loadProperty(PASSWORD);

        parameter("Репозиторий", REPOSITORY);
        parameter("Название Issue", NAME_ISSUE);
        parameter("Имя пользователя на которого назначено Issue", USER);

        steps.openMainPage();
        steps.authorization(login, password);
        steps.searchRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuesPage();
        steps.addNewIssue(NAME_ISSUE, BODY_ISSUE);
        steps.shouldSeeNameIssue(NAME_ISSUE);
        steps.shouldSeeAssignToIssue(USER);

    }
}
