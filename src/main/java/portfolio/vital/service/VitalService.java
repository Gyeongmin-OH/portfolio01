package portfolio.vital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.auth.domain.Member;
import portfolio.auth.repository.MemberRepository;
import portfolio.vital.domain.*;
import portfolio.vital.repository.VitalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VitalService {

    private final VitalRepository vitalRepository;
    private final MemberRepository memberRepository;

    // 바이탈 입력
    public VitalResponse save(String email, VitalRequest request) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 상태 계산
        VitalStatus status = VitalStatusCalculator.calculate(
                request.getSystolicBp(),
                request.getDiastolicBp(),
                request.getHeartRate(),
                request.getBodyTemperature(),
                request.getSpO2(),
                member.getAge()
        );

        VitalRecord record = VitalRecord.builder()
                .member(member)
                .systolicBp(request.getSystolicBp())
                .diastolicBp(request.getDiastolicBp())
                .heartRate(request.getHeartRate())
                .bodyTemperature(request.getBodyTemperature())
                .spO2(request.getSpO2())
                .status(status)
                .build();

        vitalRepository.save(record);
        return VitalResponse.from(record);
    }

    // 측정 이력 조회
    public List<VitalResponse> findAll(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return vitalRepository.findByMemberOrderByMeasuredAtDesc(member)
                .stream()
                .map(VitalResponse::from)
                .collect(Collectors.toList());
    }

    // 측정값 삭제
    public void delete(Long id, String email) {
        VitalRecord record = vitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 측정값입니다."));

        if (!record.getMember().getEmail().equals(email)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        vitalRepository.delete(record);
    }
}