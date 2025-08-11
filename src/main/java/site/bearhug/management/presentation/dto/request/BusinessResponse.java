package site.bearhug.management.presentation.dto.request;

import site.bearhug.management.persistence.entity.BusinessEntity;

public record BusinessResponse(String uuid, String name, String description) {
    public static BusinessResponse of(BusinessEntity entity) {
        return new BusinessResponse(entity.getId(), entity.getBusinessName(), entity.getDescription());
    }
}
