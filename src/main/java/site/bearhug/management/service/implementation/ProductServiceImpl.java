package site.bearhug.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;
import site.bearhug.management.persistence.entity.products.ProductEntity;
import site.bearhug.management.persistence.repository.product.ProductCategoryRepository;
import site.bearhug.management.persistence.repository.product.ProductRepository;
import site.bearhug.management.presentation.dto.ProductMatchResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Product;
import site.bearhug.management.presentation.dto.model.Status;
import site.bearhug.management.service.exception.ResourceNotFoundException;
import site.bearhug.management.service.interfaces.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;

    @Override
    @Transactional
    public Response<Product> createNewProduct(Product product) {
        ProductCategoryEntity category = categoryRepository.findById(product.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("category not found!"));

        if (repository.existsByBarcodeAndBusinessId(product.barcode(), category.getBusiness().getId())) {
            throw new IllegalArgumentException("Product already exists!");
        }

        ProductEntity entity = ProductEntity.of(product);
        entity.setCategory(category);
        entity.setBusiness(category.getBusiness());

        ProductEntity saved = repository.save(entity);
        return new Response<>(Product.of(saved), Status.SUCCESS, "Product created successfully", null);
    }

    @Override
    @Transactional
    public void deleteProduct(String barcode, String businessId) {
        ProductEntity product = this.findProductEntity(barcode, businessId);
        repository.delete(product);
    }

    @Override
    @Transactional
    public Response<Product> updateProduct(Product product, String businessId) {
        ProductEntity entity = this.findProductEntity(product.barcode(), businessId);

        entity.setBarcode(product.barcode());
        entity.setName(product.name());
        entity.setDescription(product.description());

        if (!entity.getCategory().getId().equals(product.categoryId())) {
            ProductCategoryEntity category = categoryRepository.findById(product.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("category not found!"));
            entity.setCategory(category);
        }

        ProductEntity saved = repository.save(entity);
        return new Response<>(Product.of(saved), Status.SUCCESS, "Product updated successfully", null);
    }

    @Override
    @Transactional(readOnly = true)
    public Response<Product> findProduct(String barcode, String businessId) {
        ProductEntity product = this.findProductEntity(barcode, businessId);
        return new Response<>(Product.of(product), Status.SUCCESS, "Product found successfully", null);
    }

    @Override
    @Transactional(readOnly = true)
    public Response<List<Product>> findAllProduct(String businessId) {
        List<ProductEntity> list = repository.findAllByBusinessId(businessId);
        List<Product> products = list.stream().map(Product::of).toList();

        return new Response<>(products, Status.SUCCESS, "productos encontrados", null);
    }

    @Transactional(readOnly = true)
    public ProductEntity findProductEntity(String barcode, String businessId) {
        return repository.findByBarcodeAndBusinessId(barcode, businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public Response<List<ProductMatchResponse>> findByMatch(String match, String businessId) {
        List<ProductEntity> list = repository.searchByBarcodeOrName(match, businessId);
        List<ProductMatchResponse> matches = list.stream().map(ProductMatchResponse::of).toList();

        return new Response<>(matches, Status.SUCCESS, null, null);
    }
}
