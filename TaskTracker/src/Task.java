public class Task {
    private int id;
    private String description;
    private String priority; // LOW, MEDIUM, HIGH
    private boolean completed;

    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String serialize() {
        // Simple pipe-separated format: id|completed|priority|description
        return id + "|" + completed + "|" + priority + "|" + description.replace("|", "/");
    }

    public static Task deserialize(String line) {
        String[] parts = line.split("\|", 4);
        if (parts.length < 4) return null;
        int id = Integer.parseInt(parts[0]);
        boolean completed = Boolean.parseBoolean(parts[1]);
        String priority = parts[2];
        String description = parts[3].replace("/", "|");
        Task t = new Task(id, description, priority);
        t.setCompleted(completed);
        return t;
    }

    @Override
    public String toString() {
        return String.format("[%d] (%s) %s %s",
                id,
                priority,
                description,
                completed ? "[DONE]" : "");
    }
}
