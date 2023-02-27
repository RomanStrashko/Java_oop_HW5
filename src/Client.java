import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 1234)) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            View view = new View();
            File file = new File("Test");
            PrintWriter pw = new PrintWriter(file);

            while (true) {
                System.out.println("Вас приветствует калькулятор! \n" + "------------------------");
                System.out.println("Введите первое вещественное число: ");
                String request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                if (request.equals("off")){
                    view.print("До новых встреч!");
                    break;
                }
                System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());


                System.out.println("Введите операцию: ");
                String request2 = scanner.nextLine();
                dataOutputStream.writeUTF(request2);
                System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());


                System.out.println("Введите второе вещественное число: ");
                String request3 = scanner.nextLine();
                dataOutputStream.writeUTF(request3);
                System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());



                System.out.println("Ответ" + " " + "=" + " " + dataInputStream.readDouble());
                System.out.println();





            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
