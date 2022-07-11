import java.util.InputMismatchException;
import java.util.Scanner;

//�� ȸ���� ����ϴ� �޴� �����ϴ� Ŭ����

public class DB2022_Team08_ExecuteCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB2022_Team08_MemberProc mm = new DB2022_Team08_MemberProc(); //DB2022_Team08_MemberProc��ü ����
		DB2022_Team08_PriceViewProc pv = new DB2022_Team08_PriceViewProc(); //DB2022_Team08_PriceViewProc ��ü ����
		
		boolean continued = true;
        while (continued) {
        	 System.out.println();
             System.out.println("============ �� ȸ�� ���� ���α׷� ============");
             System.out.println("1. �α���(���� ����)\t2. ȸ�� ����\t3. ��ǰ ��ȸ");         
             System.out.println("4. �ڷΰ���\t5. ��� ����");
             System.out.println("==========================================");
            
            Scanner scn = new Scanner(System.in);
            int num=0;
            try {
                num = scn.nextInt();
                if(!(num>0 && num<6)){ //1~5���� ���ڰ� �ԷµǸ� ���� ���� �߻�
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("�Էµ� ���� �߸��Ǿ����ϴ�. [1-5] �޴��� �������ּ���!");
            }
           
            switch (num) {
            case 1:
            	
            	DB2022_Team08_MemberDAO dao = new DB2022_Team08_MemberDAO();
                System.out.println("�˻��� ȸ���� ��ȭ��ȣ�� �Է����ּ���(010-0000-0000�������� �Է�)");//����ڷκ��� �Է¹ޱ�
                scn.nextLine();
                String phone_num = scn.nextLine();
                DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
                if (dto!=null) { //�α��� ������ ���� ������ �̵�
                    System.out.println(dto.getInfo());
                	DB2022_Team08_ReviewManagement.main(args);
                	}
                else //�α��� ����
                	System.out.println("�Է��Ͻ� ��ȭ��ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");
              break;
            case 2:
                mm.insertMember();//ȸ�����  
                break;
            case 3:
            	pv.showProductList();//�Ǹ����� ��ǰ�� ��ǰ��, �뷮, ���� list�� ������
            	break;
            case 4:
            	System.out.println("�ڷ� ���Ⱑ �Ϸ�Ǿ����ϴ�.");
            	continued=false; //while�� ���� > �ڷΰ���
            	break;
            case 5:
                System.out.println("���α׷��� �����մϴ�.");
                System.exit(0); //���α׷� ����
                   
            }//end of switch()
        }//end of while      
	}

}
