package co.edu.uniquindio.marketplace_fx.marketplace_app.viewcontroller;

import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.ProductController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.controller.SellerController;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Marketplace;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.session.Session;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.List;

public class AdministratorViewController {
    private ObservableList<ProductDto> products;
    private Marketplace datautil;
    @FXML
    public void initialize(){

    }
    public void initProducts(ObservableList<ProductDto> products) {
        this.products = products;
    }
    @FXML
    private ListView<Product> productListView;

    private StringBuilder reportContent = new StringBuilder();
    private String username;
    private Session session;

    @FXML
    private TextField txtTopLikeProducts, txtUsernameContacts;
    @FXML
    private TextField txtUsernameAllProductPublicated;

    @FXML
    private DatePicker startDatePicker,endDatePicker;
    @FXML
    private Button btnExport;
    @FXML
    private RadioButton rdbCantSeller, rdbMaxLike, rdbProductPubli, rdbProductsSeller;
    @FXML
    private StackedBarChart<?, ?> staticts;
    public void setUsername(String username) {
        this.username = username;
    }

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
        reportContent.append("══════════════════════════════════════════════\n")
                .append("           DUNIMA MARKETPLACE REPORT\n")
                .append("══════════════════════════════════════════════\n\n")
                .append("Fecha de Generación: ").append(currentDate).append("\n")
                .append("Generado por el Administrador: ").append(username).append("\n\n");

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
            generateTopLikedProductsReport();
        }
        saveReportToFile(event);
    }

    private void generateProductsByDateReport() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String usernameReport = txtUsernameAllProductPublicated.getText();

        reportContent.append("▶ Reporte de Productos Publicados Entre Fechas ◀\n")
                .append("Rango de fechas: ").append(startDate).append(" - ").append(endDate).append("\n\n");

        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            reportContent.append("⚠ Por favor, seleccione un rango de fechas válido.\n");
            return;
        }

        ProductController productController = new ProductController();
        List<ProductDto> productsInRange = productController.getProducts(usernameReport).stream()
                .filter(product -> {
                    LocalDate publicationDate = product.publicationDate().toLocalDate();
                    return !publicationDate.isBefore(startDate) && !publicationDate.isAfter(endDate) && "Published".equals(product.status());
                }).toList();

        if (productsInRange.isEmpty()) {
            reportContent.append("⚠ No se encontraron productos publicados en este rango de fechas.\n");
        } else {
            reportContent.append("Total de productos publicados: ").append(productsInRange.size()).append("\n\n");
            int count = 1;
            for (ProductDto product : productsInRange) {
                reportContent.append(count).append("️✅Nombre: ").append(product.name()).append("\n")
                        .append("   Categoría: ").append(product.category()).append("\n")
                        .append("   Precio: $").append(product.price()).append("\n")
                        .append("   Fecha de Publicación: ").append(product.publicationDate()).append("\n\n");
                count++;
            }
        }
    }

    private void generateProductsBySellerReport() {
        String seller = txtUsernameAllProductPublicated.getText();

        reportContent.append("▶ Reporte de Productos por Vendedor ◀\n")
                .append("Vendedor: ").append(seller).append("\n\n");

        if (seller == null || seller.trim().isEmpty()) {
            reportContent.append("⚠ Por favor, ingrese el nombre del vendedor.\n");
            return;
        }

        ProductController productController = new ProductController();
        List<ProductDto> productsBySeller = productController.getProducts(seller);

        if (productsBySeller.isEmpty()) {
            reportContent.append("⚠ No se encontraron productos publicados por este vendedor.\n");
        } else {
            reportContent.append("Total de productos publicados: ").append(productsBySeller.size()).append("\n\n");
            int count = 1;
            for (ProductDto product : productsBySeller) {
                reportContent.append(count).append("️✅Nombre: ").append(product.name()).append("\n")
                        .append("   Categoría: ").append(product.category()).append("\n")
                        .append("   Precio: $").append(product.price()).append("\n\n");
                count++;
            }
        }
    }
    private void generateContactsBySellerReport() {
        String usernameReport = txtUsernameContacts.getText();
        SellerController sellerController = new SellerController();
        List<SellerDto> contactsBySeller = sellerController.getFriends(usernameReport);
        reportContent.append("▶ Reporte de Contactos por Vendedor ◀\n\n");
        if (contactsBySeller.isEmpty()) {
            reportContent.append("⚠ No se encontraron contactos registrados.\n");
        } else {
            boolean firstSeller = true;
            for (SellerDto seller : contactsBySeller) {
                reportContent.append("✅Vendedor: ").append(seller.username());
                if (firstSeller) {
                    reportContent.append(" - Contactos: ").append(seller.friends().size());
                    firstSeller = false;
                }
                reportContent.append("\n");
            }
        }
    }
    private void generateTopLikedProductsReport() {
        reportContent.append("▶ Reporte de Productos Más Populares ◀\n\n");
        reportContent.append("⚠ Esta funcionalidad aún no está implementada.\n");
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
