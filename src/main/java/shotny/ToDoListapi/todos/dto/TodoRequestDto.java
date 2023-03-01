package shotny.ToDoListapi.todos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shotny.ToDoListapi.todos.Todo;

@Getter
@NoArgsConstructor
public class TodoRequestDto {
    private String content;

    // 테스트용
    @Builder
    public TodoRequestDto(String content) {
        this.content = content;
    }

    public Todo toEntity() {
        return Todo.builder()
                .content(content)
                .build();
    }
}
