import java.util.Scanner;
abstract class AbstractTaskList {
    abstract void addTask(String name, int priority);
    abstract void showTasks();
    abstract void deleteTask(String name);
}
class TaskNode {
    String taskName;
    int priority;
    TaskNode next;
    TaskNode prev;
    TaskNode(String name, int priority) {
        this.taskName = name;
        this.priority = priority;
        this.next = null;
        this.prev = null;
    }
}
class DoublyTaskList extends AbstractTaskList {
    private TaskNode head;
    public void addTask(String name, int priority) {
        TaskNode newNode = new TaskNode(name, priority);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        System.out.println("Task added: " + name + " with priority " + priority);
    }
    public void showTasks() {
        if (head == null) {
            System.out.println("No tasks to show.");
            return;
        }
        TaskNode temp = head;
        System.out.print("Tasks: ");
        while (temp != null) {
            System.out.print("[Task: \"" + temp.taskName + "\", Priority: " + temp.priority + "] <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void deleteTask(String name) {
        if (head == null) {
            System.out.println("No tasks to delete.");
            return;
        }
        TaskNode temp = head;
        while (temp != null && !temp.taskName.equals(name)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Task \"" + name + "\" not found.");
            return;
        }
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else {
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
        }
        System.out.println("Deleted task: " + name);
    }
}
public class TaskManagerMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyTaskList taskList = new DoublyTaskList();
        int choice;
        do {
            System.out.println("\n==== Task Manager Menu ====");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Delete task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();  // consume leftover newline
            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter task priority (integer): ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid priority. Please enter an integer:");
                        sc.next();
                    }
                    int priority = sc.nextInt();
                    sc.nextLine();  // consume newline
                    taskList.addTask(name, priority);
                    break;
                case 2:
                    taskList.showTasks();
                    break;
                case 3:
                    System.out.print("Enter the name of the task to delete: ");
                    String delName = sc.nextLine();
                    taskList.deleteTask(delName);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 4);
        sc.close();
    }
}
