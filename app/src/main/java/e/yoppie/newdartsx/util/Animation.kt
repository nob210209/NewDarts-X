package e.yoppie.newdartsx.util

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import e.yoppie.newdartsx.R

class Animation {
    companion object {
        fun emphasize(context: Context, view: View) {
            val handler = Handler()
            val r = object : Runnable {
                override fun run() {
                    val animation = AnimationUtils.loadAnimation(context, R.anim.emphasize)
                    view.startAnimation(animation)
                    handler.postDelayed(this, 3000)
                }
            }
            handler.post(r)
        }
    }
}