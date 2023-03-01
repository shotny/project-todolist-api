package shotny.ToDoListapi.bucket;

import lombok.RequiredArgsConstructor;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.bucket.dto.BucketResponseDto;

@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;

    // 버킷 생성
    public BucketResponseDto saveBucket(BucketRequestDto dto) {
        Bucket savedBucket = bucketRepository.save(dto.toEntity());
        return new BucketResponseDto(savedBucket);
    }

    // 버킷 이름 수정
    public BucketResponseDto updateBucketName(Long id, BucketRequestDto dto) {
        Bucket foundBucket = findById(id);
        foundBucket.updateName(dto.getBucketName());

        return new BucketResponseDto(bucketRepository.save(foundBucket));
    }

    // 버킷 삭제

    // 버킷 조회
    public Bucket findById(Long id) {
        return bucketRepository.findById(id).get();
    }
}
