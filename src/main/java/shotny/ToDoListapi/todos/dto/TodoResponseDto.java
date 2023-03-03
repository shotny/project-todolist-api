package shotny.ToDoListapi.todos.dto;

import lombok.*;
import shotny.ToDoListapi.todos.Todo;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoResponseDto {
    private Long id;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;

    @Builder
    public TodoResponseDto(Todo entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.completed = entity.isCompleted();
        this.createdAt = entity.getModifiedAt();
    }
}
