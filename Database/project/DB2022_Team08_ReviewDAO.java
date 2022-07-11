import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����*/

public class DB2022_Team08_ReviewDAO {
	private static Connection conn;
	   
    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;
   
    public DB2022_Team08_ReviewDAO() {
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
	   
	       
	    /*���� ����ϱ�*/
	    public boolean insertReview(DB2022_Team08_ReviewDTO dto){    
	       
	        boolean result = false;            
	        try {
	            getConnection();
	           
	           
	            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_���� VALUES(?, ?, ?, ?, ?)"); //insert ����
	            pstmt.setString(1,dto.getReceipt_num());
	            pstmt.setString(2,dto.getVisit_date());
	            pstmt.setString(3,dto.getWriter());
	            pstmt.setInt(4, dto.getStars());
	            pstmt.setString(5, dto.getWrite_date());
	           
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:insertReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
	    }      
	   
	    /*��������ȣ�� �ش��ϴ� �������� ����*/
	    public DB2022_Team08_ReviewDTO getReview(String receipt_num){
	        DB2022_Team08_ReviewDTO dto =null;
	        try {
	            getConnection();
	           
	            String sql = "SELECT * FROM DB2022_���� WHERE ��������ȣ=?"; //select ����
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, receipt_num);
	            ResultSet r = pstmt.executeQuery();
	           
	            if(r.next()) {
	                String m_receipt_num = r.getString("��������ȣ");
	                String m_visit_date = r.getString("�湮����");
	                String m_writer = r.getString("�ۼ���");
	                Integer m_stars = r.getInt("����");
	                String m_write_date = r.getString("�ۼ�����");
	                dto = new DB2022_Team08_ReviewDTO(m_receipt_num, m_visit_date, m_writer, m_stars, m_write_date);
	            }
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:getReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return dto;
	    }
	 
	    /*����� ���� ��� ���� > �˻��ϴ� ������ �ۼ��� �͸�*/ 
	    public List<DB2022_Team08_ReviewDTO> getMyReviewList(String phone){
	        List<DB2022_Team08_ReviewDTO> list = new ArrayList<DB2022_Team08_ReviewDTO>();
	       
	        try {
	            getConnection();
	            String sql = "SELECT * FROM DB2022_���� WHERE �ۼ��� = ?"; //select ����
	            
	            PreparedStatement pstmt = conn.prepareStatement(sql); 
	            pstmt.setString(1,phone);
	            
	            ResultSet r = pstmt.executeQuery();
	           
	            while(r.next()) {
	                String m_receipt_num = r.getString("��������ȣ");
	                String m_visit_date = r.getString("�湮����");
	                String m_writer = r.getString("�ۼ���");
	                Integer m_stars = r.getInt("����");
	                String m_write_date = r.getString("�ۼ�����");
	                list.add(new DB2022_Team08_ReviewDTO(m_receipt_num, m_visit_date, m_writer, m_stars, m_write_date));
	            }
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:getReviewList()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }  
	        return list;
	    }
	   
	 
	    /*���� ����*/
	    public boolean updateReview(DB2022_Team08_ReviewDTO dto){
	       
	        boolean result = false;            
	        try {
	            getConnection();
	           
	            String sql = "UPDATE DB2022_���� SET ��������ȣ=? , �湮����=? , �ۼ���=? , ����=?, �ۼ�����=? WHERE ��������ȣ=?"; //update ����
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	           
	            pstmt.setString(1,dto.getReceipt_num());
	            pstmt.setString(2,dto.getVisit_date());
	            pstmt.setString(3,dto.getWriter());
	            pstmt.setInt(4,dto.getStars());        
	            pstmt.setString(5,dto.getWrite_date());
	            pstmt.setString(6, dto.getReceipt_num());
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:updateReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
	    }
	   
	   
	    /*���� ����*/
	    public boolean deleteReview(String receipt){        
	        boolean result = false;            
	        try {
	            getConnection();
	           
	            String sql = "DELETE FROM DB2022_���� WHERE ��������ȣ = ?"; //delete ����
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, receipt);
	            int r = pstmt.executeUpdate();
	           
	            if(r>0) result = true;
	           
	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:deleteReview()=> "+e.getMessage());
	        }finally{          
	            dbClose();
	        }      
	        return result;
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
