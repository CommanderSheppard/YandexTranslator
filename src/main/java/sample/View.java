package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class View {

    Controller controllerInstance = new Controller();
    private static TextArea createTextArea(int taWid, int taHei, int taLayX, int taLayY, String taCol) {
        return TextAreaBuilder.create().prefWidth(taWid).prefHeight(taHei)
                .layoutX(taLayX).layoutY(taLayY).style("-fx-background-color: " + taCol + ";").build();
    }
    private static Button createButton(String butText, int butWid, int butHei, int butLayX, int butLayY, String butCol) {
        return ButtonBuilder.create().text(butText).prefWidth(butWid).prefHeight(butHei)
                .layoutX(butLayX).layoutY(butLayY).style("-fx-background-color: " + butCol + ";").build();
    }

    private static ComboBox createComboBox(int cbWid, int cbHei, int cbLayX, int cbLayY, String cbCol) {
        return ComboBoxBuilder.create().prefWidth(cbWid).prefHeight(cbHei)
                .layoutX(cbLayX).layoutY(cbLayY).style("-fx-background-color: " + cbCol + ";").build();
    }

    static TextArea ta = createTextArea(360, 100, 5, 35, "red");
    static TextArea outputArea = createTextArea(360, 100, 5, 170, "red");
    static Button translateButton = createButton("Translate", 80, 22, 145, 140, "orange");
    static Button swapButton = createButton("Swap", 50, 22, 160, 5, "orange");
    static ComboBox fromLangCB = createComboBox(150, 22, 5, 5, "grey");
    static ComboBox toLangCB = createComboBox(150, 22, 215, 5, "grey");

    static Scene createScene(){
        int width = 370;
        int height = 280;
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        outputArea.setEditable(false);
        root.getChildren().add(ta);
        root.getChildren().add(translateButton);
        root.getChildren().add(outputArea);
        root.getChildren().add(fromLangCB);
        root.getChildren().add(toLangCB);
        root.getChildren().add(swapButton);
        fromLangCB.setValue("ru:Русский");
        toLangCB.setValue("en:Английский");
        Controller.addLangs(fromLangCB);
        Controller.addLangs(toLangCB);


        swapButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Object temp = fromLangCB.getValue();
                fromLangCB.setValue(toLangCB.getValue());
                toLangCB.setValue(temp);
            }
        });

        translateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String tempo = Controller.getTranslation();
                outputArea.setText(tempo);
                // System.out.println(tempo);
            }
        });

        return scene;
    }

}
