package chuzhou;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class transfer {
	 
//    public static void main(String[] args) throws IOException {
//    	String inpath="Input\\geo�ļ�\\�����й������߾�γ��(����6����).csv";
//		File file=new File(inpath);//inpath
//		FileWriter fw =new FileWriter("Output\\�����й���������������_84(����6����).csv");//
//		BufferedWriter bw=new BufferedWriter(fw);
//		if(!file.exists()) {
//			System.out.print("�ļ������ڣ�");
//		}else {
//			System.out.print("�ļ����ڣ�");
//			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
//			String line=null;
//			line=reader.readLine();
//			System.out.print(line);
//			String[] row0=line.split(",",-1);
//			bw.write("line_name"+ ","+"lng"+ ","+"lat"+ ",");
//			bw.newLine();
//			if (row0.length==3) {
//				while((line=reader.readLine())!=null) {
//					String[] row=line.split(",",-1);
//					Double lat = Double.parseDouble(row[1]);
//			        Double lon = Double.parseDouble(row[2]);
//			        double[] doubleArr1 = gcj02_To_Wgs84(lat, lon);
//			        System.out.println(row[0]+ ","+doubleArr1[0]+"===="+doubleArr1[1]);
//			        bw.write(row[0]+ ","+doubleArr1[0]+ ","+doubleArr1[1]+ ",");
//					bw.newLine();
//	    			
//				}
//			}
//		}
//		bw.close();
//        
//    }
	
//    public static void main(String[] args) throws IOException {
//    	String inpath="Input\\վ������ת��\\Phy_Stop.csv";
//		File file=new File(inpath);//inpath
//		FileWriter fw =new FileWriter("Output\\��·\\Phy_Stop.csv");//
//		BufferedWriter bw=new BufferedWriter(fw);
//		if(!file.exists()) {
//			System.out.print("�ļ������ڣ�");
//		}else {
//			System.out.print("�ļ����ڣ�");
//			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
//			String line=null;
//			line=reader.readLine();
//			System.out.print(line);
//			String[] row0=line.split(",",-1);
//			bw.write("Phy_Stop_ID"+ ","+"Stop_Name"+ ","+"Lng"+ ","+"Lat"+ ","+"Pass_Line_Name"+ ",");
//			bw.newLine();
//			if (row0.length==5) {
//				while((line=reader.readLine())!=null) {
//					String[] row=line.split(",",-1);
//					Double lat = Double.parseDouble(row[2]);
//			        Double lon = Double.parseDouble(row[3]);
//			        double[] doubleArr1 = gcj02_To_Wgs84(lat, lon);
//			        System.out.println(row[0]+ ","+doubleArr1[0]+"===="+doubleArr1[1]);
//			        bw.write(row[0]+ ","+row[1]+ ","+doubleArr1[0]+ ","+doubleArr1[1]+ ","+row[4]+ ",");
//					bw.newLine();
//	    			
//				}
//			}
//		}
//		bw.close();
//        
//    }
    
    /**
     *�������ձ��ĸߵ�����ת��Ϊ84����
     */
    public static void main(String[] args) throws IOException {
    	String inpath="Input\\վ������ת��\\�����ձ�\\����վ�㾭γ�ȣ��ߵ����꣩.csv";
		File file=new File(inpath);//inpath
		FileWriter fw =new FileWriter("Output\\�����ձ�\\����վ�㾭γ�ȣ�84���꣩.csv");//
		BufferedWriter bw=new BufferedWriter(fw);
		if(!file.exists()) {
			System.out.print("�ļ������ڣ�");
		}else {
			System.out.print("�ļ����ڣ�");
			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
			String line=null;
			line=reader.readLine();
			System.out.print(line);
			String[] row0=line.split(",",-1);
			bw.write("��Ŧ"+ ","+"����"+ ","+"γ��"+ ",");
			bw.newLine();
			int j=0;
			if (row0.length==3 ) {
				while((line=reader.readLine())!=null& j<166) {
					String[] row=line.split(",",-1);
					System.out.println(row[1]);
					if(row[1]!="0") {
						Double lat = Double.parseDouble(row[1]);
				        Double lon = Double.parseDouble(row[2]);
				        double[] doubleArr1 = gcj02_To_Wgs84(lat, lon);
				        System.out.println(row[0]+ ","+doubleArr1[0]+"===="+doubleArr1[1]);
				        bw.write(row[0]+ ","+doubleArr1[0]+ ","+doubleArr1[1]+ ",");
						bw.newLine();
					}else {
						bw.write(row[0]+ ","+row[1]+ ","+row[2]+ ",");
						bw.newLine();
					}
					j++;
				}
			}
			bw.close();
		}
		  
    }
 
    /**
     * a
     */
    public final static double a = 6378245.0;
    /**
     * ee
     */
    public final static double ee = 0.00669342162296594323;
 
    //Բ���� GCJ_02_To_WGS_84
    public final static double pi = 3.14159265358979324;
    /**
     * @Description WGS84 to ��������ϵ (GCJ-02)
     * @param lon  ����
     * @param lat  γ��
     * @return
     */
    public static double[] wgs84_To_Gcj02(double lon, double lat) {
        if (outOfChina(lat, lon)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLon, mgLat };
    }
 
    /**
     * @Description ��������ϵ (GCJ-02) to WGS84
     * @param lon
     * @param lat
     * @return
     */
    public static double[] gcj02_To_Wgs84(double lon, double lat) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[] { lontitude, latitude };
    }
 
    /**
     * @Description ��������ϵ (GCJ-02) to �ٶ�����ϵ (BD-09)
     * @param gg_lon
     * @param gg_lat
     * @return
     */
    public static double[] gcj02_To_Bd09(double gg_lon, double gg_lat) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[] { bd_lon, bd_lat };
    }
 
    /**
     * @Description �ٶ�����ϵ (BD-09) to ��������ϵ (GCJ-02)
     * @param bd_lon
     * @param bd_lat
     * @return
     */
    public static double[] bd09_To_Gcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new double[] { gg_lon, gg_lat };
    }
    /**
     * @Description �ٶ�����ϵ (BD-09) to WGS84
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bd09_To_Wgs84(double bd_lon,double bd_lat) {
 
        double[] gcj02 = transfer.bd09_To_Gcj02(bd_lon, bd_lat);
        double[] map84 = transfer.gcj02_To_Wgs84(gcj02[0], gcj02[1]);
        return map84;
 
    }
 
    /**
     * @Description �ж��Ƿ����й���Χ��
     * @param lat
     * @param lon
     * @return
     */
    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }
 
    /**
     * @Description transform
     * @param lat
     * @param lon
     * @return
     */
    private static double[] transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] { lat, lon };
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLat, mgLon };
    }
 
    /**
     * @Description transformLat
     * @param x
     * @param y
     * @return
     */
    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }
 
    /**
     * @Description transformLon
     * @param x
     * @param y
     * @return
     */
    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }
 
    /**
     * GPS��γ��ת��Ϊ �ȣ�3114.1717,12122.1067 �� 121.37300779��31.23436014��
     * @param dms ����
     * @param type ��������  E/N
     * @return String ������ľ�γ��
     */
    public static String gpsToWgs84(String dms, String type) {
        if (dms == null || dms.equals("")) {
            return "0.0";
        }
        double result = 0.0D;
        String temp = "";
 
        if (type.equals("E")) {//����
            String e1 = dms.substring(0, 3);//��ȡ3λ���֣����ȹ�3λ�����180��
            //������һ�׶�Ϊ�����ϱ���������Ϊ0��,����������������180��
            String e2 = dms.substring(3, dms.length());//��Ҫ�����С��
 
            result = Double.parseDouble(e1);
/*            System.out.println("e2===="+e2);
            System.out.println("===="+Double.parseDouble(e2) / 60.0D);*/
            result += (Double.parseDouble(e2) / 60.0D);
            temp = String.valueOf(result);
            if (temp.length() > 11) {
                temp = e1 + temp.substring(temp.indexOf("."), 11);
            }
        } else if (type.equals("N")) {        //γ�ȣ�γ�����Գ��Ϊ��׼,�൱�ڰѵ��������,�����������ϵĵ��ƽ��н�0~90��
            String n1 = dms.substring(0, 2);//��ȡ2λ��γ�ȹ�2λ�����90��
            String n2 = dms.substring(2, dms.length());
 
            result = Double.parseDouble(n1);
/*            System.out.println("n2===="+n2);
            System.out.println("===="+Double.parseDouble(n2) / 60.0D);*/
            result += Double.parseDouble(n2) / 60.0D;
            temp = String.valueOf(result);
            if (temp.length() > 10) {
                temp = n1 + temp.substring(temp.indexOf("."), 10);
            }
        }
        return temp;
    }
 
}

