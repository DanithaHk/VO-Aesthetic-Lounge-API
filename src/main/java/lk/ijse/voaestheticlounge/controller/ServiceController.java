package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.dto.ServiceDTO;
import lk.ijse.voaestheticlounge.service.ServicesService;
import lk.ijse.voaestheticlounge.service.impl.ServiceServiceImpl;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    private final ServicesService servicesService;
    private final ServiceServiceImpl serviceServiceImpl;
    @Autowired
    JwtUtil jwtUtil;
    public ServiceController( ServicesService servicesService, ServiceServiceImpl serviceServiceImpl) {
        this.servicesService = servicesService;
        this.serviceServiceImpl = serviceServiceImpl;
    }
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid ServiceDTO serviceDTO,@RequestHeader("Authorization") String token){
        jwtUtil.getUsernameFromToken(token.substring(7));
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id,@RequestBody @Valid ServiceDTO serviceDTO,@RequestHeader("Authorization") String token){
        jwtUtil.getUsernameFromToken(token.substring(7));
        servicesService.update(id,serviceDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Service Updated SuccessFully!",null));
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ResponseDTO> getAll(@RequestHeader("Authorization") String token){
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Success",serviceServiceImpl.getAll()));
    }
}
