package lk.ijse.voaestheticlounge.service.impl;

import lk.ijse.voaestheticlounge.dto.ProductDTO;
import lk.ijse.voaestheticlounge.entity.Product;
import lk.ijse.voaestheticlounge.repo.ProductRepository;
import lk.ijse.voaestheticlounge.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void saveProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product exProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found with ID: " + id));
        exProduct.setName(productDTO.getName());
        exProduct.setDescription(productDTO.getDescription());
        exProduct.setPrice(productDTO.getPrice());
        exProduct.setStockQuantity(productDTO.getStockQuantity());
        exProduct.setImageUrl(productDTO.getImageUrl());
        productRepository.save(exProduct);

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return modelMapper.map(productRepository.findAll(),new TypeToken<List<ProductDTO>>() {}.getType());
    }
}
