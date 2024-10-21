package vn.gs.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.gs.order.entity.ExampleEntity;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {
    @Query(value = "classpath:test.sql", nativeQuery = true)
    ExampleEntity getData(@Param("id") Long id);
}
