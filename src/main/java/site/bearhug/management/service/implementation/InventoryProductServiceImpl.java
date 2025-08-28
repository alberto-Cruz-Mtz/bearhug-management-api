package site.bearhug.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.products.InventoryEntity;
import site.bearhug.management.persistence.entity.products.InventoryProductEntity;
import site.bearhug.management.persistence.entity.products.ProductEntity;
import site.bearhug.management.persistence.repository.product.InventoryProductRepository;
import site.bearhug.management.persistence.repository.product.InventoryRepository;
import site.bearhug.management.persistence.repository.product.ProductRepository;
import site.bearhug.management.presentation.dto.InventoryProductResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Status;
import site.bearhug.management.presentation.dto.request.ProductInventoryRequest;
import site.bearhug.management.service.exception.ResourceNotFoundException;
import site.bearhug.management.service.interfaces.InventoryProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryProductServiceImpl implements InventoryProductService {

    private final InventoryProductRepository repository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Response<InventoryProductResponse> addProductToInventory(Long inventoryId, String businessId, ProductInventoryRequest request) {
        InventoryEntity inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));
        ProductEntity product = productRepository
                .findByBarcodeAndBusinessId(request.barcode(), businessId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));

        InventoryProductEntity inventoryProduct = InventoryProductEntity.create(inventory, product, request.price(), request.stocks());
        InventoryProductEntity saved = repository.save(inventoryProduct);
        return new Response<>(InventoryProductResponse.of(saved), Status.SUCCESS, "product added to inventory successfully", null);
    }

    @Override
    @Transactional
    public Response<Void> removeProductFromInventory(Long inventoryId, String barcode, String businessId) {
        InventoryProductEntity product = findProductInInventory(businessId, barcode, inventoryId);
        repository.delete(product);
        return new Response<Void>(null, Status.SUCCESS, "producto removido del inventario", null);
    }

    @Override
    @Transactional
    public Response<InventoryProductResponse> updateProductInInventory(Long inventoryId, String businessId, ProductInventoryRequest request) {
        InventoryProductEntity product = findProductInInventory(businessId, request.barcode(), inventoryId);
        InventoryProductEntity.setStocksFields(request.stocks(), product);
        InventoryProductEntity.setPricesFields(request.price(), product);

        InventoryProductEntity saved = repository.save(product);

        return new Response<>(InventoryProductResponse.of(saved), Status.SUCCESS, "producto actualizado exitosamente", null);
    }

    @Override
    @Transactional(readOnly = true)
    public Response<InventoryProductResponse> findProductInInventory(Long inventoryId, String businessId, String barcode) {
        InventoryProductEntity product = this.findProductInInventory(businessId, barcode, inventoryId);
        return new Response<>(InventoryProductResponse.of(product), Status.SUCCESS, "producto creado correctamente", null);
    }

    @Transactional(readOnly = true)
    public InventoryProductEntity findProductInInventory(String businessId, String barcode, Long inventoryId) {
        ProductEntity product = productRepository.findByBarcodeAndBusinessId(barcode, businessId).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        InventoryEntity inventory = inventoryRepository.findById(inventoryId).orElseThrow(() -> new ResourceNotFoundException("Inventory not found!"));

        return repository.findByInventoryIdAndProductId(inventoryId, product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Response<List<InventoryProductResponse>> findAllProductInInventory(Long inventoryId, String businessId) {
      List<InventoryProductEntity> list = repository.findAllByInventoryIdAndBusinessId(inventoryId, businessId);
      List<InventoryProductResponse> result = list.stream().map(InventoryProductResponse::of).toList();
      return new Response<>(result, Status.SUCCESS, "productos encontrados", null);
    }

    @Transactional(readOnly = true)
    public Response<List<InventoryProductResponse>> searchByMatching(String match, String businessId) {
        List<InventoryProductEntity> list = repository.searchByMatching(match, businessId);
        List<InventoryProductResponse> result = list.stream().map(InventoryProductResponse::of).toList();
        return new Response<>(result, Status.SUCCESS, null, null);
    }
}
