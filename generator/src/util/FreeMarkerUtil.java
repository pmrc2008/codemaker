package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {
	
	private Configuration cfg;
    public void init() throws Exception {  
        cfg = new Configuration();  
        cfg.setDirectoryForTemplateLoading(new File( "D://workspace//generator//WebContent//WEB-INF//template"));  
    }
    
    public void process(FreeMarkerUtil hf) throws Exception {  
        Map root = new HashMap();  
        root.put("model_name", CodeHelper.ENTITY);

        String projectPath = "D://workspace//generator//";  

        String fileName = "I" + CodeHelper.ENTITY + "Service.java"; 
        String savePath = "src//com//data//citic//service//";
        Template template =cfg.getTemplate("IService.ftl");
        hf.buildTemplate(root, projectPath, savePath, fileName, template);  
        System.out.println("=======service接口已生成");
  
        fileName = CodeHelper.ENTITY +"ServiceImpl.java";  
        savePath = "src//com//data//citic//service//impl//";
        template = cfg.getTemplate("ServiceImpl.ftl");  
        hf.buildTemplate(root, projectPath, savePath, fileName, template);
        System.out.println("=======service实现类已生成");
        
         fileName = CodeHelper.ENTITY + "Mapper.java"; 
         savePath = "src//com//data//citic//dao//";
         template =cfg.getTemplate("Mapper.ftl");
        hf.buildTemplate(root, projectPath, savePath, fileName, template);  
        System.out.println("=======MapperDao接口已生成");

    } 
    
    public void buildTemplate(Map root, String projectPath, String savePath,  
        String fileName, Template template) {  
        String realFileName = projectPath + savePath + fileName;  
        String realSavePath = projectPath + "/" + savePath;  
        File newsDir = new File(realSavePath);  
        if (!newsDir.exists()) {  
            newsDir.mkdirs();  
        }  
        try {  
            Writer out = new OutputStreamWriter(new FileOutputStream(realFileName),"gbk");  
            template.process(root, out);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
    } 
    public static void main(String[] args) {
//        FreeMarkerUtil hf = new FreeMarkerUtil();  
//        try {
//			hf.init();
//	        hf.process(hf);  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
	}
    public static void generater() {
    	
    	
    	FreeMarkerUtil hf = new FreeMarkerUtil();  
    	try {
    		hf.init();
    		hf.process(hf);  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
