package shotny.ToDoListapi.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shotny.ToDoListapi.todos.dto.TodoRequestDto;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // 리스트 등록
    @Transactional
    public TodoResponseDto saveList(TodoRequestDto requestDto) {
        Todo entity = todoRepository.save(requestDto.toEntity());
        return new TodoResponseDto(entity);
    }

    // 리스트 내용 수정
    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 목록이 없습니다 id= " + id));
        todo.updateTodo(requestDto.getContent());
        return new TodoResponseDto(todoRepository.save(todo));
    }

    // 리스트 COMPLETE/UNCOMPLETE 수정
    public TodoResponseDto updateCompleted(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 목록이 없습니다 id= " + id));
        todo.updateCompleted();
        return new TodoResponseDto(todoRepository.save(todo));
    }

    // 리스트 전체 조회
    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    // UNCOMPLETE 리스트 조회 -> 기본
    public List<TodoResponseDto> findUncompleted() {
        return todoRepository.findByCompleted(false).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    // COMPLETE 리스트 조회
    public List<TodoResponseDto> findCompleted() {
        return todoRepository.findByCompleted(true).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    // 리스트 삭제
    @Transactional
    public void deleteList(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 목록이 없습니다 id= " + id));
        todoRepository.delete(todo);
    }
}
