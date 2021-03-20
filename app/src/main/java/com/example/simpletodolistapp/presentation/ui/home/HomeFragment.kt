package com.example.simpletodolistapp.presentation.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolistapp.databinding.DialogAddTodoBinding
import com.example.simpletodolistapp.databinding.FragmentHomeBinding
import com.example.simpletodolistapp.domain.Todo
import com.example.simpletodolistapp.presentation.ui.adapter.TodoListAdapter
import com.example.simpletodolistapp.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :Fragment(), TodoListItemListener{

    private lateinit var binding :FragmentHomeBinding
    private lateinit var adapter :TodoListAdapter
    private lateinit var dialogAddBinding :DialogAddTodoBinding
    private lateinit var dialogAlertView :AlertDialog
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        dialogAddBinding = DialogAddTodoBinding.inflate(inflater, container, false)
        dialogAlertView = AlertDialog.Builder(requireContext())
            .setView(dialogAddBinding.root)
            .create()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()

        viewModel.todosLiveData.observe(viewLifecycleOwner, {
            dialogAlertView.dismiss()
            adapter.updateList(it)
        })

        binding.addButton.setOnClickListener {
            dialogAddBinding.inputTitleTodo.setText("")
            dialogAddBinding.inputLabelTodo.setText("")
            dialogAlertView.show()
            dialogAddBinding.addButton.setOnClickListener {
                val title = dialogAddBinding.inputTitleTodo.text.toString()
                val todo = dialogAddBinding.inputLabelTodo.text.toString()
                when {
                    title.isEmpty() -> dialogAddBinding.inputTitleTodo.error = "Masukkan Judul Todo"
                    todo.isEmpty() -> dialogAddBinding.inputLabelTodo.error = "Masukkan Todo"
                    else -> viewModel.addTodos(Todo(title = title, todo = todo))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTodos()
    }

    private fun initList(){
        adapter = TodoListAdapter(this)
        binding.lvTodo.adapter = adapter
        binding.lvTodo.layoutManager = LinearLayoutManager(requireContext())
        binding.lvTodo.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onLongClick(todo: Todo) {
        AlertDialog.Builder(requireContext())
            .setTitle("Hapus Todo")
            .setMessage("Apakah Anda yakin akan menghapus Todo ${todo.title}?")
            .setPositiveButton("Ya"){ dialog, _ ->
                dialog.dismiss()
                viewModel.deleteTodo(todo)
            }.setNegativeButton("Tidak"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    override fun onClick(todo: Todo) {

    }

}