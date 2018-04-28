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
 * @Description:xml文件处理类
 * @author:zhangjq
 * @time:2015-11-17 下午4:17:22
 */
public class CodeHelper {
	
	public CodeHelper(){}
	/**
	 * mapper文件路径
	 */
	public static final String DAO_URL = "src//com//data//citic//dao//";
	
	public static final String MAPPER_URL = "src//com//data//citic//mapper//";
	/**
	 * 表名称
	 */
	public static final String TABLE = "tbn_user_tasklist_stat";
	/**
	 * 实体名称
	 */
	public static final String ENTITY = "TbnUserTasklistStat";
	
	///public static final String ACTION = "/company/"+ENTITY;

	public static void process() {
		try {
			//step1----------mapper.java文件中增加了两个接口
			//CodeHelper.addTrs();
			//step2----------mapper.xml文件中增加函数
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
	   Element blockIf = sql.addElement("if");//条件判断区块元素
	   blockIf.addAttribute("test", "domain != null  and domain!=''");
	   blockIf.addText("and domain = #{domain,jdbcType=VARCHAR}");
	   //列表统计接口
	   Element count = root.addElement("select");
	   count.addAttribute("id", "count"+ENTITY+"List");
	   count.addAttribute("resultType", "int");
	   count.setText("select count(*) from "+TABLE);
	   Element where = count.addElement("where");
	   Element include = where.addElement("include");
	   include.addAttribute("refid", "contionSql");
	   //列表区域接口
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
	   Element lf = list.addElement("if");//条件判断区块元素
	   lf.addAttribute("test", "start != null and offset != null");
	   lf.addText("limit #{start},#{offset}");
	   OutputFormat format = OutputFormat.createPrettyPrint();
	   format.setEncoding("UTF-8");
	   format.setIndent(true); //设置是否缩进
	   format.setIndent("   "); //以空格方式实现缩进
	   format.setNewlines(true); //设置是否换行
	   XMLWriter writer=new XMLWriter(new FileOutputStream(MAPPER_URL+ENTITY+"Mapper.xml"),format);
	   writer.write(doc);
	   writer.close();
	}
	
	public static void addTrs() throws Exception {  
		File file=new File(DAO_URL+ENTITY+"Mapper.java");
		Scanner stdin=new Scanner(file);
		StringBuilder sb=new StringBuilder();
		while(stdin.hasNext()){//先将最后一个"}"去除
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
		
		//移动mapper文件
		
		
	}
	public static void main(String[] args) {
		CodeHelper.process();
	}
}
