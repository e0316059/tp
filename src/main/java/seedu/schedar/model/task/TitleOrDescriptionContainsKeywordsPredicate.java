package seedu.schedar.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.schedar.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Title} matches any of the keywords given.
 */
public class TitleOrDescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TitleOrDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword ->StringUtil
                        .containsWordIgnoreCase(task.getTitle().title
                                + " "
                                + task.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleOrDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleOrDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
