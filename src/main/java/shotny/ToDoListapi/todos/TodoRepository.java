package shotny.ToDoListapi.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//    @Query("SELECT t FROM Todo t WHERE t.completed = :complete ORDER BY t.id DESC)
//    List<Todo> findByCompleted(@Param("completed")Boolean completed);

//    @Query("select * from Todo where t.completed = completed and t.bucket_id = bucketid ORDER BY t.id DESC")
//    public List<Todo> findByCompleted(@Param("bucketid") Long bucketid, @Param("complete")boolean completed);

    public List<Todo> findByCompletedAndBucketId(boolean completed, Long bucketId);
    public List<Todo> findByCompleted(boolean completed);
    public List<Todo> findByBucketId(Long bucketId);


}
