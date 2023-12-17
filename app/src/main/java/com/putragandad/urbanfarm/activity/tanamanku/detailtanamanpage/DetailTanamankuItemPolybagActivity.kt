package com.putragandad.urbanfarm.activity.tanamanku.detailtanamanpage

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.materialswitch.MaterialSwitch
import com.putragandad.urbanfarm.dao.tanamanku.TanamankuDao
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamankuItemPolybagBinding
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels
import com.putragandad.urbanfarm.notifications.tanamanku.TanamankuAlarmReceiver
import com.putragandad.urbanfarm.viewmodels.tanamanku.TanamankuViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID

class DetailTanamankuItemPolybagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamankuItemPolybagBinding
    private var id : Int = 0 // id data tanaman
    private var tanamanId: String = "" // id data tanaman, in String (example : bawangMerah)
    private var requestCode1: Int = 0 // random unique requestCode for AlarmManager purpose
    private var requestCode2: Int = 0
    private var requestCode3: Int = 0
    private var requestCode4: Int = 0
    private var requestCode5: Int = 0

    private var alarmManager : AlarmManager? = null
    private lateinit var amSiramTanamanIntent1 : PendingIntent // intent for pendingIntent purpose - Siram Tanaman
    private lateinit var amSiramTanamanIntent2 : PendingIntent
    private lateinit var amSiramTanamanIntent3 : PendingIntent
    private lateinit var amPupukTanamanIntent1 : PendingIntent // intent for pendingIntent purpose - Pupuk Tanaman
    private lateinit var amPupukTanamanIntent2 : PendingIntent

    private lateinit var switchSiramTanamanState : MaterialSwitch
    private lateinit var switchPupukTanamanState: MaterialSwitch

    lateinit var viewModel: TanamankuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamankuItemPolybagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TanamankuViewModel::class.java)

        val tanamankuListItem = intent.getParcelableExtra<TanamankuItemModels>("item_tanamanku_list")
        if(tanamankuListItem != null) {
            val imageTanamanku = binding.gambarTanamanku
            val titleTanamanku = binding.titleTanamanku
            val metodeTanamku = binding.tvMetodeTanam
            val jenisTanamanku = binding.tvJenisTanaman

            imageTanamanku.setImageResource(tanamankuListItem.fotoTanaman)
            titleTanamanku.text = tanamankuListItem.namaTanaman
            metodeTanamku.text = tanamankuListItem.metodeTanam
            jenisTanamanku.text = tanamankuListItem.jenisTanaman
            id = tanamankuListItem.id
            tanamanId = tanamankuListItem.idTanaman
            requestCode1 = tanamankuListItem.reqCode1
            requestCode2 = tanamankuListItem.reqCode2
            requestCode3 = tanamankuListItem.reqCode3
            requestCode4 = tanamankuListItem.reqCode4
            requestCode5 = tanamankuListItem.reqCode5

            Toast.makeText(this, "$id , $tanamanId, $requestCode1, $requestCode2, $requestCode3, $requestCode4, $requestCode5", Toast.LENGTH_SHORT).show()
            Log.d("DetailTanamankuItemPolybagActivity", "ID: $id")
        }

        switchSiramTanamanState = binding.switchSiramTanaman
        switchPupukTanamanState = binding.switchPupukTanaman
        switchSiramTanamanState.isChecked = tanamankuListItem?.switchSiramTanaman == true
        switchPupukTanamanState.isChecked = tanamankuListItem?.switchPupukTanaman == true

        binding.switchSiramTanaman.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.getTanamankuItemById(id)?.let { latestData ->
                        val updatedTanamankuListItem = latestData.copy(switchSiramTanaman = true)
                        updatedTanamankuListItem.id = id // set the id
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
                    setAlarmSiramTanaman(requestCode1, requestCode2, requestCode3, tanamanId, "Jangan Biarkan Tanamanmu Kekeringan!", "Siram Tanaman Anda Sekarang.")
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.getTanamankuItemById(id)?.let { latestData ->
                        val updatedTanamankuListItem = latestData.copy(switchSiramTanaman = false)
                        updatedTanamankuListItem.id = id // set the id
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
                    cancelAlarmSiramTanaman(requestCode1, requestCode2, requestCode3, tanamanId)
                }
            }
        }

        binding.switchPupukTanaman.setOnCheckedChangeListener { _, isChecked ->
            if (id != null) {
                if(isChecked) {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchPupukTanaman = true)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchPupukTanaman = false)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                    }
                }
            }
        }
    }

    private fun setAlarmSiramTanaman(
        id1: Int,
        id2: Int,
        id3: Int,
        tanamanId: String?,
        notificationTitle: String,
        notificationContent: String)
    {
        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        when(tanamanId) {
            "bawangMerah" -> {
                amSiramTanamanIntent1 = getAlarmIntent(id1, notificationTitle, notificationContent) // use id1 for requestCode
                amSiramTanamanIntent2 = getAlarmIntent(id2, notificationTitle, notificationContent) // use id2 for requestCode

                val calendar1: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 8) // hardcode hour here
                    set(Calendar.MINUTE, 5) // hardcode minute here
                } // calendar1 for Siram Tanaman - pagi

                val calendar2: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 16) // hardcode hour here
                    set(Calendar.MINUTE, 30) // hardcode minute here
                } // calendar1 for Siram Tanaman - sore

                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar1.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    amSiramTanamanIntent1
                ) // set AlarmManager for Siram Tanaman - pagi

                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar2.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    amSiramTanamanIntent2
                ) // set AlarmManager for Siram Tanaman - sore

                Toast.makeText(this, "Pengingat Siram Tanaman Dihidupkan!", Toast.LENGTH_SHORT).show()
            }

            "cabaiRawit" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 14) // hardcode hour here
                    set(Calendar.MINUTE, 10) // hardcode minute here
                }

                Toast.makeText(this, "Pengingat Siram Tanaman Dihidupkan!", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelAlarmSiramTanaman(
        id1: Int,
        id2: Int,
        id3: Int,
        idTanaman: String)
    {
        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(this, TanamankuAlarmReceiver::class.java)

        when(idTanaman) {
            "bawangMerah" -> {
                amSiramTanamanIntent1 = PendingIntent.getBroadcast(this, id1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                amSiramTanamanIntent2 = PendingIntent.getBroadcast(this, id2, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                alarmManager?.cancel(amSiramTanamanIntent1)
                alarmManager?.cancel(amSiramTanamanIntent2)
            }
        }
        Toast.makeText(this, "Pengingat Siram Tanaman telah dimatikan", Toast.LENGTH_SHORT).show()
    }

    private fun getAlarmIntent(id: Int, notificationTitle: String, notificationContent: String) : PendingIntent {
        val intent = Intent(this, TanamankuAlarmReceiver::class.java).apply {
            putExtra("customTitleNotification", notificationTitle)
            putExtra("customContentNotification", notificationContent)
        }
        return PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "UrbanFarm Notifications"
            val description = "Notification Channel for UrbanFarm app"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("urbanFarmNotificationAlarm", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(channel)

        }
    }
}

