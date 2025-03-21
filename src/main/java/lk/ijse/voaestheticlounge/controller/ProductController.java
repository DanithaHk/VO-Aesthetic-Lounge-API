package lk.ijse.voaestheticlounge.controller;

import jakarta.validation.Valid;
import lk.ijse.voaestheticlounge.dto.ProductDTO;
import lk.ijse.voaestheticlounge.dto.ResponseDTO;
import lk.ijse.voaestheticlounge.service.ProductService;
import lk.ijse.voaestheticlounge.service.impl.ProductServiceImpl;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    public final ProductService productService;
    public final ProductServiceImpl productServiceImpl;

    public ProductController(ProductService productService, ProductServiceImpl productServiceImpl) {
        this.productService = productService;
        this.productServiceImpl = productServiceImpl;
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO){
        productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Added SuccessFully!",null));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Long id,@RequestBody @Valid ProductDTO productDTO){
        productService.updateProduct(id,productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Updated SuccessFully!",null));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Product Deleted SuccessFully!",null));
    }
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Success",productServiceImpl.getAllProducts()));
    }
}
