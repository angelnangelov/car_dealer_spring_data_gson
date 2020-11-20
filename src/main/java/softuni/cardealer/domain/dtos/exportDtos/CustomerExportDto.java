package softuni.cardealer.domain.dtos.exportDtos;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class CustomerExportDto {
    @Expose
    private long id;
    @Expose
    private String  name;
    @Expose
    private String birthDate;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private Set<SalesDto> salesDtos;

    public CustomerExportDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SalesDto> getSalesDtos() {
        return salesDtos;
    }

    public void setSalesDtos(Set<SalesDto> salesDtos) {
        this.salesDtos = salesDtos;
    }
}
