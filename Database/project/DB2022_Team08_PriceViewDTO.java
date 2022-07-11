/* �� ���� (���ѵ�) ��ǰ ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_PriceViewDTO {

    
    private String name;
    private String size;
    private int price;

    //�⺻������
    public DB2022_Team08_PriceViewDTO() {

    }

    //������
    public DB2022_Team08_PriceViewDTO(String name, String size,
                                    int price) {
        super();
        this.name = name;
        this.size = size;
        this.price = price;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "PriceViewDTO{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("[ " + name + " ] ��ǰ ����====\n");
        sb.append("��ǰ��: "+name+"\n");
        
        sb.append("��ǰ������ : "+size+"\n");
        sb.append("�ǸŰ��� : "+price+"\n");
       
   

        return sb.toString();
    }

}

