import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks(); // load saved tasks

        while(true) {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) addTask();
            else if(choice == 2) viewTasks();
            else if(choice == 3) markComplete();
            else if(choice == 4) deleteTask();
            else if(choice == 5) {
                saveTasks();
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter deadline: ");
        String deadline = sc.nextLine();

        System.out.print("Enter priority: ");
        String priority = sc.nextLine();

        tasks.add(new Task(title, deadline, priority));
        saveTasks();
        System.out.println("Task added!");
    }

    static void viewTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int i = 1;
        for(Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    static void markComplete() {
        viewTasks();
        if(tasks.isEmpty()) return;

        System.out.print("Enter task number to mark complete: ");
        int index = sc.nextInt();

        if(index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).markComplete();
            saveTasks();
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    static void deleteTask() {
        viewTasks();
        if(tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt();

        if(index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            saveTasks();
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid task number!");
        }
    }

    static void saveTasks() {
        try {
            FileWriter fw = new FileWriter("tasks.txt");
            for(Task t : tasks) {
                fw.write(t.title + "," + t.deadline + "," + t.priority + "," + t.completed + "\n");
            }
            fw.close();
        } catch(IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void loadTasks() {
        try {
            File file = new File("tasks.txt");
            if(!file.exists()) return;

            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                Task t = new Task(parts[0], parts[1], parts[2]);
                t.completed = Boolean.parseBoolean(parts[3]);

                tasks.add(t);
            }

            fileScanner.close();
        } catch(Exception e) {
            System.out.println("Error loading tasks.");
        }
    }
}