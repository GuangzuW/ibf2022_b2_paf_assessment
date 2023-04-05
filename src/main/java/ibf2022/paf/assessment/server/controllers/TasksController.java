package ibf2022.paf.assessment.server.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.Utils;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoException;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8
@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/task")
    public ResponseEntity saveTasks(@RequestBody MultiValueMap<String, String> tasks, BindingResult result) throws TodoException {
        List<Task> taskArr = Utils.toTask(tasks);
        int count = 0;
        String username = taskArr.get(0).getUserName();
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new TodoException("User not found");
        }
        User user = userOptional.get();
        for (Task t : taskArr) {
            t.setUserId(user.getUserId());
            if (todoService.upsertTask(t) > 0) {
                count++;
            }
        }
    
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("taskCount", count);
        resultMap.put("username", username);
    
        if (count > 0) {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new ModelAndView("result", resultMap, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new ModelAndView("error", resultMap, HttpStatus.BAD_REQUEST));
        }
    }

    
}
