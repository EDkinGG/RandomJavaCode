import VisaTestSystem.ClientConnectToVtsServer;
import VisaTestSystem.ServerForVtsClientToConnect;

public class Main {
    public static void main(String[] args) {
        //Client Creation
        //Vts is in server role
        ClientConnectToVtsServer clientConnectToVtsServer = new ClientConnectToVtsServer();
        clientConnectToVtsServer.run();


        //Server Creation
        //Vts is in Client role
        ServerForVtsClientToConnect serverForVtsClientToConnect = new ServerForVtsClientToConnect();
        serverForVtsClientToConnect.run();

    }
}
