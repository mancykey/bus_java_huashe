package chuzhou;

public class IC {
	private int No;
	private String Userid;
	private String CardId;
	private String CardType;
	private String Danhao;
	private int Line;
	private String BusID;
	private String DateTime;
	private String Month;
	private int Money;
	
	public IC() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String cardType) {
		CardType = cardType;
	}

	public String getDanhao() {
		return Danhao;
	}

	public void setDanhao(String danhao) {
		Danhao = danhao;
	}

	public int getLine() {
		return Line;
	}

	public void setLine(int line) {
		Line = line;
	}

	public String getBusID() {
		return BusID;
	}

	public void setBusID(String busID) {
		BusID = busID;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getMoney() {
		return Money;
	}

	public void setMoney(int money) {
		Money = money;
	}

	@Override
	public String toString() {
		return "IC [Userid=" + Userid + ", CardId=" + CardId + ", CardType=" + CardType + ", Danhao="
				+ Danhao + ", Line=" + Line + ", BusID=" + BusID + ", DateTime=" + DateTime + ", Money=" + Money + "]";
	}



}
