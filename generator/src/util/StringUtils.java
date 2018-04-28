package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class StringUtils {
	public static void main(String[] args) {
		//MobileErrorLog log = new MobileErrorLog();
		try {
			//StringUtils.testReflect(log);
		} catch (Exception e) {
		}
	}
	
	public static void testReflect(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Field[] field = model.getClass().getDeclaredFields();        //��ȡʵ������������ԣ�����Field����  
        for(int j=0 ; j<field.length ; j++){     //������������
                String name = field[j].getName();    //��ȡ���Ե�����
                System.out.println("attribute name:"+name);     
                name = name.substring(0,1).toUpperCase()+name.substring(1); //�����Ե����ַ���д�����㹹��get��set����
                String type = field[j].getGenericType().toString();    //��ȡ���Ե�����
                if(type.equals("class java.lang.String")){   //���type�������ͣ���ǰ�����"class "�����������
                    Method m = model.getClass().getMethod("get"+name);
                    String value = (String) m.invoke(model);    //����getter������ȡ����ֵ
                    if(value != null){

                        System.out.println("attribute value:"+value);
                    }
                }
                if(type.equals("class java.lang.Integer")){     
                    Method m = model.getClass().getMethod("get"+name);
                    Integer value = (Integer) m.invoke(model);
                    if(value != null){
                        System.out.println("attribute value:"+value);
                    }
                }
                if(type.equals("class java.lang.Short")){     
                    Method m = model.getClass().getMethod("get"+name);
                    Short value = (Short) m.invoke(model);
                    if(value != null){
                        System.out.println("attribute value:"+value);                    }
                }       
                if(type.equals("class java.lang.Double")){     
                    Method m = model.getClass().getMethod("get"+name);
                    Double value = (Double) m.invoke(model);
                    if(value != null){                    
                        System.out.println("attribute value:"+value);  
                    }
                }                  
                if(type.equals("class java.lang.Boolean")){
                    Method m = model.getClass().getMethod("get"+name);    
                    Boolean value = (Boolean) m.invoke(model);
                    if(value != null){                      
                        System.out.println("attribute value:"+value);
                    }
                }
                if(type.equals("class java.util.Date")){
                    Method m = model.getClass().getMethod("get"+name);                    
                    Date value = (Date) m.invoke(model);
                    if(value != null){
                        System.out.println("attribute value:"+value.toLocaleString());
                    }
                }                
            }
    }

}
