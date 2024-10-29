package com.example.latihanfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [hal1.newInstance] factory method to
 * create an instance of this fragment.
 */
class hal1 : Fragment() {
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
        return inflater.inflate(R.layout.fragment_hal1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val limit = arguments?.getString("LIMIT")?.toIntOrNull()

        val allNum = mutableListOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,
            R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten)

        val defaultNum = if (limit != null) {
            val startIndex = limit - 1
            (allNum.subList(startIndex, allNum.size) + allNum).take(5).toMutableList()
        } else {
            allNum.subList(0, 5).toMutableList()
        }
        defaultNum.addAll(defaultNum)
        defaultNum.shuffle()

        val _numOne = view.findViewById<ImageView>(R.id.numOne)
        val _numTwo = view.findViewById<ImageView>(R.id.numTwo)
        val _numThree = view.findViewById<ImageView>(R.id.numThree)
        val _numFour = view.findViewById<ImageView>(R.id.numFour)
        val _numFive = view.findViewById<ImageView>(R.id.numFive)
        val _numSix = view.findViewById<ImageView>(R.id.numSix)
        val _numSeven = view.findViewById<ImageView>(R.id.numSeven)
        val _numEight = view.findViewById<ImageView>(R.id.numEight)
        val _numNine = view.findViewById<ImageView>(R.id.numNine)
        val _numTen = view.findViewById<ImageView>(R.id.numTen)

        val cards = listOf(_numOne, _numTwo, _numThree, _numFour, _numFive, _numSix, _numSeven, _numEight, _numNine, _numTen)

        var firstCard: Int? = null
        var secondCard: Int? = null

        var score = 50

        val _giveUp = view.findViewById<Button>(R.id.giveUp)

        cards.forEachIndexed{index, card ->
            card.setOnClickListener {
                if (score == 0) {
                    val mBundle = Bundle()
                    mBundle.putString("DATA", score.toString())

                    val mfDua = hal2()
                    mfDua.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.main, mfDua, hal2::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                    return@setOnClickListener
                }

                _giveUp.setOnClickListener {
                    val mBundle = Bundle()
                    mBundle.putString("DATA", score.toString())

                    val mfDua = hal2()
                    mfDua.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.main, mfDua, hal2::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                    return@setOnClickListener
                }

                if (card.tag == "flipped")return@setOnClickListener

                card.setImageResource(defaultNum[index])
                card.tag = "flipped"

                if (firstCard == null) {
                    firstCard = index
                } else if (secondCard == null) {
                    secondCard = index
                    if (defaultNum[firstCard!!] == defaultNum[secondCard!!]) {
                        firstCard = null
                        secondCard = null
                        score += 10
                    } else {
                        card.postDelayed({
                            cards[firstCard!!].setImageResource(R.drawable.background)
                            cards[secondCard!!].setImageResource(R.drawable.background)
                            cards[firstCard!!].tag = null
                            cards[secondCard!!].tag = null
                            firstCard = null
                            secondCard = null
                            score -= 5
                        }, 300)
                    }

                }

                if (cards.all { it.tag == "flipped" }) {
                    val mBundle = Bundle()
                    mBundle.putString("DATA", score.toString())

                    val mfDua = hal2()
                    mfDua.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.main, mfDua, hal2::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                    return@setOnClickListener
                }
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

        val _halTiga = view.findViewById<Button>(R.id.halTiga)
        _halTiga.setOnClickListener {
            val mfTiga = hal3()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.main, mfTiga, hal3::class.java.simpleName)
                commit()
            }
        }

        val _giveUpp = view.findViewById<Button>(R.id.giveUp)
        _giveUpp.setOnClickListener {
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
         * @return A new instance of fragment hal1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            hal1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}