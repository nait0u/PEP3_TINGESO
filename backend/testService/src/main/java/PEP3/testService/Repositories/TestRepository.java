package PEP3.testService.Repositories;

import PEP3.testService.Entities.TestEntity;
import com.sun.research.ws.wadl.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;


@Repository
public interface TestRepository extends JpaRepository<TestEntity, String> {

    @Query("select e from TestEntity e")
    ArrayList<TestEntity> getAll();

    @Query(value = "select * from test e where e.dificultad = :dif ORDER BY RAND() LIMIT 4", nativeQuery = true)
    ArrayList<TestEntity> findByDif(@Param("dif")Integer dif);

    @Query("select e from TestEntity e where e.ID_TEST = :id")
    TestEntity findById(@Param("id")Integer id);

}
