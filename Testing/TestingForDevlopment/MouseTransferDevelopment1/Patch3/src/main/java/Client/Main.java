package Client;

public class Main {

	public Main(){
		ConnectionClient connection = new ConnectionClient();
		if (connection.connectionEstablished){
			System.out.println("Ended");
		}
	}
}
