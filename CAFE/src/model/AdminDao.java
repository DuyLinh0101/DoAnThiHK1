
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
            rs=st.executeQuery("SELECT max(id) from ADMINS");
        while(rs.next()){
            row=rs.getInt(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return row+1;
    
}
    public boolean isAdminNameExist(String username){
       
        try {
            ps=con.prepareStatement("SELECT * FROM ADMINS UserName=?");
            ps.setString(1, username);
            rs=ps.executeQuery();
        if(rs.next()){
            return true;
        }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insert(Admin admin){
        String sql="insert into admin(UserID,UserName,UserPassword,UserMail) values(?,?,?,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1,admin.getID());
            ps.setString(2,admin.getUserName());
            ps.setString(3,admin.getPassWord());
            ps.setString(4,admin.getEmail());
            
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
           return false;
        }
        
    }
}
