package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.ServiceDTO;
import lk.ijse.voaestheticlounge.repo.ServiceRepository;
import lk.ijse.voaestheticlounge.service.ServicesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceServiceImpl implements ServicesService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void save(ServiceDTO serviceDTO) {
        serviceRepository.save(modelMapper.map(serviceDTO, lk.ijse.voaestheticlounge.entity.Service.class));
    }

    @Override
    public void delete(Long id) {
    serviceRepository.deleteById(id);
    }

    @Override
    public void update(Long id, ServiceDTO serviceDTO) {
        lk.ijse.voaestheticlounge.entity.Service exService = serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found with ID: "+id));
        exService.setName(serviceDTO.getName());
        exService.setDescription(serviceDTO.getDescription());
        exService.setPrice(serviceDTO.getPrice());
        exService.setImageUrl(serviceDTO.getImageUrl());

        serviceRepository.save(exService);
    }

    @Override
    public List<ServiceDTO> getAll() {
        return modelMapper.map(serviceRepository.findAll(),new TypeToken<List<ServiceDTO>>() {}.getType());
    }

    @Override
    public ServiceDTO findByName(String name) {
        return modelMapper.map(serviceRepository.findByName(name),ServiceDTO.class);
    }
}
