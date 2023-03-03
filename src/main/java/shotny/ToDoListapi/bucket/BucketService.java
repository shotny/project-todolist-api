package shotny.ToDoListapi.bucket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shotny.ToDoListapi.bucket.dto.BucketCreateResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketListResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.bucket.dto.BucketResponseDto;
import shotny.ToDoListapi.todos.Todo;
import shotny.ToDoListapi.todos.TodoRepository;
import shotny.ToDoListapi.todos.dto.TodoResponseDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BucketService {

    private final BucketRepository bucketRepository;
    private final TodoRepository todoRepository;

    // 버킷 생성
    public BucketCreateResponseDto saveBucket(BucketRequestDto dto) {
        Bucket savedBucket = bucketRepository.save(dto.toEntity());

//        System.out.println("BucketService.saveBucket");
//        System.out.println("BucketRequestDto.getName = " + dto.getName());
//        System.out.println("savedBucket.getName = " + savedBucket.getName());

        return new BucketCreateResponseDto(savedBucket);
    }

    // 버킷 이름 수정
    public BucketResponseDto updateBucketName(Long id, BucketRequestDto dto) {
        Bucket foundBucket = findById(id);
        foundBucket.updateName(dto.getName());

        return new BucketResponseDto(bucketRepository.save(foundBucket));
    }

    //버킷 전체 조회
    public List<BucketResponseDto> findAllBuckets() {
        List<Bucket> all = bucketRepository.findAll();
        List<BucketResponseDto> bucketDtoList = new ArrayList<>();


        if (all.size() != 0) {
            for (Bucket bucket : all) {
                bucketDtoList.add(BucketResponseDto.builder().entity(bucket).build());
            }
        }

        return bucketDtoList;
    }

    // 버킷에 포함된 리스트 전체 조회
    public BucketListResponseDto findListByBucketId(Long bucketId) {
        Bucket bucket = bucketRepository.findById(bucketId).get();
        List<Todo> todoList = todoRepository.findByBucketId(bucketId);
        List<TodoResponseDto> todoDtoList = new ArrayList<>();

        if (todoList.size() != 0) {
            for (Todo todo : todoList) {
                todoDtoList.add(TodoResponseDto.builder().entity(todo).build());
            }
        }

        return new BucketListResponseDto(bucket, todoDtoList);
    }

    // UNCOMPLETED 리스트 조회
    public BucketListResponseDto findUncompletedList(Long bucketId) {
        Bucket bucket = bucketRepository.findById(bucketId).get();
        List<Todo> uncompletedList = todoRepository.findByCompletedAndBucketId(false, bucketId);
        List<TodoResponseDto> uncompletedDtoList = new ArrayList<>();

        if (uncompletedList.size() != 0) {
            for (Todo todo : uncompletedList) {
                uncompletedDtoList.add(TodoResponseDto.builder().entity(todo).build());
            }
        }

        return new BucketListResponseDto(bucket, uncompletedDtoList);
    }

    // 버킷 삭제
    public void deleteBucket(Long bucketId) {
        Bucket bucket = bucketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalArgumentException("해당 버킷이 없습니다 id= " + bucketId));
        bucketRepository.deleteById(bucketId);
    }

    // ---------- 버킷 조회 ----------
    public Bucket findById(Long id) {
        return bucketRepository.findById(id).get();
    }
}
