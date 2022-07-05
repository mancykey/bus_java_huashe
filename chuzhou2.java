package chuzhou;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;



public class chuzhou2 {

	public static List<IC> readTxt(String inpath) {
//		��ȡ����ļ�:��ѭ�������ļ�������Ϊһ��list�����ж��ѭ������
		List<IC> list_text=new ArrayList<IC>();
	    try {
	    	File file=new File(inpath);//inpath
	    	if(!file.exists()) {
	    		System.out.print("�ļ������ڣ�");
	    	}else {
	    		System.out.print("�ļ����ڣ�");
	    		BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
	    		String line=null;
	    		line=reader.readLine();
	    		String[] row0=line.split(",",-1);
	    		if (row0.length==7) {
	    			while((line=reader.readLine())!=null) {
	    				String[] row=line.split(",",-1);
		    			IC ic=new IC();
		    			ic.setNo(Integer.parseInt(row[0]));
		    			ic.setCardId(row[1]);
		    			ic.setCardType(row[2]);
		    			ic.setLine(Integer.parseInt(row[3]));
		    			ic.setBusID(row[4]);
		    			ic.setDateTime(row[5]);
		    			if(row[6]=="") {
		    				ic.setMoney(0);
		    			}else {
		    				ic.setMoney(Integer.parseInt(row[6]));
		    			}
//		    			System.out.println(ic.toString());
		    			list_text.add(ic);
	    			}
	    		}if(row0.length==9){
	    			while((line=reader.readLine())!=null) {
	    				String[] row=line.split(",",-1);
		    			IC ic=new IC();
		    			ic.setNo(Integer.parseInt(row[0]));
		    			ic.setUserid(row[1]);
		    			if(row[2]=="") {
		    				ic.setCardId("0");
		    			}else {
		    				ic.setCardId(row[2]);
		    			}
		    			if(row[3]==""||row[3]=="T0341100") {
		    				ic.setCardType("100");
		    			}else {
		    				ic.setCardType(row[3]);
		    			}
		    			ic.setDanhao(row[4]);
		    			ic.setLine(Integer.parseInt(row[5]));
		    			ic.setBusID(row[6]);
		    			ic.setDateTime(row[7]);
		    			ic.setMoney(Integer.parseInt(row[8]));
//		    			System.out.println(ic.toString());
		    			list_text.add(ic);
	    			}

	    		}
	    	}	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return list_text;
	}
	
	public static List<IC> add_filename(int[] file_num) {
		List<String> filename = new ArrayList<String>();
		filename.add("Input\\ʵ�忨\\202103.txt");
		filename.add("Input\\ʵ�忨\\ʵ�忨21-09.txt");
		filename.add("Input\\ʵ�忨\\202112.txt");
		
		filename.add("Input\\΢��\\΢����ϸ2021-03û������.txt");
		filename.add("Input\\΢��\\΢��21-09.txt");
		filename.add("Input\\΢��\\΢����ϸ2021-12.txt");
		
		filename.add("Input\\��֧��\\��֧����ϸ2021-03.txt");
		filename.add("Input\\��֧��\\��֧��21-09 (2).txt");
		filename.add("Input\\��֧��\\��֧����ϸ2021-12.txt");
		
		filename.add("Input\\�����ѻ���\\�����ѻ���2021-03.txt");
		filename.add("Input\\�����ѻ���\\�����ѻ���21-09.txt");
		filename.add("Input\\�����ѻ���\\�����ѻ���2021-12.txt");
		
		filename.add("Input\\����������\\����������2021-03.txt");
		filename.add("Input\\����������\\������21-09.txt");
		filename.add("Input\\����������\\����������2021-12.txt");
		
		filename.add("Input\\֧����1\\֧������ϸ2021-03.txt");
		filename.add("Input\\֧����1\\֧����21-09.txt");
		filename.add("Input\\֧����1\\֧������ϸ2021-12.txt");
	    
	    List<IC> list_IC_all=new ArrayList<IC>();
	    for (int i:file_num) {
	    	IC ic=new IC();
	    	String inpath=filename.get(i);
	    	List<IC> list_ic1=chuzhou2.readTxt(inpath);
	    	list_IC_all.addAll(list_ic1);
	    }
	    return list_IC_all;
	}

	
	/***
	 * ȥ��:�������ڣ��ֿ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static List<IC> quchong(List<IC> a_list) throws IOException {
		System.out.println("ȥ��ǰ��"+a_list.size()+"/");
		List<IC> collect1 = a_list.stream().collect(Collectors.collectingAndThen(
	               Collectors.toCollection(() -> new TreeSet<>(
	                       Comparator.comparing(p-> p.toString()))), ArrayList::new));
		System.out.println("ȥ�غ�"+collect1.size()+"/");
		return collect1;
	}
	/***
	 * �������:�������ڣ��ֿ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby1(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\����-�����ڷֿ�������.csv");//ʵ�忨-���·ֿ�������,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<String> list_TYPE0 = a_list.stream().map(IC::getCardType).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		List<String> list_TYPE = list_TYPE0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		Collections.sort(list_TYPE);
		list_Date.forEach(e -> System.out.println("�������:" +e));
		bw.write("����"+ ",");
		list_TYPE.forEach(e ->{
			try {
				bw.write(e+"," );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		bw.newLine();
		for(String x:list_Date){
			List<IC> list_yika=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
			bw.write(x+ ",");
			for(String y:list_TYPE) {
				List<IC> list_yika2=list_yika.stream().filter(d->d.getCardType().equals(y)).collect(Collectors.toList());
				bw.write(list_yika2.size()+ ",");		
			}
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:�������ڣ��ֿ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby2(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\������-�����ڷֿ�����ͳ��ˢ����.csv");//ʵ�忨-���·ֿ�������,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<String> list_TYPE0 = a_list.stream().map(IC::getCardType).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		List<String> list_TYPE = list_TYPE0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		Collections.sort(list_TYPE);
		list_Date.forEach(e -> System.out.println("�������:" +e));
		bw.write("����"+ ",");
		list_TYPE.forEach(e ->{
			try {
				bw.write(e+"," );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bw.newLine();
		for(String x:list_Date){
			List<IC> list_yika1=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
			List<IC> list_yika = list_yika1.stream().collect(Collectors.collectingAndThen(
		               Collectors.toCollection(() -> new TreeSet<>(
		                       Comparator.comparing(p-> p.getCardId()))), ArrayList::new));
			bw.write(x+ ",");
			for(String y:list_TYPE) {
				List<IC> list_yika2=list_yika.stream().filter(d->d.getCardType().equals(y)).collect(Collectors.toList());
				bw.write(list_yika2.size()+ ",");		
			}
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:�������ڣ��ֿ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby3(List<IC> a_list) throws IOException {
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<Integer> list_TYPE0 = a_list.stream().map(IC::getLine).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		List<Integer> list_TYPE = list_TYPE0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		Collections.sort(list_TYPE);
		list_TYPE.forEach(e -> System.out.println("�������:" +e));

//		for(String x:list_Date){
//			List<IC> list_yika=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
//			int t=0;
//			for(String y:list_TYPE) {
//				List<IC> list_yika2=list_yika.stream().filter(d->d.getCardId().equals(y)).collect(Collectors.toList());		
//				if(list_yika2.size()>0) {
//					t++;	
//				}	
//			}
//			System.out.println(t);
//		}
	}
	/***
	 * �������:�������ڣ���ʱ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby4(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\�����ڷ�ʱ��ͳ��ˢ����.csv");//ʵ�忨-���·ֿ�������,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		a_list.forEach(item -> item.setDateTime(item.getDateTime().substring(11, 13)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<String> list_TYPE0 = a_list.stream().map(IC::getDateTime).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		List<String> list_TYPE = list_TYPE0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		Collections.sort(list_TYPE);
		list_Date.forEach(e -> System.out.println("�������:" +e));
		bw.write("����"+ ",");
		list_TYPE.forEach(e ->{
			try {
				bw.write(e+"," );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bw.newLine();
		for(String x:list_Date){
			List<IC> list_yika=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
			bw.write(x+ ",");
			for(String y:list_TYPE) {
				List<IC> list_yika2=list_yika.stream().filter(d->d.getDateTime().equals(y)).collect(Collectors.toList());
				bw.write(list_yika2.size()+ ",");		
			}
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:�������ڣ���·������
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby5(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\�����ڷ�·������ͳ��ˢ����.csv");//ʵ�忨,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		list_Date.forEach(e -> System.out.println("�������:" +e));
		bw.write("����"+ ","+"���й���"+ ","+"�Ǽʹ���"+ ",");
		bw.newLine();
		for(String x:list_Date){
			List<IC> list_yika=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
			bw.write(x+ ",");
			List<IC> list_yika2=list_yika.stream().filter(d->d.getLine()<28|d.getLine()==901).collect(Collectors.toList());
			List<IC> list_yika3=list_yika.stream().filter(d->d.getLine()>=28&d.getLine()!=901).collect(Collectors.toList());
			bw.write(list_yika2.size()+ ","+list_yika3.size()+ ",");		
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:�������ڣ�������
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby6(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\9����������·�ֿ�����.csv");//ʵ�忨-���·ֿ�������,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<Integer> list_Line0=a_list.stream().map(IC::getLine).collect(Collectors.toList());
		List<String> list_Type0=a_list.stream().map(IC::getCardType).collect(Collectors.toList());
		List<Integer> list_Line = list_Line0.stream().distinct().collect(Collectors.toList());
		List<String> list_Type = list_Type0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Line);
		list_Line.forEach(e -> System.out.println("�������:" +e));
		bw.write("��·"+ ",");
		list_Type.forEach(e ->{
			try {
				bw.write(e+"," );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		bw.newLine();
		for(int x:list_Line){
			List<IC> list_yika=a_list.stream().filter(d->d.getLine()==x).collect(Collectors.toList());
			bw.write(x+ ",");
			for(String y:list_Type) {
				List<IC> list_yika2=list_yika.stream().filter(d->d.getCardType().equals(y)).collect(Collectors.toList());
				bw.write(list_yika2.size()+ ",");		
			}
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:��·����ʱ���
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void groupby7(List<IC> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\����·��ʱ��ͳ��ˢ����.csv");//ʵ�忨-���·ֿ�������,΢�ţ���֧���������ѻ��룬��������֧����
		BufferedWriter bw=new BufferedWriter(fw);
		a_list.forEach(item -> item.setDateTime(item.getDateTime().substring(11, 13)));
		List<Integer> list_Month0=a_list.stream().map(IC::getLine).collect(Collectors.toList());
		List<String> list_TYPE0 = a_list.stream().map(IC::getDateTime).collect(Collectors.toList());
		List<Integer> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		List<String> list_TYPE = list_TYPE0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		Collections.sort(list_TYPE);
		list_Date.forEach(e -> System.out.println("�������:" +e));
		bw.write("����"+ ",");
		list_TYPE.forEach(e ->{
			try {
				bw.write(e+"," );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		bw.newLine();
		for(Integer x:list_Date){
			List<IC> list_yika=a_list.stream().filter(d->d.getLine()==x).collect(Collectors.toList());
			bw.write(x+ ",");
			for(String y:list_TYPE) {
				List<IC> list_yika2=list_yika.stream().filter(d->d.getDateTime().equals(y)).collect(Collectors.toList());
				bw.write(list_yika2.size()+ ",");		
			}
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:��һ��վ�����·�ϲ�
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static List<Stop> read_line_stops() throws IOException {
		String inpath="Input\\ԭʼվ����Ϣ.csv";
		File file=new File(inpath);//inpath
		List<Stop> list_text=new ArrayList<Stop>();
		if(!file.exists()) {
			System.out.print("�ļ������ڣ�");
		}else {
			System.out.print("�ļ����ڣ�");
			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
			String line=null;
			line=reader.readLine();
			String[] row0=line.split(",",-1);
			if (row0.length==6) {
				while((line=reader.readLine())!=null) {
					String[] row=line.split(",",-1);
	    			Stop stop=new Stop();
	    			stop.setLine_Name(row[0]);
	    			stop.setLine_Direction(row[1]);
	    			stop.setStop_Name(row[3]);
	    			if(row[4]!="") {
	    				stop.setLng(row[4]);
	    			}
//	    			else {
//	    				String inpath1="Input\\ԭʼվ����Ϣ.csv";
//	    				BufferedReader reader1 = new BufferedReader(new FileReader(inpath1));
//	    				stop.setLng(row[4]);
//	    			}
	    			
	    			if(row[5]!="") {
	    				stop.setLat(row[5]);
	    			}
	    			list_text.add(stop);
				}
			}
		}
		return list_text;
	}
	/***
	 * �������:վ�����ƣ�����·������,��γ��
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void method1(List<Stop> a_list) throws IOException {
		FileWriter fw =new FileWriter("Output\\Phy_Stop.csv");//
		BufferedWriter bw=new BufferedWriter(fw);
		List<String> StopName0=a_list.stream().map(Stop::getStop_Name).collect(Collectors.toList());
		List<String> StopName = StopName0.stream().distinct().collect(Collectors.toList());
//		Collections.sort(StopName);
		StopName.forEach(e -> System.out.println("�������:" +e));
		bw.write("Phy_Stop_ID"+ ","+"Stop_Name"+ ","+"Lng"+ ","+"Lat"+ ","+"Pass_Line_Name");
		bw.newLine();
		Integer t=0;
		for(String x:StopName){
			t++;
			List<Stop> list_yika=a_list.stream().filter(d->d.getStop_Name().equals(x)).collect(Collectors.toList());
			Stop stop1=list_yika.get(0);
			List<String> LineName0=list_yika.stream().map(Stop::getLine_Name).collect(Collectors.toList());
			List<String> LineName = LineName0.stream().distinct().collect(Collectors.toList());
			String PassLineName="";
			for (String y: LineName) {
	            if (y != null) {
	            	PassLineName +=  y + ";";
	            }
	        }
	        if (PassLineName.length() > 0) {
	        	PassLineName = PassLineName.substring(0, PassLineName.length() - 1);
	        }
			bw.write(t+ ","+x+ ","+stop1.getLng()+ ","+stop1.getLat()+ ","+PassLineName);		
			bw.newLine();
		}
		bw.close();
	}
	/***
	 * �������:����txt�ļ��в��ߵ�վ����Ϣ
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static List<Stop> read_line_stops_txt() throws IOException {
		String inpath="Input\\������·\\115·.txt";
		File file=new File(inpath);//inpath
		List<Stop> list_text=new ArrayList<Stop>();
		FileWriter fw =new FileWriter("Output\\115·.csv");//115·.csv
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("lng"+ ","+"lat"+ ","+"Station_name"+ ",");//"lng"+ ","+"lat"+ ","+"Station_name"+ ","
		bw.newLine();
		if(!file.exists()) {
			System.out.print("�ļ������ڣ�");
		}else {
			System.out.print("�ļ����ڣ�");
			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
			String line=null;
			line=reader.readLine();
			System.out.println(line);
			String[] row=line.split("\"",-1);
			for (int i = 0; i < row.length; i++) {
//				System.out.println(row[i]+","+row[i+30]+","+row[i+50]+",");
				if(row[i].equals("xy_coords")){//if((row[i]=="����԰")&(i+50<row.length)){
					System.out.println(row[i+2]+","+row[i-26]);//+","+row[i+30]+","+row[i+50]+","
//					String lng=row[i+2].split(";",-1);
					String[] row2=row[i+2].split(";",-1);
					bw.write(row2[0]+ ","+row2[1]+ ","+row[i-26]+ ",");
					bw.newLine();
				}
			}

		}
		bw.close();
		return list_text;
	}
	/***
	 * �������:����txt�ļ��в��ߵ�������Ϣ
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static void read_line_stops_txt2() throws IOException {
		String inpath="Input\\������·\\113·.txt";
		File file=new File(inpath);//inpath
		List<Stop> list_text=new ArrayList<Stop>();
		FileWriter fw =new FileWriter("Output\\113·����.csv");//115·.csv
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("Line_name"+ ","+"Lng"+ ","+"Lat"+ ",");//"lng"+ ","+"lat"+ ","+"Station_name"+ ","
		bw.newLine();
		if(!file.exists()) {
			System.out.print("�ļ������ڣ�");
		}else {
			System.out.print("�ļ����ڣ�");
			BufferedReader reader = new BufferedReader(new FileReader(inpath)); // ��ȡCSV�ļ�
			String line=null;
			line=reader.readLine();
			System.out.println(line);
			String[] row=line.split("\"",-1);
			for (int i = 0; i < row.length; i++) {
				if(row[i].equals("xs")|row[i].equals("ys")){
					String[] row2=row[i+2].split(",",-1);
					if(row2.length>10) {
						bw.write(row[i]+ ",");
						for(String j:row2) {
							bw.write(j+ ",");
						}
						bw.newLine();
					}
					
				}
			}

		}
		bw.close();
		
	}
	
	public static void getIC(List<IC> a_list) throws IOException {

		a_list.forEach(item -> item.setMonth(item.getDateTime().substring(0, 10)));
		List<String> list_Month0=a_list.stream().map(IC::getMonth).collect(Collectors.toList());
		List<String> list_Date = list_Month0.stream().distinct().collect(Collectors.toList());
		Collections.sort(list_Date);
		list_Date.forEach(e -> System.out.println("�������:" +e));

		for(String x:list_Date){
			String filename="Output\\IC\\T_Bus_Transaction_"+x.replace("-", ".")+".csv";
			FileWriter fw =new FileWriter(filename);//data
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write("DateTime"+ ","+"Card_ID"+ ","+"Price"+ ","+"Card_Type"+ ","+"Line_ID"+ ","+"Bus_ID"+ ",");
			bw.newLine();
			List<IC> list_yika=a_list.stream().filter(d->d.getMonth().equals(x)).collect(Collectors.toList());
			for(IC y:list_yika) {
				bw.write(y.getDateTime()+ ","+y.getCardId()+ ","+y.getMoney()+ ","+y.getCardType()+ ","+y.getLine()+ ","+y.getBusID()+ ",");
				bw.newLine();
//				System.out.println(y.getDateTime());
			}
			
			bw.close();
		}
	}
	
	/***
	 * �������:����GPS��Ϣ
	 * @param file_num
	 * @return print��csv��
	 * @throws IOException 
	 */
	public static List<GPS> read_GPS_xls(String inpath) throws IOException {
		File file=new File(inpath);//inpath
		System.out.println(file);
		List<GPS> list_text=new ArrayList<GPS>();
//		FileWriter fw =new FileWriter("Output\\GPS\\T_Bus_Gps_2021.09.01.csv");//115·.csv
//		BufferedWriter bw=new BufferedWriter(fw);
//		bw.write("DateTime"+ ","+"Line_ID"+ ","+"Bus_ID"+ ","+"Lng"+ ","+"Lat"+ ","+"Speed"+ ","+"Azimuth"+ ",");
//		bw.newLine();
		if(!file.exists()) {
			System.out.print("�ļ������ڣ�");
		}else {
			System.out.print("�ļ����ڣ�");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inpath),"GB2312")); // ��ȡCSV�ļ�
//			Workbook xwb = new XSSFWorkbook(is);
			String line=null;
			line=reader.readLine();
			line=reader.readLine();
			System.out.println(line);
			
