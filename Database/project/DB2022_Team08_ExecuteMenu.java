import java.util.InputMismatchException;
import java.util.Scanner;

public class DB2022_Team08_ExecuteMenu {

	public static void main(String[] args) {
	       
           
        while (true) {
            System.out.println();
            System.out.println("============ �ַ� ���� ���� ���α׷� ============");
            System.out.println("1. �� ȸ��\t\t\t2. ����� ȸ��");         
            System.out.println("3. ����");
            System.out.println("(������� �Ʒ� �ܼ�â�� ��µ˴ϴ�.)");
            System.out.println("==========================================");
            System.out.print("�޴��� �Է��ϼ��� : ");
           
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<4)){ //1~3���� ���ڰ� �ԷµǸ� ���� ���� �߻�
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-3] �޴��� �������ּ���!");
            }
           
            switch (num) {
            case 1:
            	DB2022_Team08_ExecuteCustomer.main(args);
                //�� ȸ�� ���� �޴�    
                break;
            case 2:
            	DB2022_Team08_ExecuteCEO.main(args);
                //����� ȸ��(����) ���� �޴�
                break;
            case 3:
                System.out.println("���α׷��� �����մϴ�.");
                System.exit(0); //���α׷� ����
                   
            }//end of switch()
        }//end of while      
    }//end of main()

}
