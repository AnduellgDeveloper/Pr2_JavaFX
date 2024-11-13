package co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.mappers;

import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.AdministratorDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.ProductDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.SellerDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.mapping.dto.UserDto;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Administrator;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Product;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.Seller;
import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;
import co.edu.uniquindio.marketplace_fx.marketplace_app.service.IMarketPlaceMapping;

import java.util.ArrayList;
import java.util.List;

public class MarketPlaceMappingImpl implements IMarketPlaceMapping {
    // ------------------------ Products -----------------------
    @Override
    public List<ProductDto> getProductsDto(List<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        List<ProductDto> listProductsDto = new ArrayList<>(products.size());
        for (Product product : products) {
            listProductsDto.add(productToProductDto(product));
        }
        return listProductsDto;
    }
    @Override
    public ProductDto productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDto(
                product.getName(),
                product.getImage(),
                product.getCategory(),
                product.getPrice(),
                product.getStatus(),
                product.getPublicationDate());
    }
    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .name(productDto.name())
                .image(productDto.image())
                .category(productDto.category())
                .price(productDto.price())
                .status(productDto.status())
                .publicationDate(productDto.publicationDate())
                .build();
    }
    @Override
    public Product toObjectProduct(ProductDto product) {
        return null;
    }

    // ------------------------ Sellers -----------------------

    @Override
    public List<SellerDto> getSellersDto(List<Seller> listSellers) {
        if (listSellers == null) {
            return null;
        }
        List<SellerDto> listSellersDto = new ArrayList<>(listSellers.size());
        for (Seller seller : listSellers) {
            listSellersDto.add(sellertToSellerDto(seller));
        }
        return listSellersDto;
    }
    @Override
    public SellerDto sellertToSellerDto(Seller seller) {
        if (seller == null) {
            return null;
        }
        return new SellerDto(
                seller.getName(),
                seller.getLastName(),
                seller.getIdNumber(),
                seller.getAddress(),
                seller.getUsername(),
                seller.getPassword());
    }
    @Override
    public Seller sellerDtoToSeller(SellerDto sellerDto) {
        if (sellerDto == null) {
            return null;
        }
        return Seller.builder()
                .name(sellerDto.name())
                .lastName(sellerDto.lastName())
                .idNumber(sellerDto.idNumber())
                .address(sellerDto.address())
                .username(sellerDto.username())
                .password(sellerDto.password())
                .build();
    }

    @Override
    public Seller toObjectSeller(SellerDto seller) {
        return null;
    }


    // ------------------------ Users -----------------------
    @Override
    public List<UserDto> getUsersDto(List<User> listUsers) {
        if (listUsers == null) {
            return null;
        }
        List<UserDto> listUsersDto = new ArrayList<>(listUsers.size());
        for (User user : listUsers) {
            listUsersDto.add(userToUserDto(user));
        }
        return listUsersDto;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getName(),
                user.getLastName(),
                user.getIdNumber(),
                user.getAddress(),
                user.getUsername(),
                user.getPassword());
    }


    @Override
    public User userDtoToUserType(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        if (isAdministrator(userDto)) {
            return Administrator.builder()
                    .name(userDto.name())
                    .lastName(userDto.lastName())
                    .idNumber(userDto.idNumber())
                    .address(userDto.address())
                    .username(userDto.username())
                    .password(userDto.password())
                    .build();
        } else if (isSeller(userDto)) {
            return Seller.builder()
                    .name(userDto.name())
                    .lastName(userDto.lastName())
                    .idNumber(userDto.idNumber())
                    .address(userDto.address())
                    .username(userDto.username())
                    .password(userDto.password())
                    .build();
        } else {
            return User.builder()
                    .name(userDto.name())
                    .lastName(userDto.lastName())
                    .idNumber(userDto.idNumber())
                    .address(userDto.address())
                    .username(userDto.username())
                    .password(userDto.password())
                    .build();
        }
    }

    private boolean isSeller(UserDto userDto) {
        return userDto.idNumber().matches("^\\d{3,}$") && !isAdministrator(userDto);
    }

    public boolean isAdministrator(UserDto userDto) {
        return userDto.idNumber() != null && userDto.idNumber().matches("^\\d{2}$");
    }



    @Override
    public User toObjectUser(UserDto user) {
        return null;
    }
    // ------------------------ Administrators -----------------------

    @Override
    public List<AdministratorDto> getAdministratorsDto(List<Administrator> listAdministrators) {
        if (listAdministrators == null) {
            return null;
        }
        List<AdministratorDto> listAdministratorsDto = new ArrayList<>(listAdministrators.size());
        for (Administrator administrator : listAdministrators) {
            listAdministratorsDto.add(administratorToAdministratorDto(administrator));
        }
        return listAdministratorsDto;
    }

    @Override
    public AdministratorDto administratorToAdministratorDto(Administrator administrator) {
        if (administrator == null) {
            return null;
        }
        return new AdministratorDto(
                administrator.getName(),
                administrator.getLastName(),
                administrator.getIdNumber(),
                administrator.getAddress(),
                administrator.getUsername(),
                administrator.getPassword());
    }

    @Override
    public Administrator administratorDtoToAdministrator(AdministratorDto administratorDto) {
        if (administratorDto == null) {
            return null;
        }
        return Administrator.builder()
                .name(administratorDto.name())
                .lastName(administratorDto.lastName())
                .idNumber(administratorDto.idNumber())
                .address(administratorDto.address())
                .username(administratorDto.username())
                .password(administratorDto.password())
                .build();
    }

    @Override
    public Administrator toObjectAdministrator(AdministratorDto administrator) {
        return null;
    }


}