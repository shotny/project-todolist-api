package shotny.ToDoListapi.bucket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;
import shotny.ToDoListapi.todos.Todo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BucketResponseDto {

    private Long id;
    private String bucketName;
    private int listCount;
    private int completedCount;
    private LocalDateTime createdAt;
    private List<Todo> todoList;

    public BucketResponseDto(Bucket entity, List<Todo> todoList) {
        this.id = entity.getId();
        this.bucketName = entity.getBucketName();
        this.listCount = entity.getListCount();
        this.completedCount = entity.getCompletedCount();
        this.createdAt = entity.getModifiedAt();
        this.todoList = todoList;
    }

    public BucketResponseDto(Bucket entity) {
        this.id = entity.getId();
        this.bucketName = entity.getBucketName();
        this.listCount = entity.getListCount();
        this.completedCount = entity.getCompletedCount();
        this.createdAt = entity.getModifiedAt();
    }
}
