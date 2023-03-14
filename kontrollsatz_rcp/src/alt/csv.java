package alt;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.ametras.datareader.TxtOutput;

public class csv {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TxtOutput txt = new TxtOutput();
		Reader in = new FileReader("C:\\Users\\mmahmoud\\Desktop\\Kontrollsätze_Workspace\\FIBCTR.csv");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(';').parse(in);
		for (CSVRecord record : records) {
		    String a = record.get(0);
		    String b = record.get(1);
		    String c = record.get(2);
		    String d = record.get(3);
		    String e = record.get(4);
		    System.out.println(a  );
		    txt.WriteToTxtFile(a + "-" +b + "-" +c +"-" +d + "-" +e + "-" , "csvtest.txt");
		}
		System.out.println("Done");

	}

}
