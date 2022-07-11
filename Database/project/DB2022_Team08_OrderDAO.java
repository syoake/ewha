import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����
public class DB2022_Team08_OrderDAO {

    private static Connection conn;

    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;


    //�⺻������
    public DB2022_Team08_OrderDAO() {

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
     * ��ǰ ����ϱ�
     */
    public boolean insertOrder(DB2022_Team08_OrderDTO dto){

        boolean result = false;
        try {
            getConnection();

            //DB2022_���� ���̺� ���ο� ���� ������ ��� ��, DB2022_��ǰ ���̺��� ��� ���� ������Ʈ�ؾ� �ϹǷ� Ʈ����� ����
            conn.setAutoCommit(false);
            //String sql = "INSERT INTO DB2022_���� VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB2022_���� VALUES(?, ?, ?, ?, ?, ?, ?)");
            Statement stmt=conn.createStatement();            
            pstmt.setString(1,dto.getOrder_num());
            pstmt.setInt(2,dto.getOrder_price());
            pstmt.setDate(3,dto.getOrder_date());
            pstmt.setTime(4,dto.getOrder_time());
            pstmt.setInt(5,dto.getOrder_total());
            pstmt.setString(6,dto.getContact_name());
            pstmt.setString(7,dto.getCompany_phone());
            
            
            int r = pstmt.executeUpdate(); //���� ���̺� tuple �߰�
            
            String sql_2 = "UPDATE DB2022_��ǰ SET ���=���+"+dto.getOrder_total()+" WHERE ��ǰ��ȣ="+dto.getOrder_num()+";";
            
            stmt.executeUpdate(sql_2); //������ ������ŭ ��ǰ ���̺� ��� ������Ʈ
            conn.commit();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("���ܹ߻�:insertOrder()=> "+e.getMessage());
            System.out.println("rolling back data here...");
			
			try {
				if(conn!=null) {
					conn.rollback();
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
        }finally{
        	try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dbClose();
        }
    
        return result;
    }

    /**
     * ���ֹ�ȣ�� �ش��ϴ� �������� ����
     */
    public DB2022_Team08_OrderDTO getOrder(Date date, Time time){
        DB2022_Team08_OrderDTO dto =null;
        try {
            getConnection();

            String sql = "SELECT * FROM DB2022_���� WHERE ���ֳ�¥ = ? and ���ֽð�=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            ResultSet r = pstmt.executeQuery();

            if(r.next()) {
                String orderNum = r.getString("���ֻ�ǰ��ȣ");
                int orderPrice = r.getInt("���ֻ�ǰ����");
                Date orderDate = r.getDate("���ֳ�¥");
                Time orderTime = r.getTime("���ֽð�");
                int orderTotal = r.getInt("���ּ���");
                String contactName = r.getString("������̸�");
                String companyPhone = r.getString("������ü��ȭ��ȣ");

                dto = new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,contactName,companyPhone);
            }

        } catch (Exception e) {
            System.out.println("���ܹ߻�:deleteOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return dto;
    }

    /**
     * ����� ���� ��� ����
     */
    public List<DB2022_Team08_OrderDTO> getOrderList(){
        List<DB2022_Team08_OrderDTO> list = new ArrayList<DB2022_Team08_OrderDTO>();

        try {
            getConnection();
            // DB2022_���� ���̺��� ������ü�� �Բ� �˻��ϱ� ���� ���� ���� ���
            String sql = "SELECT ���ֻ�ǰ��ȣ, ���ֻ�ǰ����, ���ֳ�¥, ���ֽð�, ���ּ���, ������ü, ������̸�, ������ü��ȭ��ȣ "
            		+"FROM DB2022_���� JOIN DB2022_��ǰ on DB2022_����.���ֻ�ǰ��ȣ = DB2022_��ǰ.��ǰ��ȣ;";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet r = pstmt.executeQuery();

            while(r.next()) {
                String orderNum = r.getString("���ֻ�ǰ��ȣ");
                int orderPrice = r.getInt("���ֻ�ǰ����");
                Date orderDate = r.getDate("���ֳ�¥");
                Time orderTime = r.getTime("���ֽð�");
                int orderTotal = r.getInt("���ּ���");
                String companyName = r.getString("������ü");
                String contactName = r.getString("������̸�");
                String companyPhone = r.getString("������ü��ȭ��ȣ");
                list.add(new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,companyName,contactName,companyPhone));
            }

        } catch (Exception e) {
            System.out.println("���ܹ߻�:getOrderList()=> "+e.getMessage());
        }finally{
            dbClose();
        }

        return list;
    }


    /**
     * ȸ�� ����
     */
    public boolean updateOrder(DB2022_Team08_OrderDTO dto){

        boolean result = false;
        try {
            getConnection();

            String sql = "UPDATE DB2022_���� SET ���ֻ�ǰ��ȣ=?, ���ְ���=? , ���ֳ�¥=?, ���ֽð�=?, ���ּ���=?, ������̸�=?, ������ü��ȭ��ȣ=? WHERE ���ֹ�ȣ=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,dto.getOrder_num());
            pstmt.setInt(2,dto.getOrder_price());
            pstmt.setDate(3,dto.getOrder_date());
            pstmt.setTime(4,dto.getOrder_time());
            pstmt.setInt(5,dto.getOrder_total());
            pstmt.setString(6,dto.getContact_name());
            pstmt.setString(7,dto.getCompany_phone());

            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("���ܹ߻�:updateOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
    }


    /**
     * ȸ�� ����
     */
    public boolean deleteOrder(Date date, Time time){
        boolean result = false;
        try {
            getConnection();

            String sql = "DELETE FROM DB2022_���� WHERE ���ֳ�¥ = ? and ���ֽð�=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            int r = pstmt.executeUpdate();

            if(r>0) result = true;

        } catch (Exception e) {
            System.out.println("���ܹ߻�:deleteOrder()=> "+e.getMessage());
        }finally{
            dbClose();
        }
        return result;
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
