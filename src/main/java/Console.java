import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Authorization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private static final Logger LOG = LogManager.getLogger(Console.class);


    public void start(){
        while (true) {
            System.out.println("Введіть логін");
            String userName = readFromConsole().trim();
            if (userName.equalsIgnoreCase("q")) {
                exit();}
            System.out.println("Введіть пароль");
            String password = readFromConsole().trim();
            LOG.info("the user entered the following data username = {}, password = {}",userName, password);
            Authorization authorization = new Authorization(userName, password);
            if(authorization.authorizationMethod()){
                return;
            }
        }



//        exit();
    }

    private void exit() {
        LOG.info("Close app");
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
