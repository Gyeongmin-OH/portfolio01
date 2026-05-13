package portfolio.vital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import portfolio.vital.domain.VitalRequest;
import portfolio.vital.domain.VitalResponse;
import portfolio.vital.service.VitalService;
import portfolio.vital.domain.VitalStatisticsResponse;

import java.util.List;

@RestController
@RequestMapping("/api/vitals")
@RequiredArgsConstructor
public class VitalController {

    private final VitalService vitalService;

    // 바이탈 입력
    @PostMapping
    public ResponseEntity<VitalResponse> save(Authentication authentication,
                                              @RequestBody VitalRequest request) {
        String email = authentication.getName();
        VitalResponse response = vitalService.save(email, request);
        return ResponseEntity.ok(response);
    }

    // 측정 이력 조회
    @GetMapping
    public ResponseEntity<List<VitalResponse>> findAll(Authentication authentication) {
        String email = authentication.getName();
        List<VitalResponse> responses = vitalService.findAll(email);
        return ResponseEntity.ok(responses);
    }

    // 측정값 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(Authentication authentication,
                                         @PathVariable Long id) {
        String email = authentication.getName();
        vitalService.delete(id, email);
        return ResponseEntity.ok("측정값이 삭제되었습니다.");
    }
    // 통계 조회
    @GetMapping("/statistics")
    public ResponseEntity<VitalStatisticsResponse> getStatistics(Authentication authentication) {
        String email = authentication.getName();
        VitalStatisticsResponse response = vitalService.getStatistics(email);
        return ResponseEntity.ok(response);
    }
}