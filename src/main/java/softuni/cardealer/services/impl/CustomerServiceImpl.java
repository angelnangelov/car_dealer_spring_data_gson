package softuni.cardealer.services.impl;



import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.cardealer.domain.dtos.importDtos.CustomerSeedDto;
import softuni.cardealer.domain.dtos.exportDtos.CustomerExportDto;
import softuni.cardealer.domain.enitities.Customer;
import softuni.cardealer.domain.repositories.CustomerRepository;
import softuni.cardealer.services.CustomerService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final static String CUSTOMER_PATH = "src/main/resources/jsons/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCustomer() throws IOException {
    String content = String.join(" ", Files.readAllLines(Path.of(CUSTOMER_PATH)));

        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);

        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }


    }

    @Override
    public String orderedCustomers() {
    Set<Customer> allByoreder = this.customerRepository.getAllByOrderByYoungDriverAscBirthDateAsc();


        List<CustomerExportDto> toExport = new ArrayList<>();

        for (Customer customer : allByoreder) {
          CustomerExportDto  customerExportDto=   this.modelMapper.map(customer, CustomerExportDto.class);
          toExport.add(customerExportDto);
        }
        return this.gson.toJson(toExport);

    }
}
