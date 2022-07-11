
import java.util.InputMismatchException;
import java.util.Scanner;
/* ǰ�� ���� ���α׷��� �����ϴ� Ŭ���� */
public class DB2022_Team08_ItemManagement {
	
	public static void main(String[] args) {
		
		DB2022_Team08_ItemProc rr = new DB2022_Team08_ItemProc(); // ItemProc ��ü ����
		boolean continued = true;
		while (continued) {
			System.out.println();
            System.out.println("============== ǰ�� ���� ���α׷� ==============");
			System.out.println("1. ǰ����");         
            System.out.println("2. ǰ����   3. ǰ�����   4. ǰ�����   5. ǰ��˻�");
            System.out.println("6. �ڷΰ���   7. ��� ����");
            System.out.println("==========================================");
            System.out.print("�޴��� �Է��ϼ��� : ");
            
            Scanner scn = new Scanner(System.in);
            int num = 0;
            
            try {
            	num = scn.nextInt();
            	
            	if (!(num > 0 && num < 8)) { // 1-7 �̿��� ���ڰ� �ԷµǸ� ���� ���� �߻�
            		throw new InputMismatchException();
            	}
            } catch (InputMismatchException e) {
            	System.out.println("[1-7] �޴��� �����ϼ���.");
            }
            
            switch (num) {
            case 1:
            	rr.showItemList(); // ǰ����
            	break;
            case 2:
            	rr.insertItem(); // ǰ����
            	break;
            case 3:
            	rr.deleteItem(); // ǰ�����
            	break;
            case 4:
            	rr.updateItem(); // ǰ�����
            	break;
            case 5:
            	rr.searchItem(); // ǰ��˻�
            	break;
            case 6:
            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
            	continued=false; //while�� ���� > �ڷΰ���
            	break;
            case 7 : 
            	System.out.println("���α׷��� �����մϴ�.");
            	System.exit(0); // ���α׷� ����
            }
		}
	}
}
