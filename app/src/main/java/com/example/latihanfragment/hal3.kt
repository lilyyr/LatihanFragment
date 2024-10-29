package com.example.latihanfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [hal3.newInstance] factory method to
 * create an instance of this fragment.
 */
class hal3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hal3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _batasAwal = view.findViewById<EditText>(R.id.batasAwal)
        val _error = view.findViewById<TextView>(R.id.error)
        val _submit = view.findViewById<Button>(R.id.submit)

        _submit.setOnClickListener {
            val awalValue = _batasAwal.text.toString().toIntOrNull()
            if (awalValue != null && awalValue >= 1 && awalValue <= 10) {
                val mBundle = Bundle()
                mBundle.putString("LIMIT", awalValue.toString())

                val mfSatu = hal1()
                mfSatu.arguments = mBundle

                val mFragmentManager = parentFragmentManager
                mFragmentManager.beginTransaction().apply {
                    replace(R.id.main, mfSatu, hal1::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            } else {
                _error.text = "Tolong isi angka di antara 1-10"
            }
        }



        val _halSatu = view.findViewById<Button>(R.id.halSatu)
        _halSatu.setOnClickListener {
            val mfSatu = hal1()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.main, mfSatu, hal1::class.java.simpleName)
                commit()
            }
        }

        val _halDua = view.findViewById<Button>(R.id.halDua)
        _halDua.setOnClickListener {
            val mfDua = hal2()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.main, mfDua, hal2::class.java.simpleName)
                commit()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment hal3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            hal3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}