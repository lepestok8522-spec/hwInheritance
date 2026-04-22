import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {

        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsEmptyArrayWhenNoMatches() { //Проверяем, что при отсутствии совпадений возвращается пустой массив
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        Task[] expected = {};
        Task[] actual = todos.search("Погулять с собакой");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsMultipleMatches() { //Проверяем, что метод search может вернуть несколько задач
        // ДАНО: три SimpleTask, у двух в title есть "Купить"
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Купить хлеб");
        SimpleTask task3 = new SimpleTask(3, "Позвонить маме");

        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);
        Task[] expected = {task1, task2};
        Task[] actual = todos.search("Купить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchWithEmptyQueryReturnsEmptyArray() { //Проверяем защиту от пустого запроса в методе search
        SimpleTask task = new SimpleTask(5, "Купить молоко");
        Todos todos = new Todos();
        todos.add(task);
        Task[] expected = {};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchFindsEpicBySubtask() { //Проверяем, что поиск работает с Epic через подзадачи
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(epic);
        todos.add(task);
        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchFindsMeetingByTopic() { //Проверяем поиск в Meeting по полю topic
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchFindsMeetingByProject() { //Проверяем поиск в Meeting по полю project
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = new Todos();
        todos.add(meeting);
        Task[] expected = {meeting};
        Task[] actual = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected, actual);
    }
}






