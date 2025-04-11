package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.ProductDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.service.ProductService;
import lk.ijse.voaestheticlounge.service.impl.ProductServiceImpl;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    public final ProductService productService;
    public final ProductServiceImpl productServiceImpl;
    @Autowired
    JwtUtil jwtUtil;
    public ProductController(ProductService productService, ProductServiceImpl productServiceImpl) {
        this.productService = productService;
        this.productServiceImpl = productServiceImpl;
    }
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader("Authorization") String token){
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Added SuccessFully!",null));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Long id,@RequestBody @Valid ProductDTO productDTO , @RequestHeader("Authorization") String token){
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        productService.updateProduct(id,productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Updated SuccessFully!",null));
    }
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id ,@RequestHeader("Authorization") String token){
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Deleted SuccessFully!",null));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ResponseDTO> getAllProducts(@RequestHeader("Authorization") String token){
        jwtUtil.getUserRoleCodeFromToken(token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Success",productServiceImpl.getAllProducts()));
    }
}
