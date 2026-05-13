package portfolio.vital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VitalStatisticsResponse {

    // 혈압 평균
    private double avgSystolicBp;
    private double avgDiastolicBp;

    // 심박수 평균
    private double avgHeartRate;

    // 체온 평균
    private double avgBodyTemperature;

    // 산소포화도 평균
    private double avgSpO2;

    // 상태별 횟수
    private long normalCount;
    private long cautionCount;
    private long dangerCount;

    // 총 측정 횟수
    private long totalCount;
}