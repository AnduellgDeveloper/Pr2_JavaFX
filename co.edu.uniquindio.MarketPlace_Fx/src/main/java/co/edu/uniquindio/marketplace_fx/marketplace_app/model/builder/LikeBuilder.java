package co.edu.uniquindio.marketplace_fx.marketplace_app.model.builder;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Like;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;

public  class LikeBuilder {
    private Product product;
    private int likes;

    public LikeBuilder product (Product product){
        this.product=product;
        return this;
    }
    public LikeBuilder likes(int likes){
        this.likes=likes;
        return this;
    }
    public Like build(){
        return new Like( product,likes);

    }



}
