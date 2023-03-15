package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.index.Index;
import seedu.address.model.student.exceptions.DuplicateEntryException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniqueHomeworkList homeworkList = new UniqueHomeworkList();
    private final UniqueLessonsList lessonsList = new UniqueLessonsList();
    private final UniqueExamList examList = new UniqueExamList();

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Homework> homeworkList,
                   List<Lesson> lessonList, List<Exam> examList) {
        requireAllNonNull(name, phone, email, address, tags, homeworkList, lessonList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.homeworkList.setHomeworks(homeworkList);
        this.lessonsList.setLessons(lessonList);
        this.examList.setExams(examList);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return set of tags
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of homework
     */
    public ObservableList<Homework> getHomeworkList() {
        return homeworkList.asUnmodifiableObservableList();
    }

    /**
     * Returns an immutable lessons list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of lessons
     */
    public ObservableList<Lesson> getLessonsList() {
        return lessonsList.asUnmodifiableObservableList();
    }

    /**
     * Returns an immutable exam list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of exams
     */
    public ObservableList<Exam> getExamList() {
        return examList.asUnmodifiableObservableList();
    }

    //HOMEWORK########################################################################################
    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of completed homework
     */
    public ObservableList<Homework> getCompletedHomeworkList() {
        return homeworkList.asUnmodifiableObservableList().filtered(Homework::isCompleted);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     *
     * @return list of pending homework
     */
    public ObservableList<Homework> getPendingHomeworkList() {
        return homeworkList.asUnmodifiableObservableList().filtered(homework -> !homework.isCompleted());
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    public ObservableList<Homework> getFilteredHomeworkList(Predicate<Homework> predicate) {
        return homeworkList.asUnmodifiableObservableList().filtered(predicate);
    }

    /**
     * Adds a homework to the homework list.
     *
     * @param homework homework to be added
     */
    public void addHomework(Homework homework) {
        // check for duplicate homework
        for (Homework hw : this.homeworkList) {
            if (hw.equals(homework)) {
                return;
            }
        }

        this.homeworkList.add(homework);
    }

    /**
     * Returns a homework from the homework list.
     *
     * @param index index of homework to be returned
     * @return homework
     */
    public Homework getHomework(Index index) {
        return this.homeworkList.getHomework(index.getZeroBased());
    }

    /**
     * Deletes a homework from the homework list.
     *
     * @param index index of homework to be deleted
     */
    public void deleteHomework(Index index) {
        Homework homeworkToDelete = this.homeworkList.getHomework(index.getZeroBased());
        this.homeworkList.remove(homeworkToDelete);
    }

    /**
     * Marks a homework as done from the homework list.
     *
     * @param index index of homework to be marked as done
     */
    public void markHomeworkAsDone(Index index) {
        Homework homeworkToMarkAsDone = this.homeworkList.getHomework(index.getZeroBased());
        homeworkToMarkAsDone.markAsDone();
    }

    /**
     * Marks a homework as undone from the homework list.
     *
     * @param index index of homework to be marked as undone
     */
    public void markHomeworkAsUndone(Index index) {
        Homework homeworkToMarkAsUndone = this.homeworkList.getHomework(index.getZeroBased());
        homeworkToMarkAsUndone.markAsUndone();
    }

    /**
     * Returns the pie chart data for the homework list.
     *
     * @return pie chart data for the homework list
     */
    public ObservableList<PieChart.Data> getHomeworkPieChartData() {
        return homeworkList.getHomeworkPieChartData();
    }

    //LESSONS########################################################################################
    /**
     * Returns a homework from the homework list.
     *
     * @param index index of homework to be returned
     * @return homework
     */
    public Lesson getLesson(Index index) {
        return this.lessonsList.getLesson(index.getZeroBased());
    }

    /**
     * Deletes a homework from the homework list.
     *
     * @param index index of homework to be deleted
     */
    public void deleteLesson(Index index) {
        Lesson lessonToDelete = this.lessonsList.getLesson(index.getZeroBased());
        this.lessonsList.remove(lessonToDelete);
    }
    /**
     * Adds a lesson to the lesson list
     * @param lesson the lesson to be added
     */
    public void addLesson(Lesson lesson) {
        for (Lesson l : this.lessonsList) {
            if (l.equals(lesson)) {
                return;
            }
        }
        this.lessonsList.add(lesson);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    @SafeVarargs
    public final List<Lesson> getFilteredLessonsList(Predicate<Lesson>... predicates) {
        List<Lesson> filteredLessonsList = new ArrayList<>();

        for (Lesson l : lessonsList) {
            boolean allPredicatesMatch = true;
            for (Predicate<Lesson> predicate : predicates) {
                if (!predicate.test(l)) {
                    allPredicatesMatch = false;
                    break;
                }
            }
            if (allPredicatesMatch) {
                filteredLessonsList.add(l);
            }
        }

        return Collections.unmodifiableList(filteredLessonsList);
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    public ObservableList<Lesson> getPastLessonsList() {
        return lessonsList.getPastLessons();
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    public ObservableList<Lesson> getUpcomingLessonsList() {
        return lessonsList.getUpcomingLessons();
    }

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered homework
     */
    public boolean hasLesson() {
        return this.lessonsList.hasLesson();
    }

    //Exams########################################################################################

    /**
     * Returns an immutable assignment list, which throws {@code UnsupportedOperationException}
     *
     * @return list of pending homework
     */
    public List<Exam> getUpcomingExamList() {
        List<Exam> upcomingExamList = new ArrayList<>();

        // filter exam list for pending exams
        for (Exam exam : examList) {
            if (exam.getStatus().equals(Exam.ExamStatus.Upcoming)) {
                upcomingExamList.add(exam);
            }
        }
        return Collections.unmodifiableList(upcomingExamList);
    }

    /**
     * Returns an immutable exam list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return list of filtered exams
     */
    public List<Exam> getFilteredExamList(Predicate<Exam>... predicates) {
        List<Exam> filteredExamList = new ArrayList<>();

        for (Exam e : examList) {
            boolean allPredicatesMatch = true;
            for (Predicate<Exam> predicate : predicates) {
                if (!predicate.test(e)) {
                    allPredicatesMatch = false;
                    break;
                }
            }
            if (allPredicatesMatch) {
                filteredExamList.add(e);
            }
        }

        return Collections.unmodifiableList(filteredExamList);
    }

    /**
     * Adds an Exam to the exam list.
     *
     * @param exam Exam to be added
     * @throws DuplicateEntryException when exam already exists
     */
    public void addExam(Exam exam) {
        // check for duplicate homework
        if (examList.contains(exam)) {
            throw new DuplicateEntryException();
        }
        this.examList.add(exam);
    }

    /**
     * Returns an exam from the exam list.
     *
     * @param index index of exam to be returned
     * @return Exam
     */
    public Exam getExam(Index index) {
        return this.examList.getExam(index.getZeroBased());
    }

    /**
     * Deletes an exam from the exam list.
     *
     * @param index index of exam to be deleted
     */
    public void deleteExam(Index index) {
        Exam examToDelete = this.examList.getExam(index.getZeroBased());
        this.examList.remove(examToDelete);
    }

    //UTIL########################################################################################
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson other person to compare to
     * @return boolean
     */
    public boolean isSameStudent(Student otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getHomeworkList().equals(getHomeworkList())
                && otherPerson.getLessonsList().equals(getLessonsList());
    }

    /**
     * Returns a hash code for the person.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, homeworkList, lessonsList);
    }

    /**
     * Returns a string representation of the person.
     * Format: name; phone; email; address; tags; assignments
     *
     * @return string representation of the person
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Tags: ");
        getTags().forEach(builder::append);
        builder.append("; Homework: ");
        getHomeworkList().forEach(builder::append);
        builder.append("; Lessons: ");
        getLessonsList().forEach(builder::append);

        return builder.toString();
    }
}
