package shotny.ToDoListapi.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shotny.ToDoListapi.todos.dto.TodoRequestDto;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

//@RequestMapping
@RequiredArgsConstructor
@Controller
public class TodoController {
    private final TodoService todoService;

    // 리스트 등록
    @PostMapping("/todos")
    public ResponseEntity<TodoResponseDto> todoSave(@RequestBody TodoRequestDto requestDto) {
        System.out.println(requestDto.getContent());
        return ok(todoService.saveList(requestDto));
    }

    // 리스트 내용 수정
    @PatchMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> todoUpdate (@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return ok(todoService.updateTodo(id, requestDto));
    }

    // 리스트 COMPLETE/UNCOMPLETE 수정
    @PostMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> completedUpdate(@PathVariable Long id) {
        return ok(todoService.updateCompleted(id));
    }

    // 리스트 전체 조회
    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> findAll() {
        return ok(todoService.findAll());
    }

    // UNCOMPLETE 리스트 조회 -> 기본
    @GetMapping("/todos/uncompleted")
    public ResponseEntity<List<TodoResponseDto>> findUncompleted() {
        return ok(todoService.findUncompleted());
    }

    // COMPLETE 리스트 조회
    @GetMapping("/todos/completed")
    public ResponseEntity<List<TodoResponseDto>> findCompleted() {
        return ok(todoService.findCompleted());
    }

    // 리스트 삭제
    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        todoService.deleteList(id);
        return new ResponseEntity("게시글 삭제 완료", HttpStatus.OK);
    }
}
