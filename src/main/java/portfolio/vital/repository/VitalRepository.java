package portfolio.vital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portfolio.auth.domain.Member;
import portfolio.vital.domain.VitalRecord;
import portfolio.vital.domain.VitalStatus;

import java.util.List;

public interface VitalRepository extends JpaRepository<VitalRecord, Long> {

    List<VitalRecord> findByMemberOrderByMeasuredAtDesc(Member member);

    List<VitalRecord> findTop7ByMemberOrderByMeasuredAtDesc(Member member);

    long countByMemberAndStatus(Member member, VitalStatus status);

    long countByMember(Member member);

    @Query("SELECT AVG(v.systolicBp) FROM VitalRecord v WHERE v.member = :member")
    Double avgSystolicBp(@Param("member") Member member);

    @Query("SELECT AVG(v.diastolicBp) FROM VitalRecord v WHERE v.member = :member")
    Double avgDiastolicBp(@Param("member") Member member);

    @Query("SELECT AVG(v.heartRate) FROM VitalRecord v WHERE v.member = :member")
    Double avgHeartRate(@Param("member") Member member);

    @Query("SELECT AVG(v.bodyTemperature) FROM VitalRecord v WHERE v.member = :member")
    Double avgBodyTemperature(@Param("member") Member member);

    @Query("SELECT AVG(v.spO2) FROM VitalRecord v WHERE v.member = :member")
    Double avgSpO2(@Param("member") Member member);
}