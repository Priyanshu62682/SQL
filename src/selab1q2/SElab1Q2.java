/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selab1q2;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Pri_D
 */
public class SElab1Q2 {
    /**
     * @param args the command line arguments
     */
    public static void theQuery(String query,int n){
//      System.out.println(query);
      Connection con = null;
      Statement st = null;
      try{
          Class.forName("com.mysql.jdbc.Driver");      
          con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");
          st = con.createStatement();
           ResultSet rs=null;
          if(n==1||n==2||n==3)
                st.executeUpdate(query); 
//          System.out.println("Executed");
          if(n==4||n==5){
            rs = st.executeQuery(query);
            while(rs.next()){
                String id  = rs.getString("id");
                int age = rs.getInt("age");
                String mobile_number = rs.getString("mobile_number");
                float CGPA = rs.getFloat("CGPA");
                String name = rs.getString("name");
                String father_name = rs.getString("father_name");
                String address=rs.getString("address");
                System.out.println(id+" "+age+" "+mobile_number+" "+CGPA+" "+name+" "+father_name+" "+address);
            }
          }
      }catch(HeadlessException | ClassNotFoundException | SQLException ex){
          JOptionPane.showMessageDialog(null,ex.getMessage());
      }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        int age;
        Float CGPA;
        String id,name,address,mobile_number,father_name;
        System.out.println("Select 1 to insert, 2 to update, 3 to delete, 4 to search, 5 to show list");
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        if(n==1){
            System.out.println("Enter details");
            id  = sc.next();
            age = sc.nextInt();
            mobile_number = sc.next();
            CGPA = sc.nextFloat();
            name = sc.next();
            father_name = sc.next();
            address=sc.next();
            theQuery("insert into students values('"
                    +id+"',"+age+",'"+mobile_number+"',"+CGPA+","+"'"+name+"'"+","+"'"+father_name+"'"+","+"'"+address+"')",n);
        }
        if(n==2){
            System.out.println("Enter Id no. of student to be updated");
            String qid;
            qid=sc.next();
            System.out.println("Enter new details");
            id  = sc.next();
            age = sc.nextInt();
            mobile_number = sc.next();
            CGPA = sc.nextFloat();
            name = sc.next();
            father_name = sc.next();
            address=sc.next();
            theQuery("update students set id='"
                    +id+"',age="+age+",mobile_number='"+mobile_number+"',CGPA="+CGPA+","+"name='"+name+"',"+"father_name='"+father_name+"',"+"address='"+address
                    + "' where id='"+qid+"'",n);
        }
        if(n==3){
            System.out.println("Enter Id no. of student to be deleted");
            String qid=sc.next();
            theQuery("delete from students where id='"+qid+"'",n);
        }
        if(n==4){
            System.out.println("Enter Id no. of student to be searched");
            String qid=sc.next();
            theQuery("select * from students where id='"+qid+"'",n);
        }
        if(n==5){            
            theQuery("select * from students order by name DESC,age DESC",5);
        }
    }
}