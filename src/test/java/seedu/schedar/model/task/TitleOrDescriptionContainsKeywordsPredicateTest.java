package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.schedar.testutil.ToDoBuilder;

public class TitleOrDescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleOrDescriptionContainsKeywordsPredicate firstPredicate =
                new TitleOrDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleOrDescriptionContainsKeywordsPredicate secondPredicate =
                new TitleOrDescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleOrDescriptionContainsKeywordsPredicate firstPredicateCopy =
                new TitleOrDescriptionContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleOrDescriptionContainsKeywordsPredicate predicate = new TitleOrDescriptionContainsKeywordsPredicate(
                Collections.singletonList("Lecture"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("Weekly Lecture").build()));

        // Multiple keywords
        predicate = new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList("CS2103", "Project"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));

        // Only one matching keyword
        predicate = new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList("CS2103", "Project"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Lecture").build()));

        // Mixed-case keywords
        predicate = new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList("cS2103", "PrOjEcT"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));

        // Keywords match description or title
        predicate = new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList("Project", "random", "Low"));
        assertTrue(predicate.test(new ToDoBuilder().withTitle("Lecture").withDescription("random")
                .withPriority("Low").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleOrDescriptionContainsKeywordsPredicate predicate =
                new TitleOrDescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ToDoBuilder().withTitle("Lecture").build()));

        // Non-matching keyword
        predicate = new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList("Lecture"));
        assertFalse(predicate.test(new ToDoBuilder().withTitle("CS2103 Project").build()));
    }
}
