package ddwu.com.mobile.finalreport

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    val addBinding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(addBinding.root)

        addBinding.addButton.setOnClickListener {
            val resultIntent = Intent()
            val games = ArrayList<GameDto>()

            val title = addBinding.addGameTitle.text.toString()
            val date = addBinding.addGameDate.text.toString()
            val category = addBinding.addGameCategory.text.toString()
            val detail = addBinding.addGameDetail.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (date.isEmpty()) {
                Toast.makeText(this, "날짜를 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (category.isEmpty()) {
                Toast.makeText(this, "종목을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (detail.isEmpty()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                resultIntent.putExtra("addGameTitle", title)
                resultIntent.putExtra("addGameDate", date)
                resultIntent.putExtra("addGameCategory", category)
                resultIntent.putExtra("addGameDetail", detail)

                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        addBinding.cancelButtonForAddActivity.setOnClickListener {
            val resultIntent = Intent()
            setResult(RESULT_CANCELED, resultIntent)
            finish()
        }
    }
}