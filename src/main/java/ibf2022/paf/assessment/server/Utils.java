package ibf2022.paf.assessment.server;

import ibf2022.paf.assessment.server.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoException;


public class Utils {
    
    @Autowired
    private UserRepository userRepo;



    public Utils() {
    }

    public static User toUser(SqlRowSet rs){
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        user.setName(rs.getString("name"));
        return user;
    }

    // private int taskId;
    // private String description;
    // private int priority;
    // private Date dueDate;
    // private String userId;

    public static List<Task> toTask(MultiValueMap<String, String> payload) throws TodoException {
        List<Task> tasks = new ArrayList<>();
        String username = payload.getFirst("username").toString();
        Task t;
        int index = 0;
        while ((index * 3 + 2) < payload.size()) {
            t = new Task();
            t.setDescription(payload.getFirst("description-%d".formatted(index)).toString());
            t.setPriority(Integer.parseInt(payload.getFirst("priority-%d".formatted(index)).toString()));
            LocalDate dueDate = LocalDate.parse(payload.getFirst("dueDate-%d".formatted(index)).toString());
            t.setDueDate(dueDate);
            t.setUserName(username);
            tasks.add(t);
            index++;
        }
    
        return tasks;
    }

    // public static List<Task> toTask(String jsonStr){
    //     List<Task> task = new ArrayList<>();
    //     JsonArray jsonArray = Json.createReader(new StringReader(jsonStr)).readArray();
    //     for (int i = 0; i < jsonArray.size(); i++) {
    //         JsonObject json = jsonArray.getJsonObject(i);
    //         Task t = new Task();
    //         t.setTaskId(i);

    //         t.setDescription(json.getString("description-%d".formatted(i)));
    //         t.setPriority(json.getInt("priority-%d".formatted(i)));

    //         LocalDate dueDate = LocalDate.parse(json.getString("dueDate-%d".formatted(i)));
    //         t.setDueDate(dueDate);
    //         String username=json.getString("username");
    //         String user_id = userRepo.findUserByUsername(username).get().getUserId();
    //         t.setUserId(user_id);

    //         task.add(t);
    //     }
    //     return task;
    // }
}
