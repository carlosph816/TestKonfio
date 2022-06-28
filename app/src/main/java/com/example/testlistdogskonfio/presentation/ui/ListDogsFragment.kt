package com.example.testlistdogskonfio.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testlistdogskonfio.adapter.ListDogsAdapter
import com.example.testlistdogskonfio.common.DataState
import com.example.testlistdogskonfio.data.model.ListDogsModel
import com.example.testlistdogskonfio.databinding.FragmentListDogsBinding
import com.example.testlistdogskonfio.presentation.viewmodel.ListDogsViewModel
import com.example.testlistdogskonfio.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListDogsFragment : Fragment(), View.OnClickListener {

    private var _binding:FragmentListDogsBinding? = null
    private val binding get()= _binding!!

    private var subsequentJob: Job? = null
    private val listDogsModel: ListDogsViewModel by viewModels()

    private var mCallBack:ViewCallBacks ? = null

    private lateinit var listDogsAdapter: ListDogsAdapter
    private var listDogs : ListDogsModel ? = ListDogsModel()

    private var prefs: PrefManager? = null

    companion object {
        val TAG = ListDogsFragment::class.java.canonicalName!!
        @JvmStatic
        fun newInstance() = ListDogsFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            prefs = getContext()?.let { PrefManager(it) }
            mCallBack = context as ViewCallBacks
        } catch (e: ClassCastException) {
        }
    }

    override fun onStart() {
        super.onStart()
        getListDogs()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listDogsModel.getListDogs()
    }

    override fun onStop() {
        super.onStop()
        subsequentJob?.cancelChildren()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListDogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        binding.include.icBack.setOnClickListener(this)
    }

    private fun initRecycler(){
        listDogsAdapter =ListDogsAdapter(listDogs!!)
        binding.recyclerViewDogs.run {
            adapter = listDogsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
    }

    private fun getListDogs() {
        subsequentJob = lifecycleScope.launch {
            whenStarted {
                listDogsModel.getResponse.collect {state->
                    when(state) {
                        is DataState.Loading ->{
                            mCallBack!!.showLoader(true)
                        }
                        is DataState.Success -> {
                            mCallBack!!.showLoader(false)
                            listDogs = state.data
                            initRecycler()
                            prefs!!.isFirstTimeLaunch = false
                        }
                        is DataState.Error -> {
                            mCallBack!!.showMessage(state.error.cause)
                            mCallBack!!.showLoader(false)
                        }
                    }
                }
            }
        }
    }

    interface ViewCallBacks{
        fun showLoader(showIt : Boolean)
        fun showMessage(message : String)
    }

    override fun onClick(p0: View?) {
        when(p0!!){
            binding.include.icBack->{
                activity!!.finish()
            }
        }
    }
}