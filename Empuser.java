import java.sql.*;
import java.util.Scanner;

public class Empuser {
    public static void empDetails(){
         String url="jdbc:mysql://localhost:3306/companydb";
        String user="root";
        String password="root";
    try (Connection c = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in))
              {
                    PreparedStatement p = c.prepareStatement("insert INTO emp1 values (?, ?,?,?,?)");
                    System.out.print("Enter employe ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter employe Name: ");
                    String emp_name = sc.nextLine();

                    System.out.println("Enter employe jobrole:");
                    String Emp_jobrole=sc.nextLine();

                    System.out.println("enter hire date:");
                    String hire_date=sc.nextLine();

                    System.out.println("enter employe salary:");
                    int salary=sc.nextInt();
                    sc.nextLine();
                    p.setInt(1,id);
                    p.setString(2,emp_name);
                    p.setString(3,Emp_jobrole);
                    p.setString(4, hire_date);
                    p.setInt(5,salary);


                    int rows=p.executeUpdate();
                    System.out.println(rows +"successfully inserted");

                }
                 catch(SQLException e){
            e.printStackTrace();
                    }
                
    }
    public static void getDetails(){
        String url="jdbc:mysql://localhost:3306/companydb";
        String user="root";
        String password="root";
        try(Connection conn=DriverManager.getConnection(url, user, password))
        {
            
            Statement stmt=conn.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
            
         
            ResultSet rs=stmt.executeQuery("select * from emp1");
            while(rs.next())
            {
             System.out.println("id:"+rs.getInt("id")+
             ",name:"+rs.getString("emp_name")+
             ",job role:"+rs.getString("emp_jobrole")+
             ",hire_date:"+rs.getString("hire_date")+
             ",salary:"+rs.getInt("salary")

             );   
            } 
         
        }
                 catch(SQLException e){
            e.printStackTrace();
                    }
                
    }

    public static void displayCol(){
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String password = "root";
        try(Connection c=DriverManager.getConnection(url,user,password);
    Scanner sc = new Scanner(System.in))
        {
            Statement stmt=c.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
            System.out.println("enter column name to display:");
            String colname=sc.nextLine();
            ResultSet rs=stmt.executeQuery("select "+colname+" from emp1");
            while(rs.next())
            {
             System.out.println(colname+":"+rs.getInt(colname));   
            }

            
        }
     catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void update() {
    String url = "jdbc:mysql://localhost:3306/companydb";
    String user = "root";
    String password = "root";

    try (Connection c = DriverManager.getConnection(url, user, password);
         Scanner sc = new Scanner(System.in)) {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Salary: ");
        int salary = sc.nextInt();

        PreparedStatement p = c.prepareStatement(
                "UPDATE emp1 SET salary = ? WHERE id = ?");

        p.setInt(1, salary);
        p.setInt(2, id);

        int rows = p.executeUpdate();

        if (rows > 0)
            System.out.println("Salary updated successfully");
        else
            System.out.println("Employee ID not found");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void delete() {
    String url = "jdbc:mysql://localhost:3306/companydb";
    String user = "root";
    String password = "root";

    try (Connection c = DriverManager.getConnection(url, user, password);
         Scanner sc = new Scanner(System.in)) {

        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        PreparedStatement p = c.prepareStatement(
                "DELETE FROM emp1 WHERE emp_id = ?");

        p.setInt(1, id);

        int rows = p.executeUpdate();

        if (rows > 0)
            System.out.println("Record deleted successfully");
        else
            System.out.println("Employee ID not found");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String password = "root";
        try(Connection c = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connection built successfully");
            System.out.println("-----menu------");
            System.out.println("1.add emp details");
            System.out.println("2.display details");
            System.out.println("3.display column");
            System.out.println("4.update salary");
            
            System.out.println("5.delete employe");
            
            
            System.out.println("enter your choice: ");
            int choice=sc.nextInt();
            switch (choice) {
                case 1:empDetails();
                    
                    break;
                case 2:getDetails();
                break;
                case 3:displayCol();
                break;
                case 4:
                    update();
                    break;
                case 5:
                    delete();
                    break;
                default:
                    System.out.println("not found");
                
            }
            }
            catch(SQLException e){
            e.printStackTrace();
                    }

    
    }
}