package shotny.ToDoListapi.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//    @Query("SELECT t FROM Todo t WHERE t.completed = :complete ORDER BY t.id DESC)
//    @Query("select t from Todo t where t.completed like :completed order by t.id DESC")
//    List<Todo> findByCompleted(@Param("completed")Boolean completed);

    public List<Todo> findByCompleted(boolean completed);
}
