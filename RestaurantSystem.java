import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantSystem {

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Restaurant!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Item");
            System.out.println("2. Search Item");
            System.out.println("3. View Menu");
            System.out.println("4. Update Item");
            System.out.println("5. Order History");
            System.out.println("6. Delete Item");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem(restaurant, scanner);
                    break;
                case 2:
                    searchItem(restaurant, scanner);
                    break;
                case 3:
                    restaurant.displayMenu();
                    break;
                case 4:
                    updateItem(restaurant, scanner);
                    break;
                case 5:
                    viewOrderHistory(restaurant);
                    break;
                case 6:
                    deleteItem(restaurant, scanner);
                    break;
                case 7:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItem(Restaurant restaurant, Scanner scanner) {
        System.out.print("Enter item name: ");
        String itemName = scanner.next();
        System.out.print("Enter item price: ");
        double itemPrice = scanner.nextDouble();
        System.out.print("Enter item quantity: ");
        int itemQuantity = scanner.nextInt();
        restaurant.addToMenu(itemName, itemPrice, itemQuantity);
        System.out.println(itemQuantity + " " + itemName + "(s) added to the menu.");
    }

    private static void searchItem(Restaurant restaurant, Scanner scanner) {
        System.out.print("Enter item name to search: ");
        String itemName = scanner.next();
        MenuItem item = restaurant.searchItem(itemName);
        if (item != null) {
            System.out.println("Item found: " + item.getQuantity() + " " + item.getName() + "(s) - $" + item.getPrice() + " each");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void updateItem(Restaurant restaurant, Scanner scanner) {
        System.out.print("Enter item name to update: ");
        String itemName = scanner.next();
        MenuItem existingItem = restaurant.searchItem(itemName);

        if (existingItem != null) {
            System.out.print("Enter new item price: ");
            double newItemPrice = scanner.nextDouble();
            System.out.print("Enter new item quantity: ");
            int newItemQuantity = scanner.nextInt();
            restaurant.updateItem(itemName, newItemPrice, newItemQuantity);
            System.out.println(itemName + " updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void viewOrderHistory(Restaurant restaurant) {
        restaurant.displayOrders();
    }

    private static void deleteItem(Restaurant restaurant, Scanner scanner) {
        System.out.print("Enter item name to delete: ");
        String itemName = scanner.next();
        boolean deleted = restaurant.deleteItem(itemName);

        if (deleted) {
            System.out.println(itemName + " deleted successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }
}

class Restaurant {
    private List<MenuItem> menu;
    private List<String> orderHistory;

    public Restaurant() {
        this.menu = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public void addToMenu(String itemName, double itemPrice, int itemQuantity) {
        MenuItem newItem = new MenuItem(itemName, itemPrice, itemQuantity);
        menu.add(newItem);
    }

    public MenuItem searchItem(String itemName) {
        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void updateItem(String itemName, double newItemPrice, int newItemQuantity) {
        MenuItem item = searchItem(itemName);
        if (item != null) {
            item.setPrice(newItemPrice);
            item.setQuantity(newItemQuantity);
        }
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        for (MenuItem item : menu) {
            System.out.println(item.getQuantity() + " " + item.getName() + "(s) - $" + item.getPrice() + " each");
        }
    }

    public void displayOrders() {
        System.out.println("\nOrder History:");
        for (String order : orderHistory) {
            System.out.println(order);
        }
    }

    public boolean deleteItem(String itemName) {
        MenuItem item = searchItem(itemName);
        if (item != null) {
            menu.remove(item);
            return true;
        }
        return false;
    }
}

class MenuItem {
    private String name;
    private double price;
    private int quantity;

    public MenuItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
