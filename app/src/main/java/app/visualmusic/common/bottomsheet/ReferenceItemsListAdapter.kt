package app.visualmusic.common.bottomsheet

import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.visualmusic.common.bottomsheet.ReferenceItemsListAdapter.ReferenceItemVH
import app.visualmusic.core.model.cover.ReferenceItem
import app.visualmusic.databinding.ItemReferenceItemBinding

class ReferenceItemsListAdapter(
    private val isSingleChoice: Boolean
) : ListAdapter<ReferenceItem, ReferenceItemVH>(DiffCallBack()) {

    class ReferenceItemVH(
        private val binding: ItemReferenceItemBinding,
        private val isSingleChoice: Boolean
    ) : RecyclerView.ViewHolder(binding.root) {
        private var referenceItemId: Long = -1

        fun bind(model: ReferenceItem) {
            Log.d("Test", "Binding item: ${model.name}")

            referenceItemId = model.id

            binding.apply {
                name.text = model.name

                if (isSingleChoice) {
                    checkBox.visibility = GONE
                } else {
                    radioBtn.visibility = GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReferenceItemVH {
        val vhBinding = ItemReferenceItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ReferenceItemVH(vhBinding, isSingleChoice)
    }

    override fun onBindViewHolder(
        holder: ReferenceItemVH,
        position: Int
    ) = holder.bind(getItem(position))

    class DiffCallBack : DiffUtil.ItemCallback<ReferenceItem>() {
        override fun areItemsTheSame(
            oldItem: ReferenceItem,
            newItem: ReferenceItem
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ReferenceItem,
            newItem: ReferenceItem
        ): Boolean =
            oldItem.id == newItem.id
    }
}