package amazontesttaskhaniuk.repository;

import amazontesttaskhaniuk.model.ReportContainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportContainerRepository extends MongoRepository<ReportContainer, String> {

    @Query("{ 'salesAndTrafficByDate.date' : ?0 }")
    Optional<ReportContainer> findBySalesAndTrafficByDate_Date(String date);


}
