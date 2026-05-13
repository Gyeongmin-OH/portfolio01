package portfolio.vital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VitalResponse {

    private Long id;

    // 혈압
    private int systolicBp;
    private int diastolicBp;

    // 심박수
    private int heartRate;

    // 체온
    private double bodyTemperature;

    // 산소포화도
    private int spO2;

    // 상태
    private String status;
    private String statusDescription;

    // 측정 시간
    private LocalDateTime measuredAt;

    public static VitalResponse from(VitalRecord record) {
        return new VitalResponse(
                record.getId(),
                record.getSystolicBp(),
                record.getDiastolicBp(),
                record.getHeartRate(),
                record.getBodyTemperature(),
                record.getSpO2(),
                record.getStatus().name(),
                record.getStatus().getDescription(),
                record.getMeasuredAt()
        );
    }
}