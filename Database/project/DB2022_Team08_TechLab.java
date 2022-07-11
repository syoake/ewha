import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DB2022_Team08_TechLab {

private static Connection conn;
	
	private static PreparedStatement pstmt;
	private static CallableStatement cstmt;
	private static ResultSet rs;
	
	// �⺻ ������
	public DB2022_Team08_TechLab() {
		
	}
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
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
	
	public static void main(String[] args) {
        boolean continued = true;   
        while (continued) {
            System.out.println();
    		System.out.println("                        ��� ����� �Դϴ�");
            System.out.println("==================================================================");
    		System.out.println("1. �����Ȯ��");
    		System.out.println("2. ���������ȲȮ��");
            System.out.println("3. �ڷΰ���");
            System.out.println("4. ��� ����");
            System.out.println();
            System.out.println("* ��� ������̶�? ");
            System.out.println("���� ������Ʈ ���� �� �����ϴ� ������� ������ �������ų� �ٲ� �� �ִ� ����Դϴ� :)");
            System.out.println("==================================================================");
            System.out.print("�޴��� �Է��ϼ��� : ");
           
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<5)){ //1~4���� ���ڰ� �ԷµǸ� ���� ���� �߻�
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-4] �޴��� �������ּ���!");
            }
           
            switch (num) {
            case 1:
                goodcustomers();        
                break;
            case 2:
                orderisokay();
                break;
            case 3:
            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
            	continued=false; //while�� ���� > �ڷΰ���
            	break;
            case 4:
            	 System.out.println("���α׷��� �����մϴ�.");
                 System.exit(0); //���α׷� ����                  
            }//end of switch()
        }//end of while 
	}
	
	// ���������
	public static boolean goodcustomers() {
		boolean result = false;
		
		try {
			getConnection();
			//��ø�� ���� ���
			//1�� ���� �湮�� ���� ������ ���� ������ 4�� �̻��̾��� ȸ������� �ǹ����� ���� ��� > ���ȸ��
			String sql = "select ȸ���̸�, ��ȭ��ȣ, ȸ�����\r\n"
					+ "from db2022_ȸ��\r\n"
					+ "where (ȸ�����='gold' || ȸ�����='vip') and ��ȭ��ȣ in (\r\n"
					+ "    select �ۼ���\r\n"
					+ "    from db2022_����\r\n"
					+ "    where ����>3 and �湮����>='2021-01-01'\r\n"
					+ ");";
			PreparedStatement stmt = conn.prepareStatement(sql);	
			
			rs = stmt.executeQuery();
			
			System.out.println();
			System.out.println("        1�� ���� �湮�Ͽ� 4�� �̻��� ���� ���並 �ۼ��� gold, vip ȸ���̿���.");
	        System.out.println("============================================================================");
			int count = 0;
			
			if (!rs.next()) System.out.println("�ش� ������ �������� �ʽ��ϴ�."); //empty set�� ���
			else {
				count++;
				System.out.println("ȸ�� �̸�\t\t\t\t ��ȭ��ȣ\t\t\t\tȸ�� ���");
				System.out.println("============================================================================");
				System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		        
			}
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
				count++;
			}
	        System.out.println("======================================================================�� "+count+"��=");

		} catch (Exception e) {
			System.out.println("���� �߻�: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	
		
    
	
	// ���ֻ�Ȳ����
	public static boolean orderisokay() {
		boolean result = false;
		
		try {
			getConnection();
			//��ø�� ���� ���
			//����� 50���� ���� ��ǰ�� ���ؼ� �ֱ� ���� ����� 3�Ϻ��� ������ ��ǰ�� ���
			String sql = "SELECT ���, ��ǰ��, MAX(���ֳ�¥) as �ֱٹ��ֳ�¥\r\n"
					+ "FROM DB2022_��ǰ, DB2022_����\r\n"
					+ "WHERE ���<50 and ���ֻ�ǰ��ȣ = ��ǰ��ȣ and ��ǰ��ȣ  in (\r\n"
					+ "    SELECT ���ֻ�ǰ��ȣ\r\n"
					+ "    FROM DB2022_����, DB2022_��ǰ\r\n"
					+ "    WHERE ���ֳ�¥ < Date_sub(NOW()  , INTERVAL 3 DAY)\r\n"
					+ ")\r\n"
					+ "GROUP BY ���ֻ�ǰ��ȣ;";
			
					
					
			PreparedStatement stmt = conn.prepareStatement(sql);		
			
			rs = stmt.executeQuery();
			//int r = stmt.executeUpdate();
			System.out.println();
			System.out.println("             ����� 50 �̸��̰� ������ ���ַκ��� 3�� �̻� ����Ͽ����.");
	        System.out.println("============================================================================");
			int count = 0;
			if (!rs.next()) System.out.println("�ش� ������ �������� �ʽ��ϴ�."); //empty set�� ���
			else 
			{
				count++;
				System.out.println("���\t\t\t  ��ǰ��\t\t\t\t  �ֱٹ��ֳ�¥\t\t\t");
				System.out.println("============================================================================");

		            	
				if(rs.getString(2).length()>=6)
		           	System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		        else
		        	System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
		            
			}
				
			while(rs.next()) {
				count++;
				if(rs.getString(2).length()>=6)
					System.out.println(rs.getString(1)+"\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
				else
					System.out.println(rs.getString(1)+"\t\t\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));
			}
	        System.out.println("======================================================================�� "+count+"��=");

			
		} catch (Exception e) {
			System.out.println("���� �߻�: insertReceipt() => "+ e.getMessage());
		} finally {
			dbClose();
		}
		
		return result;
	}

	
	// DB ���� ����
	public static void dbClose() {
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
