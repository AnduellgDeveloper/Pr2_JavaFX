package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Like;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;

public  class LikeBuilder {
    private Product product;
    private int likes;
    private Seller seller;

    public LikeBuilder product (Product product){
        this.product=product;
        return this;
    }
    public LikeBuilder seller(Seller seller){
        this.seller=seller;
        return this;
    }
    public LikeBuilder likes(int likes){
        this.likes=likes;
        return this;
    }
    public Like build(){
        return new Like( product,likes,seller);

    }



}
