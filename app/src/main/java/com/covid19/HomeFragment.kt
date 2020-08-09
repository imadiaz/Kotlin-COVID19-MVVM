package com.covid19

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.covid19.data.repository.APIRepository
import com.covid19.data.repository.GlobalSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.getGlobalData()
    }

     private fun getGlobalData(){
         viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
         viewModel.onLoad()
         viewModel.summary.observe(viewLifecycleOwner, Observer { summary ->
             printGlobalSummary(summary)
         })
//        val apiService = APIRepository()
//         GlobalScope.launch (Dispatchers.Main){
//             val response = apiService.getSummary()
//             if(response.isSuccessful){
//                 val summary = response.body() as GlobalSummary
//                // Log.w("DATA","${summary.Global}")
//                 printGlobalData(summary)
//               // handleProgressBar()
//             }
//         }
    }


    private fun printGlobalSummary(summary: GlobalSummary){
        val infected = view?.findViewById<TextView>(R.id.text_view_infected)
        val deaths = view?.findViewById<TextView>(R.id.text_view_deaths)
        val recovered = view?.findViewById<TextView>(R.id.text_view_recovered)
        infected?.text = summary.Global.TotalConfirmed.toString()
        deaths?.text = summary.Global.TotalDeaths.toString()
        recovered?.text = summary.Global.TotalRecovered.toString()
        this.handleProgressBar()
    }

    private fun handleProgressBar(){
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        val content = view?.findViewById<GridLayout>(R.id.grid_layout_main)
        if(progressBar!!.isVisible){
            progressBar.visibility = View.GONE
            content?.visibility=View.VISIBLE
        }else{
            progressBar.visibility = View.VISIBLE
        }
    }
}