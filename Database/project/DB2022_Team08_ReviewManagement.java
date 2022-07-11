import java.util.InputMismatchException;
import java.util.Scanner;

/* ���� ���� ���α׷��� �����ϴ� Ŭ���� */

public class DB2022_Team08_ReviewManagement {
	public static void main(String[] args) {
	       
        DB2022_Team08_ReviewProc mm = new DB2022_Team08_ReviewProc(); //DB2022_Team08_ReviewProc��ü ����
        DB2022_Team08_ReviewViewProc rv = new DB2022_Team08_ReviewViewProc(); //DB2022_Team08_ReviewViewProc��ü ����

        boolean continued = true; //�ݺ��� ���ῡ ���� �Ҹ� ����
        while (continued) {
            System.out.println();
            System.out.println("============== ���� ���� ���α׷� ==============");
            System.out.println("1. ������");         
            System.out.println("2. ������   3. �������   4. �������� ����   5. ���� �� ����");
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
            	rv.showReviewList();//�ۼ���, �湮 ����, ������ ���� ���� ������ ���� ���
                //mm.showReviewList();//���� ���         
                break;
            case 2:
                mm.insertReview(); //���� ��� >>>> ������ ���̺��� ȸ�� ��ȣ�� �´��� Ȯ���ϴ� �� �ֱ�
                break;
            case 3:
                mm.deleteReview(); //���� ����             
                break;
            case 4:
                mm.updateReview(); //�������� ����
                break;
            case 5:
            	mm.searchReview(); //���� �˻�
            	break;
            case 6:
            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
            	continued=false; //while�� ���� > �ڷΰ���
            	break; // �ڷΰ���
            case 7:
            	System.out.println("���α׷��� �����մϴ�.");
                System.exit(0); //���α׷� ����
                   
            }//end of switch()
        }//end of while      
    }//end of main()
}
