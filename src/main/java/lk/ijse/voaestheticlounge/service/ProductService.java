package lk.ijse.voaestheticlounge.service;

import lk.ijse.voaestheticlounge.dto.ProductDTO;

import java.util.List;

public interface ProductService {
     void saveProduct(ProductDTO productDTO);
     void deleteProduct(Long id);
     void updateProduct(Long id, ProductDTO productDTO);
     List<ProductDTO> getAllProducts();
}
