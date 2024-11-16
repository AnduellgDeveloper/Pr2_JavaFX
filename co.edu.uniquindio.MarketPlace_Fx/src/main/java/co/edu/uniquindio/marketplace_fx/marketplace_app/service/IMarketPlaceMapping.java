package co.edu.uniquindio.marketplace_fx.marketplace_app.service;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.*;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.*;

import java.util.List;

public interface IMarketPlaceMapping {
    // ------------------------ Products -----------------------
    List<ProductDto> getProductsDto(List<Product> products);
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> productsToProductsDto(List<Product> products);
    List<Product> productsDtoToProducts(List<ProductDto> productDtos);

    Product toObjectProduct(ProductDto product);
    // ------------------------ Sellers ------------------------
    List<SellerDto> getSellersDto(List<Seller> listSellers);
    SellerDto sellertToSellerDto(Seller seller);
    Seller sellerDtoToSeller(SellerDto sellerDto);

    Seller  toObjectSeller(SellerDto seller);
    // ------------------------ Users -----------------------
    List<UserDto> getUsersDto(List<User> listUsers);
    UserDto userToUserDto(User user);
    User userDtoToUserType(UserDto userDto);

    // ------------------------ Administrator -----------------------
    List<AdministratorDto> getAdministratorsDto(List<Administrator> listAdministrators);
    AdministratorDto administratorToAdministratorDto(Administrator administrator);
    // ------------------------ Like ------------------------
    List<LikeDto> getLikesDto(List<Like> likes);
    LikeDto likeToLikeDto(Like like);
    Like likeDtoToLike(LikeDto likeDto);
    List<LikeDto> likesToLikesDto(List<Like> likes);
    List<Like> likesDtoToLikes(List<LikeDto> likeDtos);
    Like toObjectLike(LikeDto like);
    // ------------------------ Comment ------------------------
    List<CommentDto> getCommentsDto(List<Comment> comments);
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(Comment comment);
    List<Comment> commentsTocommentsDto(List<Comment> comments);
    List<Comment> commentsDtoToComments(List<CommentDto> commentDtos);
    Like toObjectComment(CommentDto comment);


}
