package sf.travel.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sf.travel.entities.Highlight;

public interface HighlightRepository extends JpaRepository<Highlight, Long>, JpaSpecificationExecutor<Highlight> {
}
