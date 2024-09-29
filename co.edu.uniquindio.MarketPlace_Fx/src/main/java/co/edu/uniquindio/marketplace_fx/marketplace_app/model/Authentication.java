package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.util.ArrayList;
import java.util.List;

public class Authentication {
    private static Authentication instance;
    private List<Seller> sellers;
    private List<Administrator> administrators;

    public Authentication(List<Seller> sellers, List<Administrator> administrators) {
        this.sellers = sellers;
        this.administrators = administrators;
    }
// ----------------------------- Getters and Setters -----------------------------
    public static Authentication getInstance() {return instance;}
    public static void setInstance(Authentication instance) {Authentication.instance = instance;}
    public List<Seller> getSellers() {return sellers;}
    public void setSellers(List<Seller> sellers) {this.sellers = sellers;}
    public List<Administrator> getAdministrators() {return administrators;}
    public void setAdministrators(List<Administrator> administrators) {this.administrators = administrators;}
}