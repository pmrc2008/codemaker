package util;

import java.io.File;
import java.util.LinkedList;

public class CopyFileToFoloder {
	public static void main(String[] args) {
		
		String path ="D:\\DevOpsLib\\DevOpsLib";
		String filePath ="D:\\DevOpsLib\\.gitkeep";
		
		CopyFileToFoloder.traverseFolder2(path,filePath);
	}
	
//	public static void traverseFolder1(String path,String filePath) {
//        int fileNum = 0, folderNum = 0;
//        File file = new File(path);
//        if (file.exists()) {
//            LinkedList<File> list = new LinkedList<File>();
//            File[] files = file.listFiles();
//            for (File file2 : files) {
//                if (file2.isDirectory()) {
//                	//拷贝一个文件到该目录下
//                	FileUtil.copyFile(filePath,file2.getAbsolutePath()+"\\.gitkeep");
//                } else {
//                  
//                }
//            }
//        } 
//    }
	public static void traverseFolder2(String path,String filePath) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        FileUtil.copyFile(filePath,file2.getAbsolutePath()+"\\.gitkeep");
                        traverseFolder2(file2.getAbsolutePath(),filePath);
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

}
