import java.util.InputMismatchException;
import java.util.Scanner;

/* ��ǰ ���� ���α׷��� �����ϴ� Ŭ���� */

public class DB2022_Team08_ProductManagement {
    public static void main(String[] args) {

        DB2022_Team08_ProductProc mm = new DB2022_Team08_ProductProc(); //DB2022_Team08_ProductProc��ü ����
        boolean continued = true;
        while (continued) {
            System.out.println();
            System.out.println("============== ��ǰ ���� ���α׷� ==============");
            System.out.println("1. ��ǰ���");
            System.out.println("2. ��ǰ���   3. ��ǰ����   4. ��ǰ���� ����   5. ��ǰ �˻�");
            System.out.println("6. �ڷΰ���   7. �������");
            System.out.println("==========================================");
            System.out.print("�޴��� �Է��ϼ��� : ");

            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<8)){ //1~7���� ���ڰ� �ԷµǸ� ���� ���� �߻�
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-7] �޴��� �������ּ���!");
            }

            switch (num) {
                case 1:
                    mm.showProductList();//��ǰ ���
                    break;
                case 2:
                    mm.insertProduct(); //��ǰ ���
                    break;
                case 3:
                    mm.deleteProduct(); //��ǰ ����
                    break;
                case 4:
                    mm.updateProduct(); //��ǰ���� ����
                    break;
                case 5:
                    mm.searchProduct(); //��ǰ �˻�
                    break;
                case 6:
                	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
                	continued=false; //while�� ���� > �ڷΰ���
                	break;
                case 7:
                    System.out.println("���α׷��� �����մϴ�.");
                    System.exit(0); //���α׷� ����

            }//end of switch()
        }//end of while
    }//end of main()
}
