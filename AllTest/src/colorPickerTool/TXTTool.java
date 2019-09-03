package colorPickerTool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TXTTool {

	public static void writeTxt(String result){
		 try {
			 	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			 	String time = (df.format(new Date()));// new Date()为获取当前系统时间
	            FileWriter writer = new FileWriter("MyFile_" + time + ".txt", true);
	            BufferedWriter bufferedWriter = new BufferedWriter(writer);
	 
	            bufferedWriter.write(result);
	            bufferedWriter.flush();
	            bufferedWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
