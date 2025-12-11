package com.tarefas.model.enums.user;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    USER_MANAGER("user_manager"),
    USER_EMPLOYEE("user_employee");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}
