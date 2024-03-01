package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.travel.entities.User;
import sf.travel.entities.UserInfo;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.repositories.UserInfoRepository;
import sf.travel.repositories.UserRepository;
import sf.travel.rests.types.CreateRegisterReq;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    public void registerUser(CreateRegisterReq request) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ConflictError(ErrorCode.USERNAME_AVAILABLE);
        }

        // Tạo một User mới
        User user = new User();
        user.setUserName(request.getUsername());
        user.setPassword(request.getPassword());

        // Lưu thông tin User
        userRepository.save(user);

        // Tạo một UserInfo mới liên kết với User
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setName(request.getName());
        userInfo.setEmail(request.getEmail());
        userInfo.setType(request.getType());

        // Lưu thông tin UserInfo
        userInfoRepository.save(userInfo);
    }

    @Transactional(readOnly = true)
    public String login(String username, String password) {
        // Tìm kiếm người dùng theo username và password
        User user = userRepository.findByUsernameAndPassword(username, password);

        // Nếu người dùng tồn tại, đăng nhập thành công
        if (user != null) {
            return "dang nhap thanh cong";
        } else {
            return "sai tên tài khoản hoặc mật khẩu";
            }
    }
}
