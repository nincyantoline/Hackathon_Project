package cpuData;
import java.io.*;
import java.sql.*;
import org.json.simple.JSONObject;
public class cpuData {

	static String hint;
	public static void main(String[] args) throws IOException {
		int count = 0;
		JSONObject obj = new JSONObject();
		obj.put("transactionName", "Sample_Transaction");
			try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cpudb","root","Nincy@9196");  
			Statement s1=con.createStatement();
			File file = new File("C:\\Users\\nency\\Desktop\\Hackathon_Project\\input.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			float value;
			float avg = 0,max=0;
			while ((st=br.readLine())!=null)
			{
				String[] a= st.split(" ");
	
				if (a[8].length()>0)
				{
					if (a[14].length()>0)
					{
						value = Float.parseFloat(a[14]);
					}
					else 
					{
						value= Float.parseFloat(a[15]);
					}	
				}
				else 
				{
					if (a[16].length()>0)
					{
						value = Float.parseFloat(a[16]);
					}
					else 
					{
						value= Float.parseFloat(a[17]);;
					}	
				}
				avg = (avg+value)/2;
				if (value > max)
				{
					max = value;
				}
				count++;
				hint =Integer.toString(count)+ "S";
				obj.put(hint, value);
			}
			System.out.printf("Maximum Value: %f \t Average Value : %f\n",max,avg);
			obj.put("Maximum", max);
			obj.put("Average", avg);
			String sql="INSERT into cputable (transactionName,max,avg) values('Sample_Transaction','"+max+"','"+avg+"')";
		    s1.executeUpdate(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
	     	System.out.println(obj);
		}
	}
		
}
