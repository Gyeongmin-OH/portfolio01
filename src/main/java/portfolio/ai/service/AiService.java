package portfolio.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portfolio.ai.domain.AiAnalysis;
import portfolio.vital.domain.VitalRecord;
import portfolio.vital.domain.VitalStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {

    @Value("${ai.mock}")
    private boolean mockMode;

    // 바이탈 수치 분석
    public AiAnalysis analyze(VitalRecord record) {
        if (mockMode) {
            return getMockAnalysis(record.getStatus());
        }
        // 실제 API 호출 (나중에 구현)
        return getMockAnalysis(record.getStatus());
    }

    // Mock 데이터 반환
    private AiAnalysis getMockAnalysis(VitalStatus status) {
        if (status == VitalStatus.DANGER) {
            return new AiAnalysis(
                    List.of("심한 두통", "어지러움", "호흡 곤란", "가슴 통증"),
                    List.of("고혈압 위기 가능성", "심장 부정맥 가능성", "호흡기 감염 가능성"),
                    List.of("즉시 의료기관 방문을 권장합니다.", "혼자 있지 마시고 보호자와 함께 계세요.")
            );
        }
        if (status == VitalStatus.CAUTION) {
            return new AiAnalysis(
                    List.of("가벼운 두통", "피로감", "약한 어지러움"),
                    List.of("스트레스나 과로 가능성", "수분 부족 가능성", "수면 부족 가능성"),
                    List.of("충분한 휴식을 취하세요.", "수분을 충분히 섭취하세요.", "증상이 지속되면 병원을 방문하세요.")
            );
        }
        return new AiAnalysis(
                List.of("특이 증상 없음"),
                List.of("전반적으로 건강한 상태입니다."),
                List.of("현재 건강 상태를 유지하세요.", "규칙적인 운동과 균형 잡힌 식단을 유지하세요.")
        );
    }
}