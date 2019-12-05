package tester;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    public static void main(String[] args) {
        System.out.println("Enter Server IP and Port:");
        boolean exit = false;
        try (Scanner sc = new Scanner(System.in); Socket s1 = new Socket(sc.next(), sc.nextInt())) {
            System.out.println("connected to server IP" + s1.getInetAddress().getHostName() + " server  port "
                    + s1.getPort() + " clnt port " + s1.getLocalPort());
            try (DataOutputStream out = new DataOutputStream(s1.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(s1.getInputStream())) {
                while (!exit) {
                    System.out.println("***MENU***");
                    System.out.println("1.current month bill.\n" + "2.Last 6 months bill.\n"
                            + "3.Last 6 month bill using name and dob\n"
                            + "4.Find Name of the user who paid max bill.");
                    System.out.println("ENter ur choice");
                    switch (sc.nextInt()) {
                    case 1:
                        out.writeInt(1);
                        System.out.println("Enter 10 digit Phone No:");
                        out.writeUTF(sc.next());
                        System.out.println("Bill of current month: " + in.readObject());
                        break;
                    case 2:
                        out.writeInt(2);
                        System.out.println("Enter 10 digit Phone No:");
                        out.writeUTF(sc.next());
                        System.out.println("Bills of last 6 months: \n" + in.readObject());

                        break;
                    case 3:
                        out.writeInt(3);
                        System.out.println("Enter ur Name:");
                        out.writeUTF(sc.next());
                        System.out.println("Enter DOB:");
                        out.writeUTF(sc.next());
                        System.out.println("Bills of last 6 months: \n" + in.readObject());

                        break;
                    case 4:
                        out.writeInt(4);
                        System.out.println("Name of Client who paid highest bill: " + in.readObject());
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Thanks for using the applicaltion!!");
                        break;
                    default:
                        System.out.println("Invalid I/p");
                        break;
                    }
                    sc.nextLine();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
