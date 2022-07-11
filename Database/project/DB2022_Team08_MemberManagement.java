import java.util.InputMismatchException;
import java.util.Scanner;

/* ��� ���� ���α׷��� �����ϴ� Ŭ���� */

public class DB2022_Team08_MemberManagement {
	
	public static void main(String[] args) {
	       
        DB2022_Team08_MemberProc mm = new DB2022_Team08_MemberProc(); //DB2022_Team08_MemberProc��ü ����
        boolean continued = true;   
        while (continued) {
            System.out.println();
            System.out.println("============== ��� ���� ���α׷� ==============");
            System.out.println("1. ȸ�����");         
            System.out.println("2. ȸ�����   3. ȸ������   4. ȸ������ ����   5. ȸ�� �˻�");
            System.out.println("6. �ڷΰ���   7. ��� ����");
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
                mm.showMemberList();//ȸ�� ���         
                break;
            case 2:
                mm.insertMember(); //ȸ�� ���
                break;
            case 3:
                mm.deleteMember(); //ȸ�� ����             
                break;
            case 4:
                mm.updateMember(); //ȸ�� ���� ����
                break;
            case 5:
            	mm.searchMember(); //ȸ�� �˻�
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
