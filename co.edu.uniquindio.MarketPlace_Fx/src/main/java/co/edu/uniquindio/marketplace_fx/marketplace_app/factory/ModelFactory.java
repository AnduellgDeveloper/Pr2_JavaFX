package co.edu.uniquindio.marketplace_fx.marketplace_app.factory;

public class ModelFactory {
    private static ModelFactory modelFactory;
    public static ModelFactory getInstance() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private void inicializarDatos(){

    }
}
