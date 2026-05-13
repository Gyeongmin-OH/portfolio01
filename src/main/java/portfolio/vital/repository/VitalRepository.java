package portfolio.vital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.auth.domain.Member;
import portfolio.vital.domain.VitalRecord;

import java.util.List;

public interface VitalRepository extends JpaRepository<VitalRecord, Long> {

    // 회원별 측정 이력 조회 (최신순)
    List<VitalRecord> findByMemberOrderByMeasuredAtDesc(Member member);

    // 회원별 최근 7개 측정 이력 조회
    List<VitalRecord> findTop7ByMemberOrderByMeasuredAtDesc(Member member);
}