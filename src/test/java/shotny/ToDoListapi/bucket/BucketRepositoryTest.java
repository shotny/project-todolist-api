package shotny.ToDoListapi.bucket;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.todos.TodoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketRepositoryTest {

    @Autowired BucketRepository bucketRepository;
    @Autowired TodoRepository todoRepository;

    @After
    public void cleanAll() {
        bucketRepository.deleteAll();
    }

    // 버킷 생성
    @Test
    public void saveBucket() {
        //given
        BucketRequestDto requestDto = new BucketRequestDto("new Bucket1");
        Bucket bucket = requestDto.toEntity();

        //when
        Bucket savedBucket = bucketRepository.save(bucket);

        //then
        Assertions.assertThat(savedBucket.getName()).isEqualTo(requestDto.getName());
    }

    // 버킷 이름
    @Test
    public void updateName() {
        //given
        BucketRequestDto requestDto = new BucketRequestDto("new Bucket1");
        Long savedId = bucketRepository.save(requestDto.toEntity()).getId();

        //when
        Bucket saved = bucketRepository.findById(savedId).get();
        saved.updateName("update bucket name");
        bucketRepository.save(saved);

        //then
        Assertions.assertThat(bucketRepository.findById(savedId).get().getName()).isEqualTo("update bucket name");
    }

    // 버킷 조회
    @Test
    public void findBucket() {
        //given
        BucketRequestDto requestDto = new BucketRequestDto("new Bucket1");
        Long savedId = bucketRepository.save(requestDto.toEntity()).getId();

        //when
        Bucket saved = bucketRepository.findById(savedId).get();

        //then
        Assertions.assertThat(saved.getId()).isEqualTo(1);
        Assertions.assertThat(saved.getName()).isEqualTo("new Bucket1");
    }

}