package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import net.enjoy.springboot.registrationlogin.repository.ProductsRepository;
import service.collector.exception.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
@Service
public class ProductServiceImpl implements ProductService {

    ProductsRepository productsRepository;
    ProductsDetailRespository productsDetailRespository;

    @Autowired  
    ProductDetailService productDetailService;


    public ProductServiceImpl(ProductsRepository productsRepository, ProductsDetailRespository productsDetailRespository) {
        this.productsRepository = productsRepository;
        this.productsDetailRespository = productsDetailRespository;
    }

    @Override
    public Page<ProductDto> findAllProduct(Pageable pageable) {
        Page<Product> products = productsRepository.findAll(pageable);
        return products.map(this::convertEntityToDto);
    }

    @Override
    public List<ProductDto> findAllProduct() {
        // convert
        List<Product> products = productsRepository.findProductz();
        return products.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    // findProduct base on size, colo
    @Override
    public Page<ProductDto> searchProduct(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name) {
        Page<Product> products = productsRepository.findbyFilter(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        return products.map(this::convertEntityToDto);
    }

    @Override
    public Page<ProductDto> searchProductPriceAsc(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name) {
        Page<Product> products = productsRepository.findbyFilterPriceAsc(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        return products.map(this::convertEntityToDto);
    }

    @Override
    public Page<ProductDto> searchProductPriceDesc(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name) {
        Page<Product> products = productsRepository.findbyFilterPriceDesc(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        return products.map(this::convertEntityToDto);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productsRepository.findById(id);
        return convertEntityToDto(product);
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public List<ProductDetailDto> findDetailById(Long idProduct) {
        List<ProductDetail> productDetails = productsDetailRespository.findByProductId(idProduct);
        return productDetails.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductByIdDetail(Long idProduct) {
        Product products = productsRepository.findByProductDetailsId(idProduct);
        return convertEntityToDto(products);
    }



    private ProductDetail convertEntityProductDetailToDto(ProductDetail productDetail) {
        return productDetail;
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImg(product.getImg());
        productDto.setStatus(product.getStatus());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
    private ProductDetailDto convertEntityToDto(ProductDetail productdetail) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productdetail.getId());
        productDetailDto.setColor(productdetail.getColor().getColorName());
        productDetailDto.setSize(productdetail.getSize().getSizeName());
        productDetailDto.setPrice(productdetail.getPrice());
        productDetailDto.setQuantity(productdetail.getQuantity());
        return productDetailDto;
    }

    @Override
    public List<ProductDto> findAllProductWithPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        // chi lay san pham có status = 1
        Page<Product> products = productsRepository.findAllByStatus(pageable);

        return products.map(this::convertEntityToDto).stream().collect(Collectors.toList());
    }

    @Override
    public Product addProductAPI(Product product) {
        System.out.println("___ Toi da chay toi buoc cuoi cung " + product.getCategory().getId());
        System.out.println("___ Toi da chay toi buoc cuoi cung " + product.getName());

        return productsRepository.save(product);
    }

    @Override
    public Product updateProductAPI(Product product) {
        return productsRepository.save(product);
     
    }

    @Override
    public Product findProductById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public List<Map<String, Object>> getAllProductWithAllDetails() {
        List<ProductDto> products = findAllProduct();
        List<ProductDetailDto> productDetailDtos = productDetailService.getAllProductDetail();
 
        HashMap<Long, List<ProductDetailDto>> productDetailsMap = new HashMap<>();
        for (ProductDetailDto productDetailDto : productDetailDtos) {
            if (productDetailsMap.containsKey(productDetailDto.getId())) {
                productDetailsMap.get(productDetailDto.getId()).add(productDetailDto);
            } else {
                productDetailsMap.put(productDetailDto.getId(), List.of(productDetailDto));
            }
        }
        List<Map<String, Object>> result = new java.util.ArrayList<>();
        for (ProductDto product : products) {
            Map<String, Object> productWithDetails = new HashMap<>();
            productWithDetails.put("product", product);
            productWithDetails.put("details", productDetailsMap.get(product.getId()));
            result.add(productWithDetails);
        }
        return result;
    }

}
