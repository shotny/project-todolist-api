package shotny.ToDoListapi.todos;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shotny.ToDoListapi.todos.dto.TodoRequestDto;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

    @Autowired TodoService todoService;
    @Autowired TodoRepository todoRepository;

    // ㄹㅣ스트 등록
    @Test
    public void 리스트틍록() {
        //given
        TodoRequestDto requestDto = createRequest();

        //when
        TodoResponseDto responseDto = todoService.saveList(requestDto);
        Todo savedEntity = todoRepository.findById(responseDto.getId()).get();

        //then
        Assertions.assertThat(savedEntity.getContent()).isEqualTo("create new list");
        Assertions.assertThat(responseDto.getContent()).isEqualTo("create new list");
        Assertions.assertThat(savedEntity.isCompleted()).isFalse();
    }

    /*
    // 리스트 completed 수정
    @Test
    public void completed수정() {
        //given
        Todo hi = todoRepository.save(Todo.builder().content("hi").build());
        Todo hello = todoRepository.save(Todo.builder().content("hello").build());
        todoService.updateCompleted(hello.getId());

        //when
        List<Todo> completedList = todoRepository.findByCompleted(true);

        //then
        Assertions.assertThat(todoRepository.findById(hello.getId()).get().isCompleted()).isTrue();
        Assertions.assertThat(completedList.size()).isEqualTo(1);
    }*/

    public TodoRequestDto createRequest() {
        return TodoRequestDto.builder().content("create new list").build();
    }
}