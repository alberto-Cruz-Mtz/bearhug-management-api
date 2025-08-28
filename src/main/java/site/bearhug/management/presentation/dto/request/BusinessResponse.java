package site.bearhug.management.presentation.dto.request;

import site.bearhug.management.persistence.entity.BusinessEntity;

public record BusinessResponse(String uuid, String name, String description, Long inventoryId) {
  public static BusinessResponse of(BusinessEntity entity, Long inventory) {
    return new BusinessResponse(entity.getId(), entity.getBusinessName(), entity.getDescription(), inventory);
  }
}
