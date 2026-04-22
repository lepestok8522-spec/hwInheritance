import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testSimpleTaskMatchesWhenTitleContainsQuery() {// Проверяем, что метод возвращает true, когда query встречается в title
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        String query = "Позвонить";
        boolean expected = true;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSimpleTaskNotMatchesWhenTitleContainsQuery() {// Проверяем, что метод возвращает false, когда query НЕ встречается в title
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        String query = "Купить";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSimpleTaskMatchesWithEmptyQuery() {// Проверяем невозможность пустого запроса
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        String query = "";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSimpleTaskMatchesWithNullQuery() { // Проверяем невозможность null
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        String query = "null";
        boolean expected = false;
        boolean actual = task.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesWhenSubtasksContainsQuery() { //Проверяем, что метод находит query в одной из подзадач
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        String query = "Яйца";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesWhenMultipleSubtasksContainsQuery() { // Проверяем, что метод находит query в первой подзадаче
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        String query = "Молоко";
        boolean expected = true;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicDoesNotMatchWhenSubtasksDoNotContainQuery() { //Проверяем, что метод возвращает false, если ни одна подзадача не содержит query
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        String query = "Колбаса";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesWithEmptySubtasks() { // Проверяем граничный случай: пустой массив подзадач
        String[] subtasks = {};
        Epic epic = new Epic(55, subtasks);
        String query = "Хлеб";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testEpicMatchesWithEmptyQuery() { //Проверяем невозможность пустого запроса
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        String query = "";
        boolean expected = false;
        boolean actual = epic.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesWhenTopicContainsQuery() { //Проверяем поиск по полю topic
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        String query = "Выкатка";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesWhenProjectContainsQuery() { // Проверяем поиск по полю project
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        String query = "НетоБанка";
        boolean expected = true;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingDoesNotMatchWhenQueryNotFound() { //Проверяем, что метод возвращает false, если query нет ни в topic, ни в project
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        String query = "Погулять с собакой";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMeetingMatchesWithEmptyQuery() { //Проверяем невозможность пустого запроса
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        String query = "";
        boolean expected = false;
        boolean actual = meeting.matches(query);
        Assertions.assertEquals(expected, actual);
    }
}

