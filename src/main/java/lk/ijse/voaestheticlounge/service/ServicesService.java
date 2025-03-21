package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.ServiceDTO;

import java.util.List;

public interface ServicesService {
    void save(ServiceDTO serviceDTO);
    void delete(Long id);
    void update(Long id, ServiceDTO serviceDTO);
    List<ServiceDTO> getAll();
    ServiceDTO findByName(String name);
}
