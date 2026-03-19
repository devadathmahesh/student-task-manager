public class Task {
    String title;
    String deadline;
    String priority;
    boolean completed;

    public Task(String title, String deadline, String priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false;
    }

    public void markComplete() {
        completed = true;
    }

    public String toString() {
        return title + " | " + deadline + " | " + priority + " | " + (completed ? "Done" : "Pending");
    }
}