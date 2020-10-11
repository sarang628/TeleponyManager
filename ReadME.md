# TeleponyManager 분석하기

DeviceManager 라이브러리의 기능을 업데이트 하기위해 TeleponyManager을 분석해보려고합니다. 그럼 시작해볼까요?

TeleponyManager는 단말기의 <b>통신서비스</b>에 대한 정보를 제공해주는 객체입니다. 애플케이션들은 이 TeleponyManager를 이용하여 통화서비스에대한 상태를 확인할수도있고, 여러가지 구독정보에 접근도 할 수 있습니다. 그리고 리스너를 등록해서 여러 상태에대한 변화도 파악할 수 있습니다.



# 실습하기

## teleponyManager 초기화
```
telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
```

## getCallState 얻기
getCallState 함수는 현재 디바이스의 통화상태를 반환합니다.
CALL_STATE_IDLE, CALL_STATE_RINGING, CALL_STATE_OFFHOOK
의 상태가 있습니다.
```
fun getCallState(): String {
    return telephonyManager.callState.toString()
}
```

## imei 얻기
```
fun getImei(): String {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "permission denied"

            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), 0x01)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return "${telephonyManager.imei}"
        } else {
            return "${telephonyManager.deviceId}"
        }
    }
```

## 전화번호 얻기
```
private fun getLine1Number(): String {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activityMainBinding.btnPermission.setOnClickListener {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_SMS,
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.READ_PHONE_STATE
                    ), 0x01
                )
            }
            return "permission denied"
        }
        return "${telephonyManager.line1Number}"
    }
```

## 모듈로 제공해보기

### 인터페이스 정의
```
/**
 * TeleponyManager를 분석하여 새롭게 제공할 기능을 정의한 인터페이스 입니다.
 */
interface SrTelephonyManager {
    /**
     * 휴대전화번호 얻기
     */
    fun getLine1Number(): String

    /**
     * 통화 상태 얻기
     */
    fun getCallState(): String

    /**
     * imei 얻기
     */
    fun getImei(): String
}
```