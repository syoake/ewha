

import java.util.InputMismatchException;
import java.util.Scanner;
/* ������ ���� ���α׷��� �����ϴ� Ŭ���� */
public class DB2022_Team08_ReceiptManagement {
	
	public static void main(String[] args) {
		
		DB2022_Team08_ReceiptProc rr = new DB2022_Team08_ReceiptProc(); // ReceiptProc ��ü ����
		boolean continued = true;
		while (continued) {
			System.out.println();
            System.out.println("============== ������ ���� ���α׷� ==============");
            System.out.println("1. ���������");         
            System.out.println("2. ���������\t3. ����������   4. ����������   5. ������ �˻�");
            System.out.println("6. �ڷΰ���\t7. ��� ����");
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
            	rr.showReceiptList(); // ���������
            	break;
            case 2:
            	rr.insertReceipt(); // ���������
            	break;
            case 3:
            	rr.deleteReceipt(); // ����������
            	break;
            case 4:
            	rr.updateReceipt(); // ����������
            	break;
            case 5:
            	rr.searchReceipt(); //������ �˻�
            	break;
            case 6:
            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
            	continued=false; //while�� ���� > �ڷΰ���
            	break;
            case 7:
                System.out.println("���α׷��� �����մϴ�.");
                System.exit(0); //���α׷� ����
            }
		}
	}
}
