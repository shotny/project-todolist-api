package shotny.ToDoListapi.todos.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.todos.Todo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoResponseDto {
    private Long id;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;

    public TodoResponseDto(Todo entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.completed = entity.isCompleted();
        this.createdAt = entity.getModifiedAt();
    }
}
