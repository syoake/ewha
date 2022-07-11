import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */

public class DB2022_Team08_ReviewProc {
	DB2022_Team08_ReviewDAO dao;
	
	   
    //�⺻������
    public DB2022_Team08_ReviewProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_ReviewDAO();
	}
    DB2022_Team08_ReceiptDAO rdao = new DB2022_Team08_ReceiptDAO(); //������ ��ȣ Ȯ���� ���� ReceiptDAO ��ü ����
    DB2022_Team08_ReceiptDTO rdto;//������ ��ȣ Ȯ���� ���� ReceiptDTO ��ü ����
    LocalDate now = LocalDate.now();
    
    /* ���� ���ó��*/
    public void insertReview(){        
       
        Scanner scn = new Scanner(System.in);
       
        System.out.println("���������� �Է����ּ���."); //����ڷκ��� �Է¹ޱ�
        System.out.print("����������ȣ : ");
        
        // >>>> ������ ���̺��� Ȯ���ϱ�
        
       String receipt_num = reInput(scn);
       
       String write_date = now.toString(); //�ۼ� ���ڴ� ���� ��¥�� �ڵ� ����
       
        
        System.out.println("������ Ȯ���� ���� ��ȭ��ȣ�� �Է����ּ���.(010-0000-0000�������� �Է�)"); //������ ������ �������� �´��� �ۼ��� ��ȣ�� �Է¹޾� Ȯ��
       //String writer = scn.nextLine();
        String writer = reInput(scn);
        
        
        
        
        rdto = rdao.getReceipt(receipt_num);
        String visit_date = rdto.getDate(); // �湮 ���� : ������ ���̺��� ������
        String phone;
        if (rdto!=null) {
             phone = rdto.getPhoneNum();
        	if(writer.equals(phone))
        		{
        		System.out.print("������ (1~5 ������ ������ �Է��ϼ���) : ");
                //Integer stars = scn.nextInt();
                Integer stars = reInput2(scn); 
                while (!(stars==1||stars==2||stars==3||stars==4||stars==5))
                {
                	System.out.println("�ùٸ� �Է°��� �ƴմϴ�.");
                	System.out.print("������ (1~5 ������ ������ �Է��ϼ���) : ");
                    //Integer stars = scn.nextInt();
                    stars = reInput2(scn); 
                }
                DB2022_Team08_ReviewDTO dto = new DB2022_Team08_ReviewDTO(receipt_num, visit_date, writer, stars, write_date);    
                boolean r = dao.insertReview(dto); //�Է¹��� ������ �߰�
               
                if(r){
                    System.out.println("�������� ���������� �Ϸ�Ǿ����ϴ�.");
                }else{
                    System.out.println("�������� ���������� �̷������ �ʾҽ��ϴ�.");
                }
        		} //>> ��� ����
        		else 
                	System.out.println("������ ������ �������� ���ؼ��� ���並 ����� �� �ֽ��ϴ�.");
        }else {
			System.out.println("�Է��Ͻ� ������ �ش��ϴ� �������� �������� �ʽ��ϴ�.");
			System.out.println("�ùٸ� ������ ��ȣ �Ǵ� ��ȭ��ȣ���� Ȯ�����ּ���.");
		}
        
        
        
        
    }  
   
   
    /* ���� ���� ����*/
    public void updateReview(){ //����ڷκ��� �Է¹ޱ�
        Scanner scn = new Scanner(System.in);
        System.out.println("������ ������ ��������ȣ�� �Է����ּ���");
        System.out.print("��");
        String receipt_num = scn.nextLine();
        DB2022_Team08_ReviewDTO dto = dao.getReview(receipt_num);
        if (dto!=null) {
           
            System.out.println(dto.getInfo());
            
           
           
            System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){ //����ڴ� ������ ���� ����, �ۼ� ���ڴ� ������(���� ��¥)�� �ڵ� ������Ʈ
                System.out.println("-1�� �Է��Ͻø� ������ ������ �״�� �����˴ϴ�.");
  
                System.out.println("������ Ȯ���� ���� ��ȭ��ȣ�� �Է����ּ���.(010-0000-0000�������� �Է�)");//������ ������ �������� �´��� �ۼ��� ��ȣ�� �Է¹޾� Ȯ��
                String writer = scn.nextLine();
                if(writer.trim().equals("-1")) writer=dto.getWriter();
                
                rdto = rdao.getReceipt(receipt_num);
                String visit_date = rdto.getDate(); // �湮 ���� : ������ ���̺��� ������
                String phone;
                String write_date = now.toString(); //�ۼ� ���ڴ� ���� ��¥�� �ڵ� ����
                

                if (rdto!=null) {
                    phone = rdto.getPhoneNum();
               	if(writer.equals(phone))
               		{
               		System.out.print("�� ������ ���� (1~5 ������ ������ �Է��ϼ���) : ");
                       //Integer stars = scn.nextInt();
                       Integer stars = reInput2(scn); 
                       if(stars==-1) stars=dto.getStars();
                       else {while (!(stars==1||stars==2||stars==3||stars==4||stars==5))
                       {
                       	System.out.println("�ùٸ� �Է°��� �ƴմϴ�.");
                       	System.out.print("������ (1~5 ������ ������ �Է��ϼ���) : ");
                           //Integer stars = scn.nextInt();
                           stars = reInput2(scn); 
                       }
                       }//-1(���� �� ����) �Ǵ� 1~5 ������ ������ �ƴ� �ٸ� ���� �ԷµǾ��� �� ���Է�
                       dto = new DB2022_Team08_ReviewDTO(receipt_num, visit_date, writer, stars, write_date);    
                       boolean r = dao.updateReview(dto); //�Է¹��� ������ �߰�
                      
                       if(r){
                    	   System.out.println("���� ������ ������ ���� �����Ǿ����ϴ�.");
                           System.out.println(dto.getInfo());
                       }else{
                    	   System.out.println("���� ������ ���������� �������� �ʾҽ��ϴ�.");
                       }
               		} //>> ��� ����
               		else 
                       	System.out.println("������ ������ �������� ���ؼ��� ���並 ������ �� �ֽ��ϴ�.");
               }
                
            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }
           
        }else{ 
            System.out.println("�Է��Ͻ� ������ �ش��ϴ� ���䰡 �������� �ʽ��ϴ�.");
            System.out.println("�ùٸ� ������ ��ȣ �Ǵ� ��ȭ��ȣ���� Ȯ�����ּ���.");
        }
    }
   
    /*���� ����*/
    public void deleteReview(){
       
        Scanner scn = new Scanner(System.in);
        System.out.println("������ ������ ��������ȣ�� �Է����ּ���"); //����ڷκ��� �Է¹ޱ�
        String receipt_num = scn.nextLine();
        DB2022_Team08_ReviewDTO dto = dao.getReview(receipt_num);
        
        
        System.out.println("������ Ȯ���� ���� ��ȭ��ȣ�� �Է����ּ���.(010-0000-0000�������� �Է�)"); //������ ������ �������� �´��� �ۼ��� ��ȣ�� �Է¹޾� Ȯ��
        //String writer = scn.nextLine();
         String writer = reInput(scn);
         
         
         
         
         rdto = rdao.getReceipt(receipt_num);
         String phone;
         if (rdto!=null) {
              phone = rdto.getPhoneNum();
         	if(writer.equals(phone))
         	{
         		if (dto!=null) {
                    System.out.println(dto.getInfo());
                    System.out.println("�� ���� ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
                    String input = scn.nextLine();
                    if(input.equalsIgnoreCase("y")){
                        boolean r = dao.deleteReview(receipt_num);
                       
                        if(r){
                            System.out.println(receipt_num+"���� ������ ���������� �����Ǿ����ϴ�.");
                        }else{
                            System.out.println("���� ������ ���������� �������� �ʾҽ��ϴ�.");
                        }
                    }else{
                        System.out.println("���� �۾��� ����Ͽ����ϴ�.");
                    }
                }else{
                   
                    System.out.println("�Է��Ͻ� ��������ȣ�� �ش��ϴ� ���䰡 �������� �ʽ��ϴ�.");
                }
         	}
         	else 
         		System.out.println("������ ������ �������� ���ؼ��� ���並 ������ �� �ֽ��ϴ�.");
         }else {
 			System.out.println("�Է��Ͻ� ������ �ش��ϴ� �������� �������� �ʽ��ϴ�.");
 			System.out.println("�ùٸ� ������ ��ȣ �Ǵ� ��ȭ��ȣ���� Ȯ�����ּ���.");
 		}
        
        
        
    }
      
    /*���� ���� �˻� > ��ȭ��ȣ�� �˻�*/
    public void searchReview(){
    	Scanner scn = new Scanner(System.in);
    	System.out.println("������ Ȯ���� ���� ��ȭ��ȣ�� �Է����ּ���.(010-0000-0000�������� �Է�)");
    	String phone= scn.nextLine();
        List<DB2022_Team08_ReviewDTO> list = dao.getMyReviewList(phone);
       
        System.out.println("                             Review List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){         
            System.out.println("��������ȣ\t\t  �湮����\t\t     �ۼ���\t       ����\t   �ۼ�����");
            System.out.println("============================================================================");
           
            for (DB2022_Team08_ReviewDTO dto : list){
                System.out.println(dto.getReceipt_num()+"\t\t"+dto.getVisit_date()+"\t"+dto.getWriter()+"\t\t"+dto.getStars()+"\t"+dto.getWrite_date());
            }          
           
        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("====================================================================�� "+((list==null)?"0":list.size())+" ��=\n");
    
    	
      
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
