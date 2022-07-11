import java.util.List;

public class DB2022_Team08_PriceViewProc {

	DB2022_Team08_PriceViewDAO dao;

    //�⺻������
    public DB2022_Team08_PriceViewProc() {
        dao = new DB2022_Team08_PriceViewDAO();


    }
    
    /**
     * ����� ��ǰ ��� ����(��ǰ��, ��ǰ ������, �ǸŰ��ݸ� ����)
     */
    public void showProductList(){

        List<DB2022_Team08_PriceViewDTO> list = dao.getProductList();

        System.out.println("                             Product List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("��ǰ��\t\t\t  ��ǰ������\t\t  ��ǰ����\t\t");
            System.out.println("============================================================================");

            for (DB2022_Team08_PriceViewDTO dto : list){
            	String pd_name = dto.getName();
            	if(pd_name.length()>=6)
                System.out.println(pd_name+"\t\t"+dto.getSize()+"\t\t\t"+dto.getPrice());
            	else
            		System.out.println(pd_name+"\t\t\t"+dto.getSize()+"\t\t\t"+dto.getPrice());
            }

        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("============================================================================");
    }

	
}
