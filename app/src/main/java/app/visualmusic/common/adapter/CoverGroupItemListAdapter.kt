package app.visualmusic.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import app.visualmusic.common.adapter.CoverGroupItemListAdapter.CoverGroupItemVH
import app.visualmusic.common.util.convertDpToPx
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.databinding.ItemCoverGroupItemBinding

class CoverGroupItemListAdapter(
    private val fragmentActivity: FragmentActivity,
    private val onClick: (Long) -> Unit
) : ListAdapter<CoverGroupItemDto, CoverGroupItemVH>(DiffCallBack()) {

    class CoverGroupItemVH(
        private val binding: ItemCoverGroupItemBinding,
        private val fragmentActivity: FragmentActivity,
        private val onClick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CoverGroupItemDto) {
            binding.apply {
                authorName.text = model.ownerUsername
                title.text = model.title
            }

            binding.coverSlider.setOnClickListener {
                onClick(model.id)
            }

            setupCoverSlider(model.imageUrls)
        }

        private fun setupCoverSlider(imageUrls: List<String>) {
            binding.apply {
                coverSlider.adapter = CoverSliderAdapter(fragmentActivity, imageUrls)
                coverSlider.setPageTransformer(MarginPageTransformer(convertDpToPx(10)))

                if (imageUrls.size > 1) {
                    dotsIndicator.attachTo(coverSlider)
                    dotsIndicator.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoverGroupItemVH {
        val vhBinding = ItemCoverGroupItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoverGroupItemVH(vhBinding, fragmentActivity, onClick)
    }

    override fun onBindViewHolder(
        holder: CoverGroupItemVH,
        position: Int
    ) = holder.bind(getItem(position))


    class DiffCallBack : DiffUtil.ItemCallback<CoverGroupItemDto>() {
        override fun areItemsTheSame(
            oldItem: CoverGroupItemDto,
            newItem: CoverGroupItemDto
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CoverGroupItemDto,
            newItem: CoverGroupItemDto
        ): Boolean =
            oldItem.id == newItem.id
    }
}