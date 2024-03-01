package sf.travel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sf.travel.entities.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {
}
