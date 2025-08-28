package site.bearhug.management.service.interfaces;

import site.bearhug.management.presentation.dto.InventoryProductResponse;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.request.ProductInventoryRequest;

import java.util.List;

public interface InventoryProductService {

    Response<InventoryProductResponse> addProductToInventory(Long inventoryId, String businessId,
                                                             ProductInventoryRequest request);

    Response<Void> removeProductFromInventory(Long inventoryId, String barcode, String businessId);

    Response<InventoryProductResponse> updateProductInInventory(Long inventoryId, String businessId,
                                                                ProductInventoryRequest request);

    Response<InventoryProductResponse> findProductInInventory(Long inventoryId, String businessId, String barcode);

    Response<List<InventoryProductResponse>> findAllProductInInventory(Long inventoryId, String businessId);

    Response<List<InventoryProductResponse>> searchByMatching(String match, String businessId);
}
