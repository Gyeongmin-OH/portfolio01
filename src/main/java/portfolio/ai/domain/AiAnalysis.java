package portfolio.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AiAnalysis {

    // 나타날 수 있는 증상
    private List<String> symptoms;

    // 원인 추측
    private List<String> causes;

    // 권고사항
    private List<String> recommendations;
}