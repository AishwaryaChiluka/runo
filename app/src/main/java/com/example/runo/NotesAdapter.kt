package com.example.runo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.runo.Fragment.NotesFragmentDirections
import com.example.runo.databinding.ItemNotesBinding


class NotesAdapter(private val mList: ArrayList<Note>, val noteClickDeleteInterface: deleteInterface) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

	// create new views
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


		val layoutInflater = LayoutInflater.from(parent.context)
		val itemUserBinding : ItemNotesBinding = ItemNotesBinding.inflate(layoutInflater,parent,false)
		return ViewHolder(itemUserBinding)

	}

	// binds the list items to a view
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		val note = mList[position]
		holder.notesBinding.note=note
		holder.notesBinding.executePendingBindings()
        holder.itemView.setOnClickListener {
			val action = NotesFragmentDirections.actionNotesFragmentToEditFragment(note)
			holder.itemView.findNavController().navigate(action)
			holder.notesBinding.idUpdatedDate.visibility = View.VISIBLE
		}
		holder.notesBinding.idIVDelete.setOnClickListener {
			noteClickDeleteInterface.onDeleteIconClick(note)
		}
	}

	// return the number of the items in the list
	override fun getItemCount(): Int {
		return mList.size
	}
    fun updateUser(userList : ArrayList<Note>){
    	mList.clear()
		mList.addAll(userList)
		notifyDataSetChanged()
    }
	// Holds the views for adding it to image and text
	class ViewHolder(var notesBinding: ItemNotesBinding) : RecyclerView.ViewHolder(notesBinding.root) {


	}
}
