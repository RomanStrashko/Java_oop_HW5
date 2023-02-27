import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер запущен, ожидаем соединение....");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился к серверу!");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            View view = new View();
            Calculator calculator = new Calculator();

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("off")) {
                    view.print("До новых встреч!");
                    break;
                }
                view.print("Получено число" + " " + clientRequest);
                dataOutputStream.writeUTF("Мы получили чило:" + " " + clientRequest);
                double x = Double.parseDouble(clientRequest);

                String clientRequest2 = dataInputStream.readUTF();
                view.print("Получена операция" + " " + clientRequest2);
                dataOutputStream.writeUTF("Мы получили операцию:" + " " + clientRequest2);

                String clientRequest3 = dataInputStream.readUTF();
                view.print("Получено число" + " " + clientRequest3);
                dataOutputStream.writeUTF("Мы получили число:" + " " + clientRequest3);
                double y = Double.parseDouble(clientRequest3);

                switch (clientRequest2){
                    case "+":
                        dataOutputStream.writeDouble(calculator.summation(x, y));
                        break;
                    case "-":
                        dataOutputStream.writeDouble(calculator.subtraction(x, y));
                        break;
                    case "*":
                        dataOutputStream.writeDouble(calculator.multiplication(x, y));
                        break;
                    case "/":
                        dataOutputStream.writeDouble(calculator.division(x, y));
                        break;
                    default:
                        dataOutputStream.writeUTF("Введен не верный оператор");
                        break;
                }





            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
