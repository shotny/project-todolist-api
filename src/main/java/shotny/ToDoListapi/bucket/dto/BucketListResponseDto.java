package shotny.ToDoListapi.bucket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.bucket.Bucket;
import shotny.ToDoListapi.todos.Todo;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BucketListResponseDto {

    private Long id;
    private String name;
    private int count;
    private int completedCount;
    private LocalDateTime createdAt;
    private List<TodoResponseDto> todoList;

    public BucketListResponseDto(Bucket entity, List<TodoResponseDto> todoList) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.count = entity.getCount();
        this.completedCount = entity.getCompletedCount();
        this.createdAt = entity.getModifiedAt();
        this.todoList = todoList;
    }
}
