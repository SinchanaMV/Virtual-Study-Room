import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserAuth {
    static final Statement DatabaseConnection = null;

    public static boolean registerUser(String username, String email, String password, String string) {
        try {
            String query = string;
            PreparedStatement pstmt = DatabaseConnection.conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public static boolean loginUser(String email, String password) {
        try {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = DatabaseConnection.conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to Register, 2 to Login:");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter Username:");
            String username = scanner.nextLine();
            System.out.println("Enter Email:");
            String email = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();

            if (registerUser(username, email, password,
                    "INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {
                System.out.println("Registration Successful!");
            } else {
                System.out.println("Registration Failed!");
            }
        } else if (choice == 2) {
            System.out.println("Enter Email:");
            String email = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();

            if (loginUser(email, password)) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Invalid Credentials!");
            }
        }
        scanner.close();
    }

    /**
     *
     */
    static Connection conn = DatabaseConnection.getConnection();

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        UserAuth.conn = conn;
    }
}
