package alt;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import com.ametras.datareader.TxtOutput;

public class csv {

	public static void main(String[] args) throws IOException {
		String filepath = "File: C:\\Users\\mmahmoud\\Desktop\\Rel7_Workspace\\rewe-rcp\\com.ametras.rewe.utils\\com.ametras.rewe.util.util02\\com.ametras.rewe.util.util02.server\\src\\main\\java\\com\\ametras\\rewe\\util\\util02\\server\\Util02ServiceInterceptor.java";
		
		String test = StringUtils.substring(filepath, StringUtils.lastIndexOf(filepath, "\\")+1, StringUtils.lastIndexOf(filepath, "."));
		
		
		System.out.println(test);
		System.out.println("1");

	}

}
