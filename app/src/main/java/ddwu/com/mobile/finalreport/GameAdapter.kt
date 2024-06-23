package ddwu.com.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.finalreport.databinding.ListItemBinding


class GameAdapter(val games: ArrayList<GameDto>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private var displayedGames = ArrayList<GameDto>(games)

    override fun getItemCount() = displayedGames.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.itemBinding.image.setImageResource(displayedGames[position].photo)
        holder.itemBinding.gameTitle.text = displayedGames[position].title
        holder.itemBinding.gameDate.text = displayedGames[position].date
        holder.itemBinding.gameCategory.text = displayedGames[position].category
    }

    inner class GameViewHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val TAG = "GameViewHolder"

        init {
            itemBinding.root.setOnClickListener { // 수정하기
                listener.onItemClick(it, adapterPosition)
            }

            itemBinding.root.setOnLongClickListener { // 삭제하기
                longClickListener.onItemLongClick(it, adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int): Unit
    }

    lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

    lateinit var longClickListener: OnItemLongClickListener

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.longClickListener = listener
    }

    fun filterList(filteredList: ArrayList<GameDto>) {
        displayedGames.clear()
        displayedGames.addAll(filteredList)
        notifyDataSetChanged()
    }
}
