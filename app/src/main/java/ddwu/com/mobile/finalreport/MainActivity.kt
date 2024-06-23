// 과제명: 스포츠 경기 기록 앱
// 분반: 02분반
// 학번: 20220812 성명: 한수진
// 제출일: 2024년 6월

package ddwu.com.mobile.finalreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobile.finalreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val ADD_ACTIVITY_CODE = 100
    val EDIT_ACTIVITY_CODE = 200
    val DEV_INFO_ACTIVITY_CODE = 300
    val games = GameDao().games
    var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = GameAdapter(games)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // 수정하기 (클릭)
        val listener = object : GameAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val game = games[position]
                val intent = Intent(this@MainActivity, EditActivity::class.java)
                intent.putExtra("gameTitle", game.title)
                intent.putExtra("gameDate", game.date)
                intent.putExtra("gameCategory", game.category)
                intent.putExtra("gameDetail", game.detail)
                startActivityForResult(intent, EDIT_ACTIVITY_CODE)
                pos = position
            }
        }
        adapter.setOnItemClickListener(listener)

        // 삭제하기 (롱클릭)
        val longClickListener = object : GameAdapter.OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("기록 삭제")
                    .setMessage("${games[position]}를 삭제하시겠습니까?")
                    .setPositiveButton("삭제") { dialog, which ->
                        games.removeAt(position)
                        adapter.notifyItemRemoved(position)
                        adapter.notifyItemRangeChanged(position, games.size)
                    }
                    .setNegativeButton("취소") { dialog, which ->
                        dialog.dismiss()
                    }
                builder.show()
                return true
            }
        }
        adapter.setOnItemLongClickListener(longClickListener)

    }

    // 추가 & 수정한 내용 결과 표시
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 추가한 내용 반영
        if (requestCode == ADD_ACTIVITY_CODE && resultCode == RESULT_OK) {
            val newTitle = data?.getStringExtra("addGameTitle")
            val newDate = data?.getStringExtra("addGameDate")
            val newCategory = data?.getStringExtra("addGameCategory")
            val newDetail = data?.getStringExtra("addGameDetail")
            var newImg = 0
            when (newCategory) {
                "baseball" -> newImg = R.mipmap.kia_tigers
                "soccer" -> newImg = R.mipmap.ulsan_hd
                "esports(LOL)" -> newImg = R.mipmap.hle
                else -> newImg = R.mipmap.diary
            }

            if (newTitle != null && newDate != null && newCategory != null && newDetail != null) {
                games.add(GameDto(newImg, newTitle, newDate, newCategory, newDetail))
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
        // 수정한 내용 반영
        else if (requestCode == EDIT_ACTIVITY_CODE && resultCode == RESULT_OK) {
            val editTitle = data?.getStringExtra("editGameTitle")
            val editDate = data?.getStringExtra("editGameDate")
            val editCategory = data?.getStringExtra("editGameCategory")
            val editDetail = data?.getStringExtra("editGameDetail")
            var editImg = 0
            when (editCategory) {
                "baseball" -> editImg = R.mipmap.kia_tigers
                "soccer" -> editImg = R.mipmap.ulsan_hd
                "esports(LOL)" -> editImg = R.mipmap.hle
                else -> editImg = R.mipmap.diary
            }

            if (editTitle != null && editDate != null && editCategory != null && editDetail != null && pos != -1) {
                games[pos].photo = editImg
                games[pos].title = editTitle
                games[pos].date = editDate
                games[pos].category = editCategory
                games[pos].detail = editDetail
                pos = -1
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option1 -> { // 기록 추가하기
                val intent = Intent(this, AddActivity::class.java)
                startActivityForResult(intent, ADD_ACTIVITY_CODE)
            }

            R.id.option2 -> { // 개발자 정보 표시
                val intent = Intent(this, DevInfoActivity::class.java)
                startActivityForResult(intent, DEV_INFO_ACTIVITY_CODE)
            }

            R.id.option3 -> { // 앱 종료하기
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("앱 종료")
                    .setMessage("앱을 종료하시겠습니까?")
                    .setPositiveButton("종료") { dialog, which->
                        finish()
                    }
                    .setNegativeButton("취소") { dialog, which ->
                        dialog.dismiss()
                    }
                builder.show()
            }
        }

        return true
    }
}