import java.util.List;
import java.util.Scanner;

/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */

public class DB2022_Team08_ReviewViewProc {


	DB2022_Team08_ReviewViewDAO dao;
	   
    //�⺻������
    public DB2022_Team08_ReviewViewProc() {
		// TODO Auto-generated constructor stub
    	   dao = new DB2022_Team08_ReviewViewDAO();
	}
   
    
       
    /*����� ���� ���� ��� ����*/
    public void showReviewList(){
   
        List<DB2022_Team08_ReviewViewDTO> list = dao.getReviewList();
       
        System.out.println("     Review List");
        System.out.println("==========================");
        if(list!=null&&list.size()>0){         
            System.out.println("����\t   �ۼ�����");
            System.out.println("==========================");
           float sum=0; int stars; int count=0;
            for (DB2022_Team08_ReviewViewDTO dto : list){
            	stars = dto.getStars();
                System.out.println(stars+"\t"+dto.getWrite_date());
                sum+=stars; count++;
            }          
           System.out.println("��� : "+ sum/count + "��");
        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("===================�� "+((list==null)?"0":list.size())+" ��=\n");
    }
   
  
}
