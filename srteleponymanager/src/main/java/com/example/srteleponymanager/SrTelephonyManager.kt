package com.example.srteleponymanager

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