package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

import java.util.List;

public class CommentBuilder {
    private Product product;
    private List<String> comments;

    public CommentBuilder product(Product product){
        this.product= product;
        return this;
    }
    public CommentBuilder comments (List<String> comments){
        this.comments=comments;
        return this;
    }
}
