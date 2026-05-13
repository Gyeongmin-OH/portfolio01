package portfolio.vital.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.auth.domain.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "vital_record")
public class VitalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 혈압
    private int systolicBp;      // 수축기 혈압
    private int diastolicBp;     // 이완기 혈압

    // 심박수
    private int heartRate;

    // 체온
    private double bodyTemperature;

    // 산소포화도
    private int spO2;

    // 상태 (NORMAL, CAUTION, DANGER)
    @Enumerated(EnumType.STRING)
    private VitalStatus status;

    private LocalDateTime measuredAt;

    @Builder
    public VitalRecord(Member member, int systolicBp, int diastolicBp,
                       int heartRate, double bodyTemperature, int spO2,
                       VitalStatus status) {
        this.member = member;
        this.systolicBp = systolicBp;
        this.diastolicBp = diastolicBp;
        this.heartRate = heartRate;
        this.bodyTemperature = bodyTemperature;
        this.spO2 = spO2;
        this.status = status;
        this.measuredAt = LocalDateTime.now();
    }
}