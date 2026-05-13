package portfolio.vital.domain;

public class VitalStatusCalculator {

    // 전체 상태 계산
    public static VitalStatus calculate(int systolicBp, int diastolicBp,
                                        int heartRate, double bodyTemperature,
                                        int spO2, int age) {
        VitalStatus bpStatus = calculateBloodPressure(systolicBp, diastolicBp, age);
        VitalStatus hrStatus = calculateHeartRate(heartRate, age);
        VitalStatus tempStatus = calculateBodyTemperature(bodyTemperature);
        VitalStatus spo2Status = calculateSpO2(spO2);

        // 가장 심각한 상태 반환
        if (bpStatus == VitalStatus.DANGER || hrStatus == VitalStatus.DANGER ||
                tempStatus == VitalStatus.DANGER || spo2Status == VitalStatus.DANGER) {
            return VitalStatus.DANGER;
        }
        if (bpStatus == VitalStatus.CAUTION || hrStatus == VitalStatus.CAUTION ||
                tempStatus == VitalStatus.CAUTION || spo2Status == VitalStatus.CAUTION) {
            return VitalStatus.CAUTION;
        }
        return VitalStatus.NORMAL;
    }

    // 혈압 상태 계산 (나이 반영)
    private static VitalStatus calculateBloodPressure(int systolic, int diastolic, int age) {
        int normalSystolic = age >= 60 ? 130 : 120;
        int cautionSystolic = age >= 60 ? 145 : 139;
        int normalDiastolic = age >= 60 ? 85 : 80;
        int cautionDiastolic = age >= 60 ? 90 : 89;

        if (systolic > cautionSystolic || diastolic > cautionDiastolic) {
            return VitalStatus.DANGER;
        }
        if (systolic > normalSystolic || diastolic > normalDiastolic) {
            return VitalStatus.CAUTION;
        }
        return VitalStatus.NORMAL;
    }

    // 심박수 상태 계산 (나이 반영)
    private static VitalStatus calculateHeartRate(int heartRate, int age) {
        int maxNormal = age >= 60 ? 90 : 100;

        if (heartRate < 50 || heartRate > 120) {
            return VitalStatus.DANGER;
        }
        if (heartRate < 60 || heartRate > maxNormal) {
            return VitalStatus.CAUTION;
        }
        return VitalStatus.NORMAL;
    }

    // 체온 상태 계산
    private static VitalStatus calculateBodyTemperature(double temp) {
        if (temp >= 38.6 || temp < 35.0) {
            return VitalStatus.DANGER;
        }
        if (temp >= 37.6 || temp < 36.0) {
            return VitalStatus.CAUTION;
        }
        return VitalStatus.NORMAL;
    }

    // 산소포화도 상태 계산
    private static VitalStatus calculateSpO2(int spO2) {
        if (spO2 < 90) {
            return VitalStatus.DANGER;
        }
        if (spO2 < 95) {
            return VitalStatus.CAUTION;
        }
        return VitalStatus.NORMAL;
    }
}