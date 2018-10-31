package evan.chen.tutorial.revealeffect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { v ->
            val location = IntArray(2)

            //取得fab的x, y座標
            v.getLocationOnScreen(location)
            val revealX = location[0]
            val revealY = location[1]

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "Transition")

            //傳入fab的x, y座標
            val intent = Intent(this, RevealEffectActivity::class.java)
            intent.putExtra(RevealEffectActivity.ARG_CIRCULAR_REVEAL_X, revealX)
            intent.putExtra(RevealEffectActivity.ARG_CIRCULAR_REVEAL_Y, revealY)

            ActivityCompat.startActivity(this, intent, options.toBundle())
        }
    }
}
