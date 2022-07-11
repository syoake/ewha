import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

/* ����ڷκ��� �Է¹��� ���� �������� ó���ϴ� Ŭ���� */
public class DB2022_Team08_OrderProc {

    DB2022_Team08_OrderDAO dao;

    //�⺻������
    public DB2022_Team08_OrderProc() {
        dao = new DB2022_Team08_OrderDAO();

    }

    /**
     * ���� ���ó��
     */
    public void insertOrder(){

        Scanner scn = new Scanner(System.in);

        /*
         * YYYY-MM-DD hh:mm:ss*/
        System.out.println("���������� �Է����ּ���.");
        System.out.print("�����ֻ�ǰ��ȣ : ");
        //String orderNum = scn.nextLine();
        String orderNum = reInput(scn);
        System.out.print("�����ֻ�ǰ���� : ");
        //int orderPrice = Integer.parseInt(scn.nextLine());
        int orderPrice = Integer.parseInt(reInput(scn));
        System.out.print("�����ֳ�¥(YYYY-MM-DD�������� �Է�) : ");
        //Date orderDate = Date.valueOf(scn.nextLine());
        Date orderDate = Date.valueOf(reInput(scn));
        System.out.print("�����ֽð�(hh:mm:ss�������� �Է�) : ");
        //Time orderTime = Time.valueOf(scn.nextLine());
        Time orderTime = Time.valueOf(reInput(scn));
        System.out.print("�����ּ��� : ");
        //int orderTotal = Integer.parseInt(scn.nextLine());
        int orderTotal = Integer.parseInt(reInput(scn));
        System.out.print("��������̸� : ");
        //String contactName = scn.nextLine();
        String contactName = reInput(scn);
        System.out.print("��������ü ��ȭ��ȣ : ");
        //String companyPhone = scn.nextLine();
        String companyPhone = reInput(scn);

        DB2022_Team08_OrderDTO dto = new DB2022_Team08_OrderDTO(orderNum,orderPrice,orderDate,orderTime,orderTotal,contactName,companyPhone);
        
        
        
        boolean r = dao.insertOrder(dto); //�Է¹��� ������ �߰�

        if(r){
            System.out.println("��ǰ����� ���������� �Ϸ�Ǿ����ϴ�.");
        }else{
            System.out.println("��ǰ����� ���������� �̷����� �ʾҽ��ϴ�.");
        }

    }


    /**
     * ����� ���� ��� ����
     */
    public void showOrderList(){

        List<DB2022_Team08_OrderDTO> list = dao.getOrderList();

        System.out.println("                                     Order List");
        System.out.println("====================================================================================================");
        if(list!=null&&list.size()>0){
            System.out.println("���ֻ�ǰ��ȣ\t���ְ���\t���ֳ�¥\t\t���ֽð�\t\t���ּ���\t������ü\t\t������̸�\t������ü��ȭ��ȣ");
            System.out.println("====================================================================================================");

            for (DB2022_Team08_OrderDTO dto : list){
            	String company = dto.getCompany_name();
            	if (company.length()>5)
            		System.out.println(dto.getOrder_num()+"\t\t"+dto.getOrder_price()+"\t"+dto.getOrder_date()+"\t"+dto.getOrder_time()+"\t"+dto.getOrder_total()+"\t"+company+" "+dto.getContact_name()+"\t"+dto.getCompany_phone());
            	else
            		System.out.println(dto.getOrder_num()+"\t\t"+dto.getOrder_price()+"\t"+dto.getOrder_date()+"\t"+dto.getOrder_time()+"\t"+dto.getOrder_total()+"\t"+company+"\t\t"+dto.getContact_name()+"\t"+dto.getCompany_phone());
            }

        }else{
            System.out.println("����� �����Ͱ� �����ϴ�. ");
        }
        System.out.println("==============================================================================================�� "+((list==null)?"0":list.size())+" ��=\n");
    }


