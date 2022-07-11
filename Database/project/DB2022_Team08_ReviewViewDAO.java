import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����*/


public class DB2022_Team08_ReviewViewDAO {

private static Connection conn;
	   
    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;
   
    public DB2022_Team08_ReviewViewDAO() {
		// TODO Auto-generated constructor stub
    }
    
	private void getConnection() throws ClassNotFoundException, SQLException{ //����ó��
	        if(conn == null){ 
	            //��������
	            String url = "jdbc:mysql://localhost:3306/DB2022Team08"; //database �̸�
	            String user = "DB2022Team08"; //����ڸ�
	            String pw = "DB2022Team08"; //��й�ȣ
	           
	            //JDBC����̹� �ε�
	            Class.forName("com.mysql.cj.jdbc.Driver");
	           
	            //mysql�� �����Ͽ� Connection ��ü ���.
	            conn = DriverManager.getConnection(url,user,pw);
	                               
	        }
	    }
	   
	    
	  
	    /*����� ���� ��� ����*/
	    public List<DB2022_Team08_ReviewViewDTO> getReviewList(){
	        List<DB2022_Team08_ReviewViewDTO> list = new ArrayList<DB2022_Team08_ReviewViewDTO>();
	       
	        try {
	            getConnection();
	            String sql = "SELECT * FROM DB2022_REVIEW_VIEW"; //select ����
	            PreparedStatement pstmt = conn.prepareStatement(sql);          
	            ResultSet r = pstmt.executeQuery();
	           
	            while(r.next()) {
	                
	                
	                Integer m_stars = r.getInt("����");
	                String m_write_date = r.getString("�ۼ�����");
	                list.add(new DB2022_Team08_ReviewViewDTO(m_stars, m_write_date));
	            }
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:getReviewList()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }  
	        return list;
	    }
	   
	   
	    /*DB���� ����*/
	    public void dbClose(){      
	     
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                System.out.println("����:ResultSet��ü close():" + e.getMessage());
	            }
	        }
	         
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                System.out.println("����:PreparedStatement��ü close():" + e.getMessage());
	            }
	        }
	       
	        if (cstmt != null) {
	            try {
	                cstmt.close();
	            } catch (SQLException e) {
	                System.out.println("����:CallableStatement��ü close():" + e.getMessage());
	            }
	        }      
	           
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.out.println("����:Connection��ü close():" + e.getMessage());
	            }
	        }        
	        conn = null;        
	    }
}
