package site.bearhug.management.persistence.entity.products;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.persistence.entity.BusinessEntity;
import site.bearhug.management.presentation.dto.model.Product;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"barcode", "business_id"})}
)
public class ProductEntity {

    public ProductEntity(String barcode, String name, String description) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String barcode;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(length = 180)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, targetEntity = ProductCategoryEntity.class, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BusinessEntity.class, optional = false)
    @JoinColumn(name = "business_id", nullable = false)
    private BusinessEntity business;

    public static ProductEntity of(Product product) {
        return new ProductEntity(product.barcode(), product.name(), product.description());
    }
}
