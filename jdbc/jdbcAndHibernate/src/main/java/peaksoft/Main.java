package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        // реализуйте алгоритм здесь
        while (true) {
            UserService userService = new UserServiceImpl();
            int a = scanner.nextInt();
            String b = scanner.nextLine();
            if (a == 1) {
                userService.createUsersTable();
            } else if (a == 2) {
                userService.dropUsersTable();
            } else if (a == 3) {
                System.out.println("add to Database");
                System.out.println("Name:");
                String name = scanner.nextLine();
                System.out.println("Last_name:");
                String lastName = scanner.nextLine();
                System.out.println("Age:");
                byte age = (byte) scanner.nextInt();
                userService.saveUser(name, lastName, age);
            } else if (a == 4) {
                System.out.println(" which id want to delete?");
                long id = scanner.nextByte();
                userService.removeUserById(id);
                System.out.println(" successfully deleted ");
            } else if (a == 5) {
                System.out.println(userService.getAllUsers());
            } else if (a == 6) {
                userService.cleanUsersTable();
            }
        }
    }
}
