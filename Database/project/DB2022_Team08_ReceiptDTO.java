

import java.util.Formatter;
/* �� ���� ������ ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_ReceiptDTO {
	
	private String num;
	private String phoneNum;
	private int totalPrice;
	private String date;
	
	// �⺻ ������
	public DB2022_Team08_ReceiptDTO() {
		
	}
	
	// ������
	public DB2022_Team08_ReceiptDTO(String num, String phoneNum, int totalPrice, String date) {
		this.num = num;
		this.phoneNum = phoneNum;
		this.totalPrice = totalPrice;
		this.date = date;
	}
	
	// getter, setter
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		Formatter fm = new Formatter();
		String recInfo = fm.format("%5s\t  %-16s\t%-14d\t%-14s", num, phoneNum, totalPrice, date).toString();
		return recInfo;
	}
	
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[" + num + "] ������ ���� ====\n");
		sb.append("��ȭ��ȣ: " + phoneNum + "\n");
		sb.append("�� �����ݾ�: " + totalPrice + "\n");
		sb.append("�Ǹ� ����: " + date + "\n");
		
		return sb.toString();
	}

}
