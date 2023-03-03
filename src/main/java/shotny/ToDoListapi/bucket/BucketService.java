package shotny.ToDoListapi.bucket;

import lombok.RequiredArgsConstructor;
import shotny.ToDoListapi.bucket.dto.BucketCreateResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketListResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.bucket.dto.BucketResponseDto;
import shotny.ToDoListapi.todos.Todo;
import shotny.ToDoListapi.todos.TodoRepository;

import java.util.List;

@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;
    private final TodoRepository todoRepository;

    // 버킷 생성
    public BucketCreateResponseDto saveBucket(BucketRequestDto dto) {
        Bucket savedBucket = bucketRepository.save(dto.toEntity());
        return new BucketCreateResponseDto(savedBucket);
    }

    // 버킷 이름 수정
    public BucketResponseDto updateBucketName(Long id, BucketRequestDto dto) {
        Bucket foundBucket = findById(id);
        foundBucket.updateName(dto.getBucketName());

        return new BucketResponseDto(bucketRepository.save(foundBucket));
    }

    // 버킷 조회
    public Bucket findById(Long id) {
        return bucketRepository.findById(id).get();
    }

    // 버킷에 포함된 리스트 전체 조회
    public BucketListResponseDto findListByBucketId(Long id) {
        return new BucketListResponseDto(bucketRepository.findById(id).get(), todoRepository.findByBucketId(id));
    }

    // UNCOMPLETED 리스트 조회
    public BucketListResponseDto findUncompletedList(Long bucketId) {
        Bucket bucket = bucketRepository.findById(bucketId).get();
        List<Todo> uncompleted = todoRepository.findByCompletedAndBucketId(false, bucketId);

        return new BucketListResponseDto(bucket, uncompleted);
    }

    // 버킷 삭제
    public void deleteBucket(Long bucketId) {
        Bucket bucket = bucketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalArgumentException("해당 버킷이 없습니다 id= " + bucketId));
        bucketRepository.deleteById(bucketId);
    }
}
