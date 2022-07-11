import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����
public class DB2022_Team08_PriceViewDAO {
	 private static Connection conn;

	    private PreparedStatement pstmt;
	    private CallableStatement cstmt;
	    private ResultSet rs;


	    //�⺻������
	    public DB2022_Team08_PriceViewDAO() {

	    }

	    private void getConnection() throws ClassNotFoundException, SQLException{
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


	   
	    /**
	     * ����� ��ǰ ��� ����(��ǰ��, ��ǰ ������, �ǸŰ��ݸ� ����)
	     */
	    public List<DB2022_Team08_PriceViewDTO> getProductList(){
	        List<DB2022_Team08_PriceViewDTO> list = new ArrayList<DB2022_Team08_PriceViewDTO>();

	        try {
	            getConnection();

	            String sql = "SELECT * FROM DB2022_price_view";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            ResultSet r = pstmt.executeQuery();

	            while(r.next()) {
	                String name = r.getString("��ǰ��");
	                String size = r.getString("��ǰ������");
	                int price = r.getInt("�ǸŰ���");
	                list.add(new DB2022_Team08_PriceViewDTO(name,size,price));
	            }

	        } catch (Exception e) {
	            System.out.println("���ܹ߻�:getProductList()=> "+e.getMessage());
	        }finally{
	            dbClose();
	        }

	        return list;
	    }


	   


	    /**DB���� ����(�ݱ�)*/
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
	    }//dbClose()---------

}
