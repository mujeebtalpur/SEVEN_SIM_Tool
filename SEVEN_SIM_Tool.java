import java.util.InputMismatchException;
import java.util.Scanner;

class Node {
    int ticketID;
    int vID;
    int vDate;
    int FID;
    String vName;
    Node next;

    Node() {
        // Default constructor
    }

    Node(int ticketID, int vID, int vDate, int FID, String vName) {
        this.ticketID = ticketID;
        this.vID = vID;
        this.vDate = vDate;
        this.FID = FID;
        this.vName = vName;
    }

    @Override
    public String toString() {
        return ticketID + " " + vID + " " + vDate + " " + FID + " " + vName;
    }
}

class List {
    Node head;
    int size;

    List() {
        head = null;
        size = 0;
    }

    public void insert(Node newNode) {
        if (head == null || newNode.vDate < head.vDate) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.vDate < newNode.vDate) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public void remove(int vID) {
    if (head == null) {
        System.out.println("Record Not Found for " + vID);
        return;
    }

    if (head.vID == vID) {
        head = head.next;
        size--;
        return;
    }

    Node current = head;
    Node previous = null;

    while (current != null && current.vID != vID) {
        previous = current;
        current = current.next;
    }

    if (current != null) {
        previous.next = current.next;
        size--;
    } else {
        System.out.println("Record Not Found for " + vID);
    }
}

    public int cost(int vID) {
        int totalCost = 0;
        Node current = head;

        while (current != null) {
            if (current.vID == vID) {
                totalCost += calculateCost(current.FID, current.vDate);
            }
            current = current.next;
        }

        return totalCost;
    }

    public void print1(int vID) {
        Node current = head;
        while (current != null) {
            if (current.vID == vID) {
                System.out.println(current.toString());
            }
            current = current.next;
        }
    }

    public void print2(int vDate) {
        Node current = head;
        while (current != null) {
            if (current.vDate == vDate) {
                System.out.println(current.toString());
            }
            current = current.next;
        }
    }

    public void print3(int FID) {
        Node current = head;
        while (current != null) {
            if (current.FID == FID) {
                System.out.println(current.toString());
            }
            current = current.next;
        }
    }

    public void print4(int ticketID) {
        Node current = head;
        while (current != null) {
            if (current.ticketID == ticketID) {
                System.out.println(current.toString());
                return;
            }
            current = current.next;
        }
    }

    private int calculateCost(int FID, int vDate) {
        int weekdayPrice = 0;
        int weekendPrice = 0;

        switch (FID) {
            case 1:
                weekdayPrice = 200;
                weekendPrice = 250;
                break;
            case 2:
                weekdayPrice = 100;
                weekendPrice = 150;
                break;
            case 3:
                weekdayPrice = 90;
                weekendPrice = 120;
                break;
            case 4:
                weekdayPrice = 200;
                weekendPrice = 250;
                break;
            case 5:
                weekdayPrice = 100;
                weekendPrice = 120;
                break;
            case 6:
                weekdayPrice = 100;
                weekendPrice = 150;
                break;
        }

        if (vDate == 15) {
            return (int) (weekdayPrice * 0.8); // 20% discount on the 15th of the month.
        } else if (vDate % 7 == 0 || vDate % 7 == 6) {
            return weekendPrice;
        } else {
            return weekdayPrice;
        }
    }
}

public class SEVEN_SIM_Tool {
    public static void main(String[] args) {
        List list = new List();
        Scanner scanner = new Scanner(System.in);

        System.out.println("SEVEN SIM Tool - Enter commands:");
        System.out.println("1 - Insert");
        System.out.println("2 - Remove");
        System.out.println("3 - Compute Cost");
        System.out.println("4 - Print by vID");
        System.out.println("5 - Print by vDate");
        System.out.println("6 - Print by FID");
        System.out.println("Enter -1 to exit.");

        while (scanner.hasNext()) {
            try {
                int command = scanner.nextInt();

                if (command == -1) {
                    // Exit command
                    break;
                }

                if (command == 1) {
                    // Insert command
					System.out.println("input format. Please use 'ticketID, vID, vDate, FID, vName'.");
                    scanner.nextLine(); // Consume the newline character
                    String data = scanner.nextLine();
                    String[] parts = data.split(",");
                    if (parts.length == 5) {
                        int ticketID = Integer.parseInt(parts[0]);
                        int vID = Integer.parseInt(parts[1]);
                        int vDate = Integer.parseInt(parts[2]);
                        int FID = Integer.parseInt(parts[3]);
                        String vName = parts[4];
                        Node newNode = new Node(ticketID, vID, vDate, FID, vName);
                        list.insert(newNode);
                    } else {
                        System.out.println("Invalid input format. Please use 'ticketID, vID, vDate, FID, vName'.");
                    }
                } else if (command == 2) {
                    // Remove command
                    int vID = scanner.nextInt();
                    list.remove(vID);
                } else if (command == 3) {
                    // Cost command
                    int vID = scanner.nextInt();
                    int totalCost = list.cost(vID);
                    System.out.println("Total Cost: " + totalCost);
                } else if (command == 4) {
                    // Print by vID
                    int vID = scanner.nextInt();
                    list.print1(vID);
                } else if (command == 5) {
                    // Print by vDate
                    int vDate = scanner.nextInt();
                    list.print2(vDate);
                } else if (command == 6) {
                    // Print by FID
                    int FID = scanner.nextInt();
                    list.print3(FID);
                } else {
                    System.out.println("Invalid command. Please use 1, 2, 3, 4, 5, or 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid numeric values.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("SEVEN SIM Tool exited.");
    }
}