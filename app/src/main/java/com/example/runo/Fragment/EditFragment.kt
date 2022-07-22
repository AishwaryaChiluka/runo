package com.example.runo.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.runo.Note
import com.example.runo.viewmodel.NoteViewModel
import com.example.runo.R
import com.example.runo.databinding.FragmentEditBinding
import java.text.SimpleDateFormat
import java.util.*


class EditFragment : Fragment() {
    lateinit var notesViewModel: NoteViewModel
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private  val args by navArgs<EditFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setValues()
    }

    private fun setValues() {
        val updatedTime: Date = Calendar.getInstance().getTime()
        val formatter = SimpleDateFormat("dd/M/yyyy");
        _binding!!.idEdtNoteName.setText(args.updateNote.noteTitle)
        _binding!!.idEdtNoteDesc.setText(args.updateNote.noteDescription)
        _binding!!.idupdateBtn.setOnClickListener {
            var note = Note(_binding!!.idEdtNoteName.text.toString(),
                _binding!!.idEdtNoteDesc.text.toString(),
                 args.updateNote.createdBy,
                formatter.format(updatedTime).toString(),
                args.updateNote.id)
            notesViewModel.updateNote(note)
            Toast.makeText(requireContext(),"updated Successfully ",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_notesFragment)
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