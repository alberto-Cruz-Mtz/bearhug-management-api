package site.bearhug.management.persistence.entity.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.persistence.entity.BusinessEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_category", uniqueConstraints = @UniqueConstraint(columnNames = {"bussiness_id", "name"}))

public class ProductCategoryEntity {

    public ProductCategoryEntity(String categoryName, String description, BusinessEntity business) {
        this.categoryName = categoryName;
        this.description = description;
        this.business = business;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String categoryName;

    @Column(length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BusinessEntity.class, optional = false)
    @JoinColumn(name = "bussiness_id", nullable = false)
    private BusinessEntity business;
}
