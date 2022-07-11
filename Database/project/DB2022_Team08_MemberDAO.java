import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����*/

public class DB2022_Team08_MemberDAO {
		private static Connection conn;
		   
	    private PreparedStatement pstmt;
	    private CallableStatement cstmt;
	    private ResultSet rs;
	   
	    public DB2022_Team08_MemberDAO() {
			// TODO Auto-generated constructor stub
		}
	    
		private void getConnection() throws ClassNotFoundException, SQLException{ //����ó��
		        if(conn == null){ 
		            //��������
		            String url = "jdbc:mysql://localhost:3306/DB2022Team08"; //database �̸�
		            String user = "DB2022Team08"; //����ڸ�
		            String pw = "DB2022Team08";  //��й�ȣ
		           
		            //JDBC����̹� �ε�
		            Class.forName("com.mysql.cj.jdbc.Driver");
		           
		            //mysql�� �����Ͽ� Connection ��ü ���.
		            conn = DriverManager.getConnection(url,user,pw);
		                               
		        }
		    }
		
		/*ȸ�� ����ϱ�*/
		public boolean insertMember(DB2022_Team08_MemberDTO dto){    
			boolean result = false;            
			try {
				getConnection();
		        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_ȸ�� VALUES(?, ?, ?, ?)"); //insert ����
		        pstmt.setString(1,dto.getPhone_num());
		        pstmt.setString(2,dto.getName());
		        pstmt.setInt(3,dto.getPurchase());
		        pstmt.setString(4, dto.getGrade());
		        int r = pstmt.executeUpdate();
		        if(r>0) result = true;
		        } 
			catch (Exception e) {
		        	System.out.println("���ܹ߻�:insertMember()=> "+e.getMessage());
		        	}
			finally{
				dbClose();
				}      
		        
			return result;
			}      
		   
		/*ȸ����ȣ�� �ش��ϴ� ȸ������ ����*/    
		public DB2022_Team08_MemberDTO getMember(String phone_num){    
			DB2022_Team08_MemberDTO dto =null;
		    try {
		        getConnection();
		        String sql = "SELECT * FROM DB2022_ȸ�� WHERE ��ȭ��ȣ=?"; //select ����
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, phone_num);
		        ResultSet r = pstmt.executeQuery();
		        
		        if(r.next()) {
		            String m_phone_num = r.getString("��ȭ��ȣ");
		            String m_name = r.getString("ȸ���̸�");
		            Integer m_purchase = r.getInt("���űݾ�");
		            String m_grade = r.getString("ȸ�����");
		            dto = new DB2022_Team08_MemberDTO(m_phone_num, m_name, m_purchase, m_grade);
		            }
		        } catch (Exception e) {
		            System.out.println("���ܹ߻�:getMember()=> "+e.getMessage());
		            } finally{          
		            dbClose();
		            }      
		    return dto;
		}

		/*����� ȸ�� ��� ����*/
		public List<DB2022_Team08_MemberDTO> getMemberList(){
		    List<DB2022_Team08_MemberDTO> list = new ArrayList<DB2022_Team08_MemberDTO>();
		       
		    try {
		    	getConnection();
		    	String sql = "SELECT * FROM DB2022_ȸ��"; //select ����
		 
		        PreparedStatement pstmt = conn.prepareStatement(sql);          
		        ResultSet r = pstmt.executeQuery();
		           
		        while(r.next()) {
		        	String m_phone_num = r.getString("��ȭ��ȣ");
		            String m_name = r.getString("ȸ���̸�");
		            Integer m_purchase = r.getInt("���űݾ�");
		            String m_grade = r.getString("ȸ�����");
		            list.add(new DB2022_Team08_MemberDTO(m_phone_num, m_name, m_purchase, m_grade));    
		        }    
		    } catch (Exception e) {
		        System.out.println("���ܹ߻�:getMemberList()=> "+e.getMessage());
		        }finally{          
		            dbClose();
		            }  
		    return list;
		}
		   
		/*ȸ�� ���� ����*/
		public boolean updateMember(DB2022_Team08_MemberDTO dto){
		       boolean result = false;            
		       try {
		    	   getConnection();
		           String sql = "UPDATE DB2022_ȸ�� SET ��ȭ��ȣ=? , ȸ���̸�=? , ���űݾ�=? , ȸ�����=? WHERE ��ȭ��ȣ=?"; //update ����
		           PreparedStatement pstmt = conn.prepareStatement(sql);
		           pstmt.setString(1,dto.getPhone_num());
		           pstmt.setString(2,dto.getName());
		           pstmt.setInt(3,dto.getPurchase());
		           pstmt.setString(4,dto.getGrade());        
		           pstmt.setString(5,dto.getPhone_num());
		           int r = pstmt.executeUpdate();
		           if(r>0) result = true;
		        
		       } catch (Exception e) {
		           System.out.println("���ܹ߻�:updateMember()=> "+e.getMessage());
		       }finally{          
		    	   dbClose();
		       }      
		       return result;
		}
		
		/*ȸ�� ����*/
		public boolean deleteMember(String phone){        
		boolean result = false;            
		try {
			getConnection();
		    String sql = "DELETE FROM DB2022_ȸ�� WHERE ��ȭ��ȣ = ?"; //delete ����
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, phone);
		    int r = pstmt.executeUpdate();
		           
		    if(r>0) result = true;
		} catch (Exception e) {
			System.out.println("���ܹ߻�:deleteMember()=> "+e.getMessage());
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
