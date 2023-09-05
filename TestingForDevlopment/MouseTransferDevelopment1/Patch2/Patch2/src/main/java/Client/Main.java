package Client;

public class Main {

	public Main(){
		Connection connection = new Connection();
		if (connection.TCPConnection && connection.UDPConnection){
			System.out.println("Connection Established");
		}
	}
}
