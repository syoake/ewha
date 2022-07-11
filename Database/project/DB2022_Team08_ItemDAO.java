
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����
public class DB2022_Team08_ItemDAO {

	private static Connection conn;
	
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	// �⺻ ������
	public DB2022_Team08_ItemDAO() {
		
	}
	
	private void getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) { // dbConn�� null�̸� Connection ��ü ������
			// ���� ����
			String url = "jdbc:mysql://localhost:3306/DB2022Team08";
			String user = "DB2022Team08";
			String pw = "DB2022Team08";
			
			// JDBC ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// DBMS�� �����Ͽ� Connection ��ü ���
			conn = DriverManager.getConnection(url, user, pw);
		}
	}
	
	// ǰ�� ����ϱ�
	public boolean insertItem(DB2022_Team08_ItemDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "INSERT INTO DB2022_ǰ�� VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getRecNum());
			pstmt.setString(2, dto.getItemNum());
			pstmt.setInt(3, dto.getAmount());
			pstmt.setInt(4, dto.getPrice());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: insertItem() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// ������ ��ȣ�� �ش��ϴ� ǰ�� ���� ����
	public DB2022_Team08_ItemDTO getItem(String recNum, String itemNum) {
		DB2022_Team08_ItemDTO dto = null;
		
		try {
			getConnection();
			
			String sql = "SELECT ��������ȣ, ��ǰ��ȣ, ����, ���� FROM DB2022_ǰ�� WHERE ��������ȣ = ? AND ��ǰ��ȣ = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recNum);
			pstmt.setString(2, itemNum);
			ResultSet r = pstmt.executeQuery();
			
			if (r.next()) {
				String i_recNum = r.getString("��������ȣ");
				String i_itemNum = r.getString("��ǰ��ȣ");
				int i_amount = r.getInt("����");
				int i_price = r.getInt("����");
				dto = new DB2022_Team08_ItemDTO(i_recNum, i_itemNum, i_amount, i_price);				
			}
		} catch (Exception e) {
			System.out.println("���� �߻�: getItem() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return dto;
	}
	
	// ����� ǰ�� ��� ����
	public List<DB2022_Team08_ItemDTO> getItemList(){
		List<DB2022_Team08_ItemDTO> list = new ArrayList<DB2022_Team08_ItemDTO>();
		
		try {
			getConnection();
			//DB2022_ǰ�� ���̺��� ��ǰ�� �Բ� �˻��ϱ� ���� ���� ���� ���
			String sql = "SELECT ��������ȣ, I.��ǰ��ȣ, ��ǰ��, ����, ���� \r\n"
					+ "FROM DB2022_ǰ�� as I JOIN DB2022_��ǰ as P on I.��ǰ��ȣ= P.��ǰ��ȣ\r\n"
					+ "ORDER BY ��������ȣ;";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			
			while(r.next()) {
				String i_recNum = r.getString("��������ȣ");
				String i_itemNum = r.getString("��ǰ��ȣ");
				String i_Name = r.getString("��ǰ��");
				int i_amount = r.getInt("����");
				int i_price = r.getInt("����");
				list.add(new DB2022_Team08_ItemDTO(i_recNum, i_itemNum, i_Name, i_amount, i_price));
			}
		} catch (Exception e) {
			System.out.println("���� �߻�: getItemList() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	// ǰ�� ����
	public boolean updateItem(DB2022_Team08_ItemDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "UPDATE DB2022_ǰ�� SET ����=?, ����=? WHERE ��������ȣ=? AND ��ǰ��ȣ=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getAmount());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getRecNum());
			pstmt.setString(4, dto.getItemNum());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: updateItem() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// ǰ�� ����
	public boolean deleteItem(String recNum, String itemNum) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "DELETE FROM DB2022_ǰ�� WHERE ��������ȣ = ? AND ��ǰ��ȣ = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recNum);
			pstmt.setString(2, itemNum);
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: deleteItem() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// DB ���� ����
	public void dbClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("����: ResultSet ��ü close(): " + e.getMessage());
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("����: PreparedStatement ��ü close(): " + e.getMessage());
			}
		}
		
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException e) {
				System.out.println("����: CallableStatement ��ü close(): " + e.getMessage());
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("����: Connection ��ü close(): " + e.getMessage());
			}
		}
		
		conn = null;
	}
}
