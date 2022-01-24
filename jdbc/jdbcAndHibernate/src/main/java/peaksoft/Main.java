package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        // реализуйте алгоритм здесь
        UserDaoJdbcImpl userDaoJdbc1 = new UserDaoJdbcImpl();

        while (true) {
            print();
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

    public static void print() {
        System.out.println("\nНажмите 1 чтобы создать таблицу");
        System.out.println("Нажмите 2 чтобы удалить таблицу");
        System.out.println("Нажмите 3 чтобы заполнить таблицу");
        System.out.println("Нажмите 4 чтобы удалить польвователя по ID");
        System.out.println("Нажмите 5 чтобы получить всех ползователей");
        System.out.println("Нажмите 6 чтобы cleaning table");
        System.out.println();
    }
}
