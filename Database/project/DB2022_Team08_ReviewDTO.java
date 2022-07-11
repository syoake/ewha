import java.util.Formatter;

/* �� ���� ���� ������ �����ϱ� ���� Ŭ����*/

public class DB2022_Team08_ReviewDTO {
	private String receipt_num; //primary key (��������ȣ)
	private String visit_date; //�湮����
	private String writer; //�ۼ���(foreign key - ȸ�� ���̺��� ��ȭ��ȣ)
	private Integer stars; //����
	private String write_date; //�ۼ�����
	
	public DB2022_Team08_ReviewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DB2022_Team08_ReviewDTO(String receipt_num, String visit_date, String writer, Integer stars, String write_date) {
		super();
		this.receipt_num = receipt_num;
		this.visit_date = visit_date;
		this.writer = writer; 
		this.stars = stars;
		this.write_date = write_date;
	}
	
	/*��������ȣ*/
	public String getReceipt_num() {
		return receipt_num;
	}
	public void setReceipt_num(String receipt_num) {
		this.receipt_num = receipt_num;
	}
	
	/*�湮����*/
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	
	/*�ۼ���*/
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	/*����*/
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	
	/*�ۼ�����*/
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
	
	public String toString() {
		try (Formatter fm = new Formatter()) {
			return fm.toString();
		}
	}
	
	
	public String getInfo() { //���� ���� �ҷ�����
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[ " + receipt_num + " ] ���� ����====\n");
		sb.append("�湮 ���� : " + visit_date + "\n");
		sb.append("�� �� �� :" + writer + "\n");
		sb.append("��    �� : " + stars + "\n");
		sb.append("�ۼ� ���� : " + write_date + "\n");
			
		return sb.toString();
	}

}
