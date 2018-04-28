package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class filereduce {
	
	static List<String> errList = new ArrayList<String>();

	public static void main(String[] args) {
		String filepath="d:\\files\\";
		 try {

             File file = new File(filepath);
             if (!file.isDirectory()) {
//                     System.out.println("�ļ�");
//                     System.out.println("path=" + file.getPath());
//                     System.out.println("absolutepath=" + file.getAbsolutePath());
//                     System.out.println("name=" + file.getName());

             } else if (file.isDirectory()) {
//                     System.out.println("�ļ���");
                     String[] filelist = file.list();
                     for (int i = 0; i < filelist.length; i++) {
                             File readfile = new File(filepath +  filelist[i]);
                             if (!readfile.isDirectory()) {
//                                     System.out.println("path=" + readfile.getPath());
//                                     System.out.println("absolutepath="
//                                                     + readfile.getAbsolutePath());
//                                     System.out.println("name=" + readfile.getName());
                                     List<String> list =readFileByLines(readfile);
                                     writeFileByLines(list,readfile.getAbsolutePath()
                                    		 .replace("localhost_access_log.", ""));
                                     writeFileByLines(errList,filepath+"error.log");
                                     errList.clear();
                             } else if (readfile.isDirectory()) {
//                                     readfile(filepath + "\\" + filelist[i]);
                             }
                     }

             }

     } catch (Exception e) {
             System.out.println("readfile()   Exception:" + e.getMessage());
             e.printStackTrace();
     }
		
		
	}
	
	 /** 
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ� 
     */  
    public static List<String> readFileByLines(File file) throws Exception {  

        BufferedReader reader = null;  
//            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            List<String> list = new ArrayList<String>();
            int line = 1;  
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����  
            while ((tempString = reader.readLine()) != null) {  
                // ��ʾ�к�  
//                System.out.println("line " + line + ": " + tempString);  
//                line++;  
                String[] str = tempString.split(" ");
                String datenowtmp = str[3].replace("[","").replace("]","");
                
                SimpleDateFormat fm = new SimpleDateFormat("d/MMM/yyyy:HH:mm:ss",Locale.US);
                SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US) ;
        	    Date utcdate = fm.parse(datenowtmp);
        	    Calendar cal = Calendar.getInstance();
        	    cal.setTime(utcdate);
        	    String datenow = fm2.format(cal.getTime());
                
                if(str.length!=10){
                	System.out.println("error: "+tempString);
                	errList.add(tempString);
                	continue;
                }
                list.add(
                		str[0]+"|"+
                		datenow.split(" ")[0]+"|"+
                		datenow.split(" ")[1]+"|"+
                		str[5].replace("\"", "")+"|"+
                		str[6]+"|"+
                		str[7].replace("\"", "")+"|"+
                		str[8]);
            }  
            reader.close();  
            return list;
    }  
	
    
    public static void writeFileByLines(List<String> list,String path) throws Exception {  
    	
    	
    	  File file = new File(path);
    	  BufferedWriter fop = new BufferedWriter(new FileWriter(file,true));
    	   // if file doesn't exists, then create it
    	   if (!file.exists()) {
    	    file.createNewFile();
    	   }
    	  for(String content:list){
        	 
      	   fop.write(content+"\r\n");
      	   fop.flush();
    	 }
    	   // get the content in bytes
    	  
    	   fop.close();
    	 
//    	   System.out.println("Done");
    	 

    	 }
    	
    	
	
}
