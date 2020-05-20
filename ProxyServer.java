import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ProxyServer {

	private ServerSocket ss;
	private Socket s, s1;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public ProxyServer(int port1, int port2) throws IOException {
		
		ss = new ServerSocket(port1);
		s = ss.accept();
		s1 = new Socket("127.0.0.1", port2);
		rated();
	}
	
	public void rated() throws IOException {
		
		String query;
		is = s.getInputStream();
		os = s1.getOutputStream();
		dis = new DataInputStream(is);
		dos = new DataOutputStream(os);
		
		query = dis.readUTF();
		dos.writeUTF(query);
		
		is = s1.getInputStream();
		dis = new DataInputStream(is);
		boolean res = dis.readBoolean();
		os = s.getOutputStream();
		dos = new DataOutputStream(os);
		dos.writeBoolean(res);
	}
	public static void main(String[] args) throws IOException {
		
		new ProxyServer(8090, 8040);
	}

}
