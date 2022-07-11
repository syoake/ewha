
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//DB�� �����Ͽ� �۾� ó���ϱ� ���� Ŭ����
public class DB2022_Team08_ReceiptDAO {

	private static Connection conn;
	
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	// �⺻ ������
	public DB2022_Team08_ReceiptDAO() {
		
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
	
	// ������ ����ϱ�
	public boolean insertReceipt(DB2022_Team08_ReceiptDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "INSERT INTO DB2022_������ VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getPhoneNum());
			pstmt.setInt(3, dto.getTotalPrice());
			pstmt.setString(4, dto.getDate());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// ������ ��ȣ�� �ش��ϴ� ������ ���� ����
	public DB2022_Team08_ReceiptDTO getReceipt(String num) {
		DB2022_Team08_ReceiptDTO dto = null;
		
		try {
			getConnection();
			
			String sql = "SELECT ��������ȣ, ��ȭ��ȣ, �Ѱ����ݾ�, �Ǹ����� FROM DB2022_������ WHERE ��������ȣ = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet r = pstmt.executeQuery();
			
			if (r.next()) {
				String r_num = r.getString("��������ȣ");
				String r_phoneNum = r.getString("��ȭ��ȣ");
				int r_totalPrice = r.getInt("�Ѱ����ݾ�");
				String r_date = r.getString("�Ǹ�����");
				dto = new DB2022_Team08_ReceiptDTO(r_num, r_phoneNum, r_totalPrice, r_date);				
			}
		} catch (Exception e) {
			System.out.println("���� �߻�: getReceipt() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return dto;
	}
	
	// ����� ������ ��� ����
	public List<DB2022_Team08_ReceiptDTO> getReceiptList(){
		List<DB2022_Team08_ReceiptDTO> list = new ArrayList<DB2022_Team08_ReceiptDTO>();
		
		try {
			getConnection();
			
			String sql = "SELECT ��������ȣ, ��ȭ��ȣ, �Ѱ����ݾ�, �Ǹ����� FROM DB2022_������ ORDER BY ��������ȣ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet r = pstmt.executeQuery();
			
			while(r.next()) {
				String r_num = r.getString("��������ȣ");
				String r_phoneNum = r.getString("��ȭ��ȣ");
				int r_totalPrice = r.getInt("�Ѱ����ݾ�");
				String r_date = r.getString("�Ǹ�����");
				list.add(new DB2022_Team08_ReceiptDTO(r_num, r_phoneNum, r_totalPrice, r_date));
			}
		} catch (Exception e) {
			System.out.println("���� �߻�: getReceiptList() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	// ������ ����
	public boolean updateReceipt(DB2022_Team08_ReceiptDTO dto) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "UPDATE DB2022_������ SET ��ȭ��ȣ=?, �Ѱ����ݾ�=?, �Ǹ�����=? WHERE ��������ȣ=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPhoneNum());
			pstmt.setInt(2, dto.getTotalPrice());
			pstmt.setString(3, dto.getDate());
			pstmt.setString(4, dto.getNum());
			
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: updateReceipt() => " + e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	// ������ ����
	public boolean deleteReceipt(String num) {
		boolean result = false;
		
		try {
			getConnection();
			
			String sql = "DELETE FROM DB2022_������ WHERE ��������ȣ = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			int r = pstmt.executeUpdate();
			
			if (r > 0)
				result = true;
		} catch (Exception e) {
			System.out.println("���� �߻�: deleteReceipt() => "+ e.getMessage());
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
