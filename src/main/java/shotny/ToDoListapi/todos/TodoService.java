package shotny.ToDoListapi.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shotny.ToDoListapi.bucket.Bucket;
import shotny.ToDoListapi.bucket.BucketRepository;
import shotny.ToDoListapi.todos.dto.TodoRequestDto;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final BucketRepository bucketRepository;

    @Transactional
    public TodoResponseDto saveList(Long bucketId, TodoRequestDto requestDto) {
        Todo entity = todoRepository.save(requestDto.toEntity());
        entity.saveBucket(bucketRepository.findById(bucketId).get());

        Bucket bucket = bucketRepository.findById(bucketId).get();
        bucket.countUp();
        bucketRepository.save(bucket);

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
    public TodoResponseDto updateCompleted(Long bucketId, Long id) {
        Bucket bucket = bucketRepository.findById(bucketId).get();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 목록이 없습니다 id= " + id));
        Boolean isCompleted = todo.updateCompleted();

        if (isCompleted) {
            bucket.completedUp();
        } else {
            bucket.completedDown();
        }
        bucketRepository.save(bucket);

        return new TodoResponseDto(todoRepository.save(todo));
    }

    // 리스트 삭제
    @Transactional
    public void deleteList(Long bucketId, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 목록이 없습니다 id= " + id));
        todoRepository.delete(todo);

        Bucket bucket = bucketRepository.findById(bucketId).get();
        bucket.countDown();
        bucketRepository.save(bucket);
    }

    //  ---- 버킷 연동 전 ----
    // 리스트 등록
    @Transactional
    public TodoResponseDto saveList(TodoRequestDto requestDto) {
        Todo entity = todoRepository.save(requestDto.toEntity());
        return new TodoResponseDto(entity);
    }
}
