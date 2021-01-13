package com.kielbiowski.parkproject.dto;

import com.kielbiowski.parkproject.model.Role;
import com.kielbiowski.parkproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Data
public class RoleDTO {
    private Integer id;

    @NotNull
    private String name;

    public static Role toRole(RoleDTO roleDTO) {
        return new Role(
                roleDTO.getId(),
                roleDTO.getName(),
                null);
    }

    public static RoleDTO toRoleDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getName());
    }
}
