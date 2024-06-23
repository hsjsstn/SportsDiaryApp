package ddwu.com.mobile.finalreport

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ddwu.com.mobile.finalreport.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    val editBinding by lazy {
        ActivityEditBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(editBinding.root)

        // 전달받은 데이터를 가져옴
        val gameTitle = intent.getStringExtra("gameTitle")
        val gameDate = intent.getStringExtra("gameDate")
        val gameCategory = intent.getStringExtra("gameCategory")
        val gameDetail = intent.getStringExtra("gameDetail")

        // EditText의 hint를 기존 데이터로 설정
        editBinding.editGameTitle.hint = gameTitle
        editBinding.editGameDate.hint = gameDate
        editBinding.editGameCategory.hint = gameCategory
        editBinding.editGameDetail.hint = gameDetail

        editBinding.editButton.setOnClickListener {
            val title = editBinding.editGameTitle.text.toString().trim()
            val date = editBinding.editGameDate.text.toString().trim()
            val category = editBinding.editGameCategory.text.toString().trim()
            val detail = editBinding.editGameDetail.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (date.isEmpty()) {
                Toast.makeText(this, "날짜를 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (category.isEmpty()) {
                Toast.makeText(this, "종목을 입력하세요", Toast.LENGTH_SHORT).show()
            } else if (detail.isEmpty()) {
                Toast.makeText(this, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()

                resultIntent.putExtra("editGameTitle", editBinding.editGameTitle.text.toString())
                resultIntent.putExtra("editGameDate", editBinding.editGameDate.text.toString())
                resultIntent.putExtra("editGameCategory", editBinding.editGameCategory.text.toString())
                resultIntent.putExtra("editGameDetail", editBinding.editGameDetail.text.toString())

                setResult(RESULT_OK, resultIntent)
                finish()
            }

        }

        editBinding.cancelButtonForEditActivity.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
