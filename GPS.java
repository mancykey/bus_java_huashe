package chuzhou;

public class GPS {
	private String DateTime;
	private String Date;
	private int Bus_ID;
	private String Lng;
	private String Lat;
	private int Line_ID;
	private String Speed;
	private String Azimuth;
	public GPS() {
		// TODO Auto-generated constructor stub
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getBus_ID() {
		return Bus_ID;
	}
	public void setBus_ID(int bus_ID) {
		Bus_ID = bus_ID;
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
	public int getLine_ID() {
		return Line_ID;
	}
	public void setLine_ID(int line_ID) {
		Line_ID = line_ID;
	}
	public String getSpeed() {
		return Speed;
	}
	public void setSpeed(String speed) {
		Speed = speed;
	}
	public String getAzimuth() {
		return Azimuth;
	}
	public void setAzimuth(String azimuth) {
		Azimuth = azimuth;
	}
	@Override
	public String toString() {
		return "GPS [DateTime=" + DateTime + ", Bus_ID=" + Bus_ID + ", Lng=" + Lng + ", Lat=" + Lat + ", Line_ID="
				+ Line_ID + ", Speed=" + Speed + ", Azimuth=" + Azimuth + "]";
	}

}
