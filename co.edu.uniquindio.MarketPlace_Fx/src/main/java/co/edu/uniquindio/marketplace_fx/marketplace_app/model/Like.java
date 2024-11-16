package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.LikeBuilder;

public class Like {
    public Product product;
    private int likes;

    public Like(Product product, int likes) {
        this.product = product;
        likes = 0;
    }

    public int getLikes() {
        return likes;
    }
    public Product getProduct(){return product;}

    public void addLike() {
        this.likes++;
    }
    public static  LikeBuilder builder(){return new LikeBuilder();
    }

}
