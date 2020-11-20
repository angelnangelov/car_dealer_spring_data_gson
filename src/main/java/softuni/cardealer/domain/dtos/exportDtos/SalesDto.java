package softuni.cardealer.domain.dtos.exportDtos;

import com.google.gson.annotations.Expose;

public class SalesDto {
    public SalesDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Expose
    private String name;
}
