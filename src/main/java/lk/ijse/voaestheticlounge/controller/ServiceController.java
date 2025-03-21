package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.dto.ServiceDTO;
import lk.ijse.voaestheticlounge.service.ServicesService;
import lk.ijse.voaestheticlounge.service.impl.ServiceServiceImpl;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    private final ServicesService servicesService;
    private final ServiceServiceImpl serviceServiceImpl;

    public ServiceController( ServicesService servicesService, ServiceServiceImpl serviceServiceImpl) {
        this.servicesService = servicesService;
        this.serviceServiceImpl = serviceServiceImpl;
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid ServiceDTO serviceDTO){
        System.out.println(serviceDTO.getAppoimentDuration());
        System.out.println(serviceDTO.getImageUrl());
        servicesService.save(serviceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Service Added SuccessFully!",null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id){
        servicesService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Service Deleted SuccessFully!",null));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,@RequestBody @Valid ServiceDTO serviceDTO){
        servicesService.update(id,serviceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Service Updated SuccessFully!",null));
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Success",serviceServiceImpl.getAll()));
    }
}
