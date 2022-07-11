/* �� ���� ��ǰ ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_ProductDTO {

    private String category;
    private String name;
    private String size;
    private String product_num;
    private int price;
    private String company;
    private int stock;

    //�⺻������
    public DB2022_Team08_ProductDTO() {

    }

    //������
    public DB2022_Team08_ProductDTO(String product_num, String category, String name, String size,
                                    int price, String company, int stock) {
        super();
        this.category = category;
        this.name = name;
        this.size = size;
        this.product_num = product_num;
        this.price = price;
        this.company = company;
        this.stock = stock;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getProduct_num() {
        return product_num;
    }

    public void setProduct_num(String product_num) {
        this.product_num = product_num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "product_num=" + product_num + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price + '\'' +
                ", company='" + company + '\'' +
                ", stock=" + stock +
                '}';
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("[ " + product_num + " ] ��ǰ ����====\n");
        sb.append("��ǰ��: "+name+"\n");
        sb.append("��ǰī�װ� : "+category+"\n");
        sb.append("��ǰ������ : "+size+"\n");
        sb.append("�ǸŰ��� : "+price+"\n");
        sb.append("������ü : "+company+"\n");
        sb.append("��� : "+stock+"\n");

        return sb.toString();
    }

}