			while((line=reader.readLine())!=null) {
				String[] row=line.split(",",-1);
    			GPS gps=new GPS();
//    			if(row[2]!="") {
    				gps.setLine_ID(Integer.parseInt(row[0]));
    				gps.setBus_ID(Integer.parseInt(row[1]));
    				gps.setDateTime(row[2]);
    				if(row[7]=="") {
    					gps.setSpeed("0");
	    			}else {
	    				gps.setSpeed(row[7]);
	    			}
	    			gps.setLng(row[8]);
	    			gps.setLat(row[9]);
	    			if(row[10].length()<2) {
	    				
	    				gps.setAzimuth(row[10]);
	    			}else {
	    				gps.setAzimuth(row[10].substring(0, row[10].length() - 2));
	    			}
	    			System.out.println(gps.getDateTime()+ ","+gps.getLine_ID()
	    			+ ","+gps.getBus_ID()+ ","+gps.getLng()+ ","+gps.getLat()
	    			+ ","+gps.getSpeed()+ ","+gps.getAzimuth()+ ",");
//	    			bw.write(gps.getDateTime()+ ","+gps.getLine_ID()
//	    			+ ","+gps.getBus_ID()+ ","+gps.getLng()+ ","+gps.getLat()
//	    			+ ","+gps.getSpeed()+ ","+gps.getAzimuth()+ ",");
	    			list_text.add(gps);
//	    			bw.newLine();
//    			}
			}

		}
