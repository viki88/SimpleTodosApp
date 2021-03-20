package com.example.simpletodolistapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolistapp.databinding.RowTodoListBinding
import com.example.simpletodolistapp.domain.Todo
import com.example.simpletodolistapp.presentation.ui.home.TodoListItemListener

class TodoListAdapter(var todoListItemListener: TodoListItemListener) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private var todos = listOf<Todo>()

    inner class TodoViewHolder(var binding: RowTodoListBinding, var todoListItemListener: TodoListItemListener) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo :Todo){
            binding.tvTitleTodo.text = todo.title
            binding.tvTodo.text = todo.todo
            binding.root.setOnClickListener {
                todoListItemListener.onClick(todo)
            }
            binding.root.setOnLongClickListener {
                todoListItemListener.onLongClick(todo)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(RowTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false), todoListItemListener)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    fun updateList(todos :List<Todo>){
        this.todos = todos
        notifyDataSetChanged()
    }
}