package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var lastnum = false
    var lastdeci = false


    fun onDigit (view: View){
      tvinput.append((view as Button).text)
      lastnum=true
    }

    fun OnClear (view:View){
        tvinput.text=""
         lastnum = false
         lastdeci = false
    }
    fun decimalpoint (view:View){
        if (lastnum && !lastdeci)
            tvinput.append(".")
        lastnum=false
        lastdeci=true

    }

    private fun removezero (result: String): String{
        var value= result
        value=result.substring(0,result.length-2)
        return value

    }

    fun onOperator(view:View){
        if (lastnum && !isonOperatoradded(tvinput.text.toString())){
            tvinput.append((view as Button).text)
            lastnum=false
            lastdeci=false
        }
    }

    fun onEqual(view: View){
        if (lastnum){
            var tvvalue =tvinput.text.toString()
            var prefix =""
            try {
                if (tvvalue.startsWith("-")){
                    prefix="-"
                    tvvalue= tvvalue.substring(1)

                }
                // substraction code
                if (tvvalue.contains("-")){
                    val splitvalue =tvvalue.split("-")
                    var one= splitvalue[0]
                    var two= splitvalue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvinput.text = removezero ((one.toDouble() - two.toDouble()).toString())
                }
                //code for addition
                else if(tvvalue.contains("+")){
                    val splitvalue =tvvalue.split("+")
                    var one= splitvalue[0]
                    var two= splitvalue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvinput.text = removezero ((one.toDouble() + two.toDouble()).toString())
                }
                        // mutiplication code
                else if (tvvalue.contains("*")){
                    val splitvalue =tvvalue.split("*")
                    var one= splitvalue[0]
                    var two= splitvalue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvinput.text = removezero ((one.toDouble() * two.toDouble()).toString())
                }
                // division code
                 else if (tvvalue.contains("/")){
                    val splitvalue =tvvalue.split("/")
                    var one= splitvalue[0]
                    var two= splitvalue[1]

                    if (!prefix.isEmpty()){
                        one= prefix+one
                    }

                    tvinput.text = removezero((one.toDouble() / two.toDouble()).toString())
                }



            }catch (e:ArithmeticException){
                e.printStackTrace()
            }

        }
    }



  private  fun isonOperatoradded(value:String):Boolean{
      return if (value.startsWith("-")){
          false
      }else{
          value.contains("/") ||value.contains("*") ||value.contains("+")
                  ||value.contains("-")
      }

    }
}