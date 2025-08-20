package site.bearhug.management.presentation.dto;

import site.bearhug.management.persistence.entity.user.ProfileEntity;

public record ProfileResponse(
        String name,
        String lastname,
        String picture
) {

    public static ProfileResponse of(ProfileEntity entity) {
        return new ProfileResponse(entity.getName(), entity.getLastName(), entity.getPicture());
    }
}
