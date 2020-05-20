import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) {
		try {
			
			Socket s = new Socket("127.0.0.1", 8090);
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			//System.out.println("Enter the query");
			Scanner sc = new Scanner(System.in);
			String query = "select * from book";
		
			dos.writeUTF(query);
			
			Boolean res = dis.readBoolean();
			
			if(res) {
				System.out.println("Record present in the Table");
			}
			else {
				System.out.println("Record not present in the Table");
			}
			
			dos.close();
			s.close();
			sc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
