package manageTask;

import java.time.LocalDate;

public class Task {
	private int id;
    private String summary, description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;

    public Task(int id, String summary, String description, LocalDate dueDate, Priority priority, Status status) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public String getSummary() {
        return summary;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public Status getStatus() {
        return status;
    }
    public void printTaskElements() {
        System.out.println("ID：" + id);
        System.out.println("要約：" + summary);
        System.out.println("説明：" + description);
        System.out.println("期限：" + dueDate);
        System.out.println("優先度：" + priority);
        System.out.println("ステータス：" + status);
    }
    public void setSummary(String summary) { this.summary = summary; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setStatus(Status status) { this.status = status; }
}
