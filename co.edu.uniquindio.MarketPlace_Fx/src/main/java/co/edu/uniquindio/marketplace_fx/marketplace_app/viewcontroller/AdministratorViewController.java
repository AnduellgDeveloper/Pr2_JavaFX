package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;


import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AdministratorViewController {
    private StringBuilder reportContent = new StringBuilder();
    private String username;
    private Session session;




    public void init(Session session) {
        this.session = session;
        String username = (String) session.getSessionData("username");  // Obtener el username de la sesión
        System.out.println("Username del administrador: " + username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Método para obtener el username si lo necesitas
    public String getUsername() {
        return username;
    }

    @FXML
    private TextArea reportArea;

    @FXML
    private DatePicker LocalData;

    @FXML
    private Button btnExport;

    @FXML
    private RadioButton rdbCantSeller;

    @FXML
    private RadioButton rdbMaxLike;

    @FXML
    private RadioButton rdbProductPubli;

    @FXML
    private RadioButton rdbProductsSeller;

    @FXML
    private StackedBarChart<?, ?> staticts;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtIdNumber1;

    @FXML
    void onCantSeller(ActionEvent event) {

    }

    @FXML
    void onExport(ActionEvent event) {
        if (username != null) {
            System.out.println("Exportando datos para el usuario: " + username);
        } else {
            System.out.println("No se ha proporcionado el username.");
        }
        // Obtén la fecha actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());



        // Contenido del reporte
        String reportContent = "Reporte Dunima Marketplace\n\n"
                + "Fecha: " + currentDate + "\n"
                + "Reporte realizado por: " + username+ "\n\n"
                + "Información del reporte:\n"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

        // Aquí puedes agregar la lógica para llenar el reporte con los datos que desees

        // Define el nombre del archivo y la ruta donde deseas guardarlo
        String filePath = "reporte_clientes.txt"; // Puedes cambiar el nombre del archivo

        // Intentar escribir el reporte en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(reportContent);
            System.out.println("Reporte exportado correctamente a " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción y mostrar un mensaje de error si lo deseas
        }
    }


    @FXML
    void onLocalData(ActionEvent event) {

    }

    @FXML
    void onMaxLike(ActionEvent event) {

    }

    @FXML
    void onProducPubli(ActionEvent event) {

    }

    @FXML
    void onProductsSeller(ActionEvent event) {

    }

}




