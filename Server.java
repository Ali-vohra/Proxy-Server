import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(8040);
		Socket s = ss.accept();
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		
		//byte[] b = dis.readAllBytes();
		String query = dis.readUTF();
		System.out.println(query);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:xe", "system", "ali");
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			Boolean res;
			
			if(rs.next()) {
				res = true;
			}
			else
				res = false;
			
			con.close();
			dos.writeBoolean(res);
			dis.close();
			dos.close();
			s.close();
			ss.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
		
}


