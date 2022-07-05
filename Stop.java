package chuzhou;

public class Stop {
	private int Phy_Stop_ID;
	private String Stop_Name;
	private String Line_Name;
	private String Line_Direction;
	private String Lng;
	private String Lat;
	private String Pass_Line_Name;

	public int getPhy_Stop_ID() {
		return Phy_Stop_ID;
	}
	public void setPhy_Stop_ID(int phy_Stop_ID) {
		Phy_Stop_ID = phy_Stop_ID;
	}
	public String getLine_Name() {
		return Line_Name;
	}
	public void setLine_Name(String line_Name) {
		Line_Name = line_Name;
	}
	public String getLine_Direction() {
		return Line_Direction;
	}
	public void setLine_Direction(String line_Direction) {
		Line_Direction = line_Direction;
	}
	public String getStop_Name() {
		return Stop_Name;
	}
	public void setStop_Name(String stop_Name) {
		Stop_Name = stop_Name;
	}

	public String getLng() {
		return Lng;
	}
	public void setLng(String lng) {
		Lng = lng;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getPass_Line_Name() {
		return Pass_Line_Name;
	}
	public void setPass_Line_Name(String pass_Line_Name) {
		Pass_Line_Name = pass_Line_Name;
	}

}
