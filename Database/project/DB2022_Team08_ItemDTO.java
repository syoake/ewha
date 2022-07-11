

import java.util.Formatter;
/* �� ���� ǰ�� ������ �����ϱ� ���� Ŭ����*/
public class DB2022_Team08_ItemDTO {
	
	private String recNum;
	private String itemNum;
	private String i_Name;
	private int amount;
	private int price;
	
	// �⺻ ������
	public DB2022_Team08_ItemDTO() {
		
	}
	
	// ������
	public DB2022_Team08_ItemDTO(String recNum, String itemNum, int amount, int price) {
		this.recNum = recNum;
		this.itemNum = itemNum;
		this.amount = amount;
		this.price = price;
	}
	
	public DB2022_Team08_ItemDTO(String recNum, String itemNum, String i_Name, int amount, int price) {
		this.recNum = recNum;
		this.itemNum = itemNum;
		this.i_Name = i_Name;
		this.amount = amount;
		this.price = price;
	}
	
	// getter, setter
	public String getRecNum() {
		return recNum;
	}
	
	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}
	
	public String getItemNum() {
		return itemNum;
	}
	
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	
	public String getItemName() {
		return i_Name;
	}
	
	public void setItemName(String i_Name) {
		this.i_Name = i_Name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		Formatter fm = new Formatter();
		String recInfo = fm.format("%5s\t  %-16s\t%-14d\t%-14d", recNum, itemNum, amount, price).toString();
		return recInfo;
	}
	
	public String getInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append("[" + recNum + "] ������ ǰ�� ���� ====\n");
		sb.append("ǰ�� ��ȣ: " + itemNum + "\n");
		sb.append("����: " + amount + "\n");
		sb.append("����: " + price + "\n");
		
		return sb.toString();
	}
}
