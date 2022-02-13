package com.example.dbdinfos.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbdinfos.data.MainDTO
import com.example.dbdinfos.databinding.DbdListBinding
import com.squareup.picasso.Picasso


class MainDTOAdapter(
) :
    RecyclerView.Adapter<MainDTOAdapter.MyViewHolder>() {
    private var perkList = listOf<MainDTO>()
    lateinit var context: Context
    lateinit var onClickListener: OnClickListenerDTO

    inline fun setOnClickListenerDTO(crossinline listener: (MainDTO) -> Unit) {
        this.onClickListener = object : OnClickListenerDTO {
            override fun onClick(mainDTO: MainDTO) = listener(mainDTO)
        }
    }

    interface OnClickListenerDTO {
        fun onClick(mainDTO: MainDTO) = Unit
    }

    inner class MyViewHolder(val binding: DbdListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemclicked: MainDTO) {
            binding.apply {
                textView.text = itemclicked.perk_name
                Picasso.get().load(itemclicked.icon).into(imageView)

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val binding =
            DbdListBinding.inflate(LayoutInflater.from(context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val perk = perkList[position]
        holder.bind(perk)
        holder.setIsRecyclable(false)
        holder.itemView.setOnClickListener {

            onClickListener.onClick(perk)

        }
    }

    override fun getItemCount(): Int {
        return perkList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDBDLIST(list: List<MainDTO>?) {
        list?.let { perkList = list.toMutableList() }
        notifyDataSetChanged()
    }
}