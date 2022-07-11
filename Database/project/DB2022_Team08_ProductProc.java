import java.util.List;
import java.util.Scanner;
/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */
public class DB2022_Team08_ProductProc {

    DB2022_Team08_ProductDAO dao;

    //�⺻������
    public DB2022_Team08_ProductProc() {
        dao = new DB2022_Team08_ProductDAO();


    }

    /**
     * ��ǰ ���ó��
     */
    public void insertProduct(){

        Scanner scn = new Scanner(System.in);

        System.out.println("��ǰ������ �Է����ּ���.");
        System.out.print("����ǰī�װ� : ");
        //String category = scn.nextLine();
        String category = reInput(scn);
        System.out.print("����ǰ�� : ");
        //String name = scn.nextLine();
        String name = reInput(scn);
        System.out.print("����ǰ������ : ");
        //String size = scn.nextLine();
        String size = reInput(scn);
        System.out.print("����ǰ��ȣ : ");
        //String product_num = scn.nextLine();
        String product_num = reInput(scn);
        System.out.print("����ǰ���� : ");
        //Integer price = scn.nextInt();
        int price = Integer.parseInt(reInput(scn));
        System.out.print("��������ü : ");
        //String company= scn.nextLine();
        String company = reInput(scn);
        System.out.print("����� : ");
        //Integer stock = scn.nextInt();
        int stock = Integer.parseInt(reInput(scn));

        DB2022_Team08_ProductDTO dto = new DB2022_Team08_ProductDTO(product_num,category,name,size,price,company,stock);
        boolean r = dao.insertProduct(dto); //�Է¹��� ������ �߰�

        if(r){
            System.out.println("��ǰ����� ���������� �Ϸ�Ǿ����ϴ�.");
        }else{
            System.out.println("��ǰ����� ���������� �̷����� �ʾҽ��ϴ�.");
        }

    }


    /**
     * ����� ��ǰ ��� ����
     */
    public void showProductList(){

        List<DB2022_Team08_ProductDTO> list = dao.getProductList();

        System.out.println("                             Product List");
        System.out.println("============================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("��ǰ��ȣ\tī�װ� \t  ��ǰ��\t\t��ǰ������\t��ǰ����\t  ������ü\t\t       ���");

            //System.out.println("��ǰ��ȣ\t\t   ī�װ� \t\t  ��ǰ��\t\t  ��ǰ������\t\t  ��ǰ����\t\t  ������ü\t\t  ���");
            System.out.println("============================================================================");

            for (DB2022_Team08_ProductDTO dto : list){
            	String pd_name = dto.getName(); String company_name = dto.getCompany();
            	if(pd_name.length()>=6)
            		if (company_name.length()>5)
            			System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t"+dto.getSize()+"\t"+dto.getPrice()
                    +"\t"+company_name+"\t"+dto.getStock());
            		else
            			System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t\t\t"+dto.getStock());
                else
                	if (company_name.length()>5)
                		System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t"+dto.getStock());
                	else
                	System.out.println(dto.getProduct_num()+"\t"+dto.getCategory()+"\t"+pd_name+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                        +"\t"+company_name+"\t\t\t"+dto.getStock());
            	
            	
                //System.out.println(dto.getProduct_num()+"\t\t"+dto.getCategory()+"\t"+dto.getName()+"\t\t"+dto.getSize()+"\t"+dto.getPrice()
                //+"\t\t"+dto.getCompany()+"\t"+dto.getStock());
            }

        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("====================================================================�� "+((list==null)?"0":list.size())+" ��=\n");
    }


    /**
     * ��ǰ ����.
     */
    public void updateProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("������ ��ǰ�� ��ǰ��ȣ�� �Է����ּ���");
        System.out.print("��");
        String no = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(no);
        if (dto!=null) {

            System.out.println(dto.getInfo());


            System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1�� �Է��ϸ� ������ ������ �״�� �����˴ϴ�.");
                System.out.print("�������� ī�װ� : ");
                String category = scn.nextLine();
                if(category.trim().equals("-1")) category=dto.getCategory();

                System.out.print("�������� ��ǰ�� : ");
                String name = scn.nextLine();
                if(name.trim().equals("-1")) name=dto.getName();

                System.out.print("�������� ������ : ");
                String size = scn.nextLine();
                if(size.trim().equals("-1")) size=dto.getSize();

                System.out.print("�������� ��ǰ���� : ");
                Integer price = scn.nextInt();
                if(price==-1) price=dto.getPrice();

                System.out.print("�������� ������ü : ");
                String company = scn.nextLine();
                if(company.trim().equals("-1")) company=dto.getCompany();

                System.out.print("�������� ��� : ");
                Integer stock = scn.nextInt();
                if(stock==-1) price=dto.getStock();

                dto =  new DB2022_Team08_ProductDTO(no,category,name,size,price,company,stock);
                boolean r = dao.updateProduct(dto);


                if(r){

                    System.out.println("��ǰ�� ������ ������ ���� �����Ǿ����ϴ�.");
                    System.out.println(dto.getInfo());

                }else{
                    System.out.println("��ǰ�� ������ ���������� ���� ���� �ʾҽ��ϴ�.");
                }

            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }

        }else{

            System.out.println("�Է��Ͻ� ��ǰ��ȣ�� �ش��ϴ� ��ǰ�� �������� �ʽ��ϴ�.");

        }
    }

    /**
     * ��ǰ ����
     */
    public void deleteProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("������ ��ǰ�� ��ǰ��ȣ�� �Է����ּ���");
        String no = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(no);
        if (dto!=null) {
            System.out.println(dto.getInfo());

            System.out.println("�� ��ǰ�� ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteProduct(no);

                if(r){
                    System.out.println("��ǰ"+no+"�� ������ ���������� �����Ǿ����ϴ�.");
                }else{
                    System.out.println("��ǰ�� ������ ���������� ���� ���� �ʾҽ��ϴ�.");
                }
            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }
        }else{

            System.out.println("�Է��Ͻ� ��ǰ��ȣ�� �ش��ϴ� ȸ���� �������� �ʽ��ϴ�.");

        }
    }


    /*��ǰ �˻�*/
    public void searchProduct(){

        Scanner scn = new Scanner(System.in);
        System.out.println("�˻��� ��ǰ�� ��ǰ��ȣ�� �Է����ּ���"); //����ڷκ��� �Է¹ޱ�
        String product_num = scn.nextLine();
        DB2022_Team08_ProductDTO dto = dao.getProduct(product_num);
        if (dto!=null)
            System.out.println(dto.getInfo());
        else
            System.out.println("�߸��� �Է��Դϴ�");
    }


    /**
     * �����Է½� ���Է�, �׽�Ʈ �޼ҵ�...
     */
    public String reInput(Scanner scn){

        String str="";
        while(true){
            str = scn.nextLine();
            if(!(str==null || str.trim().equals(""))){
                break;
            }else{
                System.out.println("������ �Է��ϽǼ������ϴ�. �ùٸ����� �Է����ּ���!");
                System.out.print("��");
            }
        }

        return str;
    }

}