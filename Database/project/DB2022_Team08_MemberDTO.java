import java.util.Formatter;

/* �� ���� ȸ�� ������ �����ϱ� ���� Ŭ����*/

public class DB2022_Team08_MemberDTO { 
	private String phone_num; //primary key (��ȭ��ȣ)
	private String name; //���̸�
	private Integer purchase; //���űݾ�
	private String grade; //ȸ�����
	
	public DB2022_Team08_MemberDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DB2022_Team08_MemberDTO(String phone_num, String name, Integer purchase, String grade) {
		super();
		this.phone_num = phone_num;
		this.name = name;
		this.purchase = purchase;
		this.grade = grade;
	}
	
	/*��ȭ��ȣ*/
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	/*�̸�*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*���űݾ�*/
	public Integer getPurchase() {
		return purchase;
	}
	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}
	
	/*ȸ�����*/
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	public String toString() {
		try (Formatter fm = new Formatter()) {
			return fm.toString();
		}
	}
	
	public String getInfo() { //ȸ�� ���� �ҷ�����
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[ " + phone_num + " ] ȸ������ ����====\n");
		sb.append("��   �� : " + name + "\n");
		sb.append("���� �ݾ� :" + purchase + "\n");
		sb.append("ȸ�� ��� : " + grade + "\n");
		return sb.toString();
	}
}