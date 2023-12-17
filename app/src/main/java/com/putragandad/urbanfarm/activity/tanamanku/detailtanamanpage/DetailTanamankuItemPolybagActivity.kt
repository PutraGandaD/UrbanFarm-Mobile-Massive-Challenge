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
    private var id : Int = 0
    private var tanamanId: String? = null
    private var amCekKondisiTanamanId: Int? = null

    private var alarmManager : AlarmManager? = null
    private lateinit var amSiramTanamanIntent : PendingIntent
    private lateinit var amCekKondisiTanamanIntent: PendingIntent

    private lateinit var switchSiramTanamanState : MaterialSwitch
    private lateinit var switchCekTanamanState: MaterialSwitch

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
            tanamanId = tanamankuListItem?.idTanaman

            Toast.makeText(this, "$id , $tanamanId", Toast.LENGTH_SHORT).show()
            Log.d("DetailTanamankuItemPolybagActivity", "ID: $id")
        }

        switchSiramTanamanState = binding.switchSiramTanaman
        switchCekTanamanState = binding.switchCekTanaman
        switchSiramTanamanState.isChecked = tanamankuListItem?.switchSiramTanaman == true
        switchCekTanamanState.isChecked = tanamankuListItem?.switchCekTanaman == true

        binding.switchSiramTanaman.setOnCheckedChangeListener { _, isChecked ->
            if (id != null) {
                if(isChecked) {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchSiramTanaman = true)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                        setAlarmSiramTanaman(id, tanamanId, "Jangan Biarkan Tanaman Anda Kekeringan! Siram tanaman anda sekarang.")
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchSiramTanaman = false)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                        cancelAlarm(id)
                    }
                }
            }
        }

        binding.switchCekTanaman.setOnCheckedChangeListener { _, isChecked ->
            if (id != null) {
                if(isChecked) {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchCekTanaman = true)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getTanamankuItemById(id)?.let { latestData ->
                            val updatedTanamankuListItem = latestData.copy(switchCekTanaman = false)
                            updatedTanamankuListItem.id = id // set the id
                            viewModel.updateTanaman(updatedTanamankuListItem)
                        }
                    }
                }
            }
        }
    }

    private fun setAlarmSiramTanaman(id: Int, tanamanId: String?, notificationContent: String) {
        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        amSiramTanamanIntent = getAlarmIntent(id, notificationContent)

        when(tanamanId) {
            "bawangMerah" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 14) // hardcode hour here
                    set(Calendar.MINUTE, 10) // hardcode minute here
                }

                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    amSiramTanamanIntent
                )

                Toast.makeText(this, "Pengingat Siram Tanaman Dihidupkan!", Toast.LENGTH_SHORT).show()
            }

            "cabaiRawit" -> {
                val calendar: Calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(Calendar.HOUR_OF_DAY, 14) // hardcode hour here
                    set(Calendar.MINUTE, 10) // hardcode minute here
                }

                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    amSiramTanamanIntent
                )

                Toast.makeText(this, "Pengingat Siram Tanaman Dihidupkan!", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelAlarm(id: Int) {
        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(this, TanamankuAlarmReceiver::class.java)

        amSiramTanamanIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager?.cancel(amSiramTanamanIntent)
        Toast.makeText(this, "Pengingat Siram Tanaman telah dimatikan", Toast.LENGTH_SHORT).show()
    }

    private fun getAlarmIntent(id: Int, notificationContent: String) : PendingIntent {
        val intent = Intent(this, TanamankuAlarmReceiver::class.java).apply {
            putExtra("customText", notificationContent)
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

