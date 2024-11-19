package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;


import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.LikeDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdministratorViewController {
    private StringBuilder reportContent = new StringBuilder();
    private String username;
    private Session session;


    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private TextField txtTopLikeProducts;
    @FXML
    private TextField txtUsernameAllProductPublicated;
    @FXML
    private TextField txtUsernameContacts;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Button btnExport;
    @FXML
    private RadioButton rdbCantSeller, rdbMaxLike, rdbProductPubli, rdbProductsSeller;
    @FXML
    private StackedBarChart<?, ?> staticts;

    public void init(Session session) {
        this.session = session;
        this.username = (String) session.getSessionData("username");
        System.out.println("Username del administrador: " + username);
    }

    @FXML
    public void onExport(ActionEvent event) {
        if (username == null) {
            System.out.println("No se ha proporcionado el username.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        reportContent.setLength(0);
        reportContent.append("Reporte Dunima Marketplace\n\n")
                .append("Fecha: ").append(currentDate).append("\n")
                .append("Reporte realizado por: ").append(username).append("\n\n");

        if (rdbProductPubli.isSelected()) {
            generateProductsByDateReport();
        }
        if (rdbProductsSeller.isSelected()) {
            generateProductsBySellerReport();
        }
        if (rdbCantSeller.isSelected()) {
            generateContactsBySellerReport();
        }
        if (rdbMaxLike.isSelected()) {
//            generateTopLikedProductsReport();
        }
        saveReportToFile(event);
    }

    private void generateProductsByDateReport() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String usernameReport = txtUsernameAllProductPublicated.getText();
        if (startDate == null || endDate == null) {
            reportContent.append("Reporte inválido: Por favor, seleccione un rango de fechas válido.\n");
            return;
        }
        if (startDate.isAfter(endDate)) {
            reportContent.append("Reporte inválido: La fecha de inicio no puede ser posterior a la fecha de fin.\n");
            return;
        }

        ProductController productController = new ProductController();
        List<ProductDto> productsInRange = productController.getProducts(usernameReport).stream()
                .filter(product -> {
                    LocalDate publicationDate = product.publicationDate().toLocalDate();
                    boolean isInRange = !publicationDate.isBefore(startDate) && !publicationDate.isAfter(endDate);
                    return isInRange && "Published".equals(product.status());
                })
                .toList();
        reportContent.append("Reporte de productos publicados entre ")
                .append(startDate).append(" y ").append(endDate).append("\n\n");

        if (productsInRange.isEmpty()) {
            reportContent.append("No se encontraron productos publicados en este rango de fechas.\n");
        } else {
            reportContent.append("Total de productos publicados: ").append(productsInRange.size()).append("\n\n");
            for (ProductDto product : productsInRange) {
                reportContent.append("Nombre: ").append(product.name()).append("\n")
                        .append("Categoría: ").append(product.category()).append("\n")
                        .append("Precio: ").append(product.price()).append("\n\n")
                        .append("Fecha: ").append(product.publicationDate()).append("\n\n");
            }
        }
    }


    private void generateProductsBySellerReport() {
        String seller = txtUsernameAllProductPublicated.getText();

        if (seller == null || seller.trim().isEmpty()) {
            reportContent.append("Por favor, ingrese el nombre del vendedor.\n");
            return;
        }

        ProductController productController = new ProductController();
        List<ProductDto> productsBySeller = productController.getProducts(seller);

        reportContent.append("Reporte de productos publicados por el vendedor: ").append(seller).append("\n\n");

        if (productsBySeller.isEmpty()) {
            reportContent.append("No se encontraron productos para este vendedor.\n");
        } else {
            reportContent.append("Total de productos publicados: ").append(productsBySeller.size()).append("\n\n");
            for (ProductDto product : productsBySeller) {
                reportContent.append("Nombre: ").append(product.name()).append("\n")
                        .append("Categoría: ").append(product.category()).append("\n")
                        .append("Precio: ").append(product.price()).append("\n\n");
            }
        }
    }

    private void generateContactsBySellerReport() {
        SellerController sellerController = new SellerController();
        List<SellerDto> contactsBySeller = sellerController.getSellerFriends();

        reportContent.append("Cantidad de contactos por vendedor:\n\n");

        if (contactsBySeller.isEmpty()) {
            reportContent.append("No se encontraron contactos registrados.\n");
        } else {
            contactsBySeller.forEach((seller) -> {
                reportContent.append("Vendedor: ").append(seller).append(", Contactos: ").append("\n");
            });
        }
    }

    private void saveReportToFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(getStage(event));

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(reportContent.toString());
                System.out.println("Reporte exportado correctamente a " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El usuario no seleccionó un archivo.");
        }
    }

    private Stage getStage(ActionEvent event) {
        return (Stage) ((Button) event.getSource()).getScene().getWindow();
    }
}





