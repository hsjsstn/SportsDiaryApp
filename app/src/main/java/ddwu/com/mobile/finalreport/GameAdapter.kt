package ddwu.com.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.finalreport.databinding.ListItemBinding

class GameAdapter (val games: ArrayList<GameDto>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {


    override fun getItemCount() = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.itemBinding.image.setImageResource( games[position].photo )
        holder.itemBinding.gameTitle.text = games[position].title
        holder.itemBinding.gameDate.text = games[position].date
        holder.itemBinding.gameCategory.text = games[position].category
    }

    inner class GameViewHolder(val itemBinding : ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val TAG = "GameViewHolder"
        init {
            itemBinding.root.setOnClickListener{// 수정하기
                listener.onItemClick(it, adapterPosition)
            }

            itemBinding.root.setOnLongClickListener { // 삭제하기
                // 롱클릭 리스너 멤버함수 호출
                longClickListener.onItemLongClick(it, adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int) : Unit
    }

    lateinit var listener: OnItemClickListener

    fun setOnItemClickListener (listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int) : Boolean
    }

    lateinit var longClickListener: OnItemLongClickListener

    fun setOnItemLongClickListener (listener: OnItemLongClickListener) {
        this.longClickListener = listener
    }
}