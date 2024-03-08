package develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.repositories;

import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT * FROM Flight WHERE status = :p1 OR status = :p2 ", nativeQuery = true)
    List<Flight> findByStatus(@Param("p1") String p1, @Param("p2") String p2);
}
