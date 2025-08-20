package site.bearhug.management.persistence.entity.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bearhug.management.presentation.dto.request.ProfileRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {

    public ProfileEntity(String name, String lastName, String picture) {
        this.name = name;
        this.lastName = lastName;
        this.picture = picture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uid;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false, length = 70, name = "lastname")
    private String lastName;

    @Column(nullable = false)
    private String picture;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;


    public static ProfileEntity of(ProfileRequest profile, String picture) {
        return new ProfileEntity(profile.name(), profile.lastname(), picture);
    }
}
