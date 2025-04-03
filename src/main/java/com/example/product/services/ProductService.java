package com.example.product.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.dto.ProductDTO;
import com.example.product.models.Product;
import com.example.product.repo.ProductRepo;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO addProduct(ProductDTO productDTO){
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public List<ProductDTO> getProducts() {

        List<Product>productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        modelMapper.map(productDTO, Product.class);
        return productDTO;
    }

    public String deleteProduct(Integer productId){
        productRepo.deleteById(productId);
        return "Product deleted";
    }

    public ProductDTO getProductById(Integer productId) {
        Product product = productRepo.getProductById(productId);
        return modelMapper.map(product, ProductDTO.class);
    }


}
