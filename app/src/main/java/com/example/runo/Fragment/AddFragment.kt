package com.example.runo.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.runo.Note
import com.example.runo.viewmodel.NoteViewModel
import com.example.runo.R
import com.example.runo.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    lateinit var notesViewModel: NoteViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        insertNotes()
    }
    private fun insertNotes() {
        _binding?.saveNotesBtn?.setOnClickListener {
            val createdTime: Date = Calendar.getInstance().getTime()
            val formatter = SimpleDateFormat("dd/M/yyyy");
            val note : Note = Note(_binding?.idAddNoteName?.text.toString(),
                _binding?.idAddNoteDesc?.text.toString(),formatter.format(createdTime).toString(),null,null)
            notesViewModel.addNote(note)
            findNavController().navigate(R.id.action_addFragment_to_notesFragment)
        }

    }
    private fun setViewModel() {
     notesViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}