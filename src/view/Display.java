package view;
import controller.*;
import generated.Itinerary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import java.io.StringReader;


@SuppressWarnings("ALL")
public class Display extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        TextArea textArea = new TextArea();
        Button save = new Button("Enregistrer");
        Button delete = new Button("Supprimer");
        save.setOnAction(action -> {
            Itinerary i = JAXB.unmarshal(new StringReader(textArea.getText()), Itinerary.class);
            try {
                VisitPlanController.persiste(i);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        delete.setOnAction(action -> {
            textArea.setText("");
            Itinerary i = JAXB.unmarshal(new StringReader(textArea.getText()), Itinerary.class);
            try {
                VisitPlanController.persiste(i);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        });
        String res=VisitPlanController.storeXmlInFile("iti1.xml");
        textArea.setText(res);
        VBox vbox = new VBox(textArea, save, delete);

        Scene scene = new Scene(vbox, 500, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
