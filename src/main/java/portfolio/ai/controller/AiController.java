package portfolio.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import portfolio.ai.domain.AiAnalysis;
import portfolio.ai.service.AiService;
import portfolio.vital.domain.VitalRecord;
import portfolio.vital.repository.VitalRepository;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;
    private final VitalRepository vitalRepository;

    // 바이탈 수치 AI 분석
    @GetMapping("/analyze/{vitalId}")
    public ResponseEntity<AiAnalysis> analyze(Authentication authentication,
                                              @PathVariable Long vitalId) {
        VitalRecord record = vitalRepository.findById(vitalId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 측정값입니다."));

        if (!record.getMember().getEmail().equals(authentication.getName())) {
            throw new IllegalArgumentException("분석 권한이 없습니다.");
        }

        AiAnalysis analysis = aiService.analyze(record);
        return ResponseEntity.ok(analysis);
    }
}