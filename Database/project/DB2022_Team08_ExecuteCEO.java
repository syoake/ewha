import java.util.InputMismatchException;
import java.util.Scanner;
//���� ȸ���� ����ϴ� �޴� �����ϴ� Ŭ����
public class DB2022_Team08_ExecuteCEO {

	public static void main(String[] args) {
		
		DB2022_Team08_ReviewViewProc rv= new DB2022_Team08_ReviewViewProc(); //Team08_ReviewViewProc ��ü ����
		
		boolean continued = true;
		while (continued) {
			System.out.println();
	        System.out.println("============== ���� ȸ�� ���� ���α׷� ==============");
	        System.out.println("1. �� ���� ����\t2. ���� ����    3. ��ǰ ����");
	        System.out.println("4. ���� ��ȸ   \t5. ������ ����   6. ǰ�� ���� ");
	        System.out.println("7. �ڷΰ���\t8. ��� ����");
	        
	        System.out.println();
	        System.out.println("0. �����"); 
	        System.out.println("==============================================");
	        System.out.print("�޴��� �Է��ϼ��� : ");
	
	        Scanner scn = new Scanner(System.in);
	        int num=0;
	        try {
	            num = scn.nextInt();
	            if(!(num>=0 && num<9)){ //0~8���� ���ڰ� �ԷµǸ� ���� ���� �߻�
	                throw new InputMismatchException();
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [0-8] �޴��� �������ּ���!");
	        }
	
	        switch (num) {
	        	case 0:
	        		DB2022_Team08_TechLab.main(args);//����� ���α׷� ����
	        		break;
	            case 1:
	                DB2022_Team08_MemberManagement.main(args);//�� ���� ���� ���α׷� ����
	                break;
	            case 2:
	            	DB2022_Team08_OrderManagement.main(args);//���� ���� ���α׷� ����
	                break;
	            case 3:
	                DB2022_Team08_ProductManagement.main(args);//��ǰ ���� ���α׷� ����
	                break;
	            case 4:
	            	rv.showReviewList();//�ۼ���, �湮 ����, ������ ���� ���� ������ ���� ����� �����ϴ� ���� ��ȸ ���α׷� ����
	                break;
	            case 5:
	                DB2022_Team08_ReceiptManagement.main(args);//������ ���� ���α׷� ����
	                   break;
	            case 6:
	            	DB2022_Team08_ProductManagement.main(args);//ǰ�� ���� ���α׷� ����
	                break; 
	            case 7:
	            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
	            	continued=false; //while�� ���� > �ڷΰ���
	            	break;
	            case 8 : 
	                System.out.println("���α׷��� �����մϴ�.");
	                System.exit(0); //���α׷� ����
	
	        }//end of switch()

		}//end of while
    }//end of main

	

}
