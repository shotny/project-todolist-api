package shotny.ToDoListapi.bucket;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shotny.ToDoListapi.bucket.dto.BucketCreateResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketListResponseDto;
import shotny.ToDoListapi.bucket.dto.BucketRequestDto;
import shotny.ToDoListapi.bucket.dto.BucketResponseDto;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("buckets")
@RequiredArgsConstructor
@Controller
public class BucketController {
    private final BucketService bucketService;

    // 버킷 생성
    @PostMapping
    public ResponseEntity<BucketCreateResponseDto> saveBucket(@RequestBody BucketRequestDto dto) {
        return ok(bucketService.saveBucket(dto));
    }

    // 버킷 이름 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BucketResponseDto> updateBucketName(@PathVariable Long id, @RequestBody BucketRequestDto dto) {
        return ok(bucketService.updateBucketName(id, dto));
    }

    // 버킷 전체 조회
    @GetMapping
    public ResponseEntity<List<BucketResponseDto>> findBuckets() {
        return ok(bucketService.findAllBuckets());
    }

    // 버킷의 투두리스트 조회
    @GetMapping("/{id}")
    public ResponseEntity<BucketListResponseDto> findLists(@PathVariable Long id) {
        return ok(bucketService.findListByBucketId(id));
    }

    // 버킷의 uncompleted 리스트만 조회
    @GetMapping("/{id}/uncompleted")
    public ResponseEntity<BucketListResponseDto> findUncompletedList(@PathVariable Long id) {
        return ok(bucketService.findUncompletedList(id));
    }

    // 버킷 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBucket(@PathVariable Long id) {
        bucketService.deleteBucket(id);
        return new ResponseEntity<>("버킷 삭제 완료", HttpStatus.OK);
    }
}
