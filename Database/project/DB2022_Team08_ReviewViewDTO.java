import java.util.Formatter;

/* �� ���� ���� ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_ReviewViewDTO {

	
	private Integer stars; //����
	private String write_date; //�ۼ�����
	
	public DB2022_Team08_ReviewViewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DB2022_Team08_ReviewViewDTO(Integer stars, String write_date) {
		super();
		 
		this.stars = stars;
		this.write_date = write_date;
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
		sb.append("====���� ����====\n");
		
		sb.append("��    �� : " + stars + "\t");
		sb.append("�ۼ� ���� : " + write_date + "\n");
			
		return sb.toString();
	}

}
