package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.CommentBuilder;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Product product;
    private List<String> comments;

    public Comment(Product product, List<String> comments) {
        this.product = product;
        this.comments =  new ArrayList<>();;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public static CommentBuilder builder(){return new CommentBuilder();}

}
