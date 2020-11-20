package softuni.cardealer.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.cardealer.domain.dtos.importDtos.PartsSeedDto;
import softuni.cardealer.domain.enitities.Part;
import softuni.cardealer.domain.enitities.Supplier;
import softuni.cardealer.domain.repositories.PartRepository;
import softuni.cardealer.domain.repositories.SupplierRepository;
import softuni.cardealer.services.PartService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final String PARTS_PATH = "src/main/resources/jsons/parts.json";
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final SupplierRepository supplierRepository;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, Gson gson, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedParts() throws Exception {
        //content
        String content = String.join(" ", Files.readAllLines(Path.of(PARTS_PATH)));

        //dto

        PartsSeedDto[] partsSeedDtos = this.gson.fromJson(content, PartsSeedDto[].class);

        //save
        for (PartsSeedDto partsSeedDto : partsSeedDtos) {
            Part part = this.modelMapper.map(partsSeedDto, Part.class);
            part.setSupplier(getRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() throws Exception {
        Random random = new Random();
        int index = random.nextInt((int) this.supplierRepository.count()) + 1;
        Optional<Supplier> supplier = this.supplierRepository.findById((long) index);
        if(supplier.isPresent()){
             return  supplier.get();
        }else{
            throw  new Exception("Supplier don't exist");
        }
    }
}
