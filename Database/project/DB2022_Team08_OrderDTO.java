import java.sql.Time;
import java.sql.Date;
/* �� ���� ���� ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_OrderDTO {

    private String order_num;
    private int order_price;
    private Date order_date;
    private Time order_time;
    private int order_total; //���ּ���
    private String companyName;//������ü��
    private String contact_name; //����� �̸�
    private String company_phone;

    //�⺻������
    public DB2022_Team08_OrderDTO() {

    }

    public DB2022_Team08_OrderDTO(String order_num, int order_price, Date order_date, Time order_time, int order_total, String contact_name, String company_phone) {
        super();
        this.order_num = order_num;
        this.order_price = order_price;
        this.order_date = order_date;
        this.order_time = order_time;
        this.order_total = order_total;
        this.contact_name = contact_name;
        this.company_phone = company_phone;
    }
    
    public DB2022_Team08_OrderDTO(String order_num, int order_price, Date order_date, Time order_time, int order_total, String companyName, String contact_name, String company_phone) {
        super();
        this.order_num = order_num;
        this.order_price = order_price;
        this.order_date = order_date;
        this.order_time = order_time;
        this.order_total = order_total;
        this.companyName = companyName;
        this.contact_name = contact_name;
        this.company_phone = company_phone;
    }

    public int getOrder_total() {
        return order_total;
    }

    public void setOrder_total(int order_total) {
        this.order_total = order_total;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public int getOrder_price() {
        return order_price;
    }

    public void setOrder_price(int order_price) {
        this.order_price = order_price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Time getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Time order_time) {
        this.order_time = order_time;
    }

    public DB2022_Team08_OrderDTO(int order_total) {
        this.order_total = order_total;
    }

    public String getCompany_name() {
        return companyName;
    }

    public void setCompany_name(String companyName) {
        this.companyName = companyName;
    }
    
    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }


    @Override
    public String toString() {
        return "DB2022_Team08_OrderDTO{" +
                "order_num='" + order_num + '\'' +
                ", order_price=" + order_price +
                ", order_date=" + order_date +
                ", order_time=" + order_time +
                ", order_total=" + order_total +
                ", contact_name='" + contact_name + '\'' +
                ", company_phone='" + company_phone + '\'' +
                '}';
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("���ֻ�ǰ��ȣ: "+order_num+"\n");
        sb.append("���ֻ�ǰ���� : "+order_price+"\n");
        sb.append("���ֳ�¥ : "+order_date+"\n");
        sb.append("���ֽð� : "+order_time+"\n");
        sb.append("���ּ��� : "+order_total+"\n");
        sb.append("������̸� : "+contact_name+"\n");
        sb.append("������ü��ȭ��ȣ : "+company_phone+"\n");

        return sb.toString();
    }

}

