import java.util.Scanner;

class Account {
    String name;
    int accNo;
    Account next, prev;
    double balance;

    Account(String n, int a, double b) {
        name = n;
        accNo = a;
        balance = b;
        next = prev = null;
    }
}

class Bank {
    Account head;

    void addAccount(String name, int accNo, double balance) {
        Account newAcc = new Account(name, accNo, balance);
        if (head == null) {
            head = newAcc;
        } else {
            Account temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newAcc;
            newAcc.prev = temp;
        }
        System.out.println("Account added: " + name);
    }

    void viewAccounts() {
        if (head == null) {
            System.out.println("No accounts.");
            return;
        }
        Account temp = head;
        while (temp != null) {
            System.out.println(temp.name + " | Acc#: " + temp.accNo + " | Balance: $" + temp.balance);
            temp = temp.next;
        }
    }

    void deleteAccount(int accNo) {
        Account temp = head;
        while (temp != null && temp.accNo != accNo) temp = temp.next;
        if (temp == null) {
            System.out.println("Account not found.");
            return;
        }
        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else {
            if (temp.prev != null) temp.prev.next = temp.next;
            if (temp.next != null) temp.next.prev = temp.prev;
        }
        System.out.println("Deleted account: " + accNo);
    }
}

public class SimpleBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n1. Add Account\n2. View Accounts\n3. Delete Account\n4. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Account No: ");
                    int accNo = sc.nextInt();
                    System.out.print("Balance: ");
                    double bal = sc.nextDouble();
                    sc.nextLine();
                    bank.addAccount(name, accNo, bal);
                    break;
                case 2:
                    bank.viewAccounts();
                    break;
                case 3:
                    System.out.print("Account No to delete: ");
                    int delAcc = sc.nextInt();
                    sc.nextLine();
                    bank.deleteAccount(delAcc);
                    break;
                case 4:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}
