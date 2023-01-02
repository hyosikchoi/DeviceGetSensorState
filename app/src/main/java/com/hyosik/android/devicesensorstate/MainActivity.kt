package com.hyosik.android.devicesensorstate

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val sensorList = mutableListOf<SensorState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSensorList()
        getSensorState()
    }

    @SuppressLint("SetTextI18n")
    private fun getSensorState()  {
        try {
            /**
             * [확인 방법]
             * 1. SensorManager 객체를 사용해 기기 센서 지원 여부 확인 실시
             * 2. 각 속성값이 null이면 지원하는 않는 경우입니다
             */
            val mLinearLayout = findViewById<LinearLayout>(R.id.ll_main)
            val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorList.forEach { sensorState ->
                val textView = TextView(this)
                val params =  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(30.dpToPx(),12.dpToPx(),0,0)
                textView.layoutParams = params
                textView.textSize = 12.0f
                if(mSensorManager.getDefaultSensor(sensorState.sensorState) != null) {
                    textView.setText("${sensorState.sensorName} : 지원 ")
                    textView.setTextColor(getColor(R.color.blue_700))
                } else {
                    textView.setText("${sensorState.sensorName} : 미지원 ")
                    textView.setTextColor(getColor(R.color.red_700))
                }
                mLinearLayout.addView(textView)
            }

        } catch (e: Exception) {
            Log.e("exception" , e.message.toString())
        }
    }

    private fun setSensorList() {
        sensorList.add(SensorState(sensorState = Sensor.TYPE_TEMPERATURE , sensorName = "[온도감지]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_STEP_COUNTER , sensorName = "[발걸음횟수]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_STEP_DETECTOR , sensorName = "[발걸음감지]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_SIGNIFICANT_MOTION , sensorName = "[모션트리거]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_HEART_RATE , sensorName = "[심박수]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_ACCELEROMETER , sensorName = "[가속도/충격]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_AMBIENT_TEMPERATURE , sensorName = "[주위온도]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_GRAVITY , sensorName = "[축방향/중력]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_GYROSCOPE , sensorName = "[회전정보]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_LIGHT , sensorName = "[빛세기]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_LINEAR_ACCELERATION , sensorName = "[중력값제거 가속도]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_MAGNETIC_FIELD , sensorName = "[자기장]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_ORIENTATION , sensorName = "[방향각]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_PRESSURE , sensorName = "[압력]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_PROXIMITY , sensorName = "[근접감지]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_RELATIVE_HUMIDITY , sensorName = "[상대습도]"))
        sensorList.add(SensorState(sensorState = Sensor.TYPE_ROTATION_VECTOR , sensorName = "[벡터]"))
    }

    fun Int.dpToPx() : Int {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),resources.displayMetrics)
        return px.toInt()
    }

}