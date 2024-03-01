package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sf.travel.entities.Category;
import sf.travel.entities.Product;
import sf.travel.entities.UserInfo;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.helper.specifications.ProductSpec;
import sf.travel.repositories.CategoryRepository;
import sf.travel.repositories.ProductRepository;
import sf.travel.repositories.UserInfoRepository;
import sf.travel.rests.types.*;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired private final CategoryRepository categoryRepository;
    @Autowired private final UserInfoRepository userInfoRepository;
    @Autowired private final ProductSpec productSpec;
    @Autowired private final ProductRepository productRepository;

    public Product create(CreateProductReq input) {
        Optional<Category> cate = categoryRepository.findById(input.getCategoryId());
        Optional<UserInfo> user = userInfoRepository.findById(input.getUserId());

        if(cate.isEmpty()) {
            throw new ConflictError(ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(user.isEmpty()) {
            throw new ConflictError(ErrorCode.USER_NOT_FOUND);
        }
        Product product = new Product();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setImage(input.getImage());
        product.setPrice(input.getPrice());
        product.setCreatedBy(user.get());
        product.setCategory(cate.get());

        return productRepository.save(product);
    }

    public Page<Product> findAll(ProductFilter filter){
        Specification<Product> spec = null;
        if (filter.getName() != null) {
            spec = productSpec.createSpecification("name", filter.getName());
        }

        if (filter.getPriceMin() != -1 && filter.getPriceMax() != -1) {
            spec = productSpec.findByNumberRange("price", filter.getPriceMin(), filter.getPriceMax());
        }

        if (filter.getSearchText() != null) {
            spec = productSpec.createLikeSpecification("description", filter.getSearchText())
                    .or(productSpec.createLikeSpecification("name", filter.getSearchText()));
        }

        System.out.println("Conditions: " + spec);

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("id").descending());

        Page<Product> res = productRepository.findAll(spec, pageable);
        return res;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product partialUpdate(Long id, UpdateProductReq input){
        Optional<Product> newProduct = productRepository.findById(id);

        if (newProduct.isPresent()){
            Product newUpdate = newProduct.get();
            if (input.getName() != null){
                newUpdate.setName(input.getName());
            }
            if (input.getDescription() != null){
                newUpdate.setDescription(input.getDescription());
            }
            if (input.getPrice() != -1){
                newUpdate.setPrice(input.getPrice());
            }
            if (input.getImage() != null){
                newUpdate.setImage(input.getImage());
            }
            return productRepository.save(newUpdate);
        } else {
            throw new ConflictError(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
