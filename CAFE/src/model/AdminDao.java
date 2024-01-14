
package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDao {
    Connection con =MyConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    Statement st;
    
    public int getMaxRowAdminTable(){
        int row=0;
        
        try {
            st=con.createStatement();
            rs=st.executeQuery("SELECT max(UserID) from ADMINS");
        while(rs.next()){
            row=rs.getInt(1);
        }
        } catch (Exception ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return row+1;
    
}
    public boolean isAdminNameExist(String username){
       
        try {
            ps=con.prepareStatement("SELECT * FROM ADMINS WHERE UserName = ?");
            ps.setString(1, username);
            rs=ps.executeQuery();
        if(rs.next()){
            return true;
        }
        } catch (Exception ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insert(Admin admin){
        String sql="insert into ADMINS(UserName,UserPassword,UserMail) values(?,?,?)";
        try {
            System.out.println("Executing SQL:"+sql);
            ps=con.prepareStatement(sql);
            ps.setString(1,admin.getUserName());
            ps.setString(2,admin.getPassWord());
            ps.setString(3,admin.getEmail());
            
            if(ps.executeUpdate()>0){
                System.out.println("Insert successfull");
                return true;
            }else{
                System.out.println("Insert failed");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
           return false;
        }
        
    }
}
