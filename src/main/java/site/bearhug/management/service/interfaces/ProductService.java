package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Product;

public interface ProductService {

    Response<Product> createNewProduct(Product product);

    void deleteProduct(String barcode, String businessId);

    Response<Product> updateProduct(Product product, String businessId);

    Response<Product> findProduct(String barcode, String businessId);
}
