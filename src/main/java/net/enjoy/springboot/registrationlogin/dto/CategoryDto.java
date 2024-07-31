package net.enjoy.springboot.registrationlogin.dto;

public class CategoryDto {
    private int id;
    private String name;
    private boolean status;

    public CategoryDto() {
    }

    public CategoryDto(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }
}
