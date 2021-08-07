package com.example.weatherapp.ui.search

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.R

class SearchDialog(private val onSearchChange: (String) -> Unit, val onSearchClickShowTextView: () -> Unit) : DialogFragment() {

    @SuppressLint("InflateParams", "ShowToast")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.fragment_dialog_search_for_location, null))
                .setPositiveButton(R.string.ok)
                { _, _ ->
                    val searchLocation = dialog!!.findViewById<View>(R.id.name) as EditText
                    val searchForCity = searchLocation.text.toString()
                    onSearchClickShowTextView()
                    onSearchChange(searchForCity)
                }
                .setNegativeButton(R.string.cancel)
                { _, _ ->
                    dialog?.cancel()
                }
                .setTitle("Search for city")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}