
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */
public class DB2022_Team08_ReceiptProc {
	
	DB2022_Team08_ReceiptDAO dao;
	
	// �⺻ ������
	public DB2022_Team08_ReceiptProc() {
		dao = new DB2022_Team08_ReceiptDAO();
	}
	
	// ������ ��� ó��
	public void insertReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("������ ������ �Է����ּ���.");
		System.out.print("����������ȣ : ");
		String num = reInput(scn);
		System.out.print("����ȭ��ȣ : ");
		String phoneNum = reInput(scn);
		System.out.print("���Ѱ����ݾ�: ");
		int totalPrice = Integer.parseInt(reInput(scn));
		System.out.print("���Ǹ�����: ");
		String date = reInput(scn);
		
		DB2022_Team08_ReceiptDTO dto = new DB2022_Team08_ReceiptDTO(num, phoneNum, totalPrice, date);
		boolean r = dao.insertReceipt(dto);
		
		if (r) {
			System.out.println("������ ����� ���������� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("������ ����� ���������� �̷������ �ʾҽ��ϴ�.");
		}
	}
	
	// ����� ������ ��� ����
	public void showReceiptList() {
		List<DB2022_Team08_ReceiptDTO> list = dao.getReceiptList();
		
		System.out.println("                             Receipt List");
		System.out.println("============================================================================");
		
		if (list != null && list.size() > 0) {
			System.out.println("��������ȣ\t\t��ȭ��ȣ\t\t�Ѱ����ݾ�\t\t�Ǹ�����");
            System.out.println("============================================================================");
            
            for (DB2022_Team08_ReceiptDTO dto : list) {
            	System.out.println(dto);
            }
        } else {
        	System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
		
		System.out.println("====================================================================�� " + ((list == null)? "0" : list.size()) + " ��=\n");
	}
	
	// ������ ����
	public void updateReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("������ �������� ��������ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		
		String num = scn.nextLine();
		DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("�����۾��� ����Ͻðڽ��ϱ�? (Y/N)");
			String input = scn.nextLine();
			
			if(input.equalsIgnoreCase("y")){
                System.out.println("-1�� �Է��Ͻø� ������ ������ �״�� �����˴ϴ�.");
                
                System.out.print("�������� ��ȭ��ȣ : ");
                String phoneNum = scn.nextLine();
                if(phoneNum.trim().equals("-1"))
                	phoneNum = dto.getPhoneNum();
  
                System.out.print("�������� �Ѱ����ݾ� : ");
                int totalPrice = Integer.parseInt(scn.nextLine());
                if(Integer.toString(totalPrice).trim().equals("-1"))
                	totalPrice = dto.getTotalPrice();
                
                System.out.print("�������� �Ǹ����� : ");
                String date = scn.nextLine();
                if(date.trim().equals("-1"))
                	date = dto.getDate();
  
                dto =  new DB2022_Team08_ReceiptDTO(num, phoneNum, totalPrice, date);
                boolean r = dao.updateReceipt(dto);
				
				if (r) {
					System.out.println("�������� ������ ������ ���� �����Ǿ����ϴ�.");
					System.out.println(dto.getInfo());
				} else {
					System.out.println("�������� ������ ���������� �������� �ʾҽ��ϴ�.");
				}
			} else {
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�Է��Ͻ� ��������ȣ�� �ش��ϴ� �������� �������� �ʽ��ϴ�.");
		}
	}
	
	// ������ ����
	public void deleteReceipt() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("������ �������� ��������ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		
		String num = scn.nextLine();
		DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("�� �������� ������ �����Ͻðڽ��ϱ�? (Y/N)");
			String input = scn.nextLine();
			
			if (input.equalsIgnoreCase("y")) {
				boolean r = dao.deleteReceipt(num);
				
				if (r) {
					System.out.println(num + " �������� ������ ���������� �����Ǿ����ϴ�.");
				} else {
					System.out.println("�������� ������ ���������� �������� �ʾҽ��ϴ�.");
				}
			} else {
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�Է��Ͻ� ��������ȣ�� �ش��ϴ� �������� �������� �ʽ��ϴ�.");
		}
	}
	
	// ������ �˻�
    public void searchReceipt(){
        Scanner scn = new Scanner(System.in);
        
        System.out.println("�˻��� �������� ��������ȣ�� �Է����ּ���");
        System.out.print("�� ");
        
        String num = scn.nextLine();
        DB2022_Team08_ReceiptDTO dto = dao.getReceipt(num);
        
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("�߸��� �Է��Դϴ�.");
    }
	
	public String reInput(Scanner scn) {
		String str = "";
		
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().equals(""))) {
				break;
			} else {
				System.out.println("������ �Է��Ͻ� �� �����ϴ�. �ùٸ� ���� �Է��� �ּ���!");
				System.out.print("�� ");
			}
		}
		
		return str;
	}
}
