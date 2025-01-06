package net.enjoy.springboot.registrationlogin.repository;
import net.enjoy.springboot.registrationlogin.entity.ImportDetail;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail, Long> {

} 
