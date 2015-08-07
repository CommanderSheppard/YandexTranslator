package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Yandex translator");
        primaryStage.setScene(View.createScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.list(System.out);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        try {
            dos.writeBytes("file.encoding=UTF-8");
            byte[] buf;
            buf = os.toByteArray();
            os.close();
            ByteArrayInputStream is = new ByteArrayInputStream(buf);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
