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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.persistence.entity.products.InventoryEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "branch")
public class BranchEntity {

    public BranchEntity(String name, BusinessEntity business) {
        this.name = name;
        this.inventory = new InventoryEntity();
        this.business = business;
    }

    public BranchEntity(String name, String address, String description, BusinessEntity business) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.inventory = new InventoryEntity();
        this.business = business;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String address;

    @Column(length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BusinessEntity.class, optional = false)
    @JoinColumn(name = "bussiness_id", nullable = false)
    private BusinessEntity business;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true,  fetch = FetchType.LAZY,  optional = false, targetEntity = InventoryEntity.class)
    @JoinColumn(name = "inventory_id", nullable = false, updatable = false, unique = true)
    private InventoryEntity inventory;
}