    /**
     * ���� ����.
     */
    public void updateOrder(){

        Scanner scn = new Scanner(System.in);
        System.out.println("������ ������ ���ֳ�¥�� �Է����ּ���(YYYY-MM-DD�������� �Է�)");
        System.out.print("��");
        Date date = Date.valueOf(scn.nextLine());
        System.out.println("������ ������ ���ֽð��� �Է����ּ���(hh:mm:ss�������� �Է�)");
        System.out.print("��");
        Time time = Time.valueOf(scn.nextLine());
        DB2022_Team08_OrderDTO dto = dao.getOrder(date, time);
        if (dto!=null) {

            System.out.println(dto.getInfo());


            System.out.println("�����۾��� ����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                System.out.println("-1�� �Է��Ͻø� ������ ������ �״�� �����˴ϴ�.");

                System.out.print("�������� ���ֻ�ǰ��ȣ : ");
                String num = scn.nextLine();
                if(num.trim().equals("-1")) num=dto.getOrder_num();

                System.out.print("�������� ���ְ��� : ");
                Integer price = scn.nextInt();
                if(price==-1) price=dto.getOrder_price();
                
                /*System.out.print("�������� ���ֳ�¥ : ");
                Date orderDate = Date.valueOf(scn.next());
                if(date.equals(0000-00-00)) date=dto.getOrder_date();
                date=dto.getOrder_date();

                System.out.print("�������� ���ֽð� : ");
                Time orderTime = Time.valueOf(scn.next());
                if(time.equals("00:00:00")) time=dto.getOrder_time();*/


                System.out.print("�������� ���ּ��� : ");
                Integer total = scn.nextInt();
                if(total==-1) total=dto.getOrder_total();

                System.out.print("�������� ������̸� : ");
                String contactName = scn.nextLine();
                if(contactName.trim().equals("-1")) contactName=dto.getContact_name();

                System.out.print("�������� ������ü�̸� : ");
                String companyName = scn.nextLine();
                if(companyName.trim().equals("-1")) companyName=dto.getCompany_phone();

                dto =  new DB2022_Team08_OrderDTO(num,price,date,time,total,contactName,companyName);

                boolean r = dao.updateOrder(dto);

                if(r){

                    System.out.println("������ ������ ������ ���� �����Ǿ����ϴ�.");
                    System.out.println(dto.getInfo());

                }else{
                    System.out.println("������ ������ ���������� ���� ���� �ʾҽ��ϴ�.");
                }

            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }

        }else{

            System.out.println("�Է��Ͻ� ���ֹ�ȣ�� �ش��ϴ� ���ְ� �������� �ʽ��ϴ�.");

        }
    }

    /**
     * ���� ����
     */
    public void deleteOrder(){

        Scanner scn = new Scanner(System.in);
        System.out.println("������ ������ ���ֳ�¥�� �Է����ּ���(YYYY-MM-DD�������� �Է�)");
        Date date = Date.valueOf(scn.nextLine());
        System.out.println("������ ������ ���ֽð��� �Է����ּ���(hh:mm:ss�������� �Է�)");
        Time time= Time.valueOf(scn.nextLine());
        DB2022_Team08_OrderDTO dto = dao.getOrder(date, time);
        if (dto!=null) {
            System.out.println(dto.getInfo());

            System.out.println("�� ������ ������ ������ �����Ͻðڽ��ϱ�?(Y/N)");
            String input = scn.nextLine();
            if(input.equalsIgnoreCase("y")){
                boolean r = dao.deleteOrder(date, time);

                if(r){
                    System.out.println(date+" "+time+"�� ���� ������ ���������� �����Ǿ����ϴ�.");
                }else{
                    System.out.println("������ ������ ���������� ���� ���� �ʾҽ��ϴ�.");
                }
            }else{
                System.out.println("���� �۾��� ����Ͽ����ϴ�.");
            }
        }else{

            System.out.println("�Է��Ͻ� ���ֹ�ȣ�� �ش��ϴ� ���ְ� �������� �ʽ��ϴ�.");

        }
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