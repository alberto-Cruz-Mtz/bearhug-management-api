package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.ProductMatchResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Product;

import java.util.List;

public interface ProductService {

  Response<Product> createNewProduct(Product product);

  void deleteProduct(String barcode, String businessId);

  Response<Product> updateProduct(Product product, String businessId);

  Response<Product> findProduct(String barcode, String businessId);

  Response<List<Product>> findAllProduct(String businessId);

  Response<List<ProductMatchResponse>> findByMatch(String match, String businessId);
}
