package site.bearhug.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bearhug.management.persistence.entity.BusinessEntity;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;
import site.bearhug.management.persistence.repository.BusinessRepository;
import site.bearhug.management.persistence.repository.product.ProductCategoryRepository;
import site.bearhug.management.presentation.dto.Response;
import site.bearhug.management.presentation.dto.model.Status;
import site.bearhug.management.presentation.dto.request.CategoryRequest;
import site.bearhug.management.service.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository repository;
    private final BusinessRepository businessRepository;

    @Transactional()
    public Response<Void> create(CategoryRequest request, String businessId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found!"));

        if (repository.existsByCategoryNameAndBusiness(request.name(), business)) {
            throw new IllegalArgumentException("Category already exists!");
        }

        ProductCategoryEntity entity = new ProductCategoryEntity(request.name(), request.description(), business);
        ProductCategoryEntity saved = repository.save(entity);

        return new Response<>(null, Status.SUCCESS, "categoria creada correctamente", null);
    }

    @Transactional(readOnly = true)
    public Response<List<CategoryRequest>> findAll(String businessId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found!"));
        List<ProductCategoryEntity> categories = repository.findAllByBusiness(business);
        return new Response<>(categories.stream().map(CategoryRequest::of).toList(), Status.SUCCESS, "categorias encontradas", null);
    }

    @Transactional()
    public Response<Void> delete(String businessId, Long categoryId) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found!"));
        ProductCategoryEntity category = repository.findByIdAndBusiness(categoryId, businessId).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        repository.delete(category);

        return new Response<>(null, Status.SUCCESS, "categoria eliminada", null);
    }

    @Transactional
    public Response<Void> update(String businessId, Long categoryId, CategoryRequest request) {
        BusinessEntity business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found!"));
        ProductCategoryEntity category = repository.findByIdAndBusiness(categoryId, businessId).orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        category.setCategoryName(request.name());
        category.setDescription(request.description());

        repository.save(category);
        return new Response<>(null, Status.SUCCESS, "categoria actualizada", null);
    }
}
