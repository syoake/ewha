import java.util.List;
import java.util.Scanner;

/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */

public class DB2022_Team08_MemberProc {
	DB2022_Team08_MemberDAO dao;
	   
    public DB2022_Team08_MemberProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_MemberDAO();
	}
    
    /*ȸ�� ���ó��*/
    public void insertMember(){        
       
        Scanner scn = new Scanner(System.in);
       
        System.out.println("ȸ�������� �Է����ּ���."); //����ڷκ��� �Է� �ޱ�
        System.out.print("����ȭ��ȣ(010-0000-0000�������� �Է�) : ");
        //String phone_num = scn.nextLine();
        String phone_num = reInput(scn);
        System.out.print("���̸� : ");
        //String name = scn.nextLine();
        String name = reInput(scn);  
        String grade ="bronze";
        Integer purchase = 0;
        // ȸ�� ����� ������ �űԶ�� ����

        DB2022_Team08_MemberDTO dto = new DB2022_Team08_MemberDTO(phone_num, name, purchase, grade);    
        boolean r = dao.insertMember(dto); //�Է¹��� ������ �߰�
       
        if(r){
            System.out.println("ȸ������� ���������� �Ϸ�Ǿ����ϴ�.");
        }else{
            System.out.println("ȸ������� ���������� �̷������ �ʾҽ��ϴ�.");
        }
       
    }  
     
    /*����� ȸ�� ���� ��� ����*/
    public void showMemberList(){
   
        List<DB2022_Team08_MemberDTO> list = dao.getMemberList();
       
        System.out.println("                             Member List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){         
            System.out.println("��ȭ��ȣ\t\t�̸�\t\t���űݾ�\t\tȸ�����");
            System.out.println("============================================================================");
           
            for (DB2022_Team08_MemberDTO dto : list){
                System.out.println(dto.getPhone_num()+"\t"+dto.getName()+"\t\t"+dto.getPurchase()+"\t\t"+dto.getGrade());
            }          
           
        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("====================================================================�� "+((list==null)?"0":list.size())+" ��=\n");
    }
   
    /*ȸ�� ���� ����*/
    public void updateMember(){
    	Scanner scn = new Scanner(System.in);
        System.out.println("������ ȸ���� ��ȭ��ȣ�� �Է����ּ���(010-0000-0000�������� �Է�)"); //����ڷκ��� �Է¹ޱ�
        System.out.print("��");
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null) {
           
            System.out.println(dto.getInfo());
           
           
            System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1�� �Է��Ͻø� ������ ������ �״�� �����˴ϴ�.");
                System.out.print("�������� �̸� : ");
                String name = scn.nextLine();
                if(name.trim().equals("-1")) name=dto.getName();
                
                System.out.print("�������� ȸ����� : ");
                String grade = scn.nextLine();
                if(grade.trim().equals("-1")) grade=dto.getGrade();
                
                System.out.print("�������� ���űݾ� :  ");
                Integer purchase = scn.nextInt();
                if(purchase==-1) purchase=dto.getPurchase();
                
                dto =  new DB2022_Team08_MemberDTO(phone_num, name, purchase, grade);
               
                boolean r = dao.updateMember(dto);
               
                if(r){
                   
                    System.out.println("ȸ���� ������ ������ ���� �����Ǿ����ϴ�.");
                    System.out.println(dto.getInfo());
                   
                }else{
                    System.out.println("ȸ���� ������ ���������� �������� �ʾҽ��ϴ�.");
                }
               
            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }
           
        }else{
           
            System.out.println("�Է��Ͻ� ��ȭ��ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");   
        }
    }
   
    /*ȸ�� ����*/
    public void deleteMember(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("������ ȸ���� ��ȭ��ȣ�� �Է����ּ���(010-0000-0000�������� �Է�)"); //����ڷκ��� �Է¹ޱ�
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null) {
            System.out.println(dto.getInfo());
           
            System.out.println("�� ȸ���� ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteMember(phone_num);
               
                if(r){
                    System.out.println(phone_num+"ȸ���� ������ ���������� �����Ǿ����ϴ�.");
                }else{
                    System.out.println("ȸ���� ������ ���������� �������� �ʾҽ��ϴ�.");
                }
            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }
        }else{
           
            System.out.println("�Է��Ͻ� ��ȭ��ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");
           
        }
    }
    
    /*ȸ�� �˻�*/
    public void searchMember(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("�˻��� ȸ���� ��ȭ��ȣ�� �Է����ּ���(010-0000-0000�������� �Է�)"); //����ڷκ��� �Է¹ޱ�
        String phone_num = scn.nextLine();
        DB2022_Team08_MemberDTO dto = dao.getMember(phone_num);
        if (dto!=null)
            System.out.println(dto.getInfo());
        else 
        	System.out.println("�߸��� �Է��Դϴ�");
     
    }
    
    /*����, �����Է½� ���Է� �ޱ�*/
    public String reInput(Scanner scn){ //string ���¿��� ���� �Է½� ó��
       
        String str="";
        while(true){
            str = scn.nextLine();
            if(!(str==null || str.trim().equals(""))){
                break;
            }else{
                System.out.println("������ �Է��Ͻ� �� �����ϴ�. �ùٸ� ���� �Է����ּ���!");
                System.out.print("��");
            }
        }
        return str;
    }
    
    public Integer reInput2(Scanner scn){ //integer ���¿��� ���� �Է½� ó��
        
        int i=-1;
        while(true){
            i = scn.nextInt();
            if(!(i<0)){
                break;
            }else{
                System.out.println("0 ���ϴ� �Է��Ͻ� �� �����ϴ�. �ùٸ� ���� �Է����ּ���!");
                System.out.print("��");
            }
        }
       
        return i;
    }

}
