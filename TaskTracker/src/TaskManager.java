import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final String storageFile = "tasks.txt";

    public TaskManager() {
        load();
    }

    public Task addTask(String description, String priority) {
        Task t = new Task(nextId++, description, priority.toUpperCase());
        tasks.add(t);
        save();
        return t;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean completeTask(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setCompleted(true);
                save();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) {
            save();
        }
        return removed;
    }

    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(storageFile))) {
            for (Task t : tasks) {
                pw.println(t.serialize());
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    private void load() {
        File f = new File(storageFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int maxId = 0;
            while ((line = br.readLine()) != null) {
                Task t = Task.deserialize(line);
                if (t != null) {
                    tasks.add(t);
                    if (t.getId() > maxId) {
                        maxId = t.getId();
                    }
                }
            }
            nextId = maxId + 1;
        } catch (IOException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
        }
    }
}
