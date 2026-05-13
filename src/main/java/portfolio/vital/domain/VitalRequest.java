package portfolio.vital.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VitalRequest {

    // 혈압
    private int systolicBp;      // 수축기 혈압
    private int diastolicBp;     // 이완기 혈압

    // 심박수
    private int heartRate;

    // 체온
    private double bodyTemperature;

    // 산소포화도
    private int spO2;
}