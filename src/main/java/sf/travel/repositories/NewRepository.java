package sf.travel.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sf.travel.entities.New;

public interface NewRepository extends JpaRepository<New, Long>, JpaSpecificationExecutor<New> {
}