//		bw.close();
		return list_text;
	}
	
	public static void get_GPS_file() throws IOException{
		//��ʾһ���ļ�·��
		String path = "Input\\GPS_test";
		getFiles(path);
	}
	/**
	 * �ݹ��ȡĳ·���µ������ļ����ļ��У������
	 * @throws IOException 
	 */
	public static void getFiles(String clientBase) throws IOException {
		File file = new File(clientBase);
		FileWriter fw =new FileWriter("Output\\GPS\\gps_number.csv",true);//115·.csv
		BufferedWriter bw=new BufferedWriter(fw);
//		bw.write("file_name"+ ","+"gps_number"+ ",");
//		bw.newLine();
		// ������·�����ļ���
		if (file.isDirectory()) {
			// ��ȡ·���µ������ļ�
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				// ��������ļ��� �ݹ��ȡ������ļ� �ļ���
				if (files[i].isDirectory()) {
					System.out.println("Ŀ¼��" + files[i].getPath());
					//������ȡ�ļ�������������ļ�
					getFiles(files[i].getPath());
				} else {
					System.out.println("�ļ���" + files[i].getPath());
					List<GPS> list_gps=chuzhou2.read_GPS_xls(files[i].getPath());
					bw.write(files[i].getPath()+ ","+list_gps.size()+ ",");
					bw.newLine();
				}
			}
		} else {
			System.out.println("�ļ���" + file.getPath());
			List<GPS> list_gps=chuzhou2.read_GPS_xls(file.getPath());
			bw.write(file.getPath()+ ","+list_gps.size()+ ",");
			bw.newLine();
			
		}
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[] ints = {1,4,7,10,13,16};//"0,1,2","3,4,5","6,7,8","9,10,11","12,13,14","15,16,17",3��-(0,6,9,12,15),9��-(1,4,7,10,13,16),12��-(2,5,8,11,14,17),
		List<IC> a_list=chuzhou2.add_filename(ints);
		List<IC> a_list2=chuzhou2.quchong(a_list);//ȥ��
