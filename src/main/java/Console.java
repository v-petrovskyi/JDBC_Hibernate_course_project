import utils.Authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {



    public void start(){
        while (true) {
            System.out.println("Введіть логін");
            String userName = readFromConsole().trim();
            System.out.println("Введіть пароль");
            String password = readFromConsole().trim();
            Authorization authorization = new Authorization(userName, password);
            if(authorization.authorizationMethod()){
                return;
            }
        }

//        exit();
    }

    private void exit() {
        System.out.println("Exit....");
        System.exit(0);
    }

    private String readFromConsole(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
