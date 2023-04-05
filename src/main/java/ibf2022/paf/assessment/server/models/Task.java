package ibf2022.paf.assessment.server.models;

import java.time.LocalDate;

// TODO: Task 4

public class Task {
    private int taskId;
    private String description;
    private int priority;
    private LocalDate dueDate;
    private String userId;
    private String userName;

    public Task() { }

    public Task(int taskId, String description, int priority, LocalDate dueDate, String userId, String userName) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.userId = userId;
        this.userName = userName;
    }
    public int getTaskId() {   return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public String getDescription() {  return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) {  this.priority = priority; }

    public LocalDate getDueDate() { return dueDate;  }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getUserId() {  return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserName() {  return userName;}
    public void setUserName(String userName) { this.userName = userName; }
    
}
