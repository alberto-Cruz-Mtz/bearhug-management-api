package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.InventoryProductResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.ProductInventoryRequest;

public interface InventoryProductService {

    Response<InventoryProductResponse> addProductToInventory(Long inventoryId, String businessId, ProductInventoryRequest request);

    void removeProductFromInventory(Long inventoryId, String barcode, String businessId);

    Response<InventoryProductResponse> updateProductInInventory(Long inventoryId, String businessId, ProductInventoryRequest request);

    Response<InventoryProductResponse> findProductInInventory(Long inventoryId, String businessId, String barcode);
}
