package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder.LikeBuilder;

public class Like {
    public Product product;
    private int likes;
    private Seller seller;


    public Like(Product product, int likes,Seller seller) {
        this.product = product;
        likes = 0;
        this.seller=seller;
    }

    public int getLikes() {
        return likes;
    }
    public Product getProduct(){return product;}
    public Seller getSeller(){return seller;}

    public void addLike() {
        this.likes++;
    }
    public static  LikeBuilder builder(){return new LikeBuilder();
    }

}
