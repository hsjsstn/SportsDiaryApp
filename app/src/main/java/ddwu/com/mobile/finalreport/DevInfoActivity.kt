package ddwu.com.mobile.finalreport

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityAddBinding
import ddwu.com.mobile.finalreport.databinding.ActivityDevinfoBinding
import java.util.zip.Inflater

class DevInfoActivity : AppCompatActivity() {
    val devInfoBinding by lazy {
        ActivityDevinfoBinding.inflate(layoutInflater)
    }

    val MAIN_ACTIVITY_CODE = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(devInfoBinding.root)

        devInfoBinding.devBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivityForResult(intent, MAIN_ACTIVITY_CODE)
        }
    }
}