package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sf.travel.entities.UserInfo;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.helper.specifications.UserInfoSpec;
import sf.travel.repositories.UserInfoRepository;
import sf.travel.rests.types.UpdateUserInfoReq;
import sf.travel.rests.types.UserInfoFilter;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {
    @Autowired
    private final UserInfoRepository userInfoRepository;
    @Autowired
    private final UserInfoSpec userInfoSpec;

    public Page<UserInfo> findAll(UserInfoFilter filter) {
        Specification<UserInfo> spec = null;
        if (filter.getName() != null) {
            spec = userInfoSpec.createSpecification("name", filter.getName());
        }

        if (filter.getEmail() != null) {
            spec = userInfoSpec.createLikeSpecification("email", filter.getEmail());
        }

        if (filter.getType() != null) {
            spec = userInfoSpec.createSpecification("type", filter.getType());
        }

        System.out.println("Conditions: " + spec);

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), Sort.by("id").descending());

        return userInfoRepository.findAll(spec, pageable);
    }

    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findById(id);
    }

    public UserInfo partialUpdate(Long id, UpdateUserInfoReq input) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(id);

        if (userInfoOptional.isEmpty()) {
            throw new ConflictError(ErrorCode.USER_NOT_FOUND);
        }

        UserInfo userInfo = userInfoOptional.get();

        if (input.getName() != null) {
            userInfo.setName(input.getName());
        }

        if (input.getEmail() != null) {
            userInfo.setEmail(input.getEmail());
        }

        if (input.getType() != null) {
            userInfo.setType(input.getType());
        }

        return userInfoRepository.save(userInfo);
    }
}
