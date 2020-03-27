package Battery;
import java.io.*;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.nio.file.*;
public class Battery {
	
	static String value1="Uid u0a202";
	static String value2="Foreground activities";
	static float Battery_percent=0,Battery_drain=0;

	static void calculate()
	{	
		File file = new File("C:\\Users\\Nency\\Desktop\\Battery.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str,str2,str3,Foreground="";
		while ((str = br.readLine()) != null)
		{
		str2=str;
		String[] array = str2.split(":");
		if(array.length>1)
		{
		array[0]=array[0].trim();
		if(array[0].equals(value1))
		{
		        str3=array[1];
		        String[] array1 = str3.split("\\(");
		        Battery_drain=Float.parseFloat(array1[0]);
		        System.out.println(Battery_drain);
		}
		if(array[0].equals(value2))
		{
		        str3=array[1];
		        String[] array1 = str3.split("\\(r");
		        Foreground=array1[0].trim();
		        System.out.println(Foreground);
		}
		}	 
		}
		Battery_percent=(Battery_drain/1000);// Formulae to calculate the Battery Percentage
		System.out.println(Battery_percent);
	}
	
	static void printjSON()
	{
		JSONParser parser=new JSONParser();
		JSONObject obj=new JSONObject();
		obj.put("Foreground_time",Foreground); //Pushing Foreground into JSON Object
		obj.put("Battery_drain" ,Battery_drain);//Pushing Battery Drain into JSON Object
		obj.put("Battery_percentage" ,Battery_percent);//Pushing Battery Percentage into JSON Object
		FileWriter file1=new FileWriter("C:\\Users\\Nency\\eclipse-workspace\\Battery\\battery.json");
		file1.write(obj.toString());
		file1.flush();
		System.out.println(obj);//Printing the JSON Object
	}
	public static void main(String[] args)throws Exception
 {

		calculate();// Finds the Battery drain and calculates the Battery Percentage value
		printjSON();// It stores and displays the value of Battery Drain and Percentage in JSON object  

 }

}
