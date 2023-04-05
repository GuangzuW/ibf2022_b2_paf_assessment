package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.services.TodoException;

// TODO: Task 6
@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate template;

    public static final String SQL_INSERT_TASK = """
        insert into task (description, priority, due_date, user_id,username) values (?,?,?,?,?)
            """;

    public int insertTask(Task task) throws TodoException {

        int number = template.update(SQL_INSERT_TASK,task.getDescription(), task.getPriority(),
                task.getDueDate(), task.getUserId(),task.getUserName());

        if (number > 0) {
            return number;
        } else {
            throw new TodoException("Task not inserted");
        }
    }
}