//		chuzhou2.groupby1(a_list2);//�����ڷֿ���
//		chuzhou2.groupby2(a_list2);//�����ڷֿ���(ͳ��ˢ������������ˢ������)
//		chuzhou2.groupby3(a_list);//test
//		chuzhou2.groupby4(a_list2);//�����ڷ�ʱ���ͳ��-����߷����
//		chuzhou2.groupby5(a_list2);//�����ڷֳ��гǼ�ͳ��
//		chuzhou2.groupby6(a_list2);//�����ڷ���·
		chuzhou2.groupby7(a_list2);//����·��ʱ���ͳ��-����߷����
		
		//��·վ����Ϣ����׼��
//		List<Stop> alist=chuzhou2.read_line_stops();//¼��վ������
//		chuzhou2.method1(alist);//������׼����վ��csv
//		chuzhou2.read_line_stops_txt();//¼�벹����·վ������
//		chuzhou2.read_line_stops_txt2();//¼�벹����·�켣������
		
		//IC����׼��
//		int[] ints = {0,1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17};//"0,1,2","3,4,5","6,7,8","9,10,11","12,13,14","15,16,17",3��-(0,6,9,12,15),9��-(1,4,7,10,13,16),12��-(2,5,8,11,14,17),
//		List<IC> a_list=chuzhou2.add_filename(ints);//0,6,9,12,15,1,4,7,10,13,16,2,5,8,11,14,17
//		List<IC> a_list2=chuzhou2.quchong(a_list);//ȥ��
//		chuzhou2.getIC(a_list2);//�����ڷֿ���
		
		//GPS����׼��
//		String file0;
//		file0="Input\\GPS\\3\\2021-09-01\\00607D.xls";
//		List<GPS> a_list0=chuzhou2.read_GPS_xls(file0);//������GPS�ļ�д��
//		chuzhou2.get_GPS_file();//ͳ��gps��¼��
		
	}
}
