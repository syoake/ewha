
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
public class DB2022_Team08_ItemProc {
	
	DB2022_Team08_ItemDAO dao;
	
	// �⺻ ������
	public DB2022_Team08_ItemProc() {
		dao = new DB2022_Team08_ItemDAO();
	}
	
	// ǰ�� ��� ó��
	public void insertItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("ǰ�� ������ �Է����ּ���.");
		System.out.print("����������ȣ : ");
		String recNum = reInput(scn);
		System.out.print("����ǰ��ȣ : ");
		String itemNum = reInput(scn);
		System.out.print("������: ");
		int amount = Integer.parseInt(reInput(scn));
		System.out.print("������ : ");
		int price = Integer.parseInt(reInput(scn));
		
		DB2022_Team08_ItemDTO dto = new DB2022_Team08_ItemDTO(recNum, itemNum, amount, price);
		boolean r = dao.insertItem(dto); // �Է¹��� ������ �߰�
		
		if (r) {
			System.out.println("ǰ�� ����� ���������� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println("ǰ�� ����� ���������� �̷������ �ʾҽ��ϴ�.");
		}
	}
	
	// ����� ǰ�� ��� ����
	public void showItemList() {
		List<DB2022_Team08_ItemDTO> list = dao.getItemList();
		
		System.out.println("                             Item List");
		System.out.println("============================================================================");
		
		if (list != null && list.size() > 0) {
			System.out.println("��������ȣ\t\t��ǰ��ȣ\t\t��ǰ��\t\t����\t\t����");
            System.out.println("============================================================================");
            
            for (DB2022_Team08_ItemDTO dto : list) {
            	String pd_name = dto.getItemName();
            	if(pd_name.length()>=6)
                    System.out.println(dto.getRecNum()+"\t\t"+dto.getItemNum()+"\t\t"+pd_name+"\t"+dto.getAmount()+"\t\t"+dto.getPrice());
            	else           	
            		System.out.println(dto.getRecNum()+"\t\t"+dto.getItemNum()+"\t\t"+pd_name+"\t\t"+dto.getAmount()+"\t\t"+dto.getPrice());

            }
        } else {
        	System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
		
		System.out.println("====================================================================�� " + ((list == null)? "0" : list.size()) + " ��=\n");
	}
	
	// ǰ�� ����
	public void updateItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("������ ǰ���� ��������ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String recNum = scn.nextLine();
		
		System.out.println("������ ǰ���� ��ǰ��ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("�����۾��� ����Ͻðڽ��ϱ�? (Y/N)");
			String input = scn.nextLine();
			
			if(input.equalsIgnoreCase("y")){
                System.out.println("-1�� �Է��Ͻø� ������ ������ �״�� �����˴ϴ�.");
                
                System.out.print("�������� ���� : ");
                int amount = Integer.parseInt(scn.nextLine());
                if(Integer.toString(amount).trim().equals("-1"))
                	amount = dto.getAmount();
  
                System.out.print("�������� ���� : ");
                int price = Integer.parseInt(scn.nextLine());
                if(Integer.toString(price).trim().equals("-1"))
                	price = dto.getPrice();
  
                dto =  new DB2022_Team08_ItemDTO(recNum, itemNum, amount, price);
                boolean r = dao.updateItem(dto);
				
				if (r) {
					System.out.println("ǰ���� ������ ������ ���� �����Ǿ����ϴ�.");
					System.out.println(dto.getInfo());
				} else {
					System.out.println("ǰ���� ������ ���������� �������� �ʾҽ��ϴ�.");
				}
			} else {
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�Է��Ͻ� ��������ȣ�� ��ǰ��ȣ�� �ش��ϴ� ǰ���� �������� �ʽ��ϴ�.");
		}
	}
	
	// ǰ�� ����
	public void deleteItem() {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("������ ǰ���� ��������ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String recNum = scn.nextLine();
		
		System.out.println("������ ǰ���� ��ǰ��ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
		
		if (dto != null) {
			System.out.println(dto.getInfo());
			System.out.println("�� ǰ���� ������ �����Ͻðڽ��ϱ�? (Y/N)");
			String input = scn.nextLine();
			
			if (input.equalsIgnoreCase("y")) {
				boolean r = dao.deleteItem(recNum, itemNum);
				
				if (r) {
					System.out.println(recNum + " " + itemNum + " ǰ�� ������ ���������� �����Ǿ����ϴ�.");
				} else {
					System.out.println("ǰ�� ������ ���������� �������� �ʾҽ��ϴ�.");
				}
			} else {
				System.out.println("���� �۾��� ����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�Է��Ͻ� ��������ȣ�� ��ǰ��ȣ�� �ش��ϴ� ǰ���� �������� �ʽ��ϴ�.");
		}
	}
	
	// ǰ�� �˻�
    public void searchItem(){
        Scanner scn = new Scanner(System.in);
        
        System.out.println("�˻��� ǰ���� ��������ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String recNum = scn.nextLine();
		
		System.out.println("�˻��� ǰ���� ��ǰ��ȣ�� �Է��ϼ���.");
		System.out.print("�� ");
		String itemNum = scn.nextLine();
		DB2022_Team08_ItemDTO dto = dao.getItem(recNum, itemNum);
        
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("�߸��� �Է��Դϴ�.");
    }
	
	public String reInput(Scanner scn) {
		String str = "";
		
		while (true) {
			str = scn.nextLine();
			if (!(str == null || str.trim().contentEquals(""))) {
				break;
			} else {
				System.out.println("������ �Է��Ͻ� �� �����ϴ�. �ùٸ� ���� �Է��� �ּ���!");
				System.out.print("�� ");
			}
		}
		
		return str;
	}
}
