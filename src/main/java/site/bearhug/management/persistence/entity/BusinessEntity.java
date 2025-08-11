package site.bearhug.management.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.persistence.entity.products.ProductCategoryEntity;
import site.bearhug.management.persistence.entity.products.ProductEntity;
import site.bearhug.management.persistence.entity.user.UserEntity;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "business")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String businessName;

    @Column(length = 300)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = BranchEntity.class, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "business")
    private List<BranchEntity> branches;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProductCategoryEntity.class, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "business")
    private List<ProductCategoryEntity> categories;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ProductEntity.class, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "business")
    private List<ProductEntity> products;

    public static BusinessEntity create(String name, String description, UserEntity user) {
        BusinessEntity entity = BusinessEntity.builder()
                .businessName(name)
                .description(description)
                .user(user)
                .build();
        entity.setBranches(List.of(new BranchEntity("default", entity)));

        return entity;
    }
}
