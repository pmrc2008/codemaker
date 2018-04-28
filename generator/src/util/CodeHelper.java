package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @Description:xml�ļ�������
 * @author:zhangjq
 * @time:2015-11-17 ����4:17:22
 */
public class CodeHelper {
	
	public CodeHelper(){}
	/**
	 * mapper�ļ�·��
	 */
	public static final String DAO_URL = "src//com//data//citic//dao//";
	
	public static final String MAPPER_URL = "src//com//data//citic//mapper//";
	/**
	 * ������
	 */
	public static final String TABLE = "tbn_user_tasklist_stat";
	/**
	 * ʵ������
	 */
	public static final String ENTITY = "TbnUserTasklistStat";
	
	///public static final String ACTION = "/company/"+ENTITY;

	public static void process() {
		try {
			//step1----------mapper.java�ļ��������������ӿ�
			//CodeHelper.addTrs();
			//step2----------mapper.xml�ļ������Ӻ���
			CodeHelper.attach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   public static void attach()throws Exception {
	   SAXReader saxReader=new SAXReader();
	   XMLWriter xw = null; 
	   Document doc=saxReader.read(new File(MAPPER_URL+ENTITY+"Mapper.xml"));
	   Element root=doc.getRootElement();
	   Element sql=root.addElement("sql");
	   sql.addAttribute("id", "contionSql");
	   Element blockIf = sql.addElement("if");//�����ж�����Ԫ��
	   blockIf.addAttribute("test", "domain != null  and domain!=''");
	   blockIf.addText("and domain = #{domain,jdbcType=VARCHAR}");
	   //�б�ͳ�ƽӿ�
	   Element count = root.addElement("select");
	   count.addAttribute("id", "count"+ENTITY+"List");
	   count.addAttribute("resultType", "int");
	   count.setText("select count(*) from "+TABLE);
	   Element where = count.addElement("where");
	   Element include = where.addElement("include");
	   include.addAttribute("refid", "contionSql");
	   //�б�����ӿ�
	   Element list = root.addElement("select");
	   list.addAttribute("id", "select"+ENTITY+"List");
	   list.addAttribute("resultMap", "BaseResultMap");
	   list.addText(" SELECT");//
	   Element inc = list.addElement("include");
	   inc.addAttribute("refid", "Base_Column_List");
	   list.addText(" FROM "+ TABLE);
	   Element whe = list.addElement("where");
	   Element incs = whe.addElement("include");
	   incs.addAttribute("refid", "contionSql");
	   list.addText(" ORDER BY id desc");
	   Element lf = list.addElement("if");//�����ж�����Ԫ��
	   lf.addAttribute("test", "start != null and offset != null");
	   lf.addText("limit #{start},#{offset}");
	   OutputFormat format = OutputFormat.createPrettyPrint();
	   format.setEncoding("UTF-8");
	   format.setIndent(true); //�����Ƿ�����
	   format.setIndent("   "); //�Կո�ʽʵ������
	   format.setNewlines(true); //�����Ƿ���
	   XMLWriter writer=new XMLWriter(new FileOutputStream(MAPPER_URL+ENTITY+"Mapper.xml"),format);
	   writer.write(doc);
	   writer.close();
	}
	
	public static void addTrs() throws Exception {  
		File file=new File(DAO_URL+ENTITY+"Mapper.java");
		Scanner stdin=new Scanner(file);
		StringBuilder sb=new StringBuilder();
		while(stdin.hasNext()){//�Ƚ����һ��"}"ȥ��
			String line=stdin.nextLine();
			sb.append(line.replaceAll("}", "")+"\r\n");
		}
		sb.append("	int count"+ENTITY+"List(Map<String,Object> param);");
		sb.append("\r\n\r\n");
		sb.append("	List<"+ENTITY+"> select"+ENTITY+"List(Map<String,Object> param);");   
		sb.append("\r\n\r\n");
		sb.append("}");
		stdin.close();
		PrintWriter pw=new PrintWriter(file);
		pw.println(sb.toString());
		pw.close();
		
		//�ƶ�mapper�ļ�
		
		
	}
	public static void main(String[] args) {
		CodeHelper.process();
	}
}
