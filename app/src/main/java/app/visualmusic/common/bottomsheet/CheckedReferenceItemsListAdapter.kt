package app.visualmusic.common.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.visualmusic.common.bottomsheet.CheckedReferenceItemsListAdapter.ViewHolder
import app.visualmusic.databinding.ItemReferenceItemBinding

class CheckedReferenceItemsListAdapter(
    private val isSingleChoice: Boolean,
    private val onItemCheckedChange: (Long, Boolean) -> Unit
) : ListAdapter<CheckedReferenceItem, ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(
        private val binding: ItemReferenceItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CheckedReferenceItem) {
            val id = model.item.id
            binding.apply {
                name.text = model.item.name

                radioBtn.visibility = View.GONE
                checkBox.visibility = View.GONE

                val visibleBtn: CompoundButton = if (isSingleChoice) radioBtn else checkBox

                visibleBtn.apply {
                    visibility = View.VISIBLE

                    setOnCheckedChangeListener(null)
                    isChecked = model.isChecked

                    setOnCheckedChangeListener { _, isChecked ->
                        onItemCheckedChange(id, isChecked)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val vhBinding = ItemReferenceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(vhBinding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    class DiffCallBack : DiffUtil.ItemCallback<CheckedReferenceItem>() {
        override fun areItemsTheSame(
            oldItem: CheckedReferenceItem,
            newItem: CheckedReferenceItem
        ): Boolean =
            oldItem.item.id == newItem.item.id

        override fun areContentsTheSame(
            oldItem: CheckedReferenceItem,
            newItem: CheckedReferenceItem
        ): Boolean =
            oldItem.isChecked == newItem.isChecked
    }
}