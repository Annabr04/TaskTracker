import java.util.List;
import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n=== TaskTracker ===");
            System.out.println("1. List tasks");
            System.out.println("2. Add task");
            System.out.println("3. Complete task");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listTasks(manager);
                    break;
                case "2":
                    addTask(scanner, manager);
                    break;
                case "3":
                    completeTask(scanner, manager);
                    break;
                case "4":
                    deleteTask(scanner, manager);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void listTasks(TaskManager manager) {
        List<Task> tasks = manager.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    private static void addTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Priority (LOW, MEDIUM, HIGH): ");
        String prio = scanner.nextLine();
        if (prio.isBlank()) prio = "MEDIUM";
        Task t = manager.addTask(desc, prio);
        System.out.println("Added: " + t);
    }

    private static void completeTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task id to complete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (manager.completeTask(id)) {
            System.out.println("Marked task as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteTask(Scanner scanner, TaskManager manager) {
        System.out.print("Enter task id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (manager.deleteTask(id)) {
            System.out.println("Deleted task.");
        } else {
            System.out.println("Task not found.");
        }
    }
}
