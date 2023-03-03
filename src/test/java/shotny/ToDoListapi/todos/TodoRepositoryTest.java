package shotny.ToDoListapi.todos;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shotny.ToDoListapi.bucket.Bucket;
import shotny.ToDoListapi.bucket.BucketRepository;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.todos.dto.TodoRequestDto;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoRepositoryTest {
    @Autowired TodoRepository todoRepository;
    @Autowired BucketRepository bucketRepository;

    @After
    public void cleanup() {
        todoRepository.deleteAll();
    }

    String list1 = "투두 리스트 1";
    String list2 = "투두 리스트 2";
    String list3 = "투두 리스트 3";

    // 리스트 등록
    @Test
    public void 리스트등록() {
        //given
        Todo saved = todoRepository.save(Todo.builder().content(list1).build());

        //when
        List<Todo> todoList = todoRepository.findAll();
        Todo find = todoRepository.findById(saved.getId()).get();

        //then
        Assertions.assertThat(todoList.size()).isEqualTo(1);
        Assertions.assertThat(find.getContent()).isEqualTo("투두 리스트 1");
        org.junit.jupiter.api.Assertions.assertEquals("투두 리스트 1", todoList.get(0).getContent());
    }

    // 리스트 내용 수정
    @Test
    public void 리스트수정() {
        //given
        Long id = todoRepository.save(Todo.builder().content(list1).build()).getId();
        Todo findTodo = todoRepository.findById(id).get();
        findTodo.updateTodo("리스트 수정");
        todoRepository.save(findTodo);

        // 더티체킹 테스트 코드 추가하기

        //when
        Todo updatedTodo = todoRepository.findById(id).get();

        //then
        org.junit.jupiter.api.Assertions.assertEquals("리스트 수정", updatedTodo.getContent());

    }

    // 리스트 전체 조회
    @Test
    public void 리스트전체조회() {
        //given
        todoRepository.save(Todo.builder().content(list1).build());
        todoRepository.save(Todo.builder().content(list2).build());
        todoRepository.save(Todo.builder().content(list3).build());

        //when
        List<Todo> all = todoRepository.findAll();

        //then
        Assertions.assertThat(all.size()).isEqualTo(3);
    }

    // completed/uncompleted 로 리스트 조회
    @Test
    public void completed조회() {
        //given
        todoRepository.save(Todo.builder().content(list1).build());
        todoRepository.save(Todo.builder().content(list2).build());
        todoRepository.save(Todo.builder().content(list3).build()).getId();

        //when
        List<Todo> uncompleted = todoRepository.findByCompleted(false);
        List<Todo> completed = todoRepository.findByCompleted(true);

        //then
        Assertions.assertThat(uncompleted.size()).isEqualTo(3);
        Assertions.assertThat(completed.size()).isEqualTo(0);
    }

    // 리스트 삭제
    @Test
    public void 리스트삭제() {
        //given
        Long id = todoRepository.save(Todo.builder().content(list1).build()).getId();
        todoRepository.save(Todo.builder().content(list2).build());

        //when
        todoRepository.deleteById(id);

        //then
        Assertions.assertThat(todoRepository.findAll().size()).isEqualTo(1);
    }


    // 버킷에 포함된 리스트 전체 조회
    @Test
    public void 리스트조회() {
        // given
        BucketRequestDto bucketRequest = new BucketRequestDto("new Bucket1");
        Bucket savedBucket = bucketRepository.save(bucketRequest.toEntity());

        Todo save1 = todoRepository.save(new TodoRequestDto("add 1st list").toEntity());
        save1.saveBucket(savedBucket);

        Todo save2  = todoRepository.save(new TodoRequestDto("add 2nd list").toEntity());
        save2.saveBucket(savedBucket);

        todoRepository.save(save1);
        todoRepository.save(save2);

        //when
        List<Todo> todoList = todoRepository.findByBucketId(savedBucket.getId());

        //then
        Assertions.assertThat(todoList.size()).isEqualTo(2);

    }

    // UNCOMPLETED 리스트 조회
    @Test
    public void uncompleted리스트조회() {
        // given
        BucketRequestDto bucketRequest = new BucketRequestDto("new Bucket1");
        Bucket savedBucket = bucketRepository.save(bucketRequest.toEntity());

        Todo save1 = todoRepository.save(new TodoRequestDto("add 1st list").toEntity());
        save1.saveBucket(savedBucket);
        save1.updateCompleted();

        Todo save2  = todoRepository.save(new TodoRequestDto("add 2nd list").toEntity());
        save2.saveBucket(savedBucket);

        Todo save3  = todoRepository.save(new TodoRequestDto("add 3rd list").toEntity());
        save3.saveBucket(savedBucket);

        todoRepository.save(save1);
        todoRepository.save(save2);
        todoRepository.save(save3);

        //when
        List<Todo> todoList = todoRepository.findByBucketId(savedBucket.getId());
        List<Todo> uncompletedList = todoRepository.findByCompletedAndBucketId(false, savedBucket.getId());
        List<Todo> completedList = todoRepository.findByCompletedAndBucketId(true, savedBucket.getId());

        //then
        Assertions.assertThat(todoList.size()).isEqualTo(3);
        Assertions.assertThat(uncompletedList.size()).isEqualTo(2);
        Assertions.assertThat(completedList.size()).isEqualTo(1);
        Assertions.assertThat(completedList.get(0).getContent()).isEqualTo("add 1st list");
    }
}