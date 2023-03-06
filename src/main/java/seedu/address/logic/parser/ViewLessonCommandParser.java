package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.ViewLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.LessonBelongsToDatePredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new ViewHomeworkCommand object
 */
public class ViewLessonCommandParser implements Parser<ViewLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewLessonCommand
     * and returns a ViewLessonCommand object for execution.
     * @param args the user input to be parsed into a ViewLessonCommand object.
     * @return a ViewLessonCommand object.
     */
    public ViewLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE);
        Predicate<Student> namePredicate;
        boolean defaultPredicateFlag;

        // If name is present, create a predicate to filter by name
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
            // for all the names, trim the name and only take the first word
            for (int i = 0; i < nameKeywords.size(); i++) {
                String name = nameKeywords.get(i);
                name = name.trim();
                int spaceIndex = name.indexOf(" ");
                if (spaceIndex != -1) {
                    name = name.substring(0, spaceIndex);
                }
                nameKeywords.set(i, name);
            }

            namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
            defaultPredicateFlag = false;
        } else {
            namePredicate = PREDICATE_SHOW_ALL_STUDENTS;
            defaultPredicateFlag = true;
        }

        // If status is present, create a predicate to filter by status
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String date = argMultimap.getValue(PREFIX_DATE).get();
            LocalDate targetDate = ParserUtil.parseDate(date);
            LessonBelongsToDatePredicate datePredicate = new LessonBelongsToDatePredicate(targetDate);
            return new ViewLessonCommand(namePredicate, datePredicate, defaultPredicateFlag);
        } else {
            return new ViewLessonCommand(namePredicate, defaultPredicateFlag);
        }
    }

}