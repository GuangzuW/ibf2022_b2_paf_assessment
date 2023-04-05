package ibf2022.paf.assessment.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7
@Service
public class TodoService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TaskRepository taskRepo;

    public Optional<User> findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public String insertUser(User user) throws TodoException {
        return userRepo.insertUser(user);
    }

    public Boolean userExists(String userName){
        return userRepo.findUserByUsername(userName).isPresent();
    }

    @Transactional(rollbackFor = TodoException.class)
    public int upsertTask(Task task) throws TodoException {
        if(!userExists(task.getUserId())){
            User tUser = new User();
            tUser.setUsername(task.getUserId());
            userRepo.insertUser(tUser);
        }
       int count = taskRepo.insertTask(task);
       return count;
    }

}
