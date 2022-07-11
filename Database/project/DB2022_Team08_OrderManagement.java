import java.util.InputMismatchException;
import java.util.Scanner;

/* ���� ���� ���α׷��� �����ϴ� Ŭ���� */

public class DB2022_Team08_OrderManagement {
    public static void main(String[] args) {

        DB2022_Team08_OrderProc mm = new DB2022_Team08_OrderProc(); //DB2022_Team08_ProductProc��ü ����
        boolean continued = true;
        while (continued) {
            System.out.println();
            System.out.println("============== ���� ���� ���α׷� ==============");
            System.out.println("1. ���ָ��");
            System.out.println("2. ���ֵ��   3. ���ֻ���   4. �������� ���� ");
            System.out.println("5. �ڷΰ���   6. ��� ����");
            System.out.println("==========================================");
            System.out.print("�޴��� �Է��ϼ��� : ");

            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<7)){ //1~6���� ���ڰ� �ԷµǸ� ���� ���� �߻�
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-6] �޴��� �������ּ���!");
            }

            switch (num) {
                case 1:
                    mm.showOrderList();//��ǰ ���
                    break;
                case 2:
                    mm.insertOrder(); //��ǰ ���
                    break;
                case 3:
                    mm.deleteOrder(); //��ǰ ����
                    break;
                case 4:
                    mm.updateOrder(); //��Ǫ���� ����
                    break;
                case 5:
                	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
                	continued=false; //while�� ���� > �ڷΰ���
                	break;
                case 6:
                    System.out.println("���α׷��� �����մϴ�.");
                    System.exit(0); //���α׷� ����

            }//end of switch()
        }//end of while
    }//end of main()
}
