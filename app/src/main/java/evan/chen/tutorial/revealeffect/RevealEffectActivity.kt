package evan.chen.tutorial.revealeffect

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_reveal_effect.*
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator

open class RevealEffectActivity : AppCompatActivity() {

    companion object {
        const val ARG_CIRCULAR_REVEAL_X = "ARG_CIRCULAR_REVEAL_X"
        const val ARG_CIRCULAR_REVEAL_Y = "ARG_CIRCULAR_REVEAL_Y"
    }

    private var revealX: Int = 0
    private var revealY: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal_effect)

        revealX = intent.getIntExtra(ARG_CIRCULAR_REVEAL_X, 0)
        revealY = intent.getIntExtra(ARG_CIRCULAR_REVEAL_Y, 0)

        rootLayout.visibility = View.INVISIBLE

        val viewTreeObserver = rootLayout.viewTreeObserver
        if (viewTreeObserver.isAlive) {
            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    //當在一個ViewTree 中全局佈局發生改變或某個View的可視狀態發生改變時，呼叫的callback
                    //在這裡開始Reveal animation
                    startReveal(revealX, revealY)
                    rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startReveal(centerX: Int, centerY: Int) {
        //動畫的開始半徑
        val startRadius = 0.0f
        //動畫的結束半徑
        val endRadius = Math.max(rootLayout.width, rootLayout.height).toFloat()

        val circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, centerX, centerY, startRadius, endRadius)
        circularReveal.duration = 400
        circularReveal.interpolator = AccelerateInterpolator()

        rootLayout.visibility = View.VISIBLE
        circularReveal.start()
    }
}
