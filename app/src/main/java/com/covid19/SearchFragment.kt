package com.covid19

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.covid19.data.repository.APIRepository
import com.covid19.data.repository.CountryStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.searchCountry()
    }
    private fun searchCountry(){
        val searchView = view?.findViewById<SearchView>(R.id.search_view)
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                handleShowOrHide()
                getData(query.toString())
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("ACTION","Text :  ${newText}")
                return true
            }
        })
    }


    private fun getData(query:String){

        Log.e("ACTION","Ya presiono enter entro al get data:  ${query.toString()}")
        val apiService = APIRepository()
        GlobalScope.launch (Dispatchers.Main){
            val response = apiService.getCountryStatus(query)
            if(response.isSuccessful){
                val countryStatus = response.body() as List<CountryStatus>
                Log.e("TAG","Country status ${countryStatus.size}");
                Log.e("TAG","Country status ${countryStatus[countryStatus.size-1]}");
                printCurrentDayInformation(countryStatus[countryStatus.size-1])
            }else{
                Toast.makeText(context,"We can't found a country with the name ${query.toString()}",Toast.LENGTH_LONG).show()
                handleShowOrHide()
            }
        }

    }

    private fun printCurrentDayInformation(country:CountryStatus){
        val infected = view?.findViewById<TextView>(R.id.text_view_infected)
        val deaths = view?.findViewById<TextView>(R.id.text_view_deaths)
        val recovered = view?.findViewById<TextView>(R.id.text_view_recovered)
        val name = view?.findViewById<TextView>(R.id.text_view_country)
        val about = view?.findViewById<TextView>(R.id.text_view_search_about)
        about?.text = "Statistics date of ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())} "
        infected?.text=country.Confirmed.toString()
        deaths?.text=country.Deaths.toString()
        recovered?.text=country.Recovered.toString()
        name?.text=country.Country
       // this.handleShowLinearLayout()
        this.handleShowOrHide()
    }

    private fun handleShowLinearLayout(){
    val content = view?.findViewById<CardView>(R.id.card_layout_status)
        if(content!!.isVisible){
            content?.visibility = View.GONE
        }else{
            content?.visibility = View.VISIBLE
        }
    }

    private fun handleShowOrHide(){
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        val content = view?.findViewById<CardView>(R.id.card_layout_status)
        Log.e("TAG","Entro al handlew ${progressBar!!.isVisible}");
        if(progressBar!!.isVisible){
            progressBar.visibility = View.GONE
            content?.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.VISIBLE
            content?.visibility = View.GONE

        }
    }


}