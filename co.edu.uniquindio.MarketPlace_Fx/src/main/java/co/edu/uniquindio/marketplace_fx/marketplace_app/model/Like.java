package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

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

    public void addLike() {
        this.likes++;
    }

}
