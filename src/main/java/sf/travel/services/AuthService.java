package sf.travel.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sf.travel.entities.User;
import sf.travel.entities.UserInfo;
import sf.travel.errors.ConflictError;
import sf.travel.errors.ErrorCode;
import sf.travel.repositories.UserInfoRepository;
import sf.travel.repositories.UserRepository;
import sf.travel.rests.types.CreateRegisterReq;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired private final UserRepository userRepository;
    @Autowired private final UserInfoRepository userInfoRepository;

    public void registerUser(CreateRegisterReq request) {
        // Kiểm tra xem username đã tồn tại chưa
        Optional<User> optionalUser = userRepository.findByUserName(request.getUserName());

        if (optionalUser.isPresent()) {
            throw new ConflictError(ErrorCode.USERNAME_AVAILABLE);
        }

        // Tạo một User mới
        User user = new User();
        user.setUserName(request.getUserName());
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
    public String login(String userName, String password) {
        // Tìm kiếm người dùng theo username
        Optional<User> optionalUser = userRepository.findByUserName(userName);

        // Kiểm tra xem người dùng có tồn tại không
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Kiểm tra mật khẩu
            if (user.getPassword().equals(password)) {
                // Nếu mật khẩu đúng, đăng nhập thành công
                return "success";
            }
        }

        // Nếu không tìm thấy người dùng hoặc mật khẩu không đúng, trả về thông báo lỗi
        return "wrong account name or password";
    }
}
