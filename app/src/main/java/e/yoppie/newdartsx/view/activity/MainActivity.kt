package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.service.BgmService
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Sound
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val soundRepository = SoundRepository()
    private var soundEntity: SoundEntity? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BGM START!!
//        val intentBgm = Intent(application, BgmService::class.java)
//        intentBgm.putExtra("REQUEST_CODE", 1)
//        startForegroundService(intentBgm)

        Animation.emphasize(this, nob_game)
        Animation.emphasize(this, bull_game)

        val sound = Sound(this, R.raw.button_sound)
        nob_game.clicks().subscribe {
            sound.play()
        }
        bull_game.clicks().subscribe {
            sound.play()
        }
        setting_button.clicks().subscribe {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        initStetho()
    }

    private fun initStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    @SuppressLint("CheckResult")
    private fun initSound() {
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound(this) }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (soundEntity == null) {

                }
            }
    }
}
