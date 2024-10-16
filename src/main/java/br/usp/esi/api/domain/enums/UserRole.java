package br.usp.esi.api.domain.enums;

public enum UserRole {

    ADMIN("admin"),
    DISCENTE("discente"),
    DOSCENTE("doscente"),
    CCP("ccp");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}