package com.example.runo.Fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runo.*
import com.example.runo.Note
import com.example.runo.databinding.FragmentNotesBinding
import com.example.runo.viewmodel.NoteViewModel


class NotesFragment : Fragment() , deleteInterface, SearchView.OnQueryTextListener {

    lateinit var noteViewModel: NoteViewModel
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private var notesAdapter = NotesAdapter(arrayListOf(),this)
    private lateinit var list : List<Note>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        navigateToAddNotesFragment()
        observeNotesList()
    }

    private fun observeNotesList() {
        _binding!!.notesRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
        noteViewModel.allNotes.observe(
            requireActivity(), Observer {
                note -> note.let {
                list = note
                notesAdapter.updateUser(note as ArrayList<Note>)
            }
            }
        )
    }

    private fun navigateToAddNotesFragment() {
     _binding?.addNotes?.setOnClickListener {
      findNavController().navigate(R.id.action_notesFragment_to_addFragment)
      }
    }

    private fun setViewModel() {
      noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteIconClick(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.dialogTitle)
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            Toast.makeText(requireContext(),"clicked yes",Toast.LENGTH_LONG).show()
            noteViewModel.deleteNote(note)
        }
        builder.setNegativeButton("Cancel"){dialogInterface, which ->
            Toast.makeText(requireContext(),"clicked No",Toast.LENGTH_LONG).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
        // displaying a toast message
        Toast.makeText(requireContext(), " Deleted", Toast.LENGTH_LONG).show()
    }

     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu,menu)
        val search = menu?.findItem(R.id.action_search)
         val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query : String ){
        val searchQuery = "%$query"
        noteViewModel.searchDatabase(searchQuery).observe(this,{ list ->
            list.let {
                notesAdapter.updateUser(list as ArrayList<Note>)
            }})
    }

}