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
import sf.travel.entities.New;
import sf.travel.entities.UserInfo;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.helper.specifications.CategorySpec;
import sf.travel.helper.specifications.NewSpec;
import sf.travel.repositories.CategoryRepository;
import sf.travel.repositories.NewRepository;
import sf.travel.repositories.UserInfoRepository;
import sf.travel.rests.types.*;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    @Autowired private final CategoryRepository categoryRepository;
    @Autowired private final UserInfoRepository userInfoRepository;
    @Autowired private final CategorySpec categorySpec;

    public Category create(CreateCategoryReq input) {
        Optional<Category> cate = categoryRepository.findByName(input.getName());
        Optional<UserInfo> user = userInfoRepository.findById(input.getUserId());

        if(cate.isPresent()) {
            throw new ConflictError(ErrorCode.NAME_ALREADY_EXISTS);
        }
        if(user.isEmpty()) {
            throw new ConflictError(ErrorCode.USER_NOT_FOUND);
        }
        Category newPage = new Category();
        newPage.setName(input.getName());
        newPage.setDescription(input.getDescription());
        return categoryRepository.save(newPage);
    }

    public Page<Category> findAll(NewFilter filter){
        Specification<Category> spec = null;
        if (filter.getName() != null) {
            spec = categorySpec.createSpecification("name", filter.getName());
        }

        if (filter.getSearchText() != null) {
            spec = categorySpec.createLikeSpecification("description", filter.getSearchText())
                    .or(categorySpec.createLikeSpecification("name", filter.getSearchText()));
        }

        System.out.println("Conditions: " + spec);

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("id").descending());

        Page<Category> res = categoryRepository.findAll(spec, pageable);
        return res;
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Category partialUpdate(Long id, UpdateCategoryReq input){
        Optional<Category> newCategory = categoryRepository.findById(id);
        Optional<UserInfo> user = userInfoRepository.findById(input.getUserId());
        if(user.isEmpty()) {
            throw new ConflictError(ErrorCode.USER_NOT_FOUND);
        }
        if (newCategory.isPresent()){
            Category newUpdate = newCategory.get();
            if (input.getName() != null){
                newUpdate.setName(input.getName());
            }
            if (input.getDescription() != null){
                newUpdate.setDescription(input.getDescription());
            }
            newUpdate.setUpdatedBy(user.get());
            return categoryRepository.save(newUpdate);
        } else {
            throw new ConflictError(ErrorCode.CATEGORY_NOT_FOUND);
        }
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
